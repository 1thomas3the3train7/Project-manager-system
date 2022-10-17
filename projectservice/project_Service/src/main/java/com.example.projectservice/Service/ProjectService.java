package com.example.projectservice.Service;

import com.example.projectservice.DTO.ProjectDTO;
import com.example.projectservice.Model.Budget.DetailedBudget;
import com.example.projectservice.Model.Passport.DetailedPassport;
import com.example.projectservice.Model.Plan.DetailedPlan;
import com.example.projectservice.Model.Project.DetailedProject;
import com.example.projectservice.Repository.BudgetRepository;
import com.example.projectservice.Repository.PassportRepository;
import com.example.projectservice.Repository.PlanRepository;
import com.example.projectservice.Repository.ProjectRepository;
import com.example.projectservice.Utils.UtilsDto;
import com.example.projectservice.Validate.DateParse;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.transaction.Transactional;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final PassportService passportService;
    private final PlanService planService;
    private final BudgetService budgetService;
    private final Gson gson = new Gson();
    private final UtilsDto utilsDto;
    private final DateParse date;
    private final BudgetRepository budgetRepository;
    private final PlanRepository planRepository;
    private final PassportRepository passportRepository;

    public ProjectService(ProjectRepository projectRepository, PassportService passportService,
                          PlanService planService, BudgetService budgetService, UtilsDto utilsDto,
                          DateParse date, BudgetRepository budgetRepository, PlanRepository planRepository,
                          PassportRepository passportRepository) {
        this.projectRepository = projectRepository;
        this.passportService = passportService;
        this.planService = planService;
        this.budgetService = budgetService;
        this.utilsDto = utilsDto;
        this.date = date;
        this.budgetRepository = budgetRepository;
        this.planRepository = planRepository;
        this.passportRepository = passportRepository;
    }

    public String createProjectAndValid(final String request){
        if(request == null){
            return "not valid request";
        }
        final ProjectDTO projectDTO = gson.fromJson(request, ProjectDTO.class);
        if(projectDTO.getDateStart() == null){
            return "not valid request";
        }

        return createProject(projectDTO);
    }
    @Transactional(rollbackOn = Exception.class)
    public String createProject(final ProjectDTO projectDTO){
        try {
            final DetailedPassport detailedPassport = passportService.createPassport(projectDTO);
            final DetailedPlan detailedPlan = planService.createPlan(projectDTO);
            final DetailedBudget detailedBudget = budgetService.createBudget(projectDTO);
            final DetailedProject detailedProject =
                    new DetailedProject(projectDTO.getName(),projectDTO.getCustomer());
            detailedProject.setPassport(detailedPassport);
            detailedProject.setBudget(detailedBudget);
            detailedProject.setPlan(detailedPlan);
            projectRepository.saveWT(detailedProject);
            return "saved";
        }catch (Exception exc){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "exception in createProject";
        }
    }
}
