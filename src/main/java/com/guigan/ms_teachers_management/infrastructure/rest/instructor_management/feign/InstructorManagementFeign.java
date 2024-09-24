package com.guigan.ms_teachers_management.infrastructure.rest.instructor_management.feign;

import com.guigan.ms_teachers_management.infrastructure.rest.instructor_management.feign.dto.Instructor;
import com.guigan.ms_teachers_management.infrastructure.rest.instructor_management.feign.dto.InstructorList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "teacher-management", fallbackFactory = FallbackInstructorManagement.class)
public interface InstructorManagementFeign {

    @GetMapping(value = "/instructors", consumes = "application/json; charset=utf-8")
    InstructorList get(
            @RequestHeader(value = "Authorization") String authorization,
            @RequestHeader(value = "origin") String origin
    );

    @GetMapping(value = "/instructors/{id}", consumes = "application/json; charset=utf-8")
    Instructor getById(
            @RequestHeader(value = "Authorization") String authorization,
            @RequestHeader(value = "origin") String origin,
            @PathVariable(value = "id") Integer id
    );

    @PostMapping(value = "/instructors", consumes = "application/json; charset=utf-8")
    Instructor save(
            @RequestHeader(value = "Authorization") String authorization,
            @RequestHeader(value = "origin") String origin,
            @RequestBody Instructor instructor
    );
}
