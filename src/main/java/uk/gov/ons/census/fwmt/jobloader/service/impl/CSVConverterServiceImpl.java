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
import uk.gov.ons.census.fwmt.jobloader.dto.CSVRecordDTO;
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

  @Override
  public void convertCeCSVToCanonical() throws GatewayException {

    CsvToBean<CSVRecordDTO> csvToBean;
    try {
      csvToBean = new CsvToBeanBuilder(
          new InputStreamReader(path.getInputStream(), StandardCharsets.UTF_8))
          .withType(CSVRecordDTO.class)
          .build();
    } catch (IOException e) {
      throw new GatewayException(GatewayException.Fault.SYSTEM_ERROR, e,
          "Failed to convert CSV to Bean.");
    }

    for (CSVRecordDTO csvRecordDTO : csvToBean) {
      rmAdapterService.sendJobRequest(CanonicalJobHelper.createCEJob(csvRecordDTO));
      gatewayEventManager
          .triggerEvent(String.valueOf(csvRecordDTO.getCaseId()), CSV_REQUEST_EXTRACTED, LocalTime.now());
    }
  }
}
