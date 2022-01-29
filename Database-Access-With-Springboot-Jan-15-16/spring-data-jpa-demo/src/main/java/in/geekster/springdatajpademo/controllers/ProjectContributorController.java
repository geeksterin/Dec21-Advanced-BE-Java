package in.geekster.springdatajpademo.controllers;

import in.geekster.springdatajpademo.constants.ErrorCode;
import in.geekster.springdatajpademo.dtos.ProjectContributorDTO;
import in.geekster.springdatajpademo.exceptions.EmployeeNotFoundException;
import in.geekster.springdatajpademo.exceptions.ProjectNotFoundException;
import in.geekster.springdatajpademo.models.requests.ProjectContributorCreationRequest;
import in.geekster.springdatajpademo.models.responses.ApiResponse;
import in.geekster.springdatajpademo.models.responses.Error;
import in.geekster.springdatajpademo.models.responses.ProjectContributorResponse;
import in.geekster.springdatajpademo.services.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("projects/{projectId}/contributors")
@Slf4j
public class ProjectContributorController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProjectContributorResponse>> addProjectContributor(
            @PathVariable("projectId") final Long projectId,
            @Validated @RequestBody final ProjectContributorCreationRequest request) {
        log.info("Received Project Contributor Creation requesr: {}", request);
        ProjectContributorDTO dto = new ProjectContributorDTO();
        dto.setProjectId(projectId);
        dto.setEmployeeId(request.getEmployeeId());
        try {
            dto = projectService.addProjectContributor(dto);
            log.debug("Created Project Contributor: {}", dto);
            final ProjectContributorResponse response = new ProjectContributorResponse();
            response.setProjectId(dto.getProjectId());
            response.setEmployeeId(dto.getEmployeeId());
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (ProjectNotFoundException | EmployeeNotFoundException e) {
            log.error("Error adding Project Contributor:\n", e);
            final String errorCode;
            if (e instanceof ProjectNotFoundException) {
                errorCode = ErrorCode.PROJECT_NOT_FOUND;
            } else {
                errorCode = ErrorCode.EMPLOYEE_NOT_FOUND;
            }
            final Error error = Error.create(errorCode, e.getMessage());
            return ResponseEntity.badRequest().body(ApiResponse.error(error));
        }
    }
}
