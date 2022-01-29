package in.geekster.springdatajpademo.services;

import in.geekster.springdatajpademo.dtos.EmployeeDTO;
import in.geekster.springdatajpademo.exceptions.NoRoleFoundException;
import in.geekster.springdatajpademo.exceptions.ProjectNotFoundException;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

public interface EmployeeService {

    EmployeeDTO create(final EmployeeDTO employeeDTO) throws NoRoleFoundException;
    List<EmployeeDTO> getAllByProjectIdAndRoleId(final Long projectId, final Long roleId) throws ProjectNotFoundException, RoleNotFoundException;
}
