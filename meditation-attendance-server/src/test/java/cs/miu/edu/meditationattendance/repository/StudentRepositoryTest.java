package cs.miu.edu.meditationattendance.repository;

import cs.miu.edu.meditationattendance.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @Test
    void itShouldReturnStudentByBarcord(){
        //given
        Student student = new Student();
        student.setStudentId("611941");
        student.setBarcode(611941l);
        student.setFirstName("Prabhat");
        student.setLastName("Gyawali");
        student.setEntryDateTime(LocalDateTime.now());
        underTest.save(student);
        //when
        Optional<Student> expected = underTest.findByBarcode(611941l);




    }
}
