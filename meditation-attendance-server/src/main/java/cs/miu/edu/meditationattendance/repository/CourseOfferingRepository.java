package cs.miu.edu.meditationattendance.repository;

import org.springframework.data.repository.CrudRepository;

import cs.miu.edu.meditationattendance.domain.CourseOffering;

public interface CourseOfferingRepository extends JpaRepository<CourseOffering, Long> {

	@Query("select co from CourseOffering co")
	public List<CourseOffering> coursesWithAttendance();

}
