package uk.gov.ons.census.fwmt.jobloader.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseholdCsvDTO {

  @CsvBindByName(column = "actionId")
  private String actionId;

  @CsvBindByName(column = "actionPlan")
  private String actionPlan;

  @CsvBindByName(column = "actionType")
  private String actionType;

  @CsvBindByName(column = "caseId")
  private String caseId;

  @CsvBindByName(column = "caseReference")
  private String caseReference;

  @CsvBindByName(column = "establishmentType")
  private String establishmentType;

  @CsvBindByName(column = "fieldOfficerId")
  private String mandatoryResource;

  @CsvBindByName(column = "coordinatorId")
  private String coordinatorId;

  @CsvBindByName(column = "type")
  private String type;

  @CsvBindByName(column = "organisationName")
  private String organisationName;

  @CsvBindByName(column = "arid")
  private String arid;

  @CsvBindByName(column = "uprn")
  private String uprn;

  @CsvBindByName(column = "line1")
  private String line1;

  @CsvBindByName(column = "line2")
  private String line2;

  @CsvBindByName(column = "line3")
  private String line3;

  @CsvBindByName(column = "townName")
  private String townName;

  @CsvBindByName(column = "postCode")
  private String postCode;

  @CsvBindByName(column = "country")
  private String country;

  @CsvBindByName(column = "ladCode")
  private String ladCode;

  @CsvBindByName(column = "oa")
  private String oa;

  @CsvBindByName(column = "latitude")
  private BigDecimal latitude;

  @CsvBindByName(column = "longitude")
  private BigDecimal longitude;

  @CsvBindByName(column = "addressType")
  private String addressType;

  @CsvBindByName(column = "addressLevel")
  private String addressLevel;

  @CsvBindByName(column = "treatmentId")
  private String treatmentId;

  @CsvBindByName(column = "undeliveredAsAddressed")
  private Boolean undeliveredAddressed;

  @CsvBindByName(column = "blankQreReturned")
  private Boolean blankQreReturned;

  @CsvBindByName(column = "ce1Completed")
  private Boolean ce1Completed;

  @CsvBindByName(column = "ceExpectedCapacity")
  private int ceExpectedResponses;

  @CsvBindByName(column = "ceActualResponses")
  private int ceActualResponses;

  @CsvBindByName(column = "actionableFrom")
  private String actionableFrom;
}
