package cs.miu.edu.meditationattendance.repository;

import org.springframework.data.repository.CrudRepository;

import cs.miu.edu.meditationattendance.domain.CourseRegistration;

public interface CourseRegistrationRepository extends CrudRepository<CourseRegistration, Long> {

}
