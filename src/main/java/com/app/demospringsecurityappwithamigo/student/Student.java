package com.app.demospringsecurityappwithamigo.student;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@RequiredArgsConstructor
@ToString
public class Student {

    private final Integer studentId;
    private final String studentName;
}
