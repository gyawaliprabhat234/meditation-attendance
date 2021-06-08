package cs.miu.edu.meditationattendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import cs.miu.edu.meditationattendance.domain.CourseOffering;

import java.util.List;

public interface CourseOfferingRepository extends JpaRepository<CourseOffering, Long> {

	@Query("select co from CourseOffering co where co.faculty.id = ?1")
	public List<CourseOffering> coursesWithAttendance(Long idFaculty);

}
