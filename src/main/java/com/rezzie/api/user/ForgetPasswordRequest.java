package com.rezzie.api.user;

import javax.validation.constraints.NotEmpty;

public class ForgetPasswordRequest {
    @NotEmpty
    private String email;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
