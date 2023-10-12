package edu.icet.controllers;

import edu.icet.dto.Student;
import edu.icet.service.StudentService;
import edu.icet.utility.StudentUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/Student")
public class StudentController {

    @Autowired
    StudentService service;

    //------------------ Add a new student ------------------//
    @PostMapping("/SignUp")
    public long addUser(@RequestBody Student tempStudent) throws IOException {
        return service.addUser(tempStudent).getStudentId();

    }


    //---------------- Set profile picture -----------------//
    @PostMapping("/SetProfilePic")
    public void setProfilePic(@RequestParam("image") MultipartFile file, @RequestParam long id) throws IOException {
        service.uploadImage(id, file);
    }

    //---------------- Check phone number -------------------//
    @GetMapping("/CheckPhoneNumber")
    public boolean checkPhoneNumber(@RequestParam String phoneNumber) {
        return StudentUtility.checkPhoneNumber(phoneNumber);
    }

    //---------------- Check duplicate username -------------//
    @GetMapping("/CheckDuplicateUsername")
    public boolean checkDuplicateUsername(@RequestParam String username) {
        return service.duplicateUsername(username);
    }

    //----------------- Check birthday ----------------------//
    @GetMapping("/CheckBirthdayValidation")
    public boolean checkBirthday(@RequestParam String birthday) {
        return service.checkBirthday(birthday);
    }

    //------------------- Student login ----------------------//
    @GetMapping("/Login")
    public Student login(@RequestParam String username, @RequestParam String password) {
        return service.studentLoginCheck(username, password);
    }

    //------------------- List all students -------------------//
    @GetMapping("/List")
    public List<Student> table() {
        return service.getAllUsers();
    }

    //---------------- Get profile picture ---------------------//
    @GetMapping("/Image")
    public ResponseEntity<byte[]> getProfilePic(@RequestParam(name = "id", required = true) long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(service.getProfilePic(id));
    }

    //---------------- Get student by ID -----------------------//
    @GetMapping("/getStudent")
    public Student getStudent(@RequestParam long id) {
        return service.getStudentById(id);
    }

}
