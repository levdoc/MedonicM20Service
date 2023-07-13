package com.levdoc.m20service.service;

import com.levdoc.m20service.constants.MailConstants;
import com.levdoc.m20service.dto.PatientDTO;
import com.levdoc.m20service.dto.UserDTO;
import com.levdoc.m20service.mapper.PatientMapper;
import com.levdoc.m20service.mapper.UserMapper;
import com.levdoc.m20service.model.PatientModel;
import com.levdoc.m20service.model.UserModel;
import com.levdoc.m20service.repository.UserRepository;
import com.levdoc.m20service.utils.MailUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class UserService {

    protected final UserRepository userRepository;
    protected final UserMapper userMapper;
    protected final PatientMapper patientMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JavaMailSender javaMailSender;

    public UserService(UserRepository userRepository,
                       UserMapper userMapper,
                       PatientMapper patientMapper,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       JavaMailSender javaMailSender) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.patientMapper = patientMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.javaMailSender = javaMailSender;
    }


    public void save(UserModel userModel) {
        userRepository.save(userModel);
    }

    public List<UserDTO> listAll() {
        return userMapper.modelsToDtos(userRepository.findAll());
    }

    public UserDTO getUserById(Long id) {
        return userMapper.modelToDto(userRepository.findUserModelById(id));
    }

    public void update(UserDTO userDTO) {
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        userMapper.modelToDto(userRepository.save(userMapper.DtoToModel(userDTO)));
    }

    public void hardDelete(Long id) {
        userRepository.deleteById(id);
    }

    public void blockUser(Long id) {
        UserModel user = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Пользователь не найден")
        );
        user.setBlocked(true);
        userRepository.save(user);
    }

    public void unblockUser(Long id) {
        UserModel user = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Пользователь не найден")
        );
        user.setBlocked(false);
        userRepository.save(user);
    }

    public UserDTO getUserByLogin(String login) {
        return userMapper.modelToDto(userRepository.findUserModelByLogin(login));
    }

    public void create(UserDTO newUserDTO) {

        SimpleMailMessage mailMessage = MailUtils.creteMailMessage(
                newUserDTO.getEmail(),
                MailConstants.MAIL_SUBJECT_FOR_GRANTED_ACCESS,
                MailConstants.MAIL_MESSAGE_FOR_GRANTED_ACCESS
                        + newUserDTO.getLogin() + " и пароль "
                        + newUserDTO.getPassword()
        );

        newUserDTO.setPassword(bCryptPasswordEncoder.encode(newUserDTO.getPassword()));
        newUserDTO.setBlocked(false);
        userRepository.save(userMapper.DtoToModel(newUserDTO));

        javaMailSender.send(mailMessage);
    }

    public List<PatientDTO> getPatientDtoListByUserId(Long id) {
        return patientMapper.modelsToDtos(
                userRepository.findUserModelById(id).getPatientModel());
    }




}
