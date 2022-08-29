package com.api.administration.models.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor(staticName = "getInstance")
@NoArgsConstructor
public class ErrorDetail {
    private Integer httpStatus;
    private String message;
    private long timestamp;
    private Map<String, List<ValidationError>> fields;
}
