package com.example.projectservice.Repository;

import com.example.projectservice.Model.Project.BaseProject;
import com.example.projectservice.Model.Project.DetailedProject;

public interface ProjectRepository {
    void save(BaseProject baseProject);
    void saveWT(BaseProject baseProject);
    void delete(BaseProject baseProject);
    DetailedProject getDetailedProjectByName(final String name);
}
