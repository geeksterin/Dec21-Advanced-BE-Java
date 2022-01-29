package in.geekster.springdatajpademo.services;

import in.geekster.springdatajpademo.dtos.ProjectContributorDTO;
import in.geekster.springdatajpademo.dtos.ProjectDTO;
import in.geekster.springdatajpademo.exceptions.EmployeeNotFoundException;
import in.geekster.springdatajpademo.exceptions.ProjectNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    ProjectDTO create(final ProjectDTO projectDTO);
    Optional<ProjectDTO> findById(final Long id);
    ProjectDTO update(final Long id, final ProjectDTO projectDTO) throws ProjectNotFoundException;
    ProjectContributorDTO addProjectContributor(final ProjectContributorDTO projectContributorDTO) throws ProjectNotFoundException, EmployeeNotFoundException;
    List<ProjectDTO> getAllProjects();
}
