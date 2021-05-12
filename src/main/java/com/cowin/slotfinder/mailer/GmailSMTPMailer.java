package com.cowin.slotfinder.mailer;

import com.cowin.slotfinder.model.AvailableSlot;
import com.cowin.slotfinder.service.MailContentBuilderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GmailSMTPMailer implements com.cowin.slotfinder.mailer.SMTPMailerService {

    @Autowired
    @Qualifier("gmail")
    private JavaMailSender mailSender;

    @Autowired
    private MailContentBuilderService mailContentBuilderService;

    public void sendMail(String from, String subject, String toAddresses, String ccAddresses, String bccAddresses, List<AvailableSlot> availableSlots) {
        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setTo(toAddresses.split("[,;]"));
            message.setFrom(from, "PalashVaccineApp");
            message.setSubject(subject);
            if (!ccAddresses.isEmpty())
                message.setCc(ccAddresses.split("[;,]"));
            if (!bccAddresses.isEmpty())
                message.setBcc(bccAddresses.split("[;,]"));
            String content = mailContentBuilderService.build(availableSlots);
            message.setText(content, true);
        };
        System.out.println("Sending Email!");
        try {
            mailSender.send(preparator);
            System.out.println("Mail Sent!");
        } catch (Exception ex) {
            System.out.println("Exception while sending email : " + ex.getMessage());
        }
    }
}
