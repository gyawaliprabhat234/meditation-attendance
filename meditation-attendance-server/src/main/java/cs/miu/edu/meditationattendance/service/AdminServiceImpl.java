package cs.miu.edu.meditationattendance.service;

import cs.miu.edu.meditationattendance.domain.Role;
import cs.miu.edu.meditationattendance.domain.User;
import cs.miu.edu.meditationattendance.exception.ResourceNotFoundException;
import cs.miu.edu.meditationattendance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private UserRepository userRepository;

    public boolean assignRole(Long userId, String newRole) throws ResourceNotFoundException{
        Optional<User> findUser = userRepository.findById(userId);
        if (findUser.isPresent()){
            User user = findUser.get();
            Set<Role> role1 = user.getRole();
            role1.add(new Role(newRole));
            userRepository.save(user);
            return true;
        }else {
            throw  new ResourceNotFoundException("User " + userId + " not found");
        }
    }
}
