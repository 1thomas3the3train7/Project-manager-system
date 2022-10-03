package com.example.projectservice.Grpc;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class TestService extends TestServiceGrpc.TestServiceImplBase{
    @Override
    public void getTest(Test.testRequest request, StreamObserver<Test.testResponse> responseObserver) {
        final Test.testResponse response = Test.testResponse.newBuilder().setResponse1("test1").build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
