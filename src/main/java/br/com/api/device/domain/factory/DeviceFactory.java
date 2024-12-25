package br.com.api.device.domain.factory;

import br.com.api.device.domain.dto.DeviceDTO;
import br.com.api.device.domain.entity.Device;

public abstract class DeviceFactory {

    public abstract Device createDevice(DeviceDTO deviceDTO);

    public static DeviceDTO transformToDTO(Device device) {
        return DeviceDTO.create(device.getId(), device.getName(), device.getBrand(), device.getCreationTime());
    }
}
