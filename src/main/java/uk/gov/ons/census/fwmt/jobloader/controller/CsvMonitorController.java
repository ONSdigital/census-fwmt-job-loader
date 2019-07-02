package uk.gov.ons.census.fwmt.jobloader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import uk.gov.ons.census.fwmt.jobloader.service.impl.CSVConverterServiceImpl;

@Controller
public class CsvMonitorController {

  @Autowired
  private CSVConverterServiceImpl csvConverterServiceImpl;

  @GetMapping("/ingestCeCsvFile")
  public ResponseEntity ingestCeCsvFile()
      throws Exception {
    csvConverterServiceImpl.convertCeCSVToCanonical();
    return ResponseEntity.ok("CSV adapter service activated");
  }
}