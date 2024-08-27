package com.example.userservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
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
    private String email;
    private String address;
    private String about;
}
