package br.com.api.device.application.rest.v1.device;

import br.com.api.device.application.common.*;
import br.com.api.device.domain.dto.DeviceDTO;
import br.com.api.device.domain.usecase.DeviceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/devices")
@RequiredArgsConstructor
@Tag(name = "Device Management", description = "Operations for managing devices")
public class DeviceController {

    private final DeviceService service;
    private final MessageService messageService;

    @Operation(
            summary = "Add a new device",
            description = "Create a new device with the provided information"
    )
    @PostMapping
    public ResponseEntity<ApiResponse<DeviceDTO>> addDevice(
            @RequestBody DeviceDTO dto) {
        DeviceDTO addedDevice = service.addDevice(dto);
        ApiResponseMessage message = messageService.getMessage(MessageType.DEVICE_ADDED_SUCCESS);
        return ResponseEntity.ok(ApiResponse.of(addedDevice, message));
    }

    @Operation(
            summary = "Get device by ID",
            description = "Retrieve a device by its unique identifier"
    )
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DeviceDTO>> getDeviceById(
            @PathVariable Long id) {
        try {
            DeviceDTO deviceDTO = service.getDeviceById(id);
            ApiResponseMessage message = messageService.getMessage(MessageType.DEVICE_ADDED_SUCCESS);
            return ResponseEntity.ok(ApiResponse.of(deviceDTO, message));
        } catch (IllegalArgumentException e) {
            ApiResponseMessage message = messageService.getMessage(MessageType.DEVICE_NOT_FOUND, id);
            return ResponseEntity.badRequest().body(ApiResponse.of(null, message));
        }
    }

    @Operation(
            summary = "List all devices",
            description = "Fetch a list of all registered devices"
    )
    @GetMapping
    public ResponseEntity<ApiResponse<List<DeviceDTO>>> listAllDevices(
            @RequestParam int offset,
            @RequestParam int limit) {
        List<DeviceDTO> devices = service.listAllDevices(offset, limit);
        long count = service.countAllDevices();
        ApiResponsePageable pageable = new ApiResponsePageable(offset, limit, count);
        ApiResponseMessage message = messageService.getMessage(MessageType.DEVICE_ADDED_SUCCESS);
        return ResponseEntity.ok(ApiResponse.ofPaged(devices, message, pageable));
    }

    @Operation(
            summary = "Update an existing device",
            description = "Update an existing device's information"
    )
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DeviceDTO>> updateDevice(
            @PathVariable Long id,
            @RequestBody DeviceDTO dto) {
        try {
            DeviceDTO updatedDevice = service.updateDevice(id, dto);
            ApiResponseMessage message = messageService.getMessage(MessageType.DEVICE_UPDATED_SUCCESS);
            return ResponseEntity.ok(ApiResponse.of(updatedDevice, message));
        } catch (IllegalArgumentException e) {
            ApiResponseMessage message = messageService.getMessage(MessageType.DEVICE_NOT_FOUND, id);
            return ResponseEntity.badRequest().body(ApiResponse.of(null, message));
        }
    }

    @Operation(
            summary = "Delete a device",
            description = "Remove a device from the system by its ID"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteDevice(
            @PathVariable Long id) {
        try {
            service.deleteDevice(id);
            ApiResponseMessage message = messageService.getMessage(MessageType.DEVICE_DELETED_SUCCESS);
            return ResponseEntity.ok(ApiResponse.of(null, message));
        } catch (IllegalArgumentException e) {
            ApiResponseMessage message = messageService.getMessage(MessageType.DEVICE_NOT_FOUND, id);
            return ResponseEntity.badRequest().body(ApiResponse.of(null, message));
        }
    }

    @Operation(
            summary = "Search devices by brand",
            description = "Search and list devices by their brand"
    )
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<DeviceDTO>>> searchByBrand(
            @RequestParam String brand) {
        List<DeviceDTO> devices = service.searchByBrand(brand);
        ApiResponseMessage message = messageService.getMessage(MessageType.DEVICE_ADDED_SUCCESS);
        return ResponseEntity.ok(ApiResponse.of(devices, message));
    }
}
