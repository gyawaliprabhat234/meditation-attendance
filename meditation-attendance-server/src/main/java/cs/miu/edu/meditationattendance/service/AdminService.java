package cs.miu.edu.meditationattendance.service;

import cs.miu.edu.meditationattendance.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {
   boolean assignRole(Long userId, String role) throws ResourceNotFoundException;
}
