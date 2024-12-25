package br.com.api.device.application.rest.v1.device;

import br.com.api.device.application.common.ApiResponse;
import br.com.api.device.application.common.ApiResponseMessage;
import br.com.api.device.application.common.MessageService;
import br.com.api.device.application.common.MessageType;
import br.com.api.device.domain.dto.DeviceDTO;
import br.com.api.device.domain.services.DeviceApplicationService;
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

    private final DeviceApplicationService deviceApplicationService;
    private final MessageService messageService;

    @Operation(
            summary = "Add a new device",
            description = "Create a new device with the provided information"
    )
    @PostMapping
    public ResponseEntity<ApiResponse<DeviceDTO>> addDevice(@RequestBody DeviceDTO deviceDTO) {
        DeviceDTO addedDevice = deviceApplicationService.addDevice(deviceDTO);
        ApiResponseMessage message = messageService.getMessage(MessageType.DEVICE_ADDED_SUCCESS);
        return ResponseEntity.ok(ApiResponse.of(addedDevice, message));
    }

    @Operation(
            summary = "Get device by ID",
            description = "Retrieve a device by its unique identifier"
    )
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DeviceDTO>> getDeviceById(@PathVariable Long id) {
        DeviceDTO deviceDTO = deviceApplicationService.getDeviceById(id);
        ApiResponseMessage message = messageService.getMessage(MessageType.DEVICE_ADDED_SUCCESS);
        return ResponseEntity.ok(ApiResponse.of(deviceDTO, message));
    }

    @Operation(
            summary = "List all devices",
            description = "Fetch a list of all registered devices"
    )
    @GetMapping
    public ResponseEntity<ApiResponse<List<DeviceDTO>>> listAllDevices(
            @RequestParam int offset,
            @RequestParam int limit) {
        List<DeviceDTO> devices = deviceApplicationService.listAllDevices(offset, limit);
        ApiResponseMessage message = messageService.getMessage(MessageType.DEVICE_ADDED_SUCCESS);
        return ResponseEntity.ok(ApiResponse.of(devices, message));
    }

    @Operation(
            summary = "Update an existing device",
            description = "Update an existing device's information"
    )
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DeviceDTO>> updateDevice(@PathVariable Long id, @RequestBody DeviceDTO dto) {
        DeviceDTO updatedDevice = deviceApplicationService.updateDevice(id, dto);
        ApiResponseMessage message = messageService.getMessage(MessageType.DEVICE_UPDATED_SUCCESS);
        return ResponseEntity.ok(ApiResponse.of(updatedDevice, message));
    }

    @Operation(
            summary = "Delete a device",
            description = "Remove a device from the system by its ID"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteDevice(@PathVariable Long id) {
        deviceApplicationService.deleteDevice(id);
        ApiResponseMessage message = messageService.getMessage(MessageType.DEVICE_DELETED_SUCCESS);
        return ResponseEntity.ok(ApiResponse.of(null, message));
    }

    @Operation(
            summary = "Search devices by brand",
            description = "Search and list devices by their brand"
    )
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<DeviceDTO>>> searchByBrand(@RequestParam String brand) {
        List<DeviceDTO> devices = deviceApplicationService.searchByBrand(brand);
        ApiResponseMessage message = messageService.getMessage(MessageType.DEVICE_ADDED_SUCCESS);
        return ResponseEntity.ok(ApiResponse.of(devices, message));
    }
}
