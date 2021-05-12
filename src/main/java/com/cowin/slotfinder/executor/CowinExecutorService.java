package com.cowin.slotfinder.executor;

import com.cowin.slotfinder.manager.CowinManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;

public class CowinExecutorService implements Runnable {

    @Autowired
    @Lazy
    private CowinManager cowinManager;

    @Value("${recipientEmails}")
    public String recipientEmails; //= "palashjain.18@gmail.com;arnavj343@gmail.com;yashjain191919@gmail.com;ankur1991garg@gmail.com;lokeshahuja20@gmail.com;bhakargourav@gmail.com;tushardangi@gmail.com;patidaranimesh50@gmail.com;abhishek.tiwari27691@gmail.com;mayankamodiya8@gmail.com;jiteshtawar@gmail.com;sharad9770467075@gmail.com;parthg432@gmail.com;ajaybaghela191@gmail.com;";

    @Value("${pinCode}")
    private String pinCode;

    @Value("${startDate}")
    private String startDate;

    public String emails454775 = "Nishankjain@rediffmail.com";
    public boolean emails454001Sent;
    public boolean emails454775Sent;


    @Override
    public void run() {
        while(true) {
            try {

               // System.out.println("Started : ");
                if(!emails454001Sent)
                    emails454001Sent = cowinManager.getAvailableSlots(pinCode, startDate, recipientEmails);
/*                if(!emails454775Sent)
                    emails454775Sent = cowinManager.getAvailableSlots("454775", "07-05-2021", emails454775);*/
                Thread.sleep(20000);
               // System.out.println("Completed : ");

            } catch (InterruptedException e) {

                e.printStackTrace();

            }
        }

    }
}
