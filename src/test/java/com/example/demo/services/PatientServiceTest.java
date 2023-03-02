package com.example.demo.services;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import com.example.demo.patient.Patient;
import com.example.demo.patient.PatientRepository;
import com.example.demo.patient.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)

// Main class
class PatientServiceTest {

    @Mock private PatientRepository patientRepository;

    private PatientService patientService;

    Patient patient1 = new Patient("khaoula", "khaoula@gmail.com", LocalDate.of(2000, 7, 29));
    Patient patient2 = new Patient("Salim", "salim@gmail.com", LocalDate.of(1995, 8, 20));
    Patient patient3 = new Patient("Jake", "jake@gmail.com", LocalDate.of(2005, 2, 12));
    @BeforeEach void setUp()
    {
        this.patientService
                = new PatientService(this.patientRepository);
    }

    @Test void getAllPatients()
    {
        patientService.getPatients();
        verify(patientRepository).findAll();
    }

    @Test void getPatientById_WhenExistingId_ThenReturnPatient()
    {
        patientService.getPatient(1L);
        verify(patientRepository).findById(1L);
    }

    @Test void addNewPatients_WhenEmailTaken_Then_ThrowException(){

        Patient khaoula = new Patient ("khaoula", "omar@gmail.com", LocalDate.of(2000, 03, 04));
        when(patientRepository.findPatientByEmail(eq("omar@gmail.com"))).thenReturn(Optional.of(khaoula));
        assertThrows(IllegalStateException.class, ()-> patientService.addNewPatient(khaoula));

    }

    @Test void addNewPatients_WhenEmailIsNotTaken_Then_AddPatient(){
        Patient khaoula = new Patient ("khaoula", "khaoula@gmail.com", LocalDate.of(2000, 03, 04));
        patientService.addNewPatient(khaoula);
        verify(patientRepository).save(khaoula);

    }

    @Test void deletePatient_WhenNonExistentId_Then_ThrowException(){
        Long patientId = 4L;
        assertThrows(IllegalStateException.class, () -> patientService.deletePatient(patientId));
    }

    @Test void deletePatient_WhenExistentId_Then_Delete(){
        Patient khaoula = new Patient ("khaoula", "omar@gmail.com", LocalDate.of(2000, 03, 04));
        when(patientRepository.existsById(eq(1L))).thenReturn(true);
        patientService.deletePatient(1L);
        verify(patientRepository).deleteById(1L);
    }

    @Test void updatePatient_WhenNonExistentId_Then_ThrowException(){
        Long patientId = 4L;
        assertThrows(IllegalStateException.class, () -> patientService.updatePatient(patientId, "test", "test@gmail.com"));
    }

    @Test void updatePatient_WhenNameIsNullAndEmailIsNull_Then_DontUpdatePatient(){
        Patient khaoula = new Patient ("khaoula", "khaoula@gmail.com", LocalDate.of(2000, 03, 04));
        when(patientRepository.findById(eq(1L))).thenReturn(Optional.of(khaoula));
        patientService.updatePatient(1L, null, null);
        assertEquals(khaoula.getName(), "khaoula");
        assertEquals(khaoula.getEmail(), "khaoula@gmail.com");
    }

    @Test void updatePatient_WhenNameLengthIs0AndEmailLengthIs0_Then_DontUpdatePatient(){
        Patient khaoula = new Patient ("khaoula", "khaoula@gmail.com", LocalDate.of(2000, 03, 04));
        when(patientRepository.findById(eq(1L))).thenReturn(Optional.of(khaoula));
        patientService.updatePatient(1L, "", "");
        assertEquals(khaoula.getName(), "khaoula");
        assertEquals(khaoula.getEmail(), "khaoula@gmail.com");
    }

    @Test void updatePatient_WhenNameEnteredIsSameAsPrevious_AndEmailIsSameAsPrevious_Then_DontUpdatePatient(){
        Patient khaoula = new Patient ("khaoula", "khaoula@gmail.com", LocalDate.of(2000, 03, 04));
        when(patientRepository.findById(eq(1L))).thenReturn(Optional.of(khaoula));
        patientService.updatePatient(1L, "khaoula", "khaoula@gmail.com");
        assertEquals(khaoula.getName(), "khaoula");
        assertEquals(khaoula.getEmail(), "khaoula@gmail.com");
    }


    @Test void updatePatient_WhenExistentId_Then_Update() {
        Patient khaoula = new Patient ("khaoula", "omar@gmail.com", LocalDate.of(2000, 03, 04));
        when(patientRepository.findById(eq(1L))).thenReturn(Optional.of(khaoula));
        patientService.updatePatient(1L, "test", "test@gmail.com");
        assertEquals(khaoula.getName(), "test");
        assertEquals(khaoula.getEmail(), "test@gmail.com");

    }








}
