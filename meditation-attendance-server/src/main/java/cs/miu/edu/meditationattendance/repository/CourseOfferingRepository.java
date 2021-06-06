package cs.miu.edu.meditationattendance.repository;

import cs.miu.edu.meditationattendance.domain.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import cs.miu.edu.meditationattendance.domain.CourseOffering;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CourseOfferingRepository extends CrudRepository<CourseOffering, Long> {

    @Query("select distinct co.course from CourseRegistration cr join cr.courseOffering co where cr.student.studentId = ?1")
    List<Course> findByStudentId(Long studentId);

}