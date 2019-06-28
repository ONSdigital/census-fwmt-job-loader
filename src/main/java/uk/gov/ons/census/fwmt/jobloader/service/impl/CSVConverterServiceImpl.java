package uk.gov.ons.census.fwmt.jobloader.service.impl;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import uk.gov.ons.census.fwmt.common.error.GatewayException;
import uk.gov.ons.census.fwmt.events.component.GatewayEventManager;
import uk.gov.ons.census.fwmt.jobloader.dto.householdCsvDTO;
import uk.gov.ons.census.fwmt.jobloader.service.CSVConverterService;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;

import static uk.gov.ons.census.fwmt.jobloader.config.GatewayEventsConfig.CSV_REQUEST_EXTRACTED;

@Slf4j
@Service
public class CSVConverterServiceImpl implements CSVConverterService {
  @Value("${gcpBucket.location}")
  private Resource path;

  @Autowired
  private RmAdapterServiceImpl rmAdapterService;

  @Autowired
  private GatewayEventManager gatewayEventManager;

  private String xmlMessage;

  @Override
  public void convertCeCSVToCanonical() throws GatewayException {

    CsvToBean<householdCsvDTO> csvToBean;
    try {
      csvToBean = new CsvToBeanBuilder(
          new InputStreamReader(path.getInputStream(), StandardCharsets.UTF_8))
          .withType(householdCsvDTO.class)
          .build();
    } catch (IOException e) {
      throw new GatewayException(GatewayException.Fault.SYSTEM_ERROR, e,
          "Failed to convert CSV to Bean.");
    }

    for (householdCsvDTO householdCsvDTO : csvToBean) {

      switch (householdCsvDTO.getActionType()) {
      case "create":

        constructCreate(householdCsvDTO);

        break;
      case "cancel":

        constructCancel(householdCsvDTO);

        break;
      case "update":

        constructUpdate(householdCsvDTO);

        break;
      }
      rmAdapterService.sendJobRequest(xmlMessage);
      gatewayEventManager
          .triggerEvent(String.valueOf(householdCsvDTO.getCaseId()), CSV_REQUEST_EXTRACTED, LocalTime.now());
    }
  }

  private void constructCreate(householdCsvDTO householdCsvDTO) {
    xmlMessage = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
        + "<ins:actionInstruction xmlns:ins=\"http://ons.gov.uk/ctp/response/action/message/instruction\">\n"
        + "    <actionRequest>\n"
        + "        <actionId>a133b2cd-ef3f-4755-ad07-616b7298a12a</actionId>\n"
        + "        <responseRequired>false</responseRequired>\n"
        + "        <actionPlan>ad3fbcb5-3681-4540-b386-ff719ec93643</actionPlan>\n"
        + "        <actionType>Create</actionType>\n"
        + "        <contact>\n"
        + "            <title>Mr</title>\n"
        + "            <forename>John</forename>\n"
        + "            <surname>Doe</surname>\n"
        + "            <phoneNumber>12345 67890</phoneNumber>\n"
        + "            <emailAddress>John.Doe@Email.com</emailAddress>\n"
        + "        </contact>\n"
        + "        <address>\n"
        + "            <arid>1234567890</arid>\n"
        + "            <uprn>1234567890</uprn>\n"
        + "            <type>HH</type>\n"
        + "            <estabType>Residential</estabType>\n"
        + "            <locality>E</locality>\n"
        + "            <line1>1 Whalesborough Place</line1>\n"
        + "            <line2></line2>\n"
        + "            <line3></line3>\n"
        + "            <townName>BUDE</townName>\n"
        + "            <postcode>EX23 8GB</postcode>\n"
        + "            <country>E</country>\n"
        + "            <ladCode>string</ladCode>\n"
        + "            <latitude>50.8299346</latitude>\n"
        + "            <longitude>-4.5314548</longitude>\n"
        + "            <oa>E</oa>\n"
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
        + "        <addressType>HH</addressType>\n"
        + "        <treatmentID>treatmentId</treatmentID>\n"
        + "        <coordinatorId>TWH1-HA</coordinatorId>\n"
        + "        <undeliveredAsAddress>false</undeliveredAsAddress>\n"
        + "        <blankQreReturned>false</blankQreReturned>\n"
        + "    </actionRequest>\n"
        + "</ins:actionInstruction>";
  }

  private void constructCancel(householdCsvDTO householdCsvDTO) {
    xmlMessage = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
        + "<ins:actionInstruction xmlns:ins=\"http://ons.gov.uk/ctp/response/action/message/instruction\">\n"
        + "    <actionCancel>\n"
        + "        <actionId>a133b2cd-ef3f-4755-ad07-616b7298a12a</actionId>\n"
        + "        <responseRequired>true</responseRequired>\n"
        + "        <actionType>Cancel</actionType>\n"
        + "        <reason>HQ case closure</reason>\n"
        + "        <caseId>" + householdCsvDTO.getCaseId() + "</caseId>\n"
        + "        <caseRef>Test123</caseRef>\n"
        + "        <addressType>HH</addressType>\n"
        + "    </actionCancel>\n"
        + "</ins:actionInstruction>";
  }

  private void constructUpdate(householdCsvDTO householdCsvDTO) {
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
        + "        <addressType>HH</addressType>\n"
        + "        <addressLevel></addressLevel>\n"
        + "        <undeliveredAsAddress>false</undeliveredAsAddress>\n"
        + "        <blankQreReturned>true</blankQreReturned>\n"
        + "        <actionableFrom>2019-05-27T00:00:00.000Z</actionableFrom>\n"
        + "        <ce1Complete>false</ce1Complete>\n"
        + "        <ceExpectedResponses>0</ceExpectedResponses>\n"
        + "        <ceActualResponses>0</ceActualResponses>\n"
        + "    </actionUpdate>\n"
        + "</ins:actionInstruction>";
  }
}
