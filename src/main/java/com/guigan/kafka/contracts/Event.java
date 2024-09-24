package com.guigan.kafka.contracts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    private String uuid;
    private String createdDate;
    private List<Teacher> teachers;

}
