package br.com.api.device.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Representation of a Device")
public class DeviceDTO {
    @Schema(description = "Unique identifier of the device", example = "1")
    private Long id;

    @NotEmpty
    @Schema(description = "Name of the device", example = "Smartphone X200")
    private String name;

    @NotEmpty
    @Schema(description = "Brand of the device", example = "XPhone")
    private String brand;

    @NotNull
    @Schema(description = "Creation timestamp of the device", example = "2023-12-23T10:15:30")
    private LocalDateTime creationTime;

    // Método de fábrica público
    public static DeviceDTO create(Long id, String name, String brand, LocalDateTime creationTime) {
        return new DeviceDTO(id, name, brand, creationTime);
    }
}
