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
import uk.gov.ons.census.fwmt.jobloader.dto.HouseholdCsvDTO;
import uk.gov.ons.census.fwmt.jobloader.service.CSVConverterService;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;

import static uk.gov.ons.census.fwmt.jobloader.config.GatewayEventsConfig.CSV_REQUEST_EXTRACTED;

@Slf4j
@Service
public class CSVConverterServiceImpl implements CSVConverterService {
  @Value("${gcpBucket.cancelLocation}")
  private Resource cancelPath;

  @Value("${gcpBucket.createLocation}")
  private Resource createPath;

  @Value("${gcpBucket.updateLocation}")
  private Resource updatePath;

  private Resource csvLocation;

  @Autowired
  private RmAdapterServiceImpl rmAdapterService;

  @Autowired
  private GatewayEventManager gatewayEventManager;

  @Autowired
  private XMLProducer xmlProducer;

  private String xmlMessage;

  @Override
  public void convertCSVToCanonical(String ingestType) throws GatewayException {

    switch (ingestType) {
      case "Cancel":
        csvLocation = cancelPath;
        break;
      case "Create":
        csvLocation = createPath;
        break;
      case "Update":
        csvLocation = updatePath;
        break;
      default:
        break;
    }

    CsvToBean<HouseholdCsvDTO> csvToBean;
    try {
      csvToBean = new CsvToBeanBuilder(
          new InputStreamReader(csvLocation.getInputStream(), StandardCharsets.UTF_8))
          .withType(HouseholdCsvDTO.class)
          .build();
    } catch (IOException e) {
      throw new GatewayException(GatewayException.Fault.SYSTEM_ERROR, e,
          "Failed to convert CSV to Bean.");
    }

    for (HouseholdCsvDTO householdCsvDTO : csvToBean) {

      switch (householdCsvDTO.getActionType().toLowerCase()) {
      case "create":

        xmlMessage = xmlProducer.constructCreate(householdCsvDTO);

        break;
      case "cancel":

        xmlMessage = xmlProducer.constructCancel(householdCsvDTO);

        break;
      case "update":

        xmlMessage = xmlProducer.constructUpdate(householdCsvDTO);

        break;

      default:
        break;
      }
      rmAdapterService.sendJobRequest(xmlMessage);
      gatewayEventManager
          .triggerEvent(String.valueOf(householdCsvDTO.getCaseId()), CSV_REQUEST_EXTRACTED, LocalTime.now());
    }
  }
}
