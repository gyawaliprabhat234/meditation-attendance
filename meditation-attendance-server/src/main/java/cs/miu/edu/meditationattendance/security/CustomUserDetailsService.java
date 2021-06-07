package cs.miu.edu.meditationattendance.security;

import cs.miu.edu.meditationattendance.domain.Faculty;
import cs.miu.edu.meditationattendance.domain.Role;
import cs.miu.edu.meditationattendance.domain.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    //TODO
    //@Autowired
    //UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail)
            throws UsernameNotFoundException {
        //Let people login with either username or email
//        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
//                .orElseThrow(() ->
//                        new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail)
//        );

        User user = createAMockFaculty();
        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
//        User user = userRepository.findById(id).orElseThrow(
//            () -> new ResourceNotFoundException("User", "id", id)
//        );

        User user = createAMockFaculty();
        return UserPrincipal.create(user);
    }

    @NotNull
    private User createAMockFaculty() {
        HashSet<Role> roles = new HashSet<>();
        roles.add(new Role(1L, "FACULTY", "ALL"));
        User user = new Faculty("title", "firstname", "lastname",
                "mail@mai.com", "admin",new BCryptPasswordEncoder().encode("password"), roles);
        return user;
    }
}
