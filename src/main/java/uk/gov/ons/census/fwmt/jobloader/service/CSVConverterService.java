package uk.gov.ons.census.fwmt.jobloader.service;

public interface CSVConverterService {
  void convertCSVToCanonical(String ingestType) throws Exception;
}
