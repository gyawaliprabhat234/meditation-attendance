package cs.miu.edu.meditationattendance.repository;

import org.springframework.data.repository.CrudRepository;

import cs.miu.edu.meditationattendance.domain.Attendance;

public interface AttendanceRepository extends CrudRepository<Attendance, Long>{

}
