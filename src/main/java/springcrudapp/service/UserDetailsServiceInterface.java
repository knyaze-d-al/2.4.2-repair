package springcrudapp.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import springcrudapp.model.Role;
import springcrudapp.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDetailsServiceInterface extends UserDetailsService {

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    User findUserById(Long userId);

    List<User> allUsers();

    boolean saveUser(User user);

    boolean deleteUser(Long userId);

    void editUser(User user);

    Optional<Role> findRole(Long id);

}
