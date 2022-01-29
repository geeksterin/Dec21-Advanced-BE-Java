package in.geekster.springdatajpademo.repositories;

import in.geekster.springdatajpademo.entities.ProjectEntity;
import in.geekster.springdatajpademo.enums.ProjectStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends IDedRepository<ProjectEntity> {

    Optional<ProjectEntity> findByName(final String name);
    List<ProjectEntity> findByStatus(final ProjectStatus status);
}
