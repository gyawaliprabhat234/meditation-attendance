package cs.miu.edu.mediatationattendance.repository;

import org.springframework.data.repository.CrudRepository;

import cs.miu.edu.mediatationattendance.domain.Attendance;

public interface AttendanceRepository extends CrudRepository<Attendance, Long>{

}
