package com.llcatarino.schoolapi.entities.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    
    @Autowired

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    
    @Transactional
    public void updateUser(Long userId, User newUser) {
        User user = userRepository.findById(userId).orElseThrow();

        if (newUser.getFirstName() != null && newUser.getFirstName().length() > 0) {
            user.setFirstName(newUser.getFirstName());
        }

        if (newUser.getLastName() != null && newUser.getLastName().length() > 0) {
            user.setLastName(newUser.getLastName());
        }

        if (newUser.getDob() != null) {
            user.setDob(newUser.getDob());
        }

        if (newUser.getEmail() != null && newUser.getEmail().length() > 0) {
            Optional<User> userOptional = userRepository.findUserByEmail(newUser.getEmail());

            if (userOptional.isPresent()) {
                throw new IllegalStateException("user with email already exists");
            }
            user.setEmail(newUser.getEmail());
        }
    }
}
