package com.jf.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class ProjectDto {

    @NotEmpty(message = "CAN NOT BE EMPTY")
    private String name;

    @NotEmpty(message = "CAN NOT BE EMPTY")
    private String technology;

    @NotNull(message = "CAN NOT BE NULL")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Future(message = "MUST BE IN THE FUTURE")
    private Date delivery;
}
