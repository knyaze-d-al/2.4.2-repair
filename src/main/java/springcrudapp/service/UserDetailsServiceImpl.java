package springcrudapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springcrudapp.dao.RoleRepository;
import springcrudapp.dao.UserRepository;
import springcrudapp.model.Role;
import springcrudapp.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsServiceInterface {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found!");
        }

        return user;
    }

    @Override
    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);

        return userFromDb.orElse(new User());
    }

    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean saveUser(User user) {
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null) {
            return false;
        }

        userRepository.save(user);

        return true;
    }

    @Override
    public boolean deleteUser(Long userId) {

        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);

            return true;
        }

        return false;
    }

    @Override
    public void editUser(User user) {

        userRepository.save(user);
    }

    @Override
    public Optional<Role> findRole(Long id) {
        return Optional.empty();
    }
}
