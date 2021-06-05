package cs.miu.edu.meditationattendance.repository;

import org.springframework.data.repository.CrudRepository;

import cs.miu.edu.meditationattendance.domain.TimeSlot;

public interface TimeSlotRepository extends CrudRepository<TimeSlot, Integer> {

}
