package bank_transaction.controller;

import bank_transaction.DTO.UserDTO;
import bank_transaction.model.User;
import bank_transaction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    //My method
    @GetMapping
    public List<UserDTO> getAllUsers() {

        return userService.getUsers();
    }

    //My method
    @GetMapping(value = "/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping(value="/register")
    public void registerUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @PatchMapping(value = "/{id}/admin/{adminId}/role")
    public void updateRole(@RequestBody Map<Object, Object> fields, @PathVariable Long id, @PathVariable Long adminId) {
        userService.changeRole(fields, id, adminId);
    }
}
