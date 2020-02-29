package kr.co.kwan.eatgo.application;

import kr.co.kwan.eatgo.domain.User;
import kr.co.kwan.eatgo.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /*@Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }*/

    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public User addUser(String email, String name) {
        User user = User.builder().email(email).name(name).level(1L).build();
        return userRepository.save(user);
    }

    public User updateUser(Long id, String email, String name, Long level) {
        User user = userRepository.findById(id).orElseThrow(null);
        user.setName(name);
        user.setEmail(email);
        user.setLevel(level);
        return user;
    }

    public User deactiveUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(null);
        user.deactive();
        return user;
    }
}
