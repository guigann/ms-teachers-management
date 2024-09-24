package com.guigan.ms_teachers_management.core.port.out.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherListDtoOutput {
    @JsonProperty("teachers")
    private List<TeacherDtoOutput> teachers = null;

}
