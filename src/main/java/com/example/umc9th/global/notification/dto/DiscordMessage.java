package com.example.umc9th.global.notification.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DiscordMessage {
    private String content;

    @JsonProperty("embeds")
    private Embed[] embeds;

    @Getter
    @Builder
    public static class Embed {
        private String title;
        private String description;
        private int color;
    }
}
