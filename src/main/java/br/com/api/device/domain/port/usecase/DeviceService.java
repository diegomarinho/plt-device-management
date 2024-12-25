package br.com.api.device.domain.port.usecase;

import br.com.api.device.application.common.MessageService;
import br.com.api.device.application.common.MessageType;
import br.com.api.device.domain.dto.DeviceDTO;
import br.com.api.device.domain.entity.Device;
import br.com.api.device.domain.factory.DeviceFactory;
import br.com.api.device.infrastructure.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final MessageService messageService;

    public DeviceService(DeviceRepository deviceRepository, MessageService messageService) {
        this.deviceRepository = deviceRepository;
        this.messageService = messageService;
    }

    public DeviceDTO addDevice(DeviceDTO deviceDTO) {
        Device device = new Device();
        device.setName(deviceDTO.getName());
        device.setBrand(deviceDTO.getBrand());
        device.setCreationTime(deviceDTO.getCreationTime());
        Device savedDevice = deviceRepository.save(device);
        return DeviceFactory.transformToDTO(savedDevice);
    }

    public DeviceDTO updateDevice(Long deviceId, DeviceDTO updatedDTO) {
        Device existingDevice = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new IllegalArgumentException(String.valueOf(messageService.getMessage(MessageType.DEVICE_NOT_FOUND, deviceId))));

        existingDevice.setName(updatedDTO.getName());
        existingDevice.setBrand(updatedDTO.getBrand());
        Device updatedDevice = deviceRepository.save(existingDevice);
        return DeviceFactory.transformToDTO(updatedDevice);
    }

    public void deleteDevice(Long deviceId) {
        if (!deviceRepository.existsById(deviceId)) {
            throw new IllegalArgumentException(String.valueOf(messageService.getMessage(MessageType.DEVICE_NOT_FOUND, deviceId)));
        }
        deviceRepository.deleteById(deviceId);
    }

    public DeviceDTO getDeviceById(Long deviceId) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new IllegalArgumentException(String.valueOf(messageService.getMessage(MessageType.DEVICE_NOT_FOUND, deviceId))));
        return DeviceFactory.transformToDTO(device);
    }

    public List<DeviceDTO> listAllDevices(int offset, int limit) {
        return deviceRepository.findAll().stream()
                .skip(offset)
                .limit(limit)
                .map(DeviceFactory::transformToDTO)
                .collect(Collectors.toList());
    }

    public long countAllDevices() {
        return deviceRepository.count();
    }

    public List<DeviceDTO> searchByBrand(String brand) {
        return deviceRepository.findByBrand(brand).stream()
                .map(DeviceFactory::transformToDTO)
                .collect(Collectors.toList());
    }
}
