package com.example.authservice.Grpc;

import com.example.authservice.DTO.UserDTO;
import com.google.common.util.concurrent.ListenableFuture;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;


@Service
public class GrpcClient {
    @net.devh.boot.grpc.client.inject.GrpcClient("user-service")
    private UserServiceGrpc.UserServiceFutureStub userServiceFutureStub;

    public String getUserByEmail(final String email){
        try {
            final ListenableFuture<User.UserGetResponse> response = userServiceFutureStub.getUserByEmail(
                    User.UserGetRequest.newBuilder()
                            .setEmail(email)
                            .build());

            return response.get().getResponse();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "FAIL";
        } catch (ExecutionException e) {
            e.printStackTrace();
            return "FAIL";
        }
    }
    public String userSave(final UserDTO userDTO){
        try {
            final ListenableFuture<User.UserSaveResponse> response = userServiceFutureStub.saveUser(
                    User.UserSaveRequest.newBuilder()
                            .setEmail(userDTO.getEmail())
                            .setPassword(userDTO.getPassword())
                            .setName(userDTO.getFirstName())
                            .build());
            return response.get().getResponse();
        } catch (ExecutionException e) {
            e.printStackTrace();
            return "FAIL";
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "FAIL";
        }
    }

}
