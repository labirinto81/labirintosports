package ch.labirintosports.controller;

import ch.labirintosports.exception.ResourceNotFoundException;
import ch.labirintosports.model.User;
import ch.labirintosports.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.Valid;
import java.util.List;



@RestController
@RequestMapping("/api")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;

    // Get All User
    @GetMapping("/users")
    public List<User> getAllUsers() {
    return userRepository.findAll();
}

    // Create a new User
    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
    return userRepository.save(user);
}

    // Get a Single User
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable(value = "id") Long userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
}

    // Update a User
@PutMapping("/users/{id}")
public User updateUser(@PathVariable(value = "id") Long userId,
                                        @Valid @RequestBody User userDetails) {

    User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user.setLastName(userDetails.getLastName());
        user.setFirstName(userDetails.getFirstName());

    User updatedUser = userRepository.save(user);
    return updatedUser;
}

    // Delete a User
@DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        userRepository.delete(user);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(path = "/hello")
    public @ResponseBody String sayHello() {
        LOG.info("GET called on /hello resource");
        return "Hello World from the Backend :-D";
    }

}