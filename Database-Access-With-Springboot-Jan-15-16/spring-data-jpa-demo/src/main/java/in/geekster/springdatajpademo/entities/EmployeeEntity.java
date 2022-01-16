package in.geekster.springdatajpademo.entities;

import in.geekster.springdatajpademo.enums.Gender;
import in.geekster.springdatajpademo.enums.IdentityType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;


/**
 * Entities are essentially part of Data Access Layer, they are also known as Data Access Objects (DAO)
 * This entity usually remains within the bounds of Service Layer and Data Access Layer
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
 * name = specifies the column name in the DB
 * columnDefinition allows you to specify SQL level column definition
 * nullable controls whether it will be NOT NULL or DEFAULT NULL
 *
 * @Enumerated(EnumType.STRING) -- will ensure ENUM values are persisted as VARCHAR in the DB
 *
 * @OneToMany(mappedBy = "role") -- this specifies how many other entities this single entity maps to
 * mappedBy specifies what this current entity is known as in the other entity
 *
 *  @JoinColumn(name = "role_id", referencedColumnName = "id")
 *  This here connects {@link RoleEntity} to this Entity based on Foreign Key parameter
 *  referencedColumnName = "id" specifies which column in the foreign table
 *  name = "role_id" specifies how that foreign column will be known in this table
 *
 *  @ManyToOne(cascade = CascadeType.ALL)
 *  This here specifies that one external entity [ {@link RoleEntity} in our case] can map to
 *  many instances of the current entity {@link EmployeeEntity}
 *
 *  cascade = Specifies the cascading rules of this specific foreign key
 */

@Table(name = "employee", uniqueConstraints = {
        @UniqueConstraint(name = "uk_identity_type_number", columnNames = {"identity_number", "identity_type"})
})
@Entity(name = "employee")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EmployeeEntity extends AbstractEntity {

    @Column(name = "first_name", columnDefinition = "VARCHAR(100)", nullable = false)
    private String firstName;

    @Column(name = "last_name", columnDefinition = "VARCHAR(100)")
    private String lastName;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "identity_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private IdentityType identityType;

    @Column(name = "identity_number", columnDefinition = "VARCHAR(50)", nullable = false)
    private String identityNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private RoleEntity role;
}
