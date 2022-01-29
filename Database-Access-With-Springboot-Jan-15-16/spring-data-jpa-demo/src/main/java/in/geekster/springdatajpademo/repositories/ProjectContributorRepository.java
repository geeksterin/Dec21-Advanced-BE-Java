package in.geekster.springdatajpademo.repositories;

import in.geekster.springdatajpademo.entities.ProjectContributorEntity;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;

@Repository
public interface ProjectContributorRepository extends IDedRepository<ProjectContributorEntity> {

    @Query(value = "SELECT pc FROM projectContributor AS pc WHERE pc.project.id = ?1")
    List<ProjectContributorEntity> fetchAllByProjectId(final Long projectId);


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "SELECT pc FROM projectContributor AS pc WHERE pc.project.id = :projectId AND pc.employee.id IN (SELECT em.id FROM employee AS em WHERE em.role.id = :roleId)")
    List<ProjectContributorEntity> fetchAllByProjectIdAndRoleId(@Param("projectId") final Long projectId, @Param("roleId") final Long roleId);

}
