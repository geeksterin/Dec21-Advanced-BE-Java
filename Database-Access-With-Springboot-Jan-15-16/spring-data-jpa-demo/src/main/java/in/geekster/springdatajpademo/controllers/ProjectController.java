package in.geekster.springdatajpademo.controllers;

import in.geekster.springdatajpademo.constants.ErrorCode;
import in.geekster.springdatajpademo.dtos.ProjectDTO;
import in.geekster.springdatajpademo.models.requests.ProjectCreationRequest;
import in.geekster.springdatajpademo.models.responses.ApiResponse;
import in.geekster.springdatajpademo.models.responses.Error;
import in.geekster.springdatajpademo.models.responses.ProjectResponse;
import in.geekster.springdatajpademo.services.ProjectService;
import in.geekster.springdatajpademo.utils.DataMapperUtil;
import in.geekster.springdatajpademo.utils.SecurityContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("projects")
@Slf4j
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProjectResponse>> createProject(
            @Validated @RequestBody final ProjectCreationRequest projectCreationRequest) {
        log.info("Received Project Creation Request: {}", projectCreationRequest);
        try {
            ProjectDTO projectDTO = DataMapperUtil.convertTo(projectCreationRequest, ProjectDTO.class);
            projectDTO = projectService.create(projectDTO);
            log.info("Created Project: {}", projectDTO);
            final ProjectResponse projectResponse = DataMapperUtil.convertTo(projectDTO, ProjectResponse.class);
            return ResponseEntity.ok(ApiResponse.success(projectResponse));
        } catch (final DataIntegrityViolationException e) {
            log.error("Error while creating Project:\n", e);
            final String errorDescription = String.format("Project with the name %s already exists", projectCreationRequest.getName());
            final Error error = Error.create(ErrorCode.DUPLICATE_PROJECT, errorDescription);
            return ResponseEntity.badRequest().body(ApiResponse.error(error));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProjectResponse>>> getAllProject() {
        log.info("Getting All Projects");
        log.info("REQUESTING CLIENT: {}", SecurityContextUtil.getRequestingClient());
        final List<ProjectDTO> projectDTOS = projectService.getAllProjects();
        final List<ProjectResponse> projectResponseList = projectDTOS
                .stream()
                .map(pd -> DataMapperUtil.convertTo(pd, ProjectResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(projectResponseList));
    }
}
