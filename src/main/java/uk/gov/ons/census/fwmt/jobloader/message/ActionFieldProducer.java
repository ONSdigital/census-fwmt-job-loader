package uk.gov.ons.census.fwmt.jobloader.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import static uk.gov.ons.census.fwmt.jobloader.config.ActionFieldQueueConfig.ACTION_INSTRUCTION_QUEUE;

@Slf4j
@Component
public class ActionFieldProducer {

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Retryable
  public void sendMessage(String message) {
    rabbitTemplate.convertAndSend(ACTION_INSTRUCTION_QUEUE, message);
    log.info("ActionInstruction sent to queue");
  }
}
