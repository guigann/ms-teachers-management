package com.guigan.ms_teachers_management.infrastructure.rest.instructor_management.feign.mapper;


import com.guigan.ms_teachers_management.core.port.out.dto.TeacherListDtoOutput;
import com.guigan.ms_teachers_management.infrastructure.rest.instructor_management.feign.dto.InstructorList;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class InstructorListToTeacherListOutputMapper {
    public TeacherListDtoOutput responseGetTeacherListMapper(InstructorList instructorList) {
        TeacherListDtoOutput teacherListDtoOutput = new TeacherListDtoOutput();
        if (CollectionUtils.isNotEmpty(instructorList.getInstructors())) {
            teacherListDtoOutput.setTeachers(instructorList.getInstructors().stream().filter(Objects::nonNull).map(instructor -> {
                InstructorToTeacherOutputMapper mapper = new InstructorToTeacherOutputMapper();
                return mapper.responseGetTeacher(instructor);
            }).toList());
        }
        return teacherListDtoOutput;
    }
}
