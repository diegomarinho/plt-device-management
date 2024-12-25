package br.com.api.device.domain.port.usecase;

import br.com.api.device.domain.dto.DeviceDTO;
import br.com.api.device.domain.entity.Device;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

    public Device createDeviceFromDTO(DeviceDTO deviceDTO) {
        Device device = new Device();
        device.setName(deviceDTO.getName());
        device.setBrand(deviceDTO.getBrand());
        device.setCreationTime(deviceDTO.getCreationTime());
        return device;
    }

    public Device getDeviceById(Long deviceId) {
        return new Device(deviceId, "Dummy Device", "BrandX", null); // Simulação, pois o repositório é chamado no Service.
    }

    public void updateDeviceFromDTO(Device device, DeviceDTO deviceDTO) {
        device.setName(deviceDTO.getName());
        device.setBrand(deviceDTO.getBrand());
        device.setCreationTime(deviceDTO.getCreationTime());
    }
}
