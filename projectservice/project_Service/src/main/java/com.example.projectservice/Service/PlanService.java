package com.example.projectservice.Service;

import com.example.projectservice.DTO.ProjectDTO;
import com.example.projectservice.Model.Plan.DetailedPlan;
import com.example.projectservice.Repository.PlanRepository;
import org.springframework.stereotype.Service;

@Service
public class PlanService {
    private final PlanRepository planRepository;

    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public DetailedPlan createPlan(final ProjectDTO projectDTO){
        final DetailedPlan detailedPlan = new DetailedPlan(projectDTO.getPlanDescription());
        return detailedPlan;
    }
}
