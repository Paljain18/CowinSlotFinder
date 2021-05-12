package com.cowin.slotfinder;

import com.cowin.slotfinder.config.MailerConfig;
import com.cowin.slotfinder.executor.CowinExecutorService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
@EnableConfigurationProperties(MailerConfig.class)
public class CowinSlotFinderApplication {


    public static void main(String[] args) {
        SpringApplication.run(CowinSlotFinderApplication.class, args);

    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // Do any additional configuration here
        return builder.build();
    }

    @Bean
    public CowinExecutorService executeCowinService1(){
        return new CowinExecutorService();
    }

    @Bean
    public void executeCowinService(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(executeCowinService1());
    }

/*    @Bean("gmail")
    public JavaMailSender gmailMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("palashjain.1291@gmail.com");
        mailSender.setPassword("Pv@15121992");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "false");

        return mailSender;
    }*/

    @Bean("executorService")
    public ExecutorService initCowinExecutorService(){
        return Executors.newSingleThreadExecutor();
    }
}
