package cs.miu.edu.meditationattendance.repository;

import cs.miu.edu.meditationattendance.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    @Sql("classpath:test-data.sql")
    void it_should_return_correct_student_by_passing_barcode(){
        Optional<Student> student = studentRepository.findByStudentId("000-61-1941");
        System.out.println(student);
        assertThat(student.isPresent()).isEqualTo(true);
        assertThat(student.get().getStudentId()).isEqualTo("000-61-1941");
        assertThat(student.get().getId()).isEqualTo(101);
        assertThat(student.get().getEmailAddress()).isEqualTo("pgyawali@miu.edu");
        assertThat(student.get().getUserName()).isEqualTo("gyawaliprabhat");
        assertThat(student.get().getFirstName()).isEqualTo("Prabhat");
        assertThat(student.get().getLastName()).isEqualTo("Gyawali");
    }

    @Test
    @Sql("classpath:test-data.sql")
    void it_should_return_correct_student_by_passing_session_id(){
        List<Student> students = studentRepository.findAllStudentBySessionId(3l);
        System.out.println(students);
        assertThat(students.size()).isEqualTo(4);
    }

}
