package com.example.authservice.Validate;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public final class EmailValidator {
    private final String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    public boolean validateEmail(final String emailAddress) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
}
