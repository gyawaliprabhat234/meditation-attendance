package edu.miu.cs.cs544.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.cs.cs544.exception.ResourceNotFoundException;
import edu.miu.cs.cs544.model.AttendanceDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class AttendanceServiceImpl implements AttendanceService{

	public static final String VAR_TMP_DATA_JSON = "/var/tmp/data.json";
	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${spring-boot-server.name}")
	private String serverName;

	@Value("${location.bldg}")
	private String buildingName;

	@Value("${location.room}")
	private String roomName;

	@Override
	public List<AttendanceDTO> getSessions() {
		String url = getBaseServiceUrl() + "/attendances/session?bldgName=" + buildingName + "&roomName=" +roomName;
		AttendanceDTO[] forObject = restTemplate.getForObject(url, AttendanceDTO[].class);
		saveToJson(forObject);
		return readDataFromJson();
	}

	@Override
	public List<AttendanceDTO> readDataInLocal() {
		return readDataFromJson();
	}

	@Override
	public void checkAndUpdateAttendance(Long baCode) throws ResourceNotFoundException {
		updateBarcodeToFile(baCode);
	}

	@Override
	public void importData() {
		String url = getBaseServiceUrl() + "/attendances/saveAll";
		List<AttendanceDTO> attendanceDTOS = readDataFromJson();
		Boolean result = restTemplate.postForObject(url, attendanceDTOS, Boolean.class);
		log.info("Save attendance : " + (result ? "sucess" : "falied"));
	}

	private void updateBarcodeToFile(Long baCode) throws ResourceNotFoundException {
			List<AttendanceDTO> attendanceDTOS = readDataFromJson();
			Optional<AttendanceDTO> att = attendanceDTOS.stream().filter(attendance -> baCode.equals(attendance.getBarCode()))
					.findAny();
			if (att.isPresent()){
				att.ifPresent(a->a.setTimeStamp(LocalDateTime.now()));
				AttendanceDTO[] forObject = attendanceDTOS.toArray(new AttendanceDTO[attendanceDTOS.size()]);
				saveToJson(forObject);
			} else{
				throw new ResourceNotFoundException("Student is not in this class");
			}
	}

	private List<AttendanceDTO> readDataFromJson() {
		File file = new File(VAR_TMP_DATA_JSON);
		ObjectMapper objectMapper = new ObjectMapper();
		List<AttendanceDTO> attendanceDTOS = new ArrayList<>();
		try {
			attendanceDTOS = objectMapper.readValue(file, new TypeReference<List<AttendanceDTO>>() {});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return attendanceDTOS;
	}

	private void saveToJson(AttendanceDTO[] forObject) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			File convertFile = new File(VAR_TMP_DATA_JSON);
			if (convertFile.exists()) {
				convertFile.delete();
			}
			convertFile.createNewFile();
			FileOutputStream fout = new FileOutputStream(convertFile);
			objectMapper.writeValue(fout, forObject);
		} catch (IOException e) {
			log.error("Cannot write data to file", e);
		}
	}

	private String getBaseServiceUrl() {
		List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serverName);
		return serviceInstances.get(0).getUri().toString();
	}
}
