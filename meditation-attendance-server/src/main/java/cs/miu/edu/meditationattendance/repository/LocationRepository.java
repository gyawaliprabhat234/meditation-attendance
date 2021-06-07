package cs.miu.edu.meditationattendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import cs.miu.edu.meditationattendance.domain.Location;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface LocationRepository extends JpaRepository<Location, Integer> {

    Optional<Location> findByBuildingNameAndRoomName(String buildingName, String roomName);
}
