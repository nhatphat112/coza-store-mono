package com.apigateway.client;

import com.apigateway.payload.request.GetUserByUsernameOrEmailRequest;
import com.apigateway.payload.response.BaseResponse;
import com.apigateway.payload.response.UserResponse;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserServiceClient {
    @Autowired
    private WebClient.Builder webClientBuilder;
    private final String uri = "http://user-service";
    private Gson gson = new Gson();
    public Mono<UserResponse> getUserByUsernameOrEmail(String username, String email) {
        GetUserByUsernameOrEmailRequest request = GetUserByUsernameOrEmailRequest.builder()
                .username(username)
                .email(email)
                .build();

        return webClientBuilder.build()
                .post()
                .uri(uri + "/api/user/get-user-by-username-or-email")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(gson.toJson(request))
                .retrieve()
                .bodyToMono(BaseResponse.class)
                .flatMap(baseResponse -> {
                    if (baseResponse != null && baseResponse.getData() != null) {
                        try {
                            UserResponse response = gson.fromJson(gson.toJson(baseResponse.getData()), UserResponse.class);
                            return Mono.just(response);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    return Mono.empty();
                });
    }

}
