package com.example.springtraining.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.Constraint;

@Entity
public class User {
    @Id
    @GeneratedValue
    Long id;
    String username;
    String password;
}
