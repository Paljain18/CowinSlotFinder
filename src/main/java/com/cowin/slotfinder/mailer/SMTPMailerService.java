package com.cowin.slotfinder.mailer;

import com.cowin.slotfinder.model.AvailableSlot;

import java.util.List;

public interface SMTPMailerService {

    public void sendMail(String from, String subject, String toAddresses, String ccAddresses, String bccAddresses, List<AvailableSlot> availableSlots);
}
