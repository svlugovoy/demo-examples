package com.svlugovoy.sorting.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "employees")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
@NamedQuery(name = "Employee.findByAgeGreaterThanNamedJPQL",
    query = "SELECT e FROM Employee e WHERE e.age > :age ORDER BY e.firstName ASC")
@NamedNativeQuery(name = "Employee.findAllNamedNativeSQL",
    query = "SELECT * FROM Employee e ORDER BY e.age DESC")
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String firstName;
  private String lastName;
  private int age;
  private double salary;
}
