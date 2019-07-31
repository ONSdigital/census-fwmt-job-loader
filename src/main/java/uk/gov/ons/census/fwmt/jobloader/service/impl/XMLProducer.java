package uk.gov.ons.census.fwmt.jobloader.service.impl;

import org.springframework.stereotype.Component;
import uk.gov.ons.census.fwmt.jobloader.dto.HouseholdCsvDTO;

@Component
public class XMLProducer {

    private String xmlMessage;

    public String constructCreate(HouseholdCsvDTO householdCsvDTO) {
        xmlMessage = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<ins:actionInstruction xmlns:ins=\"http://ons.gov.uk/ctp/response/action/message/instruction\">\n"
                + "    <actionRequest>\n"
                + "        <actionId>" + householdCsvDTO.getActionId() + "</actionId>\n"
                + "        <responseRequired>false</responseRequired>\n"
                + "        <actionPlan>" + householdCsvDTO.getActionPlan() + "</actionPlan>\n"
                + "        <actionType>" + householdCsvDTO.getActionType() + "</actionType>\n"
                + "        <address>\n"
                + "            <arid>" + householdCsvDTO.getArid() + "</arid>\n"
                + "            <uprn>" + householdCsvDTO.getUprn() + "</uprn>\n"
                + "            <type>" + householdCsvDTO.getType() + "</type>\n"
                + "            <estabType>" + householdCsvDTO.getEstablishmentType() + "</estabType>\n"
                + "            <locality></locality>\n"
                + "            <line1>" + householdCsvDTO.getLine1() + "</line1>\n"
                + "            <line2>" + householdCsvDTO.getLine2() + "</line2>\n"
                + "            <line3>" + householdCsvDTO.getLine3() + "</line3>\n"
                + "            <townName>" + householdCsvDTO.getTownName() + "</townName>\n"
                + "            <postcode>" + householdCsvDTO.getPostCode() + "</postcode>\n"
                + "            <country>" + householdCsvDTO.getCountry() + "</country>\n"
                + "            <ladCode>" + householdCsvDTO.getLadCode() + "</ladCode>\n"
                + "            <latitude>" + householdCsvDTO.getLatitude() + "</latitude>\n"
                + "            <longitude>" + householdCsvDTO.getLongitude() + "</longitude>\n"
                + "            <oa>" + householdCsvDTO.getOa() + "</oa>\n"
                + "        </address>\n"
                + "        <legalBasis>Voluntary Not Stated</legalBasis>\n"
                + "        <caseGroupStatus>NOTSTARTED</caseGroupStatus>\n"
                + "        <caseId>" + householdCsvDTO.getCaseId() + "</caseId>\n"
                + "        <priority>medium</priority>\n"
                + "        <caseRef>" + householdCsvDTO.getCaseReference() + "</caseRef>\n"
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
                + "        <fieldOfficerId>" + householdCsvDTO.getMandatoryResource() + "</fieldOfficerId>\n"
                + "        <undeliveredAsAddress>false</undeliveredAsAddress>\n"
                + "        <blankQreReturned>false</blankQreReturned>\n"
                + "    </actionRequest>\n"
                + "</ins:actionInstruction>";

        return xmlMessage;
    }

    public String constructCancel(HouseholdCsvDTO householdCsvDTO) {
        xmlMessage = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<ins:actionInstruction xmlns:ins=\"http://ons.gov.uk/ctp/response/action/message/instruction\">\n"
                + "    <actionCancel>\n"
                + "        <responseRequired>true</responseRequired>\n"
                + "        <actionType>" + householdCsvDTO.getActionType() + "</actionType>\n"
                + "        <reason>HQ case closure</reason>\n"
                + "        <caseId>" + householdCsvDTO.getCaseId() + "</caseId>\n"
                + "        <caseRef>Test123</caseRef>\n"
//                + "        <addressType>" + householdCsvDTO.getAddressType() + "</addressType>\n"
                + "        <addressType>HH</addressType>\n"
                + "    </actionCancel>\n"
                + "</ins:actionInstruction>";

        return xmlMessage;

    }

    public String constructUpdate(HouseholdCsvDTO householdCsvDTO) {
        xmlMessage = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<ins:actionInstruction xmlns:ins=\"http://ons.gov.uk/ctp/response/action/message/instruction\">\n"
                + "    <actionUpdate>\n"
                + "        <responseRequired>true</responseRequired>\n"
                + "        <priority>lower</priority>\n"
                + "        <actionType>" + householdCsvDTO.getActionType() + "</actionType>\n"
                + "        <events></events>\n"
                + "        <event></event>\n"
                + "        <caseId>" + householdCsvDTO.getCaseId() +"</caseId>\n"
                + "        <addressType>" + householdCsvDTO.getAddressType() + "</addressType>\n"
                + "        <addressLevel>" + householdCsvDTO.getAddressLevel() + "</addressLevel>\n"
                + "        <undeliveredAsAddress>" + householdCsvDTO.getUndeliveredAddressed() + "</undeliveredAsAddress>\n"
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
