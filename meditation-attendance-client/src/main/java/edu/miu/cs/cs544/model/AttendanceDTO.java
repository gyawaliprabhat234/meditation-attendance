package edu.miu.cs.cs544.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDTO {
    private Long id;
    private String studentId;
    private String studentName;
    private String firstName;
    private String lastName;
    private String courseName;
    private LocalDate courseStartDate;
    private LocalDate courseEndDate;
    private String timeSlotCode;
    private String timeSlotCodeDescription;
    private Long sessionId;
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime timeStamp;
    private String emailAddress;
    private String buildingName;
    private Long barCode;
}
