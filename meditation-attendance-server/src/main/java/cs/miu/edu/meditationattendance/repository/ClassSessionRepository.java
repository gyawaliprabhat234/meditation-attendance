package cs.miu.edu.meditationattendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import cs.miu.edu.meditationattendance.domain.ClassSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Repository
@Transactional
public interface ClassSessionRepository extends JpaRepository<ClassSession, Long> {


    @Query("select session from CourseOffering offering " +
            "join offering.sessions session " +
            "where session.location.locationId = ?1 " +
            "and session.date = ?2 " +
            "and session.timeslot.endTime > ?3 " +
            "and session.timeslot.startTime < ?4")
    Optional<ClassSession> findClassSessionByLocationIdAndCurrentTime(Integer locationId, LocalDate date, LocalTime time, LocalTime increasedTime);
}
