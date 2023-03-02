package com.example.demo.controller;

import com.example.demo.patient.Patient;
import com.example.demo.patient.PatientController;
import com.example.demo.patient.PatientRepository;
import com.example.demo.patient.PatientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(PatientController.class)

public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PatientService patientService;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private PatientRepository patientRepository;




    @InjectMocks
    private PatientController patientController;
    Patient patient2 = new Patient("Hamza", "hamza@gmail.com", LocalDate.of(2000, 11, 01));


    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(patientController).build();

    }

    @Test
    public void shouldAddNewPatient() throws Exception {
        Patient patient = new Patient("khaoula", "khaoulakerrou@gmail.com", LocalDate.of(2000, 07, 29));

        patientService.addNewPatient(patient);
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);
        this.mockMvc.perform(post("/addPatient")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patient)))
                .andExpect(status().isCreated())
                .andExpect((ResultMatcher) jsonPath("$.name", is(patient.getName())));

    }
    @Test
    public void getPatients_WhenSuccessful() throws Exception {

        List<Patient> allPatients = new ArrayList<>(Arrays.asList( patient2));
        when(patientRepository.findAll()).thenReturn(allPatients);



        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/patients/getPatients")
                        .contentType(APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", hasSize(2)))
                        .andExpect((ResultMatcher) jsonPath("$[0].name", is("khaoula")));
    }

    @Test
    public void getPatientById_WhenSuccessful() throws Exception {
        Patient patient = new Patient("khaoula", "khaoulakerrou@gmail.com", LocalDate.of(2000, 07, 29));

        when(patientController.getPatient(eq(1L))).thenReturn(Optional.of(patient));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/getPatient/1")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect((ResultMatcher) jsonPath("$.name", is("khaoula")));
    }
}