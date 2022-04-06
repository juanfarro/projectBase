package com.jf.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
public class DeveloperDto {

    @NotEmpty(message = "CAN NOT BE EMPTY")
    private String firstName;

    @NotEmpty(message = "CAN NOT BE EMPTY")
    private String lastName;

    @NotEmpty(message = "CAN NOT BE EMPTY")
    private String seniority;
}
