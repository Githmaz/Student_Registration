package edu.icet.utility;

import edu.icet.repository.StudentRepository;

import java.time.LocalDate;
import java.time.Period;

public class StudentUtility {
     private StudentUtility() {
          throw new IllegalStateException("Utility class");
     }

     // duplicate username check
     public static boolean duplicateUsername(String name, StudentRepository studentRepository){
          return studentRepository.existsByUserName(name);
     }

     //check Phone number
     public  static  boolean checkPhoneNumber(String phoneNumber){
          return (phoneNumber.length()==10 && phoneNumber.charAt(0) =='0');
     }

     // Check birthday
     public static boolean checkBirthday(String tempBirthday){
          LocalDate birthday = LocalDate.parse(tempBirthday);
          LocalDate currentDate = LocalDate.now();
          Period age = Period.between(birthday, currentDate);
          return age.getYears() <= 18 && age.getYears()>=6;
     }
}
