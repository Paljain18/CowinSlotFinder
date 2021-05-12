package com.cowin.slotfinder.manager;

import com.cowin.slotfinder.config.MailerConfig;
import com.cowin.slotfinder.executor.CowinExecutorService;
import com.cowin.slotfinder.mailer.SMTPMailerService;
import com.cowin.slotfinder.model.AvailableSlot;
import com.cowin.slotfinder.model.Center;
import com.cowin.slotfinder.model.CenterList;
import com.cowin.slotfinder.model.Session;
import com.cowin.slotfinder.service.CowinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CowinManager {

    @Autowired
    private CowinService cowinService;

    @Autowired
    private MailerConfig mailerConfig;

    @Autowired
    private CowinExecutorService cowinExecutorService;

    @Autowired
    private SMTPMailerService mailerService;

    public boolean mailSent;


    public boolean getAvailableSlots(String pinCode, String fromDate, String emails){
        CenterList centerList = cowinService.getCenterList(pinCode, fromDate);
        List<AvailableSlot> availableSlots = parseAvailableSlotsFromCenters(centerList);
        if(availableSlots == null || availableSlots.size() == 0) {
            System.out.println("No Available Slots at PinCode :" + pinCode + "Last checked at : " + new java.util.Date());
            mailerService.sendMail("VaccineNotificationApp",   "0 Vaccine Center available for booking in your area", emails, "", "", availableSlots);
            return false;
        }
        else{
            System.out.println("Found " + availableSlots.size() +  " available Slots at Pin : + " + pinCode);
            for(AvailableSlot slot : availableSlots){
                System.out.println("===========================================");
                System.out.println("Date : " + slot.getDate());
                System.out.println("Center Address : " + slot.getCenterAddress());
                System.out.println("Min Age : " + slot.getMinAge());
                System.out.println("Available capacity : " + slot.getAvailableCapacity());
                System.out.println("Vaccine : " + slot.getVaccine());
                System.out.println("===========================================");
            }
            mailerService.sendMail("VaccineNotificationApp", availableSlots.size() + " Vaccine Center available for booking in your area", emails, "", "", availableSlots);
            return true;
        }

    }

    private List<AvailableSlot> parseAvailableSlotsFromCenters(CenterList centerList){

        if(centerList == null)
            return null;
        List<AvailableSlot> availableSlots = new ArrayList<>();
        for(Center center : centerList.getCenters()){
            for(Session session : center.getSessions()){
                if(session.getMin_age_limit() == 18 && session.getAvailable_capacity() > 0){
                    System.out.println("Slot Available at center : " + center.getName());
                    AvailableSlot availableSlot = new AvailableSlot();
                    availableSlot.setCenterId(center.getCenter_id());
                    availableSlot.setCenterName(center.getName());
                    availableSlot.setCenterAddress(center.getAddress());
                    availableSlot.setAvailableCapacity(session.getAvailable_capacity());
                    availableSlot.setMinAge(session.getMin_age_limit());
                    availableSlot.setVaccine(session.getVaccine());
                    availableSlot.setDate(session.getDate());

                    availableSlots.add(availableSlot);
                }
            }
        }

        return availableSlots;
    }
}
