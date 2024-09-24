package com.guigan.ms_teachers_management.core.port.out.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDtoOutput {
    @JsonProperty("id")
    private Integer id = null;

    @JsonProperty("name")
    private String name = null;

}
