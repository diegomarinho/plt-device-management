package br.com.api.device.domain.factory;

import br.com.api.device.domain.dto.DeviceDTO;
import br.com.api.device.domain.entity.Device;

public class SmartphoneDeviceFactory extends DeviceFactory {

    @Override
    public Device createDevice(DeviceDTO deviceDTO) {
        Device device = new Device();
        device.setId(deviceDTO.getId());
        device.setName(deviceDTO.getName());
        device.setBrand(deviceDTO.getBrand());
        device.setCreationTime(deviceDTO.getCreationTime());
        return device;
    }
}
