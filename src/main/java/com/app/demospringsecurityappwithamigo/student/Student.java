package com.app.demospringsecurityappwithamigo.student;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class Student {

    private final Integer studentId;
    private final String studentName;
}
