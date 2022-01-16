package in.geekster.springdatajpademo.repositories;

import in.geekster.springdatajpademo.entities.RoleEntity;
import in.geekster.springdatajpademo.enums.Department;
import in.geekster.springdatajpademo.enums.Designation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    /**
     * The method below is an example of Derived Query. Spring has certain rules to when it comes to
     * writing your method names. This will save you from having to write Queries and Spring will
     * figure them out from your method name
     *
     * For more on the rules: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
     */
    List<RoleEntity> findByDepartment(final Department department);
    Optional<RoleEntity> findByDepartmentAndDesignation(final Department department, final Designation designation);

    /**
     * This method takes in a JPQL query and has placeholders in inject values into the query from
     * method params
     *
     * For more info: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.at-query
     */
    @Query(value = "SELECT r FROM roleABC r WHERE r.department = ?1 AND r.designation = ?2")
    Optional<RoleEntity> customMethod(final Department department, final Designation designation);

    /**
     * This method takes in a JPQL query and has placeholders in inject values into the query from
     * method params
     * @Param -- annotation explicitly links the method argument to a particular placeholder
     *
     * For more info: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.at-query
     */
    @Query(value = "SELECT r FROM roleABC r WHERE r.department = :department AND r.designation = :designation")
    Optional<RoleEntity> customMethod2(@Param("department") final Department department, @Param("designation") final Designation designation);

    /**
     * The method below takes an actual SQL query
     */
    @Query(value = "SELECT * FROM role r WHERE r.department = 'ENGINEERING'", nativeQuery = true)
    Optional<RoleEntity> customMethod3();
}
