package com.example.projectservice;

import com.example.projectservice.DTO.ProjectDTO;
import com.example.projectservice.DTO.UserDTO;
import com.example.projectservice.Model.Passport.DetailedPassport;
import com.example.projectservice.Model.Project.DetailedProject;
import com.example.projectservice.Model.User.ShortUser;
import com.example.projectservice.Repository.*;
import com.example.projectservice.Service.ProjectService;
import com.example.projectservice.Service.UserService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@SpringBootTest
class ProjectserviceApplicationTests {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PassportRepository passportRepository;
	@Autowired
	private PlanRepository planRepository;
	private final Gson gson = new Gson();
	@Autowired
	private UserService userService;
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private BudgetRepository budgetRepository;
	@PersistenceContext
	private EntityManager em;
	@Autowired
	private ProjectService projectService;

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
	@Test
	@Transactional
	void forproject1(){
		DetailedPassport detailedPassport = new DetailedPassport();
		DetailedProject detailedProject = new DetailedProject();
		detailedProject.setName("name");
		detailedProject.setPassport(detailedPassport);
		projectRepository.saveWT(detailedProject);
		DetailedProject detailedProject1 = projectRepository.getDetailedProjectByName("name");
		System.out.println(detailedProject1.getPassport().getId());
	}
	@Test
	void forproject2(){
		ProjectDTO projectDTO = new ProjectDTO();
		projectDTO.setBudgetMoney(1000);
		projectDTO.setName("name");
		projectDTO.setCustomer("customer");
		projectDTO.setPlanDescription("planDescription");
		projectDTO.setDateStart("10.10.2022");
		projectDTO.setDateEnd("31.12.2022");
		projectService.createProject(projectDTO);
	}
	@Test
	void forUser1(){
		UserDTO userDTO = new UserDTO();
		userDTO.setUser_id(2);
		userDTO.setEmail("email");
		System.out.println(userService.saveNewUserAndValid(gson.toJson(userDTO)));
		ShortUser shortUser = userRepository.getShortUserByEmail("email");
		System.out.println(shortUser.getEmail());
		System.out.println(shortUser.getUserService_id());
	}

}
