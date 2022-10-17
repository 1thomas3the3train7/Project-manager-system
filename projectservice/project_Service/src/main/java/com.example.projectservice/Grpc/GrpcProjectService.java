package com.example.projectservice.Grpc;

import Grpc.Project;
import Grpc.ProjectServiceGrpc;
import com.example.projectservice.Service.ProjectService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class GrpcProjectService extends ProjectServiceGrpc.ProjectServiceImplBase {
    private final ProjectService projectService;

    public GrpcProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public void createProject(Project.CreateProjectRequest request,
                              StreamObserver<Project.CreateProjectResponse> responseObserver) {
        final Project.CreateProjectResponse response = Project.CreateProjectResponse.newBuilder()
                .setResponse(projectService.createProjectAndValid(request.getRequest()))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
