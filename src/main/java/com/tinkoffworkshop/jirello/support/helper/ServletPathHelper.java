package com.tinkoffworkshop.jirello.support.helper;

import lombok.NoArgsConstructor;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ServletPathHelper {
    public static String getServletPath(WebRequest request) {
        return ((ServletWebRequest) request).getRequest().getServletPath();
    }
}
