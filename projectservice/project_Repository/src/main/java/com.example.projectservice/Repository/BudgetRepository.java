package com.example.projectservice.Repository;

import com.example.projectservice.Model.Budget.BaseBudget;
import com.example.projectservice.Model.Project.BaseProject;

public interface BudgetRepository {
    void save(BaseBudget budget);
    void delete(BaseBudget budget);
    void appendProjectAndBudget(BaseProject baseProject,BaseBudget budget);
    void appendProjectAndBudget(int project_id,int budget_id);
}
