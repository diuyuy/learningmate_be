package org.kc5.learningmate.domain.auth.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kc5.learningmate.common.exception.CommonException;
import org.kc5.learningmate.common.exception.ErrorCode;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender javaMailSender;

    public void sendMail(String to, String subject, String text) {
        try {
            MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, "utf-8");

            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(text, true);
            javaMailSender.send(mimeMailMessage);
        } catch (MessagingException e) {
            log.error(e.getMessage());
            throw new CommonException(ErrorCode.SEND_EMAIL_FAIL);
        }


    }
}
