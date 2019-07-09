package uk.gov.ons.census.fwmt.jobloader.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.ons.census.fwmt.jobloader.message.ActionFieldProducer;
import uk.gov.ons.census.fwmt.jobloader.service.RmAdapterService;

@Slf4j
@Component
public class RmAdapterServiceImpl implements RmAdapterService {

  @Autowired
  private ActionFieldProducer actionFieldProducer;

  @Override
  public void sendJobRequest(String xmlMessage) {
    actionFieldProducer.sendMessage(xmlMessage);
  }
}
