package com.levdoc.m20service.service;

import com.levdoc.m20service.constants.MailConstants;
import com.levdoc.m20service.dto.PatientDTO;
import com.levdoc.m20service.mapper.PatientMapper;
import com.levdoc.m20service.mapper.UserMapper;
import com.levdoc.m20service.model.PatientModel;
import com.levdoc.m20service.model.UserModel;
import com.levdoc.m20service.repository.PatientRepository;
import com.levdoc.m20service.repository.UserRepository;
import com.levdoc.m20service.utils.MailUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class DoctorService {
    protected final UserRepository userRepository;
    protected final PatientRepository patientRepository;
    protected final UserMapper userMapper;
    protected final PatientMapper patientMapper;
    private final JavaMailSender javaMailSender;

    public DoctorService(UserRepository userRepository,
                         PatientRepository patientRepository,
                         UserMapper userMapper,
                         PatientMapper patientMapper,
                         JavaMailSender javaMailSender) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
        this.userMapper = userMapper;
        this.patientMapper = patientMapper;
        this.javaMailSender = javaMailSender;
    }


    public void createPatient(PatientDTO patientDTO, Long userId) {
        patientDTO.setDateOfRegistration(LocalDate.now());
        patientDTO.setUuid(UUID.randomUUID());

        UserModel user = userRepository.findUserModelById(userId);
        List<PatientModel> patientModelList = user.getPatientModel();
        patientModelList.add(patientMapper.dtoToModel(patientDTO));

        userRepository.save(user);

        SimpleMailMessage mailMessage = MailUtils.creteMailMessage(
                patientDTO.getEmail(),
                MailConstants.MAIL_PERSONAL_AREA_FOR_PATIENT_ACCESS,
                MailConstants.MAIL_MESSAGE_PERSONAL_AREA_FOR_PATIENT_ACCESS
                        + patientDTO.getUuid()
        );
        javaMailSender.send(mailMessage);
    }

    public PatientDTO getPatientById(Long id) {
        return patientMapper.modelToDto(patientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Пациент не найден")
                ));
    }
}
