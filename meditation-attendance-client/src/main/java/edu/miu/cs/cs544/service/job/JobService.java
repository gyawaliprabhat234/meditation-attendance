package edu.miu.cs.cs544.service.job;

import edu.miu.cs.cs544.service.AttendanceService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class JobService {

    @Autowired
    private AttendanceService attendanceService;

    @Scheduled(cron = "${cronJob.getStudentsJob}")
    public void getStudents(){
        log.info("Job: getStudents started");
        //attendanceService.getSessions();
        log.info("Job: getStudents finished");
    }

    @Scheduled(cron = "${cronJob.importJob}")
    public void importAttendance(){
        log.info("Job: importAttendance started");
        //attendanceService.importData();
        log.info("Job: importAttendance finished");
    }
}
