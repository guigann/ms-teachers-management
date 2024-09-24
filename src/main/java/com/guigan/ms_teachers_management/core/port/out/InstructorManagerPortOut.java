package com.guigan.ms_teachers_management.core.port.out;

import com.guigan.ms_teachers_management.core.port.out.dto.TeacherDtoOutput;
import com.guigan.ms_teachers_management.core.port.out.dto.TeacherListDtoOutput;
import com.guigan.ms_teachers_management.infrastructure.rest.instructor_management.feign.dto.Instructor;

public interface InstructorManagerPortOut {
    TeacherListDtoOutput get(String authorization, String origin);
    TeacherDtoOutput getById(String authorization, String origin, Integer id);
    TeacherDtoOutput save(String authorization, String origin, Instructor instructor);
}
