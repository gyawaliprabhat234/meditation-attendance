package cs.miu.edu.mediatationattendance.repository;

import org.springframework.data.repository.CrudRepository;

import cs.miu.edu.mediatationattendance.domain.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {

}
