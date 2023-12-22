package edu.icet.controllers;

import edu.icet.dto.Student;
import edu.icet.dto.response.StudentResponse;
import edu.icet.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTable() {
        // Arrange
        List<Student> mockStudentList = Arrays.asList(
                new Student(1, "John", "Doe", "john.doe", "john@example.com", "0123456789", "123 Main St", "password", "2000-01-01", null, null),
                new Student(2, "Jane", "Doe", "jane.doe", "jane@example.com", "9876543210", "456 Oak St", "password", "1998-05-15", null, null)
        );

        when(studentService.getAllUsers()).thenReturn(mockStudentList);

        // Act
        StudentResponse result = studentController.table();

        // Assert
        assertEquals(mockStudentList, result.getStudentList(), "Student lists should match");

        // Verify that the getAllUsers method is called once
        verify(studentService, times(1)).getAllUsers();
    }
}