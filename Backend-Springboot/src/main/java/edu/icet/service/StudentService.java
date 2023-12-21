package edu.icet.service;

import edu.icet.dao.StudentEntity;
import edu.icet.dto.Student;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface StudentService {
    StudentEntity addUser(Student customer) throws IOException;
    void uploadImage(long id ,MultipartFile file) throws IOException;
    List<Student> getAllUsers();

    byte[] getProfilePic(long id);
    boolean duplicateUsername(String username);

    boolean checkBirthday(String birthday);

    Student studentLoginCheck(String username, String password);

    Student getStudentById(Long id);


}
