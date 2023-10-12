package edu.icet.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Students")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Student ID")
    private long studentId;

    @Column(name = "First Name", nullable = false)
    private String firstName;

    @Column(name = "Last Name", nullable = false)
    private String lastName;

    @Column(name = "Username", nullable = false, unique = true)
    private String userName;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "Phone Number", nullable = false)
    private String phoneNumber;

    @Column(name = "Address")
    private String address;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "Birthday", nullable = false)
    private String birthday;

    @OneToOne
    @JoinColumn(name = "Guardian ID")
    private GuardianEntity guardian;

    @OneToOne
    @JoinColumn(name = "Profile Pic ID")
    private ProfilePicEntity profilePic;


}
