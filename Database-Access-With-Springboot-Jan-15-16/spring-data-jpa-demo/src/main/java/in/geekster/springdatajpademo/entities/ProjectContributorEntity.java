package in.geekster.springdatajpademo.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity(name = "projectContributor")
@Table(name = "project_contributor", uniqueConstraints = {
        @UniqueConstraint(name = "uk_proj_id_employee_id", columnNames = {"project_id", "employee_id"})

})
public class ProjectContributorEntity extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private ProjectEntity project;

    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL)
    private EmployeeEntity employee;
}
