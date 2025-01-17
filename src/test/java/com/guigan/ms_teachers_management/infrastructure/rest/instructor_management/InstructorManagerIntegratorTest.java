package com.guigan.ms_teachers_management.infrastructure.rest.instructor_management;

import com.guigan.ms_teachers_management.core.port.out.dto.SubjectDtoOutput;
import com.guigan.ms_teachers_management.core.port.out.dto.TeacherDtoOutput;
import com.guigan.ms_teachers_management.core.port.out.dto.TeacherListDtoOutput;
import com.guigan.ms_teachers_management.infrastructure.rest.instructor_management.feign.InstructorManagementFeign;
import com.guigan.ms_teachers_management.infrastructure.rest.instructor_management.feign.dto.Course;
import com.guigan.ms_teachers_management.infrastructure.rest.instructor_management.feign.dto.Instructor;
import com.guigan.ms_teachers_management.infrastructure.rest.instructor_management.feign.dto.InstructorList;
import com.guigan.ms_teachers_management.infrastructure.rest.instructor_management.feign.mapper.InstructorListToTeacherListOutputMapper;
import com.guigan.ms_teachers_management.infrastructure.rest.instructor_management.feign.mapper.InstructorToTeacherOutputMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InstructorManagerIntegratorTest {

    @Mock
    private InstructorListToTeacherListOutputMapper instructorListToTeacherListOutputMapper;
    @Mock
    private InstructorToTeacherOutputMapper instructorToTeacherOutputMapper;
    @Mock
    private InstructorManagementFeign feign;

    private InstructorManagerIntegrator instructorManagerIntegratorUnderTest;

    private String AUTHORIZATION;
    private String ORIGIN;
    private TeacherListDtoOutput teacherListDtoOutput;
    private TeacherDtoOutput teacherDtoOutput;
    private Instructor instructor;
    private InstructorList instructorList;

    @BeforeEach
    void setUp() {
        instructorManagerIntegratorUnderTest = new InstructorManagerIntegrator(
                instructorListToTeacherListOutputMapper, instructorToTeacherOutputMapper, feign);

        startMocks();
    }

    @Test
    void shouldReturnTeacherListWhenGetIsCalled() {
        when(feign.get(AUTHORIZATION, ORIGIN)).thenReturn(instructorList);
        when(instructorListToTeacherListOutputMapper.responseGetTeacherListMapper(instructorList))
                .thenReturn(teacherListDtoOutput);

        TeacherListDtoOutput result = instructorManagerIntegratorUnderTest.get(AUTHORIZATION, ORIGIN);

        assertThat(result).isEqualTo(teacherListDtoOutput);
    }

    @Test
    void shouldReturnTeacherWhenGetByIdIsCalled() {
        when(feign.getById(AUTHORIZATION, ORIGIN, 0)).thenReturn(instructor);
        when(instructorToTeacherOutputMapper.responseGetTeacher(instructor)).thenReturn(teacherDtoOutput);

        TeacherDtoOutput result = instructorManagerIntegratorUnderTest.getById(AUTHORIZATION, ORIGIN, 0);

        assertThat(result).isEqualTo(teacherDtoOutput);
    }

    @Test
    void shouldSaveTeacherWhenSaveIsCalled() {
        when(feign.save(AUTHORIZATION, ORIGIN, instructor)).thenReturn(instructor);
        when(instructorToTeacherOutputMapper.responseGetTeacher(instructor)).thenReturn(teacherDtoOutput);

        TeacherDtoOutput result = instructorManagerIntegratorUnderTest.save(AUTHORIZATION, ORIGIN, instructor);

        assertThat(result).isEqualTo(teacherDtoOutput);
    }

    private void startMocks() {
        AUTHORIZATION = "Basic dGVzdDp0ZXN0MTIz";
        ORIGIN = "http://localhost:8080";
        
        teacherListDtoOutput = new TeacherListDtoOutput();
        teacherDtoOutput = new TeacherDtoOutput();
        teacherDtoOutput.setId(0);
        teacherDtoOutput.setName("name");
        SubjectDtoOutput subjectDtoOutput = new SubjectDtoOutput();
        subjectDtoOutput.setId(0);
        subjectDtoOutput.setName("name");
        teacherDtoOutput.setSubject(subjectDtoOutput);
        teacherListDtoOutput.setTeachers(List.of(teacherDtoOutput));

        instructor = new Instructor();
        instructor.setId(0);
        instructor.setFullName("fullName");
        Course course = new Course();
        course.setId(0);
        course.setTitle("title");
        instructor.setCourse(course);

        instructorList = new InstructorList();
        instructorList.setInstructors(List.of(instructor));
    }
}