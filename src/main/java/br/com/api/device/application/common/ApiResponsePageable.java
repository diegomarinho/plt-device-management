package br.com.api.device.application.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponsePageable {
    private final int offset;
    private final int limit;
    private final long count;
}