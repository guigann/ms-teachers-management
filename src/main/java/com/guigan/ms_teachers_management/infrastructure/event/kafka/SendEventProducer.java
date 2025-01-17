package com.guigan.ms_teachers_management.infrastructure.event.kafka;

import com.guigan.kafka.contracts.Event;
import com.guigan.ms_teachers_management.core.port.out.SendEventProducerPortOut;
import com.guigan.ms_teachers_management.core.port.out.dto.TeacherDtoOutput;
import com.guigan.ms_teachers_management.core.port.out.dto.TeacherListDtoOutput;
import com.guigan.ms_teachers_management.infrastructure.event.kafka.mapper.KafkaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
public class SendEventProducer implements SendEventProducerPortOut {

    private final KafkaTemplate<String, Event> kafkaTemplate;

    private final KafkaMapper mapper;

    @Value("${spring.kafka.topics.teacher-info}")
    private String topicName;

    @Override
    public void sendTeacherInfoEvent(TeacherListDtoOutput teacher) {

        Event event = mapper.createEvent(teacher);

        this.writeEventOnTopic(event);
    }

    @Override
    public void sendTeacherInfoEvent(TeacherDtoOutput teacher) {
        TeacherListDtoOutput teacherList = new TeacherListDtoOutput();
        teacherList.setTeachers(List.of(teacher));

        Event event = mapper.createEvent(teacherList);
        this.writeEventOnTopic(event);
    }

    private void writeEventOnTopic(Event event) {
        try {
            kafkaTemplate.send(produceIn(event));
            log.info("[KAFKA PRODUCER - TEACHER INFO] - EVENTO POSTADO " + event);
        } catch (RuntimeException e) {
            log.error("[KAFKA PRODUCER - TEACHER INFO] - ERRO");
            log.error("[KAFKA PRODUCER - TEACHER INFO] - ERRO: " + e.getMessage());
            log.error("[KAFKA PRODUCER - TEACHER INFO] - ERRO STACK: " + e.getStackTrace());
            throw e;
        }
    }

    private ProducerRecord<String, Event> produceIn(Event event) {
        return new ProducerRecord<>(this.topicName, event);
    }
}
