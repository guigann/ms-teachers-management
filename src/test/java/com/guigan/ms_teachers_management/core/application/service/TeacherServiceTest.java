package com.guigan.ms_teachers_management.core.application.service;

import com.guigan.ms_teachers_management.core.port.in.dto.SubjectDto;
import com.guigan.ms_teachers_management.core.port.in.dto.TeacherDto;
import com.guigan.ms_teachers_management.core.port.in.dto.TeacherListDto;
import com.guigan.ms_teachers_management.core.port.out.InstructorManagerPortOut;
import com.guigan.ms_teachers_management.core.port.out.SendEventProducerPortOut;
import com.guigan.ms_teachers_management.core.port.out.dto.SubjectDtoOutput;
import com.guigan.ms_teachers_management.core.port.out.dto.TeacherDtoOutput;
import com.guigan.ms_teachers_management.core.port.out.dto.TeacherListDtoOutput;
import com.guigan.ms_teachers_management.infrastructure.rest.instructor_management.feign.dto.Course;
import com.guigan.ms_teachers_management.infrastructure.rest.instructor_management.feign.dto.Instructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeacherServiceTest {

    @Mock
    private InstructorManagerPortOut instructorManagerPortOut;
    @Mock
    private SendEventProducerPortOut sendEventProducerPortOut;
    @Mock
    private ModelMapper modelMapper;

    private TeacherService service;

    private String AUTHORIZATION;
    private String ORIGIN;
    private Integer ID;
    private TeacherListDtoOutput teacherListDtoOutput;
    private TeacherListDto teacherListDto;
    private TeacherDtoOutput teacherDtoOutput;
    private TeacherDto teacherDto;
    private Instructor instructor;

    @BeforeEach
    void setUp() {
        service = new TeacherService(instructorManagerPortOut, sendEventProducerPortOut,
                modelMapper);

        startMocks();
    }

    @Test
    void shouldReturnAListOfTeachersWhenAuthorizationAndOriginIsProvided() {
        when(instructorManagerPortOut.get(AUTHORIZATION, ORIGIN)).thenReturn(teacherListDtoOutput);
        when(modelMapper.map(teacherListDtoOutput, TeacherListDto.class)).thenReturn(teacherListDto);

        TeacherListDto result = service.list(AUTHORIZATION, ORIGIN);

        assertThat(result).isEqualTo(teacherListDto);
        verify(sendEventProducerPortOut).sendTeacherInfoEvent(teacherListDtoOutput);
    }

    @Test
    void shouldReturnNullWhenNoTeachersAreAvailable() {
        when(instructorManagerPortOut.get(AUTHORIZATION, ORIGIN)).thenReturn(new TeacherListDtoOutput());
        when(modelMapper.map(new TeacherListDtoOutput(), TeacherListDto.class)).thenReturn(new TeacherListDto());

        TeacherListDto result = service.list(AUTHORIZATION, ORIGIN);

        assertThat(result.getTeachers()).isNull();
        verify(sendEventProducerPortOut).sendTeacherInfoEvent(new TeacherListDtoOutput());
    }

    @Test
    void shouldReturnATeacherWhenAuthorizationOriginAndIdIsProvided() {
        when(instructorManagerPortOut.getById(AUTHORIZATION, ORIGIN, ID)).thenReturn(teacherDtoOutput);
        when(modelMapper.map(teacherDtoOutput, TeacherDto.class)).thenReturn(teacherDto);

        TeacherDto result = service.list(AUTHORIZATION, ORIGIN, ID);

        assertThat(result).isEqualTo(teacherDto);
        verify(sendEventProducerPortOut).sendTeacherInfoEvent(teacherDtoOutput);
    }

    @Test
    void shouldReturnNotFoundWhenTeacherIdDoesNotExist() {
        when(instructorManagerPortOut.getById(AUTHORIZATION, ORIGIN, ID)).thenReturn(null);

        TeacherDto result = service.list(AUTHORIZATION, ORIGIN, ID);

        assertThat(result).isNull();
    }

    @Test
    void shouldSaveATeacherWhenAuthorizationOriginAndTheTeacherIsProvided() {
        when(instructorManagerPortOut.save(AUTHORIZATION, ORIGIN, instructor)).thenReturn(teacherDtoOutput);
        when(modelMapper.map(teacherDtoOutput, TeacherDto.class)).thenReturn(teacherDto);

        TeacherDto result = service.save(AUTHORIZATION, ORIGIN, instructor);

        assertThat(result).isEqualTo(teacherDto);
        verify(sendEventProducerPortOut).sendTeacherInfoEvent(teacherDtoOutput);
    }

    private void startMocks(){
        AUTHORIZATION = "Basic dGVzdDp0ZXN0MTIz";
        ORIGIN = "http://localhost:8080";
        ID = 1;

        teacherListDtoOutput = new TeacherListDtoOutput();
        teacherDtoOutput = new TeacherDtoOutput();
        teacherDtoOutput.setId(0);
        teacherDtoOutput.setName("name");
        SubjectDtoOutput subjectDtoOutput = new SubjectDtoOutput();
        subjectDtoOutput.setId(0);
        subjectDtoOutput.setName("name");
        teacherDtoOutput.setSubject(subjectDtoOutput);
        teacherListDtoOutput.setTeachers(List.of(teacherDtoOutput));

        teacherListDto = new TeacherListDto();
        teacherDto = new TeacherDto();
        teacherDto.setId(0);
        teacherDto.setName("name");
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(0);
        subjectDto.setName("name");
        teacherDto.setSubject(subjectDto);
        teacherListDto.setTeachers(List.of(teacherDto));

        instructor = new Instructor();
        instructor.setId(0);
        instructor.setFullName("fullName");
        Course course = new Course();
        course.setId(0);
        course.setTitle("title");
        instructor.setCourse(course);
    }
}
