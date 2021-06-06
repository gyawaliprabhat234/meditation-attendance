package cs.miu.edu.meditationattendance.repository;

import cs.miu.edu.meditationattendance.domain.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import cs.miu.edu.meditationattendance.domain.Attendance;

import java.util.List;

public interface AttendanceRepository extends CrudRepository<Attendance, Long>{
    @Query("select distinct co.course from CourseRegistration cr join cr.courseOffering co where cr.student.studentId = ?1")
    List<Attendance> findAttendanceByStudentId(Long studentId);
}
