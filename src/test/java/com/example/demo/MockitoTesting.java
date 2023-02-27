/*
package com.example.demo;

import com.example.demo.patient.Patient;
import com.example.demo.patient.PatientRepository;
import com.example.demo.patient.PatientService;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import com.example.demo.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MockitoTesting {
    @Autowired
    private UserService service;
    private PatientService patientService;
    private UserRepository repository;
    private PatientRepository patientRepository;

    @Test
    public void getUsersTest(){
        when(repository.findAll()).thenReturn(
                Stream.of(
                        new User("salim21",
                        "s123alim!"),
                        new User("khaoula29", "k07haoula!"))
                        .collect((Collectors.toList())));
        assertEquals(2, service.getUsers().size());

    }

    @Test
    public void getPatientsTest(){
        when(patientRepository.findAll()).thenReturn(
                Stream.of(
                                new Patient("Jake",
                                        "jake@gmail.com!",
                                        LocalDate.of(1998, 9, 04)),
                                new Patient("Jane",
                                        "jane@gmail.com!",
                                        LocalDate.of(2000, 4, 29)))
                        .collect((Collectors.toList())));
        assertEquals(2, patientService.getPatients().size());

    }



}
*/
