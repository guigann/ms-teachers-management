package com.guigan.ms_teachers_management.infrastructure.rest.instructor_management.feign;

import com.guigan.ms_teachers_management.infrastructure.rest.instructor_management.feign.dto.Instructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FallbackInstructorManagementTest {

    private FallbackInstructorManagement fallbackInstructorManagementUnderTest;

    private String AUTHORIZATION;
    private String ORIGIN;
    private String MESSAGE;
    private String EXCEPTION_MESSAGE;

    @BeforeEach
    void setUp() {
        fallbackInstructorManagementUnderTest = new FallbackInstructorManagement();

        startMocks();
    }

    @Test
    void shouldThrowRuntimeExceptionWhenGetIsCalled() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            fallbackInstructorManagementUnderTest.create(new Exception(MESSAGE))
                    .get(AUTHORIZATION, ORIGIN);
        });
        assertEquals(EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    void shouldThrowRuntimeExceptionWhenGetByIdIsCalled() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            fallbackInstructorManagementUnderTest.create(new Exception(MESSAGE))
                    .getById(AUTHORIZATION, ORIGIN, 1);
        });
        assertEquals(EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    void shouldThrowRuntimeExceptionWhenSaveIsCalled() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            fallbackInstructorManagementUnderTest.create(new Exception(MESSAGE))
                    .save(AUTHORIZATION, ORIGIN, new Instructor());
        });
        assertEquals(EXCEPTION_MESSAGE, exception.getMessage());
    }

    private void startMocks() {
        AUTHORIZATION = "Basic dGVzdDp0ZXN0MTIz";
        ORIGIN = "http://localhost:8080";
        MESSAGE = MESSAGE;
        EXCEPTION_MESSAGE = "Internal Server Error - Erro inesperado no processamento da requisição. Por favor, tente novamente em alguns instantes";
    }

}
