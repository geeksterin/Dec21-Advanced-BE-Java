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

}
