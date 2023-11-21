package com.ayush.foodiez.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role",
    uniqueConstraints = @UniqueConstraint(
            name = "role_constraint",
            columnNames = "role_name"
    )
)
public class Role {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "role_name",nullable = false)
    private String roleName;
}
