package com.example.umc9th.global.notification.service;

import com.example.umc9th.global.notification.dto.DiscordMessage;

public interface DiscordNotificationService {
    void sendMessage(DiscordMessage message);
}
