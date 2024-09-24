package com.guigan.ms_teachers_management.infrastructure.rest.instructor_management;

import com.guigan.ms_teachers_management.core.port.out.InstructorManagerPortOut;
import com.guigan.ms_teachers_management.core.port.out.dto.TeacherDtoOutput;
import com.guigan.ms_teachers_management.core.port.out.dto.TeacherListDtoOutput;
import com.guigan.ms_teachers_management.infrastructure.rest.instructor_management.feign.InstructorManagementFeign;
import com.guigan.ms_teachers_management.infrastructure.rest.instructor_management.feign.dto.Instructor;
import com.guigan.ms_teachers_management.infrastructure.rest.instructor_management.feign.mapper.InstructorListToTeacherListOutputMapper;
import com.guigan.ms_teachers_management.infrastructure.rest.instructor_management.feign.mapper.InstructorToTeacherOutputMapper;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RequiredArgsConstructor
@Component
public class InstructorManagerIntegrator implements InstructorManagerPortOut {

    private final InstructorListToTeacherListOutputMapper instructorListToTeacherListOutputMapper;
    private final InstructorToTeacherOutputMapper instructorToTeacherOutputMapper;
    private final InstructorManagementFeign feign;

    @Override
    public TeacherListDtoOutput get(String authorization, String origin) {
        log.info("[ADAPTER OUT - TeacherManagerIntegrator.get] - Enviando a requisição para a operacao get da API Teachers Management");
        try {
            var response = feign.get(authorization, origin);
            log.info("[ADAPTER OUT - TeacherManagerIntegrator.get] - Chamada a operacao get da API Teachers Management realizada com sucesso");

            return instructorListToTeacherListOutputMapper.responseGetTeacherListMapper(response);
        } catch (FeignException e) {
            log.error("[ADAPTER OUT - TeacherManagerIntegrator.get] - Falha na chamada da operacao get da API Teachers Management. erro: {}", e.getMessage());
            throw new RuntimeException("Falha na chamada da operacao get da API Teachers Management", e.getCause());
        }
    }

    @Override
    public TeacherDtoOutput getById(String authorization, String origin, Integer id) {
        log.info("[ADAPTER OUT - TeacherManagerIntegrator.getbyId] - Enviando a requisição para a operacao getbyId da API Teachers Management");
        try {
            var response = feign.getById(authorization, origin, id);
            log.info("[ADAPTER OUT - TeacherManagerIntegrator.getbyId] - Chamada a operacao getbyId da API Teachers Management realizada com sucesso");

            return instructorToTeacherOutputMapper.responseGetTeacher(response);
        } catch (FeignException e) {
            log.error("[ADAPTER OUT - TeacherManagerIntegrator.getbyId] - Falha na chamada da operacao getbyId da API Teachers Management. erro: {}", e.getMessage());
//            throw new RuntimeException("Falha na chamada da operacao getbyId da API Teachers Management");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found");
        }
    }

    @Override
    public TeacherDtoOutput save(String authorization, String origin, Instructor instructor) {
        log.info("[ADAPTER OUT - TeacherManagerIntegrator.save] - Enviando a requisição para a operacao save da API Teachers Management");
        try {
            var response = feign.save(authorization, origin, instructor);
            log.info("[ADAPTER OUT - TeacherManagerIntegrator.save] - Chamada a operacao save da API Teachers Management realizada com sucesso");

            return instructorToTeacherOutputMapper.responseGetTeacher(response);
        } catch (FeignException e) {
            log.error("[ADAPTER OUT - TeacherManagerIntegrator.save] - Falha na chamada da operacao save da API Teachers Management. erro: {}", e.getMessage());
            throw new RuntimeException("Falha na chamada da operacao save da API Teachers Management");
        }
    }
}
