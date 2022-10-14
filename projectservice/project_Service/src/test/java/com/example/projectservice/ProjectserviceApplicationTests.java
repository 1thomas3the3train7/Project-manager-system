package com.example.projectservice;

import com.example.projectservice.Model.Budget.DetailedBudget;
import com.example.projectservice.Model.Passport.DetailedPassport;
import com.example.projectservice.Model.Plan.DetailedPlan;
import com.example.projectservice.Model.Project.DetailedProject;
import com.example.projectservice.Model.User.DetailedUsers;
import com.example.projectservice.Repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProjectserviceApplicationTests {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PassportRepository passportRepository;
	@Autowired
	private PlanRepository planRepository;
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private BudgetRepository budgetRepository;

	@Test
	void contextLoads() {
	}
	@Test
	void forProject(){
		/*for(int i = 0; i < 1000;i++){
			DetailedProject detailedProject = new DetailedProject();
			detailedProject.setName("name");
			DetailedPassport detailedPassport = new DetailedPassport();
			detailedPassport.setDescription("disc");
			DetailedPlan detailedPlan = new DetailedPlan();
			detailedPlan.setDescription("desc");
			DetailedBudget detailedBudget = new DetailedBudget();
			detailedBudget.setMoney(1000);
			DetailedUsers detailedUsers = new DetailedUsers();
			detailedUsers.setFirstName("firstname");
			projectRepository.save(detailedProject);
			budgetRepository.save(detailedBudget);
			planRepository.save(detailedPlan);
			passportRepository.save(detailedPassport);
			userRepository.save(detailedUsers);
			budgetRepository.appendProjectAndBudget(detailedProject.getId(),detailedBudget.getId());
			passportRepository.appendProjectAndPassport(detailedProject.getId(),detailedPassport.getId());
			userRepository.appendProjectAndUser(detailedProject.getId(), detailedUsers.getId());
			planRepository.appendProjectAndPlan(detailedProject.getId(),detailedPlan.getId());
			DetailedProject detailedProject1 = projectRepository.getDetailedProjectByName("name");
			System.out.println(detailedProject1.getBudget().getMoney());
		}*/

	}

}
