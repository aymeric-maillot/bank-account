package com.votybe.bankaccount.users;

import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public User toUser(UserRegistrationRequest request) {
        return User.builder()
                .username(request.username())
                .password(request.password())
                .build();
    }

    public UserRegistrationRequest toUserDTO(User user) {
        return new UserRegistrationRequest(
                user.getUsername(),
                user.getPassword()
        );
    }
}
