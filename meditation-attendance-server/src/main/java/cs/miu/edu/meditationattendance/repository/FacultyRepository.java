package cs.miu.edu.meditationattendance.repository;

import cs.miu.edu.meditationattendance.domain.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
