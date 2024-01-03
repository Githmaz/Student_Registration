package edu.icet.controllers;

import edu.icet.dto.Student;
import edu.icet.dto.response.StudentResponse;
import edu.icet.service.StudentService;
import edu.icet.utility.StudentUtility;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/Student")
public class StudentController {

    @Autowired
    StudentService service;

    //------------------ Add a new student ------------------//
    @PostMapping("/SignUp")
    @Operation(summary = "Add a new student", description = "Endpoint to add a new student.")
    public ResponseEntity<Map<String, Object>> addUser(@RequestBody Student tempStudent) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "Message", "Student added successfully",
                "studentId", service.addUser(tempStudent).getStudentId()
        ));
    }


    //---------------- Set profile picture -----------------//
    @PostMapping("/SetProfilePic")
    @Operation(summary = "Set profile picture", description = "Endpoint to set the profile picture of a student.")
    public ResponseEntity<Object> setProfilePic(@Parameter(description = "Image file", required = true)@RequestParam("image") MultipartFile file, @RequestParam long id) {
        try {
            service.uploadImage(id, file);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading the profile picture.");
        }
    }
    //---------------- Check phone number -------------------//
    @GetMapping("/CheckPhoneNumber")
    @Operation(summary = "Check phone number", description = "Endpoint to check the validity of a phone number.")
    public boolean checkPhoneNumber(@RequestParam String phoneNumber) {return StudentUtility.checkPhoneNumber(phoneNumber);
    }

    //---------------- Check duplicate username -------------//
    @GetMapping("/CheckDuplicateUsername")
    @Operation(summary = "Check duplicate username", description = "Endpoint to check if a username already exists.")
    public boolean checkDuplicateUsername(@RequestParam String username) {
        return service.duplicateUsername(username);
    }

    //----------------- Check birthday ----------------------//
    @GetMapping("/CheckBirthdayValidation")
    @Operation(summary = "Check birthday validation", description = "Endpoint to check the validity of a birthday.")
    public boolean checkBirthday(@RequestParam String birthday) {
        return service.checkBirthday(birthday);
    }

    //------------------- Student login ----------------------//
    @GetMapping("/Login")
    @Operation(summary = "Student login", description = "Endpoint for student login.")
    public Student login(@RequestParam String username, @RequestParam String password) {
        return service.studentLoginCheck(username, password);
    }

    //------------------- List all students -------------------//
    @GetMapping("/List")
    @Operation(summary = "List all students", description = "Endpoint to retrieve a list of all students.")
    public StudentResponse table() {
        return  StudentResponse.builder()
                .studentList(service.getAllUsers())
                .build();
    }

    //---------------- Get profile picture ---------------------//
    @GetMapping("/Image")
    @Operation(summary = "Get profile picture", description = "Endpoint to retrieve the profile picture of a student.")
    public ResponseEntity<byte[]> getProfilePic(@RequestParam(name = "id", required = true) long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(service.getProfilePic(id));
    }

    //---------------- Get student by ID -----------------------//
    @GetMapping("/getStudent")
    @Operation(summary = "Get student by ID", description = "Endpoint to retrieve a student by ID.")
    public Student getStudent(@RequestParam long id) {
        return service.getStudentById(id);
    }

}
