package com.guigan.ms_teachers_management.core.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guigan.ms_teachers_management.core.port.in.dto.SalaryDto;
import com.guigan.ms_teachers_management.core.port.in.dto.SubjectDto;
import com.guigan.ms_teachers_management.core.port.in.dto.TeacherDto;
import com.guigan.ms_teachers_management.core.port.out.InstructorManagerPortOut;
import com.guigan.ms_teachers_management.core.port.out.SendEventProducerPortOut;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TeacherServiceTest {

    private static final String AUTHORIZATION = "Basic dGVzdDp0ZXN0MTIz";
    private static final String ORIGIN = "http://localhost:8080";

    private Integer ID;
    private TeacherDto TEACHER;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private TeacherService service;
    @Mock
    private InstructorManagerPortOut instructorManagerPortOut;
    @Mock
    private SendEventProducerPortOut sendEventProducerPortOut;
    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startMocks();
    }

    @Test
    void shouldReturnAListOfTeachersWhenAuthorizationAndOriginIsProvided() {
        try {
            mockMvc.perform(get("/teachers")
                            .header("Authorization", AUTHORIZATION)
                            .header("Origin", ORIGIN))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void shouldReturnATeacherWhenAuthorizationOriginAndIdIsProvided() {
        try {
            mockMvc.perform(get("/teachers/{id}", ID)
                            .header("Authorization", AUTHORIZATION)
                            .header("Origin", ORIGIN))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(ID));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldSaveATeacherWhenAuthorizationOriginAndTheTeacherIsProvided() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            mockMvc.perform(post("/teachers")
                            .header("Authorization", AUTHORIZATION)
                            .header("Origin", ORIGIN)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(TEACHER)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").value(TEACHER.getId()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void startMocks() {
        ID = 1;
        TEACHER = new TeacherDto(1, "John Doe", new SubjectDto(101, "Mathematics"), new SalaryDto(55000f, "USD"));
    }
}