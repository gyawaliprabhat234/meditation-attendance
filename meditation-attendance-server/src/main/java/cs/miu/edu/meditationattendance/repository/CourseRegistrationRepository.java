package cs.miu.edu.meditationattendance.repository;

import cs.miu.edu.meditationattendance.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import cs.miu.edu.meditationattendance.domain.CourseRegistration;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Repository
@Transactional
public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Long> {

    @Query("select course from CourseRegistration cr " +
                "inner join cr.courseOffering co " +
                "inner join co.course course " +
//                 "inner join cr.student s" +
                    "where cr.student.studentId = ?1 " +
                    "and co.startDate <= ?2 " +
                    "and co.endDate >= ?2")
    Optional<Course> findCurrentCourseByStudentId(String studentId, LocalDate date);
}
