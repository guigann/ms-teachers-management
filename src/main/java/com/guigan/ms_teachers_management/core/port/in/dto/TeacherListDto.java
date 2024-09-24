package com.guigan.ms_teachers_management.core.port.in.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherListDto {
    @JsonProperty("teachers")
    private List<TeacherDto> teachers = null;

}
