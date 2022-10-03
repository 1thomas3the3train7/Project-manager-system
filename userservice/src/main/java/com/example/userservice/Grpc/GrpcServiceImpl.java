package com.example.userservice.Grpc;

import com.example.userservice.Service.UserService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class GrpcServiceImpl extends UserServiceGrpc.UserServiceImplBase{
    private final UserService userService;

    public GrpcServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void getUserByEmail(User.UserGetRequest request, StreamObserver<User.UserGetResponse> responseObserver) {
        final User.UserGetResponse response = User.UserGetResponse.newBuilder().setResponse(
                userService.getUserByEmail(request.getEmail())).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
