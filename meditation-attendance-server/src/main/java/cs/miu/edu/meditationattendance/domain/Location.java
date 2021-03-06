package cs.miu.edu.meditationattendance.domain;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`Location`")
public class Location {
    @Id
    @GeneratedValue
    private Integer locationId;
    private String buildingName;
    private String roomName;
    @Column(length = 4000)
    private String description;
}
