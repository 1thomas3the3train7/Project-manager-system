package com.example.projectservice.Repository;

import com.example.projectservice.Model.Plan.BasePlan;
import com.example.projectservice.Model.Project.BaseProject;

public interface PlanRepository {
    void save(BasePlan basePlan);
    void delete(BasePlan basePlan);
    void appendProjectAndPlan(BaseProject baseProject,BasePlan basePlan);
    void appendProjectAndPlan(int project_id,int plan_id);
}
