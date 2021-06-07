package cs.miu.edu.meditationattendance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import cs.miu.edu.meditationattendance.domain.CourseOffering;

public interface CourseOfferingRepository extends CrudRepository<CourseOffering, Long> {
	
	@Query("select co from CourseOffering co")
	public List<CourseOffering> coursesWithAttendance();

}
