package com.cowin.slotfinder.service;

import com.cowin.slotfinder.model.AvailableSlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

@Service
public class MailContentBuilderService {

    private TemplateEngine templateEngine;

    @Autowired
    public MailContentBuilderService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String build(List<AvailableSlot> availableSlots) {
        Context context = new Context();
        context.setVariable("availableSlots", availableSlots);
        return templateEngine.process("mailTemplate", context);
    }
}
