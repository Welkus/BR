package com.manager.br.util;


import lombok.Getter;

@Getter
public enum Messages {

    //Error messages
    INCORRECT("Incorrect username or password"),
    SUCCESS("User created successfully!"),
    PASSWORD_MISMATCH("Passwords do not match."),
    WEAK_PASSWORD("Password must be at least 4 characters long and contain at least one number."),
    CREATION_FAILED("User creation failed."),

    //Error colors
    RED("color:red;"),
    GREEN("color:green;");

    private final String text;

    Messages(String text) {
        this.text = text;
    }
}
