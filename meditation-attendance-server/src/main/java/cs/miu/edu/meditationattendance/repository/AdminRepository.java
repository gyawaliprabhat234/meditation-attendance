package cs.miu.edu.meditationattendance.repository;

import cs.miu.edu.meditationattendance.domain.Administrator;
import cs.miu.edu.meditationattendance.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface AdminRepository extends JpaRepository<Administrator, Long> {
}
