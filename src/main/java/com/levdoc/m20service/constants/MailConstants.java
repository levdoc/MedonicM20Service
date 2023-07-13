package com.levdoc.m20service.constants;

public interface MailConstants {

    /*Информация для письма о предоставлении доступа*/
    String MAIL_SUBJECT_FOR_GRANTED_ACCESS = "Вам предоставлен доступ к ЛИС M20Service";
    String MAIL_MESSAGE_FOR_GRANTED_ACCESS = """
            Добрый день! \n
            Вы получили это пиьсмо так как Ваш e-mail был исспользован администратором для регистрации в ЛИС M20Service. \n
            Даные для входа в систему: 
            """;

    String MAIL_PERSONAL_AREA_FOR_PATIENT_ACCESS = "Вам предоставлен доступ к личному кабинету";
    String MAIL_MESSAGE_PERSONAL_AREA_FOR_PATIENT_ACCESS = """
            Добрый день! \n
            Вы получили это пиьсмо так как Ваш e-mail был исспользован врачем для регистрации в ЛИС M20Service. \n
            Для получения доступа к личному кабинету, перейдите по ссылке http://localhost:8080/patient/
            """;


}
