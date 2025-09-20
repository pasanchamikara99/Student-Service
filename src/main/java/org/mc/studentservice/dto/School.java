package org.mc.studentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class School {
    private int id;
    private String schoolName;
    private String location;
    private String principalName;
}
