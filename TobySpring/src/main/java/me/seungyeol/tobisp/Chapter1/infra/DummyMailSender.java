package me.seungyeol.tobisp.Chapter1.infra;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DummyMailSender implements MailSender {
    @Override
    public void send(SimpleMailMessage simpleMessage) throws MailException {
        log.info("dummy");
    }

    @Override
    public void send(SimpleMailMessage... simpleMessages) throws MailException {
        log.info("dummy");
    }
}
