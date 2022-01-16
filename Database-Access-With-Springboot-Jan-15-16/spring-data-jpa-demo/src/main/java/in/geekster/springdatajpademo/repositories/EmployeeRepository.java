package in.geekster.springdatajpademo.repositories;

import in.geekster.springdatajpademo.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Repositories are classes that provide access to Database. These classes come with methods that allow
 * you to perform CRUD operations on the DB
 *
 * This is also a part of Data Access Layer and usually consumes an Entity to figure out which table
 * to talk to
 *
 * @Repository -- annotation allows Spring to identify as a Repository and implement the methods
 * @NoRepositoryBean -- this annotation specifies that Spring should not create a Repository out of it
 *
 *
 * Derived Query: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference
 */

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>, JpaSpecificationExecutor<EmployeeEntity> {
}
