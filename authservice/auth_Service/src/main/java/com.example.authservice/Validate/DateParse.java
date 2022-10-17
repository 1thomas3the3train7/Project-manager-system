package com.example.authservice.Validate;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DateParse {
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public Date parseStandartFormat(final String date) throws ParseException {
        return simpleDateFormat.parse(date);
    }
}
