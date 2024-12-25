package br.com.api.device.application.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ApiResponseMessage {
    private final String code;
    private final String description;
}