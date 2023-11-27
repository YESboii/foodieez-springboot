package com.ayush.foodiez.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user",uniqueConstraints = @UniqueConstraint(name = "unique_constraint",columnNames = {"email","phone_number"}))
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id" )
    private int id;
    @Column(name = "email")
    private String email;
    @Column(name="phone_number")
    private long phoneNumber;
    @Column(name="full_name")
    private String name;

    @Column(name="password")
    private String password;
    @Column(name = "age")
    private int age;
    @ManyToOne(optional = false)
    @JoinColumn(name="role_id",referencedColumnName = "id")
    private Role role;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="city",column = @Column(name = "city")),
            @AttributeOverride(name="pinCode",column = @Column(name="pin_code")),
            @AttributeOverride(name="street",column = @Column(name="street"))
    })
    private Address address;
}
