package uk.gov.ons.census.fwmt.jobloader.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActionFieldQueueConfig {

  public static final String ACTION_INSTRUCTION_QUEUE = "Action.Field";
  public static final String ACTION_INSTRUCTION_DLQ = "Action.FieldDLQ";

  @Autowired
  private AmqpAdmin amqpAdmin;

  //Queues
  @Bean
  public Queue gatewayActionsQueue() {
    Queue queue = QueueBuilder.durable(ACTION_INSTRUCTION_QUEUE)
        .withArgument("x-dead-letter-exchange", "")
        .withArgument("x-dead-letter-routing-key", ACTION_INSTRUCTION_DLQ)
        .build();
    queue.setAdminsThatShouldDeclare(amqpAdmin);
    return queue;
  }

  //Dead Letter Queue
  @Bean
  public Queue gatewayActionsDeadLetterQueue() {
    Queue queue = QueueBuilder.durable(ACTION_INSTRUCTION_DLQ).build();
    queue.setAdminsThatShouldDeclare(amqpAdmin);
    return queue;
  }
}
