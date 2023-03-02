package com.example.demo.patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping(path = "/api/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }
    @GetMapping("/getPatients")
    public List<Patient> getPatients(){
        return patientService.getPatients();
    }

    @GetMapping("/getPatient/{patientId}")
    public Optional<Patient> getPatient(@PathVariable("patientId") Long patientId){
        return patientService.getPatient(patientId);
    }


    @PostMapping()
    public void registerNewPatient(@RequestBody Patient patient){
        patientService.addNewPatient(patient);
    }

    @DeleteMapping(path = "/deletePatient/{patientId}")
    public void deletePatient(@PathVariable("patientId") Long patientId){
        patientService.deletePatient(patientId);
    }

    @PutMapping(path = "/{patientId}")
    public void updatePatient(@PathVariable("patientId") Long patientId,
                              @RequestBody(required = false) Patient patient
                              ){
        patientService.updatePatient(patientId, patient.getName(), patient.getEmail());
    }

}
