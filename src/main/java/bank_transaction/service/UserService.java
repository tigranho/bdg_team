package bank_transaction.service;

import bank_transaction.DTO.UserDTO;
import bank_transaction.model.User;
import bank_transaction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public List<UserDTO> getUsers() {
        List<UserDTO> userList = new ArrayList<>();
        userRepository.findAll().forEach(t -> userList.add(new UserDTO(t)));
        return userList;
    }

    public UserDTO getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(UserDTO::new).orElse(null);
    }

    public void addUser(User user) {
        String s = user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(s));
        userRepository.save(user);
    }

    public void changeRole(Map<Object, Object> fields, Long id, Long adminId) {
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.orElse(null);
        Optional<User> adminOptional = userRepository.findById(adminId);
        User admin = adminOptional.orElse(null);

        if(admin != null && user != null && admin.getRole().equals("ADMIN")) {
            for (Map.Entry<Object, Object> entry : fields.entrySet()) {
                String role = entry.getKey().toString();
                if(role.equals("role")) {
                    user.setRole(entry.getValue().toString());
                }
            }
            userRepository.save(user);
        }

    }


}
