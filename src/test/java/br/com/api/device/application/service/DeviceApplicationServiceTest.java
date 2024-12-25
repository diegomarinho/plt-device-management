package br.com.api.device.application.service;

import br.com.api.device.application.common.MessageService;
import br.com.api.device.application.common.MessageType;
import br.com.api.device.domain.dto.DeviceDTO;
import br.com.api.device.domain.entity.Device;
import br.com.api.device.domain.port.usecase.DeviceService;
import br.com.api.device.domain.services.DeviceApplicationService;
import br.com.api.device.infrastructure.repository.DeviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class DeviceApplicationServiceTest {

    @InjectMocks
    private DeviceApplicationService deviceApplicationService;

    @Mock
    private DeviceRepository deviceRepository;

    @Mock
    private MessageService messageService;

    @Mock
    private DeviceService deviceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddDevice() {
        DeviceDTO deviceDTO = DeviceDTO.create(1L, "Device1", "BrandA", null);
        Device device = new Device(1L, "Device1", "BrandA", null);

        when(deviceService.createDeviceFromDTO(deviceDTO)).thenReturn(device);
        when(deviceRepository.save(device)).thenReturn(device);

        DeviceDTO result = deviceApplicationService.addDevice(deviceDTO);

        assertNotNull(result);
        assertEquals(deviceDTO.getName(), result.getName());

        verify(deviceRepository, times(1)).save(device);
    }

    @Test
    void testUpdateDevice() {
        Long deviceId = 1L;
        DeviceDTO updatedDTO = DeviceDTO.create(1L, "UpdatedDevice", "BrandB", null);
        Device device = new Device(1L, "OldDevice", "BrandA", null);

        when(deviceService.getDeviceById(deviceId)).thenReturn(device);
        when(deviceRepository.save(device)).thenReturn(device);

        DeviceDTO result = deviceApplicationService.updateDevice(deviceId, updatedDTO);

        assertNotNull(result);

        verify(deviceRepository, times(1)).save(device);
    }

    @Test
    void testDeleteDevice() {
        Long deviceId = 1L;
        Device device = new Device(1L, "Device1", "BrandA", null);

        when(deviceService.getDeviceById(deviceId)).thenReturn(device);

        deviceApplicationService.deleteDevice(deviceId);

        verify(deviceRepository, times(1)).delete(device);
    }

    @Test
    void testListAllDevices() {
        Device device1 = new Device(1L, "Device1", "BrandA", null);
        Device device2 = new Device(2L, "Device2", "BrandB", null);

        when(deviceRepository.findAll()).thenReturn(List.of(device1, device2));

        List<DeviceDTO> devices = deviceApplicationService.listAllDevices(0, 10);

        assertNotNull(devices);
        assertEquals(2, devices.size());
    }
}
