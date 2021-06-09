package cs.miu.edu.meditationattendance.security;

import cs.miu.edu.meditationattendance.domain.Faculty;
import cs.miu.edu.meditationattendance.domain.Role;
import cs.miu.edu.meditationattendance.domain.User;
import cs.miu.edu.meditationattendance.exception.ResourceNotFoundException;
import cs.miu.edu.meditationattendance.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail)
            throws UsernameNotFoundException {
        //Let people login with either username or email
        User user = userRepository.findByUserNameOrEmailAddress(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail)
       );
       return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = null;
        try {
            user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found")
            );
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }

        return UserPrincipal.create(user);
    }
}
