package edu.icet.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "image")
public class ProfilePicEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "Profile Pic ID")
    private long profilePicID;

    @Column(name = "Image name")
    private String name;

    @Column(name = "Image type")
    private String type;
    @Lob
    @Column(name = "Profile pic",length = 100000000)
    private byte[] image;

}
