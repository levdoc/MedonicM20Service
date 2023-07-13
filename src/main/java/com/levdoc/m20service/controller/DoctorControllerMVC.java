package com.levdoc.m20service.controller;

import com.levdoc.m20service.dto.PatientDTO;
import com.levdoc.m20service.service.DoctorService;
import com.levdoc.m20service.service.PatientService;
import com.levdoc.m20service.service.UserService;
import com.levdoc.m20service.service.customuserdetalis.CustomUserDetalis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/doctor")
public class DoctorControllerMVC {
    private final DoctorService doctorService;
    private final UserService userService;
    private final PatientService patientService;

    public DoctorControllerMVC(DoctorService doctorService,
                               UserService userService,
                               PatientService patientService) {
        this.doctorService = doctorService;
        this.userService = userService;
        this.patientService = patientService;
    }

    @GetMapping
    public String getAllPatientByActiveUser (Model model) {
        CustomUserDetalis customUserDetalis = (CustomUserDetalis) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        List<PatientDTO> patientDTOList = userService
                .getPatientDtoListByUserId(Long.valueOf(customUserDetalis.getUserId()));

        model.addAttribute("patients", patientDTOList);

        return "doctor/patientList";
    }

//    @GetMapping("/get/all/patients")
//    public String getAllPatientBySystem (Model model) {

//        List<PatientDTO> patientDTOList = patientService.findAllPatinetDTO();
//
//        model.addAttribute("patients", patientDTOList);
//
//        return "doctor/patientList";
//    }

    @GetMapping("/get/all/patients")
    public String getAllPatientBySystem (@RequestParam(value = "page", defaultValue = "1") int page,
                                         @RequestParam(value = "size", defaultValue = "3") int pageSize,
                                         Model model) {

        PageRequest pageRequest = PageRequest.of(page-1, pageSize);

        Page<PatientDTO> patientDTOList = patientService.findAllPatinetDTO(pageRequest);

        model.addAttribute("patients", patientDTOList);

        return "doctor/patientListAll";
    }

    @GetMapping("/create/patient")
    public String createPatient (Model model) {
        model.addAttribute("patientForm", new PatientDTO());
        return "doctor/registrationPatient";
    }

    @PostMapping("/create/patient")
    public String createPatient (@ModelAttribute("patientForm") PatientDTO patientDTO) {
        CustomUserDetalis customUserDetalis = (CustomUserDetalis) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        Long userId = Long.valueOf(customUserDetalis.getUserId());
        doctorService.createPatient(patientDTO, userId);
        return "redirect:/doctor";
    }

    @GetMapping("/view/patient/info/{id}")
    public String getPatineInfo(@PathVariable Long id,
                                Model model) {
        PatientDTO patient = doctorService.getPatientById(id);
        model.addAttribute("patient", patient);
        return "patient/viewPatientInfoByDoctor";
    }

}
