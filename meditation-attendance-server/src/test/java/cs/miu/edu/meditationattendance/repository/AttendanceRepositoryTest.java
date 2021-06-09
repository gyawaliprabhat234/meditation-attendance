package cs.miu.edu.meditationattendance.repository;

import cs.miu.edu.meditationattendance.domain.Attendance;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AttendanceRepositoryTest {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Test
    @Sql("classpath:test-data.sql")
    void it_should_return_correct_attendance_of_student_passing_id() {
        List<Attendance> attendanceList = attendanceRepository.findAllAttendanceByStudentId("000-61-1941");
        assertThat(attendanceList.size()).isEqualTo(1);
        assertThat(attendanceList.get(0).getBarCode()).isEqualTo(611941);
        assertThat(attendanceList.get(0).getClassSession().getSessionId()).isEqualTo(3);
        AssertionsForClassTypes.assertThat(attendanceList.get(0).getTimeStamp()).isEqualTo(LocalDateTime.of(2021, 06,8, 10, 00));
    }

}