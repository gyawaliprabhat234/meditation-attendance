package cs.miu.edu.mediatationattendance.repository;

import org.springframework.data.repository.CrudRepository;

import cs.miu.edu.mediatationattendance.domain.CourseRegistration;

public interface CourseRegistrationRepository extends CrudRepository<CourseRegistration, Long> {

}
