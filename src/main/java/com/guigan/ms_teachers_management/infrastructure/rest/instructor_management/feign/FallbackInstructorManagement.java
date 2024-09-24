package com.guigan.ms_teachers_management.infrastructure.rest.instructor_management.feign;

import com.guigan.ms_teachers_management.infrastructure.rest.instructor_management.feign.dto.Instructor;
import com.guigan.ms_teachers_management.infrastructure.rest.instructor_management.feign.dto.InstructorList;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Component
public class FallbackInstructorManagement implements FallbackFactory<InstructorManagementFeign> {
    @Override
    public InstructorManagementFeign create(Throwable cause) {
        return new InstructorManagementFeign() {
            @Override
            public InstructorList get(
                    @RequestHeader(value = "Authorization") String authorization,
                    @RequestHeader(value = "origin") String origin) {
                throw new RuntimeException("Internal Server Error - Erro inesperado no processamento da requisição. Por favor, tente novamente em alguns instantes");
            }

            public Instructor getById(
                    @RequestHeader(value = "Authorization") String authorization,
                    @RequestHeader(value = "origin") String origin,
                    @PathVariable(value = "id") Integer id
            ) {
                throw new RuntimeException("Internal Server Error - Erro inesperado no processamento da requisição. Por favor, tente novamente em alguns instantes");
            }

            public Instructor save(
                    @RequestHeader(value = "Authorization") String authorization,
                    @RequestHeader(value = "origin") String origin,
                    @RequestBody Instructor body
            ) {
                throw new RuntimeException("Internal Server Error - Erro inesperado no processamento da requisição. Por favor, tente novamente em alguns instantes");
            }
        };
    }
}
