package com.example.projectservice.Service;

import com.example.projectservice.DTO.ProjectDTO;
import com.example.projectservice.Model.Budget.DetailedBudget;
import com.example.projectservice.Repository.BudgetRepository;
import org.springframework.stereotype.Service;

@Service
public class BudgetService {
    private final BudgetRepository budgetRepository;

    public BudgetService(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }
    public DetailedBudget createBudget(final ProjectDTO projectDTO){
        final DetailedBudget detailedBudget = new DetailedBudget(projectDTO.getBudgetMoney());
        return detailedBudget;
    }
}
