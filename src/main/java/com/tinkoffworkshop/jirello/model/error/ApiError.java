package com.tinkoffworkshop.jirello.model.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tinkoffworkshop.jirello.support.constant.DateFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    @JsonFormat(shape = STRING, pattern = DateFormat.API_ERROR_DATE_FORMAT)
    private Date timestamp;
    private Integer status;
    private HttpStatus error;
    private String message;
    private String path;
}
