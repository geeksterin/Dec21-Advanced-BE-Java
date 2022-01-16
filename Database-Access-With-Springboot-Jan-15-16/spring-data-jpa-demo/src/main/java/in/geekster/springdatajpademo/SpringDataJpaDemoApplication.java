package in.geekster.springdatajpademo;

import in.geekster.springdatajpademo.enums.Department;
import in.geekster.springdatajpademo.enums.Designation;
import in.geekster.springdatajpademo.repositories.EmployeeRepository;
import in.geekster.springdatajpademo.services.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;


@SpringBootApplication
@Slf4j
public class SpringDataJpaDemoApplication {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private RoleService roleService;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaDemoApplication.class, args);
	}


	/**
	 * @PostConstruct is used to enter into the phase of Spring Bean's lifecycle where the Bean has just been created
	 * but not ready to use yet. This is the preferred place for doing one-time things, like establishing DB connections
	 * or making API calls to download things required for the entire lifecycle of the Bean
	 *
	 * For more info: https://medium.com/swlh/the-lifecycle-of-spring-beans-b0edb8936189
	 */
	@PostConstruct
	private void init() {
//		RoleDTO roleDTO1 = new RoleDTO();
//		roleDTO1.setDesignation(Designation.SSE);
//		roleDTO1.setDepartment(Department.ENGINEERING);
//		roleDTO1 = roleService.createRole(roleDTO1);
//		log.info("Role DTO 1: {}", roleDTO1);
//
//		RoleDTO roleDTO2 = new RoleDTO();
//		roleDTO2.setDesignation(Designation.SE);
//		roleDTO2.setDepartment(Department.ENGINEERING);
//		roleDTO2 = roleService.createRole(roleDTO2);
//		log.info("Role DTO 2: {}", roleDTO2);
//
//		RoleDTO roleDTO3 = new RoleDTO();
//		roleDTO3.setDesignation(Designation.ASSOCIATE_MARKETER);
//		roleDTO3.setDepartment(Department.MARKETING);
//		roleDTO3 = roleService.createRole(roleDTO3);
//		log.info("Role DTO 3: {}", roleDTO3);
//
//		RoleDTO roleDTO4 = new RoleDTO();
//		roleDTO4.setDesignation(Designation.MARKETING_MANAGER);
//		roleDTO4.setDepartment(Department.MARKETING);
//		roleDTO4 = roleService.createRole(roleDTO4);
//		log.info("Role DTO 4: {}", roleDTO4);
//
//		RoleDTO roleDTO5 = new RoleDTO();
//		roleDTO5.setDesignation(Designation.SENIOR_BUSINESS_DEVELOPER);
//		roleDTO5.setDepartment(Department.SALES);
//		roleDTO5 = roleService.createRole(roleDTO5);
//		log.info("Role DTO 5: {}", roleDTO5);

		final List<Designation> designationsEngineering = roleService.getAllDesignationsByDepartment(Department.ENGINEERING);
		log.info("Engineering Designations: {}", designationsEngineering);

//		final List<Designation> designationsMarketer = roleService.getAllDesignationsByDepartment(Department.MARKETING);
//		log.info("Marketing Designations: {}", designationsMarketer);
//
//		final List<Designation> designationsSales = roleService.getAllDesignationsByDepartment(Department.SALES);
//		log.info("Sales Designations: {}", designationsSales);
//
//		final List<Designation> designationsHr = roleService.getAllDesignationsByDepartment(Department.HR);
//		log.info("HR Designations: {}", designationsHr);
	}

	// Engineering - ARCH
	// Engineering - QA

}
