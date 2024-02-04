package com.kdu.smartHome.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    private String userName;

    private String password;

    private String name;

    private String firstName;

    private String lastName;

    private String emailId;


}
//username (Type: String) - The username of the user.
//● password (Type: String) - The password for the user.
//        ● name (Type: String) - The full name of the user.
//        ● firstName (Type: String) - The first name of the user.
//        ● lastName (Type: String) - The last name of the user.
//        ● emailId (Type: String) - The email ID of the user.