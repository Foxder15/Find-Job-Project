package com.foxdev.project.findJobProject.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(unique = true, nullable = false)
    String name;
    @Column(columnDefinition = "MEDIUMTEXT")
    String description;
    String address;
    String logo;
    Instant createdAt;
    Instant updatedAt;
    String createdBy;
    String updatedBy;
}
