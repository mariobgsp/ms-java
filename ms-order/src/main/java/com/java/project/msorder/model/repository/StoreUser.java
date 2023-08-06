package com.java.project.msorder.model.repository;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class StoreUser {

    private int id;
    private String userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean specialProduct;
    private boolean recurring;
    private String token;

}
