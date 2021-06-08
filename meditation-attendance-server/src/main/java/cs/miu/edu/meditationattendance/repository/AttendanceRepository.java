package cs.miu.edu.meditationattendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import cs.miu.edu.meditationattendance.domain.Attendance;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    @Query("select a from Attendance a where a.student.studentId = ?1")
    List<Attendance> findAllAttendanceByStudentId(String id);

    @Query("select attendance from Attendance attendance " +
            "where attendance.classSession.courseOffering.course.courseNumber = ?1 " +
            "and attendance.student.studentId = ?2 ")
    List<Attendance> findAllAttendanceByCourseNumber(String courseNumber, String studentId);
}
