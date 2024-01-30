package com.example.userservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@MappedSuperclass
//@Table(name = "t_user")
//@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    private String id;
    private Integer credentialId;
    private String name;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
    private Date birthday;
    private String gender;
    private String tel;
    private String address;
    private String about;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
