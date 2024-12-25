package br.com.api.device.application.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {
    private T result;
    private ApiResponseMessage message;

    // Para retorno paginado
    private ApiResponsePageable pageable;

    public static <T> ApiResponse<T> of(T result, ApiResponseMessage message) {
        return new ApiResponse<>(result, message, null);
    }

    public static <T> ApiResponse<T> ofPaged(T result, ApiResponseMessage message, ApiResponsePageable pageable) {
        return new ApiResponse<>(result, message, pageable);
    }
}
