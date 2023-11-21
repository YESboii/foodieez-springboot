package com.ayush.foodiez.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vendor",uniqueConstraints = @UniqueConstraint(name = "email_constraint"
,columnNames = "email"))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "shop_name")
    private String shopName;

    @Column(name = "name")
    private String name;


    @Column(name = "speciality")
    private String speciality;

    @Column(name = "email",nullable = false)
    private String email;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id",referencedColumnName = "id")
    private Role role;

    @OneToMany(mappedBy = "vendor",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Category> categories = new ArrayList<>();
}
