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

  private String ingestType;

  @GetMapping("/ingestCancelCsvFile")
  public ResponseEntity ingestCancelCsvFile()
      throws Exception {
    ingestType = "Cancel";
    csvConverterServiceImpl.convertCSVToCanonical(ingestType);
    return ResponseEntity.ok("CSV adapter service activated");
  }

  @GetMapping("/ingestCreateCsvFile")
  public ResponseEntity ingestCreateCsvFile()
          throws Exception {
    ingestType = "Create";
    csvConverterServiceImpl.convertCSVToCanonical(ingestType);
    return ResponseEntity.ok("CSV adapter service activated");
  }

  @GetMapping("/ingestUpdateCsvFile")
  public ResponseEntity ingestUpdateCsvFile()
          throws Exception {
    ingestType = "Update";
    csvConverterServiceImpl.convertCSVToCanonical(ingestType);
    return ResponseEntity.ok("CSV adapter service activated");
  }
}