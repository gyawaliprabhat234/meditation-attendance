package cs.miu.edu.meditationattendance.repository;

import com.netflix.discovery.converters.Auto;
import cs.miu.edu.meditationattendance.domain.Attendance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
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
        assertEquals(attendanceList.size(), 1);
        assertEquals(attendanceList.get(0).getBarCode(), 611941);
        assertEquals(attendanceList.get(0).getClassSession().getSessionId(), 3);
        assertEquals(attendanceList.get(0).getTimeStamp(), LocalDateTime.of(2021, 06,8, 10, 00));
    }

}