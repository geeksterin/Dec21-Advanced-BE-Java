package in.geekster.springdatajpademo.services.impls;

import in.geekster.springdatajpademo.dtos.EmployeeDTO;
import in.geekster.springdatajpademo.dtos.EmployeeMetaDTO;
import in.geekster.springdatajpademo.entities.*;
import in.geekster.springdatajpademo.exceptions.NoRoleFoundException;
import in.geekster.springdatajpademo.exceptions.ProjectNotFoundException;
import in.geekster.springdatajpademo.models.responses.EmployeeResponse;
import in.geekster.springdatajpademo.repositories.*;
import in.geekster.springdatajpademo.services.EmployeeService;
import in.geekster.springdatajpademo.utils.DataMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.RoleNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Scope(value = "prototype")
@Slf4j
@Transactional(readOnly = true)
public class DefaultEmployeeService implements EmployeeService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMetaRepository employeeMetaRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectContributorRepository projectContributorRepository;

    @Override
    @Transactional
    public EmployeeDTO create(final EmployeeDTO employeeDTO) throws NoRoleFoundException {
        log.debug("Creating Employee: {}", employeeDTO);

        // 1st
        final Long roleId = employeeDTO.getRole().getId();
        final RoleEntity roleEntity = roleRepository.findById(roleId).orElseThrow(() -> new NoRoleFoundException("No Role found by ID: " + roleId));
//        log.debug("Retrieved Role Entity: {}", roleEntity);
        EmployeeEntity employeeEntity = DataMapperUtil.convertTo(employeeDTO, EmployeeEntity.class);
        employeeEntity.setRole(roleEntity);

        // 2nd
        employeeEntity = employeeRepository.save(employeeEntity);
//        log.debug("Saved Employee Entity: {}", employeeEntity);

        final EmployeeDTO createdEmployeeDTO = DataMapperUtil.convertTo(employeeEntity, EmployeeDTO.class);

        if (employeeDTO.getEmployeeMeta() != null) {
            EmployeeMetaEntity employeeMetaEntity = DataMapperUtil.convertTo(employeeDTO.getEmployeeMeta(), EmployeeMetaEntity.class);
            employeeMetaEntity.setEmployee(employeeEntity);

            // 3rd
            employeeMetaEntity = employeeMetaRepository.save(employeeMetaEntity);
//        log.debug("Saved Employee Meta Entity: {}", employeeMetaEntity);

            final EmployeeMetaDTO employeeMetaDTO = DataMapperUtil.convertTo(employeeMetaEntity, EmployeeMetaDTO.class);
            createdEmployeeDTO.setEmployeeMeta(employeeMetaDTO);
        }
        return createdEmployeeDTO;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public List<EmployeeDTO> getAllByProjectIdAndRoleId(final Long projectId, final Long roleId) throws ProjectNotFoundException, RoleNotFoundException {
        log.debug("Getting all Employees by Project ID: {} and Role ID: {}", projectId, roleId);
//        final RoleEntity roleEntity = roleRepository.findById(roleId)
//                .orElseThrow(() -> new RoleNotFoundException("No Role found by ID: " + roleId));
//        final ProjectEntity projectEntity = projectRepository.findById(projectId)
//                .orElseThrow(() -> new ProjectNotFoundException("No Project found by ID: " + projectId));
//        final List<EmployeeDTO> employeeDTOS = new ArrayList<>();
//        final List<ProjectContributorEntity> projectContributorEntities =
//                projectContributorRepository.fetchAllByProjectId(projectEntity.getId());
//        projectContributorEntities.forEach((entity) -> {
//            final RoleEntity role = entity.getEmployee().getRole();
//            if (Objects.equals(role.getId(), roleEntity.getId())) {
//                final EmployeeDTO employeeDTO = DataMapperUtil.convertTo(entity.getEmployee(), EmployeeDTO.class);
//                employeeDTOS.add(employeeDTO);
//            }
//        });
//        return employeeDTOS;

        final List<ProjectContributorEntity> projectContributorEntities = projectContributorRepository.fetchAllByProjectIdAndRoleId(projectId, roleId);
        final List<EmployeeDTO> employeeDTOList = new ArrayList<>();

        projectContributorEntities.forEach((pce) -> {
            final EmployeeEntity e = pce.getEmployee();
            final EmployeeDTO employeeDTO = DataMapperUtil.convertTo(e, EmployeeDTO.class);
            employeeDTOList.add(employeeDTO);
        });
        return employeeDTOList;
    }
}
