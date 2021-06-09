package cs.miu.edu.meditationattendance.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import cs.miu.edu.meditationattendance.domain.Course;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

	@Query("select distinct c from Course c where c.id in (select co.id from "
			+ "CourseOffering co where co.startDate >= ?1 and co.faculty.id = ?2)")
	public List<Course> coursesPastSixMonths(LocalDate date, Long idFaculty);

	@Query("select c from Course c where c.courseNumber = ?1 ")
    Optional<Course> findCourseByCourseNumber(String courseNumber);
}
