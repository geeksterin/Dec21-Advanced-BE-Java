package in.geekster.springdatajpademo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import in.geekster.springdatajpademo.enums.ProjectStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity(name = "project")
@Table(name = "project", uniqueConstraints = {
        @UniqueConstraint(name = "uk_name_project", columnNames = {"name"})
})
public class ProjectEntity extends AbstractEntity {

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @Column
    private LocalDateTime finishedOn;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProjectContributorEntity> projectContributors;
}
