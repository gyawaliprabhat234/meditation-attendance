package cs.miu.edu.meditationattendance.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import cs.miu.edu.meditationattendance.domain.Course;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

	@Query("select distinct c from Course c where c.id in (select co.id from "
			+ "CourseOffering co where co.startDate >= ?1)")
	public List<Course> coursesPastSixMonths(LocalDate date);

}
