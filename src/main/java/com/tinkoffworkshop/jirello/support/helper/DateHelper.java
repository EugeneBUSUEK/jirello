package com.tinkoffworkshop.jirello.support.helper;

import lombok.NoArgsConstructor;

import java.util.Date;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class DateHelper {
    public static Date getCurrentUtilDate() {
        return new Date(System.currentTimeMillis());
    }

    public static java.sql.Date getCurrentSQLDate() {
        return new java.sql.Date(System.currentTimeMillis());
    }
}
