package com.guigan.ms_teachers_management.infrastructure.rest.instructor_management.feign.mapper;

import com.guigan.ms_teachers_management.core.port.out.dto.SalaryDtoOutput;
import com.guigan.ms_teachers_management.core.port.out.dto.SubjectDtoOutput;
import com.guigan.ms_teachers_management.core.port.out.dto.TeacherDtoOutput;
import com.guigan.ms_teachers_management.infrastructure.rest.instructor_management.feign.dto.Instructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InstructorToTeacherOutputMapper {
    public TeacherDtoOutput responseGetTeacher(Instructor instructor) {
        TeacherDtoOutput teacherDtoOutput = new TeacherDtoOutput();
        if (instructor != null) {
            teacherDtoOutput.setId(instructor.getId());
            teacherDtoOutput.setName(instructor.getFullName());

            SalaryDtoOutput salaryDtoOutput = new SalaryDtoOutput();
            salaryDtoOutput.setAmount(instructor.getWage().getTotal());
            salaryDtoOutput.setCurrency(instructor.getWage().getCurrency());

            SubjectDtoOutput subjectDtoOutput = new SubjectDtoOutput();
            subjectDtoOutput.setId(instructor.getId());
            subjectDtoOutput.setName(instructor.getCourse().getTitle());

            teacherDtoOutput.setSalary(salaryDtoOutput);
            teacherDtoOutput.setSubject(subjectDtoOutput);

            return teacherDtoOutput;
        }
        return teacherDtoOutput;
    }
}
