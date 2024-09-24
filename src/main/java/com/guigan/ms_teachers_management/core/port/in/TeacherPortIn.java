package com.guigan.ms_teachers_management.core.port.in;

import com.guigan.ms_teachers_management.core.port.in.dto.TeacherDto;
import com.guigan.ms_teachers_management.core.port.in.dto.TeacherListDto;
import com.guigan.ms_teachers_management.infrastructure.rest.instructor_management.feign.dto.Instructor;

public interface TeacherPortIn {
    TeacherListDto list(String authorization, String origin);
    TeacherDto list(String authorization, String origin, Integer id);
    TeacherDto save(String authorization, String origin, Instructor instructor);
}
