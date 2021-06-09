package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.exception.ResourceNotFoundException;
import edu.miu.cs.cs544.model.AttendanceDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AttendanceService {
	List<AttendanceDTO> getSessions();

	List<AttendanceDTO> readDataInLocal();

	boolean deleteJson();

	void checkAndUpdateAttendance(Long baCode) throws ResourceNotFoundException;

	void importData();
}
