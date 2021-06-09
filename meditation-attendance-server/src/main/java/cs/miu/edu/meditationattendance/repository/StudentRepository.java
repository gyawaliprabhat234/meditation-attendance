package cs.miu.edu.meditationattendance.repository;

import cs.miu.edu.meditationattendance.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByBarcode(Long barCode);
    Optional<Student> findByStudentId(String studentId);

    @Query("select registration.student from CourseRegistration registration " +
            "inner join registration.courseOffering offering " +
            "inner join offering.sessions session " +
            "where session.sessionId = ?1")
    List<Student> findAllStudentBySessionId(Long sessionId);

    Optional<Student> findById(Long id);
}
