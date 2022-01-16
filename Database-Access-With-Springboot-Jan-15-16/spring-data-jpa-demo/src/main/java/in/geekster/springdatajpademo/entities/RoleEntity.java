package in.geekster.springdatajpademo.entities;

import in.geekster.springdatajpademo.enums.Department;
import in.geekster.springdatajpademo.enums.Designation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * Entities are essentially part of Data Access Layer, they are also known as Data Access Objects (DAO)
 * This entity usually remains within the bounds of Service Layer and Data Access Layer
 *
 *
 * @Entity(name = "roleABC") -- this component denotes how the ORM tool will refer to this object.
 * name = 'roleABC' is the name given to this object and when Hibernate wants to call this object,
 * it will call the object by this name
 *
 * @Table -- This specifies how this object will be called in the actual DB
 * name = 'role' will create table with the name role and will map every variable in this class to
 * their respective columns
 *
 * @UniqueConstraint -- this annotation is used to define a unique constraint, either on a single key
 * or on multiple keys
 * name - is the name of the constraint
 * columnNames = the columns on which this constraint will apply
 *
 * @Column -- maps a variable in class to an actual column in the DB
 * @Enumerated(EnumType.STRING) -- will ensure ENUM values are persisted as VARCHAR in the DB
 *
 * @OneToMany(mappedBy = "role") -- this specifies how many other entities this single entity maps to
 * mappedBy specifies what this current entity is known as in the other entity
 *
 */

@Entity(name = "roleABC")
@Table(name = "role", uniqueConstraints = {
        @UniqueConstraint(name = "uk_department_and_designation_role", columnNames = {"department", "designation"})
})
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RoleEntity extends AbstractEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Department department;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Designation designation;

    @OneToMany(mappedBy = "role")
    private List<EmployeeEntity> employeeEntities;

}
