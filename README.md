# CowinSlotFinder

This Java application helps finding available slots on cowin portal for the provided pinCode

The app will poll on Cowin for the available slot and will notify via email as soon as a slot is available


To Run the project follow below steps 

Make sure you have Java installed on your machine

Install Maven on your machine as we have used maven for dependencies

Run the command - mvn clean package spring-boot:repackage this will create a Jar file named CowinSlotFinderV1-1.0-SNAPSHOT.jar inside the target folder

Run Jar file with proper values of commandLine arguments - java -jar target/CowinSlotFinderV1-1.0-SNAPSHOT.jar --senderEmailId=senderEmailHere --senderPassword=Pv@senderPasswordHere --recipientEmails=recipientEmailsHere --pinCode=pinCodeHere --startDate=startDateHere(ex. 12-05-2021)

Note - For safety create a new gmail account which will be used to send emails. Also, make sure to enable less secure mode on your email accoount to allow this application to send mail using the account.
