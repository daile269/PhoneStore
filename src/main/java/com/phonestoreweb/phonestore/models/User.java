package com.phonestoreweb.phonestore.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String name;
    private String urlAvatar;
    private String email;
    private String phone;
    private String address;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    @Value("true")
    private Boolean status;

    @ElementCollection
    private Set<String> roles;

    @OneToMany(mappedBy = "userOrder")
    private List<Orders> orders;

    @OneToOne(mappedBy = "userCart")
    private Cart cartUser;
}
