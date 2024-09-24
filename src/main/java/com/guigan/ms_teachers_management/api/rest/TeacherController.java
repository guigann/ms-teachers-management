package com.guigan.ms_teachers_management.api.rest;

import com.guigan.ms_teachers_management.core.port.in.TeacherPortIn;
import com.guigan.ms_teachers_management.core.port.in.dto.TeacherDto;
import com.guigan.ms_teachers_management.core.port.in.dto.TeacherListDto;
import com.guigan.ms_teachers_management.infrastructure.rest.instructor_management.feign.dto.Instructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherPortIn teacherService;

    @GetMapping
    public ResponseEntity<TeacherListDto> get(
            @RequestHeader(value = "Authorization") String authorization,
            @RequestHeader(value = "Origin") String origin
    ) {
        return ResponseEntity.ok(teacherService.list(authorization, origin));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getById(
            @RequestHeader(value = "Authorization") String authorization,
            @RequestHeader(value = "Origin") String origin,
            @PathVariable(value = "id") Integer id
    ){
        return ResponseEntity.ok(teacherService.list(authorization, origin, id));
    }

    @PostMapping
    public ResponseEntity<TeacherDto> save(
            @RequestHeader(value = "Authorization") String authorization,
            @RequestHeader(value = "Origin") String origin,
            @RequestBody Instructor instructor
    ){
        return new ResponseEntity<TeacherDto>(teacherService.save(authorization, origin, instructor), HttpStatus.CREATED);
    }
}
