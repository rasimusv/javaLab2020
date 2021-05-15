package ru.itis.rasimusv.rest.dto;

import lombok.Data;

import java.util.List;

@Data
public class CourseDto {
    private String title;
    private List<String> teachers;
}
