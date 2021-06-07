package cs.miu.edu.meditationattendance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
public class LocationDTO {
    private String buildingName;
    private String roomName;
}
