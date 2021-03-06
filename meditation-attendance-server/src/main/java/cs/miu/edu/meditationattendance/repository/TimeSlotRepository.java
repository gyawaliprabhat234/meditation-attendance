package cs.miu.edu.meditationattendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import cs.miu.edu.meditationattendance.domain.TimeSlot;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Integer> {

}
