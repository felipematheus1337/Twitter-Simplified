package twitter_simplify.springsecurity.config;

import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import twitter_simplify.springsecurity.entities.Role;
import twitter_simplify.springsecurity.entities.User;
import twitter_simplify.springsecurity.repository.RoleRepository;
import twitter_simplify.springsecurity.repository.UserRepository;

import java.util.Set;

@Configuration
public class AdminUserConfig  implements CommandLineRunner {


    private RoleRepository repository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public AdminUserConfig(RoleRepository repository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {


        var roleAdmin = repository.findByName(Role.Values.ADMIN.name());

        var userAdmin = userRepository.findByUserName("admin");

        userAdmin.ifPresentOrElse(
                (user) -> {
                    System.out.println("admin já existe");
                },
                () -> {
            var user = new User();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("123"));
            user.setRoles(Set.of(roleAdmin.get()));
            userRepository.save(user);
        }
        );

    }
}
