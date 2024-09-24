package com.guigan.ms_teachers_management.infrastructure.event.kafka.mapper;

import com.guigan.kafka.contracts.Event;
import com.guigan.kafka.contracts.Teacher;
import com.guigan.ms_teachers_management.core.port.out.dto.TeacherListDtoOutput;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class KafkaMapper {
    private static final String PHYSICS = "Physics";
    private static final String MATHEMATICS = "Mathematics";

    private final ModelMapper modelMapper = new ModelMapper();

    public Event createEvent(TeacherListDtoOutput teacherList) {
        Event event = new Event();

        event.setUuid(UUID.randomUUID().toString());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        event.setCreatedDate(formatter.format(LocalDateTime.now()));

        event.setTeachers(filterListByPhysicsAndMathematicsSubject(teacherList));
        return event;
    }

    private List<Teacher> filterListByPhysicsAndMathematicsSubject(TeacherListDtoOutput teacherListDtoOutput) {
        return teacherListDtoOutput.getTeachers()
                .stream()
                .filter(teacher -> {
                    var isPhysics = teacher.getSubject().getName().equalsIgnoreCase(PHYSICS);
                    var isMathematics = teacher.getSubject().getName().equalsIgnoreCase(MATHEMATICS);
                    return isPhysics || isMathematics;
                })
                .map(teacher -> modelMapper.map(teacher, Teacher.class)).toList();
    }

}
