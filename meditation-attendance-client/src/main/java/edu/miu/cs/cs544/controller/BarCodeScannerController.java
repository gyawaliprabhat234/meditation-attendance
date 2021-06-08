package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.exception.ResourceNotFoundException;
import edu.miu.cs.cs544.model.AttendanceDTO;
import edu.miu.cs.cs544.service.AttendanceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public List<AttendanceDTO> getSession(){
        return attendanceService.getSessions();
    }
    @GetMapping("/scan")
    public List<AttendanceDTO> scanBarCode(@RequestParam("barcode") Long barcode) throws ResourceNotFoundException {
        return attendanceService.update(barcode);
    }

}
