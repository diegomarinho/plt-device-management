package br.com.api.device.domain.services;

import br.com.api.device.application.common.MessageService;
import br.com.api.device.domain.dto.DeviceDTO;
import br.com.api.device.domain.entity.Device;
import br.com.api.device.domain.factory.DeviceFactory;
import br.com.api.device.domain.port.usecase.DeviceService;
import br.com.api.device.infrastructure.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceApplicationService {

    private final DeviceRepository deviceRepository;
    private final MessageService messageService;
    private final DeviceService deviceService;

    public DeviceApplicationService(DeviceRepository deviceRepository, MessageService messageService, DeviceService deviceService) {
        this.deviceRepository = deviceRepository;
        this.messageService = messageService;
        this.deviceService = deviceService;
    }

    public DeviceDTO addDevice(DeviceDTO deviceDTO) {
        Device device = deviceService.createDeviceFromDTO(deviceDTO);
        deviceRepository.save(device);
        return DeviceFactory.transformToDTO(device);
    }

    public DeviceDTO updateDevice(Long deviceId, DeviceDTO updatedDTO) {
        Device existingDevice = deviceService.getDeviceById(deviceId);
        deviceService.updateDeviceFromDTO(existingDevice, updatedDTO);
        deviceRepository.save(existingDevice);
        return DeviceFactory.transformToDTO(existingDevice);
    }

    public void deleteDevice(Long deviceId) {
        Device device = deviceService.getDeviceById(deviceId);
        deviceRepository.delete(device);
    }

    public DeviceDTO getDeviceById(Long deviceId) {
        Device device = deviceService.getDeviceById(deviceId);
        return DeviceFactory.transformToDTO(device);
    }

    public List<DeviceDTO> listAllDevices(int offset, int limit) {
        return deviceRepository.findAll().stream()
                .skip(offset)
                .limit(limit)
                .map(DeviceFactory::transformToDTO)
                .collect(Collectors.toList());
    }

    public List<DeviceDTO> searchByBrand(String brand) {
        return deviceRepository.findByBrand(brand).stream()
                .map(DeviceFactory::transformToDTO)
                .collect(Collectors.toList());
    }
}
