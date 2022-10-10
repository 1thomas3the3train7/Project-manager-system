package com.example.authservice.grpc;

import Grpc.User;
import Grpc.UserServiceGrpc;
import com.google.common.util.concurrent.ListenableFuture;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;


@Service
public class GrpcClient {
    @net.devh.boot.grpc.client.inject.GrpcClient("user-service")
    private UserServiceGrpc.UserServiceFutureStub userServiceFutureStub;

    public String getUserByEmail(final String email){
        try {
            ListenableFuture<User.UserGetResponse> response = userServiceFutureStub.getUserByEmail(
                    User.UserGetRequest.newBuilder().setEmail(email).build());

            return response.get().getResponse();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "FAIL";
        } catch (ExecutionException e) {
            e.printStackTrace();
            return "FAIL";
        }
    }
}
