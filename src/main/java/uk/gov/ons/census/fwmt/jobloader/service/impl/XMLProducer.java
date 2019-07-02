package uk.gov.ons.census.fwmt.jobloader.service.impl;

import uk.gov.ons.census.fwmt.jobloader.dto.HouseholdCsvDTO;

public class XMLProducer {

    private String xmlMessage;

    public String constructCreate(HouseholdCsvDTO householdCsvDTO) {
        xmlMessage = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<ins:actionInstruction xmlns:ins=\"http://ons.gov.uk/ctp/response/action/message/instruction\">\n"
                + "    <actionRequest>\n"
                + "        <actionId>a133b2cd-ef3f-4755-ad07-616b7298a12a</actionId>\n"
                + "        <responseRequired>false</responseRequired>\n"
                + "        <actionPlan>ad3fbcb5-3681-4540-b386-ff719ec93643</actionPlan>\n"
                + "        <actionType>Create</actionType>\n"
//        + "        <contact>\n"
//        + "            <title>Mr</title>\n"
//        + "            <forename>John</forename>\n"
//        + "            <surname>Doe</surname>\n"
//        + "            <phoneNumber>12345 67890</phoneNumber>\n"
//        + "            <emailAddress>John.Doe@Email.com</emailAddress>\n"
//        + "        </contact>\n"
                + "        <address>\n"
                + "            <arid>" + householdCsvDTO.getArid() + "</arid>\n"
                + "            <uprn>" + householdCsvDTO.getUprn() + "</uprn>\n"
                + "            <type>" + householdCsvDTO.getType() + "</type>\n"
                + "            <estabType>" + householdCsvDTO.getEstablishmentType() + "</estabType>\n"
//        + "            <locality></locality>\n"
                + "            <line1>" + householdCsvDTO.getLine1() + "</line1>\n"
                + "            <line2>" + householdCsvDTO.getLine2() + "</line2>\n"
                + "            <line3>" + householdCsvDTO.getLine3() + "</line3>\n"
                + "            <townName>" + householdCsvDTO.getTownName() + "</townName>\n"
                + "            <postcode>" + householdCsvDTO.getPostCode() + "</postcode>\n"
                + "            <country>" + householdCsvDTO.getCountry() + "</country>\n"
                + "            <ladCode>" + householdCsvDTO.getLadCode() + "</ladCode>\n"
                + "            <latitude>" + householdCsvDTO.getLattitude() + "</latitude>\n"
                + "            <longitude>" + householdCsvDTO.getLongitude() + "</longitude>\n"
                + "            <oa>" + householdCsvDTO.getOa() + "</oa>\n"
                + "        </address>\n"
                + "        <legalBasis>Voluntary Not Stated</legalBasis>\n"
                + "        <caseGroupStatus>NOTSTARTED</caseGroupStatus>\n"
                + "        <caseId>" + householdCsvDTO.getCaseId() + "</caseId>\n"
                + "        <priority>medium</priority>\n"
                + "        <caseRef>02e52571-95c0-46ba-aab3-3e9f4c7555f7</caseRef>\n"
                + "        <iac>9wbmjwt557p3</iac>\n"
                + "        <events>\n"
                + "            <event>CASE_CREATED : null : SYSTEM : Case created when Initial creation of case</event>\n"
                + "        </events>\n"
                + "        <exerciseRef>1</exerciseRef>\n"
                + "        <userDescription>FWMT Comet Test</userDescription>\n"
                + "        <surveyName>Household</surveyName>\n"
                + "        <surveyRef>HH</surveyRef>\n"
                + "        <returnByDate>06/02/2019</returnByDate>\n"
                + "        <addressType>" + householdCsvDTO.getAddressType() + "</addressType>\n"
                + "        <addressLevel>" + householdCsvDTO.getAddressLevel() + "</addressLevel>\n"
                + "        <treatmentID>" + householdCsvDTO.getTreatmentId() + "</treatmentID>\n"
                + "        <coordinatorId>" + householdCsvDTO.getTreatmentId() +"</coordinatorId>\n"
                + "        <undeliveredAsAddress>" + householdCsvDTO.getUndeliveredAddress() + "</undeliveredAsAddress>\n"
                + "        <blankQreReturned>" + householdCsvDTO.getBlankQreReturned() + "</blankQreReturned>\n"
                + "    </actionRequest>\n"
                + "</ins:actionInstruction>";

        return xmlMessage;
    }

    public String constructCancel(HouseholdCsvDTO householdCsvDTO) {
        xmlMessage = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<ins:actionInstruction xmlns:ins=\"http://ons.gov.uk/ctp/response/action/message/instruction\">\n"
                + "    <actionCancel>\n"
                + "        <actionId>a133b2cd-ef3f-4755-ad07-616b7298a12a</actionId>\n"
                + "        <responseRequired>true</responseRequired>\n"
                + "        <actionType>Cancel</actionType>\n"
                + "        <reason>HQ case closure</reason>\n"
                + "        <caseId>" + householdCsvDTO.getCaseId() + "</caseId>\n"
                + "        <caseRef>Test123</caseRef>\n"
                + "        <addressType>" + householdCsvDTO.getAddressType() + "</addressType>\n"
                + "    </actionCancel>\n"
                + "</ins:actionInstruction>";

        return xmlMessage;

    }

    public String constructUpdate(HouseholdCsvDTO householdCsvDTO) {
        xmlMessage = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<ins:actionInstruction xmlns:ins=\"http://ons.gov.uk/ctp/response/action/message/instruction\">\n"
                + "    <actionUpdate>\n"
                + "        <actionId>a133b2cd-ef3f-4755-ad07-616b7298a12a</actionId>\n"
                + "        <responseRequired>true</responseRequired>\n"
                + "        <priority>lower</priority>\n"
                + "        <actionType>update</actionType>\n"
                + "        <events></events>\n"
                + "        <event></event>\n"
                + "        <caseId>" + householdCsvDTO.getCaseId() +"</caseId>\n"
                + "        <addressType>" + householdCsvDTO.getAddressType() + "</addressType>\n"
                + "        <addressLevel>" + householdCsvDTO.getAddressLevel() + "</addressLevel>\n"
                + "        <undeliveredAsAddress>" + householdCsvDTO.getUndeliveredAddress() + "</undeliveredAsAddress>\n"
                + "        <blankQreReturned>" + householdCsvDTO.getBlankQreReturned() + "</blankQreReturned>\n"
                + "        <actionableFrom>" + householdCsvDTO.getActionableFrom() + "</actionableFrom>\n"
                + "        <ce1Complete>" + householdCsvDTO.getCe1Completed() + "</ce1Complete>\n"
                + "        <ceExpectedResponses>" + householdCsvDTO.getCeExpectedResponses() + "</ceExpectedResponses>\n"
                + "        <ceActualResponses>" + householdCsvDTO.getCeActualResponses() + "</ceActualResponses>\n"
                + "    </actionUpdate>\n"
                + "</ins:actionInstruction>";

        return xmlMessage;
    }
}
