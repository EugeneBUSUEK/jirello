package com.tinkoffworkshop.jirello.support.mapper;

import com.tinkoffworkshop.jirello.model.error.ApiError;
import com.tinkoffworkshop.jirello.support.helper.DateHelper;
import com.tinkoffworkshop.jirello.support.helper.ServletPathHelper;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ApiErrorMapper {
    public static ResponseEntity<Object> errorToEntity(HttpStatus status, String message, WebRequest request) {
        ApiError apiError = ApiError.builder()
                .timestamp(DateHelper.getCurrentUtilDate())
                .status(status.value())
                .error(status)
                .message(message)
                .path(ServletPathHelper.getServletPath(request))
                .build();

        return new ResponseEntity<>(apiError, status);
    }
}
