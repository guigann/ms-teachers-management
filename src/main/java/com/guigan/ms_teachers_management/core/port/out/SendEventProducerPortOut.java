package com.guigan.ms_teachers_management.core.port.out;

import com.guigan.ms_teachers_management.core.port.out.dto.TeacherDtoOutput;
import com.guigan.ms_teachers_management.core.port.out.dto.TeacherListDtoOutput;

public interface SendEventProducerPortOut {

    void sendTeacherInfoEvent(TeacherListDtoOutput teacher);
}
