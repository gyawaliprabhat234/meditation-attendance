package cs.miu.edu.meditationattendance.repository;

import org.springframework.data.repository.CrudRepository;

import cs.miu.edu.meditationattendance.domain.Location;

public interface LocationRepository extends CrudRepository<Location, Integer> {

}
