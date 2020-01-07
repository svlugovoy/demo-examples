package com.svlugovoy.sorting.rest;

import com.svlugovoy.sorting.entity.Employee;
import com.svlugovoy.sorting.repository.EmployeeRepository;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

  private final EmployeeRepository repository;

  public EmployeeController(EmployeeRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/unsorted")
  public List<Employee> getAll() {
    return repository.findAll();
  }

  @GetMapping("/sorted_first_name")
  public List<Employee> getAllSortedByFirstName() {
    Sort sort = Sort.by("firstName").ascending();
    return repository.findAll(sort);
  }

  @GetMapping("/sorted_last_first_name")
  public List<Employee> getAllSortedByFirstLastName() {
    Sort sort = Sort.by("lastName").ascending().and(Sort.by("firstName")).ascending();
    return repository.findAll(sort);
  }

  @GetMapping("/sorted")
  public List<Employee> getAllSorted(@RequestParam(name = "col") String column, @RequestParam(name = "dir") String direction) {
    Sort sort = Sort.by(Direction.fromString(direction), column);
    return repository.findAll(sort);
  }

  @GetMapping("/sorted_salary_range")
  public List<Employee> getAllSortedBySalaryRange(
      @RequestParam(name = "min") String min, @RequestParam(name = "max") String max,
      @RequestParam(name = "col") String column, @RequestParam(name = "dir") String direction) {
    Sort sort = Sort.by(Direction.fromString(direction), column);
    return repository.findBySalaryRange(Double.parseDouble(min), Double.parseDouble(max), sort);
  }
}
