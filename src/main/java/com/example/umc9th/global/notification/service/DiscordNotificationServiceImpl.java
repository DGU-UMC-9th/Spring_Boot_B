package com.example.umc9th.global.notification.service;

import com.example.umc9th.global.notification.dto.DiscordMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiscordNotificationServiceImpl implements DiscordNotificationService {

    @Value("${discord.webhook.url}")
    private String discordWebhookUrl;

    private final RestTemplate restTemplate;

    @Override
    public void sendMessage(DiscordMessage message) {
        try {
            log.info("Sending Discord notification.");
            restTemplate.postForObject(discordWebhookUrl, message, String.class);
        } catch (Exception e) {
            log.error("Failed to send Discord notification.", e);
        }
    }
}
