package com.devzees.auth.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Author: Zeeshan Adil
 * User:mhmdz
 * Date:26-10-2024
 * Time:15:34
 */

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ROLES")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    private String name;

}