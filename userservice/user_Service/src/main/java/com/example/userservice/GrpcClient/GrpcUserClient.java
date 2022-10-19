package com.example.userservice.GrpcClient;

import Grpc.UserProject;
import Grpc.UserProjectServiceGrpc;
import com.example.userservice.DTO.UserDTO;
import com.example.userservice.Service.UserService;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.gson.Gson;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class GrpcUserClient {
    @GrpcClient("project-service")
    private UserProjectServiceGrpc.UserProjectServiceFutureStub futureStub;
    private final Gson gson = new Gson();
    public String saveUserInProjectService(final String request){
        try {
            final ListenableFuture<UserProject.UserProjectSaveResponse> response = futureStub.saveUserProject(
                    UserProject.UserProjectSaveRequest.newBuilder()
                            .setRequest(request)
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
