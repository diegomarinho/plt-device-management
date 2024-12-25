package br.com.api.device.domain.port.usecase;

import br.com.api.device.domain.dto.DeviceDTO;
import br.com.api.device.domain.entity.Device;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DeviceServiceTest {

    private final DeviceService deviceService = new DeviceService();

    @Test
    void testCreateDeviceFromDTO() {
        DeviceDTO deviceDTO = DeviceDTO.create(1L, "Device1", "BrandA", null);
        Device device = deviceService.createDeviceFromDTO(deviceDTO);

        assertNotNull(device);
        assertEquals(deviceDTO.getName(), device.getName());
        assertEquals(deviceDTO.getBrand(), device.getBrand());
    }

    @Test
    void testUpdateDeviceFromDTO() {
        Device device = new Device(1L, "OldDevice", "BrandA", null);
        DeviceDTO deviceDTO = DeviceDTO.create(1L, "UpdatedDevice", "BrandB", null);

        deviceService.updateDeviceFromDTO(device, deviceDTO);

        assertEquals(deviceDTO.getName(), device.getName());
        assertEquals(deviceDTO.getBrand(), device.getBrand());
    }
}
