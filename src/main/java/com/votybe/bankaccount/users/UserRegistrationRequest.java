package com.votybe.bankaccount.users;

public record UserRegistrationRequest(
        String username,
        String password
) {}
