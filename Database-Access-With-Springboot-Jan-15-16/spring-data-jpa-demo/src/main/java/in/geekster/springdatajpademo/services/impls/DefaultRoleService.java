package in.geekster.springdatajpademo.services.impls;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.geekster.springdatajpademo.dtos.RoleDTO;
import in.geekster.springdatajpademo.entities.RoleEntity;
import in.geekster.springdatajpademo.enums.Department;
import in.geekster.springdatajpademo.enums.Designation;
import in.geekster.springdatajpademo.repositories.RoleRepository;
import in.geekster.springdatajpademo.services.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Service annotation to turn this class into a Spring-managed Bean
 * @Transactional -- creates a proxy around this class. Transaction annotation is responsible for many things,
 * some of which are getting a DB connection from the connection pool, closing the connection,
 * reverting changes (rollback) in case of exceptions
 */

@Service
@Slf4j
@Transactional
public class DefaultRoleService implements RoleService {

    /**
     * @Autowired will find the implementation of RoleRepository and inject it here
     * This is how Spring achieves Dependency Injection
     */
    @Autowired
    private RoleRepository roleRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();


    /**
     * @PostConstruct is used to enter into the phase of Spring Bean's lifecycle where the Bean has just been created
     * but not ready to use yet. This is the preferred place for doing one-time things, like establishing DB connections
     * or making API calls to download things required for the entire lifecycle of the Bean
     *
     * For more info: https://medium.com/swlh/the-lifecycle-of-spring-beans-b0edb8936189
     */
    @PostConstruct
    private void init() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public RoleDTO createRole(final RoleDTO roleDTO) {
        log.info("Creating Role: {}", roleDTO);
        RoleEntity roleEntity = objectMapper.convertValue(roleDTO, RoleEntity.class);
        roleEntity = roleRepository.save(roleEntity);
        log.info("Saved Entity: {}", roleEntity);
        return objectMapper.convertValue(roleEntity, RoleDTO.class);
    }


    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Optional<RoleDTO> updateRoleById(final Long roleId, final RoleDTO roleDTO) {

            final Optional<RoleEntity> roleEntityOptional = roleRepository.findById(roleId);
            if (roleEntityOptional.isPresent()) {
                final RoleEntity roleEntity = roleEntityOptional.get();
                if (roleDTO.getDepartment() != null) {
                    roleEntity.setDepartment(roleDTO.getDepartment());
                }
                if (roleEntity.getDesignation() != null) {
                    roleEntity.setDesignation(roleDTO.getDesignation());
                }
                return Optional.of(objectMapper.convertValue(roleEntity, RoleDTO.class));
            }

        return Optional.empty();

    }

    @Override
    public Optional<RoleDTO> getRoleById(final Long roleId) {
        final Optional<RoleEntity> roleEntityOptional = roleRepository.findById(roleId);
        if (roleEntityOptional.isPresent()) {
            return Optional.of(objectMapper.convertValue(roleEntityOptional.get(), RoleDTO.class));
        }
        return Optional.empty();
    }

    @Override
    public List<Designation> getAllDesignationsByDepartment(final Department department) {
        log.info("Getting all Designations by Department: {}", department);

        /**
         * Below is one of the ways in which you can retrieve from Database
         */
        final RoleEntity sampleRoleEntity = new RoleEntity();
        sampleRoleEntity.setDepartment(department);
        final Example<RoleEntity> exampleEntity = Example.of(sampleRoleEntity);
        final List<RoleEntity> searchResult1 = roleRepository.findAll(exampleEntity);

        /**
         * The method below is another way of fetching rows using Derived Query Method
         */
        final List<RoleEntity> searchResult2 = roleRepository.findByDepartment(department);
        final List<Designation> designations = new ArrayList<>();
        searchResult1.forEach((roleEn) -> {
            final Designation designation = roleEn.getDesignation();
            designations.add(designation);
        });

        /**
         * This method below uses our custom method running JPQL query
         */
        final Optional<RoleEntity> roleEntities = roleRepository.customMethod(Department.ENGINEERING, Designation.ARCHITECT);
        roleEntities.ifPresent((roleEntity -> {
            log.info("Role ENT: {}", objectMapper.convertValue(roleEntity, RoleDTO.class));
        }));
        return designations;
    }
}
