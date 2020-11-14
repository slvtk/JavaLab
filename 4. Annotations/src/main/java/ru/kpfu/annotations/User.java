package ru.kpfu.annotations;

@HtmlForm(method = "post", action = "/users")
public class User {
    @HtmlInput(type = "text", name = "first_name", placeholder = "Users name")
    private String firstName;
    @HtmlInput(type = "text", name = "last_name", placeholder = "Users surname")
    private String lastName;
    @HtmlInput(type = "email", name = "email", placeholder = "Users Email")
    private String email;
    @HtmlInput(type = "password", name = "password", placeholder = "Users password")
    private String password;
}