package edu.icet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Guardian {
    private long guardianId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String career;


}
