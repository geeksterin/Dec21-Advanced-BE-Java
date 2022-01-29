package in.geekster.springdatajpademo.services.impls;

import com.fasterxml.jackson.databind.ObjectMapper;
import in.geekster.springdatajpademo.dtos.ProjectContributorDTO;
import in.geekster.springdatajpademo.dtos.ProjectDTO;
import in.geekster.springdatajpademo.entities.EmployeeEntity;
import in.geekster.springdatajpademo.entities.ProjectContributorEntity;
import in.geekster.springdatajpademo.entities.ProjectEntity;
import in.geekster.springdatajpademo.enums.ProjectStatus;
import in.geekster.springdatajpademo.exceptions.EmployeeNotFoundException;
import in.geekster.springdatajpademo.exceptions.ProjectNotFoundException;
import in.geekster.springdatajpademo.repositories.EmployeeRepository;
import in.geekster.springdatajpademo.repositories.ProjectContributorRepository;
import in.geekster.springdatajpademo.repositories.ProjectRepository;
import in.geekster.springdatajpademo.services.ProjectService;
import in.geekster.springdatajpademo.utils.DataMapperUtil;
import in.geekster.springdatajpademo.utils.SecurityContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class DefaultProjectService implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectContributorRepository projectContributorRepository;

    @Override
    @Transactional
    public ProjectDTO create(final ProjectDTO projectDTO) {
        log.debug("Creating new Project: {}", projectDTO);
        ProjectEntity projectEntity = DataMapperUtil.convertTo(projectDTO, ProjectEntity.class);
        projectEntity.setStatus(ProjectStatus.ACTIVE);
        projectEntity = projectRepository.save(projectEntity);
        log.debug("Created Project: {}", projectEntity);
        return DataMapperUtil.convertTo(projectEntity, ProjectDTO.class);
    }

    @Override
    public Optional<ProjectDTO> findById(Long id) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public ProjectDTO update(Long id, ProjectDTO projectDTO) throws ProjectNotFoundException {
        return null;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public ProjectContributorDTO addProjectContributor(final ProjectContributorDTO projectContributorDTO) throws ProjectNotFoundException, EmployeeNotFoundException {
        log.debug("Adding Project Contributor: {}", projectContributorDTO);
        final Long projectId = projectContributorDTO.getProjectId();
        final Long employeeId = projectContributorDTO.getEmployeeId();

        final ProjectEntity projectEntity = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("No Project found by ID: " + projectId));
        final EmployeeEntity employeeEntity = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("No Employee found by ID: " + employeeId));

        ProjectContributorEntity projectContributorEntity = new ProjectContributorEntity();
        projectContributorEntity.setProject(projectEntity);
        projectContributorEntity.setEmployee(employeeEntity);
        projectContributorEntity = projectContributorRepository.save(projectContributorEntity);
        final ProjectContributorDTO projectContributorDTO1 = new ProjectContributorDTO();
        projectContributorDTO1.setProjectId(projectContributorEntity.getProject().getId());
        projectContributorDTO1.setEmployeeId(projectContributorEntity.getEmployee().getId());
        return projectContributorDTO1;
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        log.debug("Getting All Projects");

        log.info("REQUESTING CLIENT: {}", SecurityContextUtil.getRequestingClient());
        final List<ProjectEntity> projectEntities = projectRepository.findAll();
        final List<ProjectDTO> projectDTOS = new ArrayList<>();
        projectEntities.forEach((pe) -> {
            final ProjectDTO projectDTO = DataMapperUtil.convertTo(pe, ProjectDTO.class);
            projectDTOS.add(projectDTO);
        });
        log.debug("All Project DTOs: {}", projectDTOS);
        return projectDTOS;
    }
}
