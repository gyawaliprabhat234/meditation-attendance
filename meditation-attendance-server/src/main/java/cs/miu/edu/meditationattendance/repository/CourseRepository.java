package cs.miu.edu.meditationattendance.repository;

import org.springframework.data.repository.CrudRepository;

import cs.miu.edu.meditationattendance.domain.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {

}
