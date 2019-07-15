package br.com.juliogriebeler.movrent.controller.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * @author Julio Griebeler
 */
@JsonAutoDetect
public class LoginRequest {

    @NotNull
    @Email
    private String username;
    @NotNull
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
