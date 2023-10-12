package edu.icet.service.impl;

import edu.icet.dto.ProfilePic;
import edu.icet.service.ProfilePicService;
import edu.icet.dao.StudentEntity;
import edu.icet.dto.Guardian;
import edu.icet.dto.Student;
import edu.icet.repository.StudentRepository;
import edu.icet.service.GuardianService;
import edu.icet.service.StudentService;
import edu.icet.utility.ProfilePicUtility;
import edu.icet.utility.StudentUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private GuardianService guardianService;

    @Autowired
    private ProfilePicService profilePicService;

    //---------------------- add a new user -------------------------//
    @Override
    public StudentEntity addUser(Student student) throws IOException {
        return studentRepository.save(new StudentEntity(student.getId(),student.getFirstName(), student.getLastName(), student.getUserName(), student.getEmail(),student.getPhoneNumber(),student.getAddress() ,student.getPassword(), student.getBirthday(),guardianService.createGuardian(student.getGuardian()),profilePicService.getDefaultProfilePic()));
    }

    //--------------- upload a user's profile picture --------------//
    @Override
    public void uploadImage(long id,MultipartFile file) throws IOException {
        studentRepository.updateProfilePic(id, profilePicService.createImage(file));
    }

    //--------------------- get all students -----------------------//
    @Override
    public List<Student>  getAllUsers() {

        Iterable<StudentEntity> studentEntities = studentRepository.findAll();

        List<Student> studentList = new ArrayList<>();

        for (StudentEntity studentEntity : studentEntities) {
            studentList.add(new Student(studentEntity.getStudentId(), studentEntity.getFirstName(), studentEntity.getLastName(), studentEntity.getUserName(), studentEntity.getEmail(),studentEntity.getPhoneNumber(),studentEntity.getAddress(), null, studentEntity.getBirthday(),new Guardian(studentEntity.getGuardian().getGuardianId(),studentEntity.getGuardian().getFirstName(),studentEntity.getGuardian().getLastName(),studentEntity.getGuardian().getPhoneNumber(),studentEntity.getGuardian().getCareer()),new ProfilePic(studentEntity.getProfilePic().getProfilePicID(),studentEntity.getProfilePic().getName(),studentEntity.getProfilePic().getType(), ProfilePicUtility.decompressImage(studentEntity.getProfilePic().getImage()))));
        }

        return studentList;
    }

    //--------------- get a user's profile picture ---------------//
    @Override
    public  byte[] getProfilePic(long id){
        return ProfilePicUtility.decompressImage(studentRepository.findBystudentId(id).getProfilePic().getImage());
    }


    //--------------- check for duplicate username ---------------//
    @Override
    public  boolean duplicateUsername(String username){
        return StudentUtility.duplicateUsername(username,studentRepository);
    }

    //---------------------- check birthday ---------------------//
    @Override
    public boolean checkBirthday(String birthday) {
        return StudentUtility.checkBirthday(birthday);
    }

    //--------------- check username and password ---------------//
    @Override
    public Student studentLoginCheck(String username, String password) {
        StudentEntity studentEntity =  studentRepository.findByUserName(username);
        return (studentEntity.getPassword().equals(password)) ? new Student(studentEntity.getStudentId(), studentEntity.getFirstName(), studentEntity.getLastName(), studentEntity.getUserName(), studentEntity.getEmail(),studentEntity.getPhoneNumber(),studentEntity.getAddress(),studentEntity.getPassword(), studentEntity.getBirthday(),new Guardian(studentEntity.getGuardian().getGuardianId(),studentEntity.getGuardian().getFirstName(),studentEntity.getGuardian().getLastName(),studentEntity.getGuardian().getPhoneNumber(),studentEntity.getGuardian().getCareer()),new ProfilePic(studentEntity.getProfilePic().getProfilePicID(),studentEntity.getProfilePic().getName(),studentEntity.getProfilePic().getType(),studentEntity.getProfilePic().getImage())): new Student();

    }

    //------------------ get a student by ID --------------------//
    @Override
    public Student getStudentById(Long id) {
        StudentEntity studentEntity =  studentRepository.findBystudentId(id);
        return new Student(studentEntity.getStudentId(), studentEntity.getFirstName(), studentEntity.getLastName(), studentEntity.getUserName(), studentEntity.getEmail(),studentEntity.getPhoneNumber(),studentEntity.getAddress(),null, studentEntity.getBirthday(),new Guardian(studentEntity.getGuardian().getGuardianId(),studentEntity.getGuardian().getFirstName(),studentEntity.getGuardian().getLastName(),studentEntity.getGuardian().getPhoneNumber(),studentEntity.getGuardian().getCareer()),new ProfilePic(studentEntity.getProfilePic().getProfilePicID(),studentEntity.getProfilePic().getName(),studentEntity.getProfilePic().getType(),ProfilePicUtility.decompressImage(studentEntity.getProfilePic().getImage())));
    }


}
