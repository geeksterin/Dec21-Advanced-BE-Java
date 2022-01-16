package in.geekster.springdatajpademo.entities;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Entities are essentially part of Data Access Layer, they are also known as Data Access Objects (DAO)
 * This entity usually remains within the bounds of Service Layer and Data Access Layer
 *
 * @MappedSuperclass -- Used on a class for which you don't want a table to exist in the DB
 * {@link AbstractEntity} here is used to extract the common properties that will be shared
 * across all the other entities
 */

@MappedSuperclass
@Data
public abstract class AbstractEntity {

    /**
     * @Id does what PRIMARY KEY in MySQL DB does. It makes the variable (id) UNIQUE and NOT NULL
     * @GeneratedValue -- Basically dictates how the ID generation is to be done. It takes strategy as input
     * GenetationType.AUTO basically delegates the task of generating and incrementing the ID to the underlying
     * vendor
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
