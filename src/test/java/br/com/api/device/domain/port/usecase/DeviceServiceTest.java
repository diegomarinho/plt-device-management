package br.com.api.device.domain.port.usecase;

import br.com.api.device.application.common.MessageService;
import br.com.api.device.application.common.MessageType;
import br.com.api.device.domain.dto.DeviceDTO;
import br.com.api.device.domain.entity.Device;
import br.com.api.device.infrastructure.repository.DeviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeviceServiceTest {

    @InjectMocks
    private DeviceService deviceService;

    @Mock
    private DeviceRepository deviceRepository;

    @Mock
    private MessageService messageService;

    private Device device;
    private DeviceDTO deviceDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        device = new Device(1L, "Smartphone X200", "XPhone", null);
        deviceDTO = DeviceDTO.builder()
                .id(1L)
                .name("Smartphone X200")
                .brand("XPhone")
                .creationTime(null)
                .build();
    }

    @Test
    void addDevice_shouldReturnDeviceDTO() {
        // Arrange
        when(deviceRepository.save(any(Device.class))).thenReturn(device);
        when(messageService.getMessage(MessageType.DEVICE_ADDED_SUCCESS)).thenReturn(null);

        // Act
        DeviceDTO result = deviceService.addDevice(deviceDTO);

        // Assert
        assertNotNull(result);
        assertEquals(device.getId(), result.getId());
        assertEquals(device.getName(), result.getName());
    }

    @Test
    void updateDevice_shouldReturnUpdatedDeviceDTO() {
        // Arrange
        when(deviceRepository.findById(1L)).thenReturn(Optional.of(device));
        when(deviceRepository.save(any(Device.class))).thenReturn(device);

        // Act
        DeviceDTO updatedDeviceDTO = deviceService.updateDevice(1L, deviceDTO);

        // Assert
        assertNotNull(updatedDeviceDTO);
        assertEquals(deviceDTO.getName(), updatedDeviceDTO.getName());
    }

    @Test
    void updateDevice_shouldThrowException_whenDeviceNotFound() {
        // Arrange
        when(deviceRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> deviceService.updateDevice(1L, deviceDTO));
    }

    @Test
    void deleteDevice_shouldExecuteSuccessfully() {
        // Arrange
        when(deviceRepository.existsById(1L)).thenReturn(true);

        // Act
        deviceService.deleteDevice(1L);

        // Assert
        verify(deviceRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteDevice_shouldThrowException_whenDeviceNotFound() {
        // Arrange
        when(deviceRepository.existsById(1L)).thenReturn(false);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> deviceService.deleteDevice(1L));
    }
}
