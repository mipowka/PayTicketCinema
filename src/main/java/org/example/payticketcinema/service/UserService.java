package org.example.payticketcinema.service;

import lombok.RequiredArgsConstructor;
import org.example.payticketcinema.mapping.UserMapper;
import org.example.payticketcinema.model.dto.UserForLkDto;
import org.example.payticketcinema.model.entity.User;
import org.example.payticketcinema.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public boolean loginUser(User userFromForm) {
        var userByName = userRepository.findByUsername(userFromForm.getUsername());
        if (userByName == null) {
            return false;
        }
        if (!(userByName.getPassword().equals(userFromForm.getPassword()) &&
                userByName.getUsername().equals(userFromForm.getUsername())
        )) {
            return false;
        }

        return true;
    }

    public UserForLkDto getUserByUsername(String username) {
        User byUsername = userRepository.findByUsername(username);
        UserForLkDto userForLkDto = userMapper.toDto(byUsername);

        return userForLkDto;
    }

}
