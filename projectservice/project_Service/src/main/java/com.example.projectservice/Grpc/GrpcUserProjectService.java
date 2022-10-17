package com.example.projectservice.Grpc;

import Grpc.UserProject;
import Grpc.UserProjectServiceGrpc;
import com.example.projectservice.Service.UserService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class GrpcUserProjectService extends UserProjectServiceGrpc.UserProjectServiceImplBase {
    private final UserService userService;

    public GrpcUserProjectService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void saveUserProject(UserProject.UserProjectSaveRequest request,
                                StreamObserver<UserProject.UserProjectSaveResponse> responseObserver) {
        final UserProject.UserProjectSaveResponse response = UserProject.UserProjectSaveResponse.newBuilder()
                .setResponse(userService.saveNewUserAndValid(request.getRequest()))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
