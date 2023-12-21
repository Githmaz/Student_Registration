package edu.icet.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Guardians")
public class GuardianEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "Guardian ID")
    private long guardianId;

    @Column(name = "First Name",nullable = false)
    private String firstName;

    @Column(name = "Last Name",nullable = false)
    private String lastName;

    @Column(name = "Phone Number",nullable = false)
    private String phoneNumber;

    @Column(name = "career")
    private String career;

}
