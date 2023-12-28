package edu.icet.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.dto.Student;
import edu.icet.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;



import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();

    @Test
    void testAddUserSuccess() throws Exception {
        // Arrange
        Student mockStudent = new Student(); // You may need to initialize this with relevant data
        mockStudent.setStudentId(1L);

        when(studentService.addUser(mockStudent)).thenReturn(mockStudent);

        // Act and Assert
        mockMvc.perform(post("/SignUp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockStudent)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.Message").value("Student added successfully"))
                .andExpect(jsonPath("$.studentId").value(1));
    }

    @Test
    void testAddUserFailure() throws Exception {
        // Arrange
        Student mockStudent = new Student(); // You may need to initialize this with relevant data

        // Assuming the service method returns null for failure
        when(studentService.addUser(mockStudent)).thenReturn(null);

        // Act and Assert
        mockMvc.perform(post("/SignUp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockStudent)))
                .andExpect(status().isOk()) // Adjust the expected status code for failure
                .andExpect(jsonPath("$.Message").value("Failed to add student"));
    }

    // Add more test methods for different scenarios if needed
}
