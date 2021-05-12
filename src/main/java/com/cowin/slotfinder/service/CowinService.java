package com.cowin.slotfinder.service;

import com.cowin.slotfinder.model.CenterList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Component
public class CowinService {

    @Autowired
    private final RestTemplate restTemplate;

    public CowinService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    private String cowinCalenderByPinUrl = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin";

    public CenterList getCenterList(String pinCode, String fromDate) {
        try {
            String url = cowinCalenderByPinUrl + "?pincode=" + pinCode +  "&date=" + fromDate;
            HttpHeaders headers = new HttpHeaders();
            headers.set("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");
            // build the request
            HttpEntity request = new HttpEntity(headers);

            ResponseEntity<CenterList> response = this.restTemplate.exchange(url, HttpMethod.GET, request, CenterList.class, 1);
            return response.getBody();

        }  catch (Exception ex) {
            System.out.println("Some exception occured while fetching centers " + ex.getMessage());
            return null;
        }
    }
}
