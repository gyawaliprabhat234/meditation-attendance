package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.exception.ResourceNotFoundException;
import edu.miu.cs.cs544.model.AttendanceDTO;
import edu.miu.cs.cs544.service.AttendanceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/barcode")
class BarCodeScannerController {

    @Autowired
    private AttendanceServiceImpl attendanceService;

    @GetMapping("/scan")
    public void checkAndUpdateAttendance(@RequestParam("barcode") Long barcode) throws ResourceNotFoundException {
        attendanceService.checkAndUpdateAttendance(barcode);
    }

    @GetMapping("/local")
    public List<AttendanceDTO> get(){
        return attendanceService.readDataInLocal();
    }

    @GetMapping("/sessions")
    public List<AttendanceDTO> getSession(){
        return attendanceService.getSessions();
    }

    @GetMapping("/push")
    public void pushDataToServer(){
        attendanceService.importData();
    }

    @Value("${location.room}")
    private String room;

    @GetMapping("/info")
    public String info(){
        return room;
    }
}
