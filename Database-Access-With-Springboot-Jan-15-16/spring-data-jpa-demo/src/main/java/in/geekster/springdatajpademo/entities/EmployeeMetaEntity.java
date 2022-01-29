package in.geekster.springdatajpademo.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity(name = "employeeMeta")
@Table(name = "employee_meta")
public class EmployeeMetaEntity extends AbstractEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", referencedColumnName = "id", unique = true)
    private EmployeeEntity employee;

    @Column(columnDefinition = "VARCHAR(20)")
    private String phone;

    @Column(columnDefinition = "VARCHAR(50)")
    private String email;
}
