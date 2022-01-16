package in.geekster.springdatajpademo.services;

import in.geekster.springdatajpademo.dtos.RoleDTO;
import in.geekster.springdatajpademo.enums.Department;
import in.geekster.springdatajpademo.enums.Designation;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    RoleDTO createRole(final RoleDTO roleDTO);

    Optional<RoleDTO> updateRoleById(final Long roleId, final RoleDTO roleDTO);

    Optional<RoleDTO> getRoleById(final Long roleId);

    List<Designation> getAllDesignationsByDepartment(final Department department);

}
