package com.GrooveSpring.socket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
    }
    @Override public void registerStompEndpoints(StompEndpointRegistry registry){
        registry.addEndpoint("/stomp", "/conversationChat")
                .setAllowedOrigins("http://localhost:4200", "http://localhost:4201","http://localhost:4202", "http://localhost:4203");
//                .withSockJS();
    }


}

