package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/barcode")
class BarCodeScannerController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping
    public String scanBarCode(@RequestParam String barCodeId){
        return attendanceService.scanBarcode(barCodeId);
    }
}
