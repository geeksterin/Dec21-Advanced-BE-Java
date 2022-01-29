package in.geekster.springdatajpademo.controllers;

import in.geekster.springdatajpademo.constants.ErrorCode;
import in.geekster.springdatajpademo.dtos.EmployeeDTO;
import in.geekster.springdatajpademo.dtos.RoleDTO;
import in.geekster.springdatajpademo.exceptions.NoRoleFoundException;
import in.geekster.springdatajpademo.exceptions.ProjectNotFoundException;
import in.geekster.springdatajpademo.models.requests.EmployeeCreationRequest;
import in.geekster.springdatajpademo.models.responses.ApiResponse;
import in.geekster.springdatajpademo.models.responses.EmployeeResponse;
import in.geekster.springdatajpademo.models.responses.Error;
import in.geekster.springdatajpademo.services.EmployeeService;
import in.geekster.springdatajpademo.utils.DataMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("employees")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeResponse>> createEmployee(
            @Validated @RequestBody final EmployeeCreationRequest employeeCreationRequest) {
        log.info("Received Employee Creation Request: {}", employeeCreationRequest);
        try {
            EmployeeDTO employeeDTO = DataMapperUtil.convertTo(employeeCreationRequest, EmployeeDTO.class);
            final RoleDTO roleDTO = new RoleDTO();
            roleDTO.setId(employeeCreationRequest.getRoleId());
            employeeDTO.setRole(roleDTO);
            employeeDTO = employeeService.create(employeeDTO);
            log.info("Created Employee: {}", employeeDTO);
            final EmployeeResponse employeeResponse = DataMapperUtil.convertTo(employeeDTO, EmployeeResponse.class);
            return ResponseEntity.ok(ApiResponse.success(employeeResponse));
        } catch (final NoRoleFoundException e) {
            log.error("Error creating Employee:\n", e);
            return ResponseEntity.badRequest().body(ApiResponse.error(Error.create(ErrorCode.NO_ROLE_FOUND, e.getMessage())));
        }
    }

    @GetMapping(params = {"roleId", "projectId"})
    public ResponseEntity<ApiResponse<List<EmployeeResponse>>> getAllEmployeesByProjectAndRole(
            @RequestParam("roleId") final Long roleId,
            @RequestParam("projectId") final Long projectId) throws ProjectNotFoundException, RoleNotFoundException {
        List<EmployeeDTO> employeeDTOS = employeeService.getAllByProjectIdAndRoleId(projectId, roleId);
        log.info("Retrieved Employee List: {}", employeeDTOS);
        final List<EmployeeResponse> employeeResponseList = new ArrayList<>();
        employeeDTOS.forEach((emp) -> {
            final EmployeeResponse employeeResponse = DataMapperUtil.convertTo(emp, EmployeeResponse.class);
            employeeResponseList.add(employeeResponse);
        });
        return ResponseEntity.ok(ApiResponse.success(employeeResponseList));
    }
}
