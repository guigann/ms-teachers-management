package com.guigan.ms_teachers_management.core.application.service;

import com.guigan.ms_teachers_management.core.port.in.TeacherPortIn;
import com.guigan.ms_teachers_management.core.port.in.dto.TeacherDto;
import com.guigan.ms_teachers_management.core.port.in.dto.TeacherListDto;
import com.guigan.ms_teachers_management.core.port.out.InstructorManagerPortOut;
import com.guigan.ms_teachers_management.core.port.out.SendEventProducerPortOut;
import com.guigan.ms_teachers_management.infrastructure.rest.instructor_management.feign.dto.Instructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class TeacherService implements TeacherPortIn {

    private final InstructorManagerPortOut instructorManagerPortOut;
    private final SendEventProducerPortOut sendEventProducerPortOut;
    private final ModelMapper modelMapper;

    @Override
    public TeacherListDto list(String authorization, String origin) {
        log.info("[SERVICE - teacherManangerPortOut.getTeachers] - Executar a chamada da API de TeacherMananger");
        var teacherListOutput = instructorManagerPortOut.get(authorization, origin);
        log.info("[SERVICE - teacherManangerPortOut.getTeachers] - Chamada à API de TeacherMananger realizada com sucesso");

        sendEventProducerPortOut.sendTeacherInfoEvent(teacherListOutput);

        return modelMapper.map(teacherListOutput, TeacherListDto.class);
    }

    @Override
    public TeacherDto list(String authorization, String origin, Integer id) {
        log.info("[SERVICE - teacherManangerPortOut.getTeachers] - Executar a chamada da API de TeacherMananger");
        var teacherOutput = instructorManagerPortOut.getById(authorization, origin, id);
        log.info("[SERVICE - teacherManangerPortOut.getTeachers] - Chamda à API de TeacherMananger realizada com sucesso");

        return modelMapper.map(teacherOutput, TeacherDto.class);
    }

    @Override
    public TeacherDto save(String authorization, String origin, Instructor instructor) {
        log.info("[SERVICE - teacherManangerPortOut.save] - Executar a chamada da API de TeacherMananger");
        var teacherOutput = instructorManagerPortOut.save(authorization, origin, instructor);
        log.info("[SERVICE - teacherManangerPortOut.save] - Chamda à API de TeacherMananger realizada com sucesso");

        return modelMapper.map(teacherOutput, TeacherDto.class);
    }
}
