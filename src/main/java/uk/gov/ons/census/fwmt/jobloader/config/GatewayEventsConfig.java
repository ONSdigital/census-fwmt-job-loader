package uk.gov.ons.census.fwmt.jobloader.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.gov.ons.census.fwmt.events.component.GatewayEventManager;

@Configuration
public class GatewayEventsConfig {

  public static final String ACTION_INSTRUCTION_CREATE_SENT = "RM - ActionInstruction Sent to Action.Field";

  @Bean
  public GatewayEventManager gatewayEventManager() {
    GatewayEventManager gatewayEventManager = new GatewayEventManager();
    gatewayEventManager.addEventTypes(new String[] {ACTION_INSTRUCTION_CREATE_SENT});
    return gatewayEventManager;
  }
}
