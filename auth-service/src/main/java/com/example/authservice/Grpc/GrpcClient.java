package com.example.authservice.Grpc;

import Grpc.User;
import Grpc.UserServiceGrpc;
import com.example.authservice.DTO.UserDTO;
import com.example.authservice.DTO.UserSearchDTO;
import com.example.authservice.Exception.ServiceNotFoundException;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.gson.Gson;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;


@Service
public class GrpcClient extends UserServiceGrpc.UserServiceImplBase {
    @net.devh.boot.grpc.client.inject.GrpcClient("user-service")
    private UserServiceGrpc.UserServiceFutureStub userServiceFutureStub;
    private final Gson gson = new Gson();

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
    public String searchUser(final UserSearchDTO userSearchDTO){
        try {
            System.out.println(gson.toJson(userSearchDTO));
            final ListenableFuture<User.SearchUserResponse> response = userServiceFutureStub.searchUser(
                    User.SearchUserRequest.newBuilder()
                            .setName(gson.toJson(userSearchDTO))
                            .build());
            return response.get().getResponse();
        } catch (ExecutionException e){
            e.printStackTrace();
            return "FAIL";
        } catch (InterruptedException i){
            i.printStackTrace();
            return "FAIL";
        }
    }
    /*public String userSave(final UserDTO userDTO){
        return null;
    }
    public String getUserByEmail(final String email){
        return null;
    }*/

}
