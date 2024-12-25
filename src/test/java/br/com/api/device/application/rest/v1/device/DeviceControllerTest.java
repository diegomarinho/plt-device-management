package br.com.api.device.application.rest.v1.device;

import br.com.api.device.application.common.*;
import br.com.api.device.domain.dto.DeviceDTO;
import br.com.api.device.domain.usecase.DeviceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static br.com.api.device.application.common.HTTPStatus.BAD_REQUEST;
import static br.com.api.device.application.common.HTTPStatus.OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class DeviceControllerTest {

    private static final long DEVICE_ID = 1L;
    private static final int PAGE_SIZE = 10;

    @InjectMocks
    private DeviceController deviceController;

    @Mock
    private DeviceService deviceService;

    @Mock
    private MessageService messageService;

    @Mock
    private ApiResponseMessage apiResponseMessage;

    private DeviceDTO deviceDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        deviceDTO = DeviceDTO.builder()
                .id(DEVICE_ID)
                .name("Smartphone X200")
                .brand("XPhone")
                .creationTime(null) // Valor default para LocalDateTime
                .build();
    }

    @Test
    void addDevice_shouldReturnAddedDevice() {
        // Arrange
        when(deviceService.addDevice(any(DeviceDTO.class))).thenReturn(deviceDTO);
        when(messageService.getMessage(MessageType.DEVICE_ADDED_SUCCESS)).thenReturn(apiResponseMessage);

        // Act
        ResponseEntity<ApiResponse<DeviceDTO>> response = deviceController.addDevice(deviceDTO);

        // Assert
        assertResponse(response, OK, deviceDTO);
    }

    @Test
    void getDeviceById_shouldReturnDevice_whenDeviceExists() {
        // Arrange
        when(deviceService.getDeviceById(DEVICE_ID)).thenReturn(deviceDTO);
        when(messageService.getMessage(MessageType.DEVICE_ADDED_SUCCESS)).thenReturn(apiResponseMessage);

        // Act
        ResponseEntity<ApiResponse<DeviceDTO>> response = deviceController.getDeviceById(DEVICE_ID);

        // Assert
        assertResponse(response, OK, deviceDTO);
    }

    @Test
    void getDeviceById_shouldReturnBadRequest_whenDeviceNotFound() {
        // Arrange
        when(deviceService.getDeviceById(DEVICE_ID)).thenThrow(new IllegalArgumentException("Device not found"));
        when(messageService.getMessage(MessageType.DEVICE_NOT_FOUND, DEVICE_ID)).thenReturn(apiResponseMessage);

        // Act
        ResponseEntity<ApiResponse<DeviceDTO>> response = deviceController.getDeviceById(DEVICE_ID);

        // Assert
        assertResponse(response, BAD_REQUEST, null);
    }

    @Test
    void listAllDevices_shouldReturnDevices() {
        // Arrange
        List<DeviceDTO> devices = List.of(deviceDTO);
        when(deviceService.listAllDevices(0, PAGE_SIZE)).thenReturn(devices);
        when(deviceService.countAllDevices()).thenReturn(1L);
        when(messageService.getMessage(MessageType.DEVICE_ADDED_SUCCESS)).thenReturn(apiResponseMessage);

        // Act
        ResponseEntity<ApiResponse<List<DeviceDTO>>> response = deviceController.listAllDevices(0, PAGE_SIZE);

        // Assert
        assertResponse(response, OK, devices);
    }

    private void assertResponse(ResponseEntity<ApiResponse<DeviceDTO>> response, HTTPStatus status, DeviceDTO expectedDevice) {
        assertEquals(status.getStatusCode(), response.getStatusCodeValue());
        assertNotNull(response.getBody());
        if (expectedDevice != null) {
            assertEquals(expectedDevice, response.getBody().getResult());
        }
    }

    private void assertResponse(ResponseEntity<ApiResponse<List<DeviceDTO>>> response, HTTPStatus status, List<DeviceDTO> expectedDevices) {
        assertEquals(status.getStatusCode(), response.getStatusCodeValue());
        assertNotNull(response.getBody());
        if (expectedDevices != null) {
            assertEquals(expectedDevices.size(), Optional.ofNullable(response.getBody())
                    .map(ApiResponse::getResult)
                    .map(List::size)
                    .orElse(0));
        }
    }
}
