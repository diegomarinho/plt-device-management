package br.com.api.device.application.rest.v1.device;

import br.com.api.device.application.common.MessageService;
import br.com.api.device.domain.dto.DeviceDTO;
import br.com.api.device.domain.services.DeviceApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DeviceController.class)
class DeviceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeviceApplicationService deviceApplicationService;

    @MockBean
    private MessageService messageService;

    @BeforeEach
    void setUp() {
        Mockito.reset(deviceApplicationService, messageService);
    }

    @Test
    void testAddDevice() throws Exception {
        DeviceDTO deviceDTO = DeviceDTO.create(1L, "Device1", "BrandA", null);

        when(deviceApplicationService.addDevice(Mockito.any(DeviceDTO.class))).thenReturn(deviceDTO);

        mockMvc.perform(post("/api/v1/devices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Device1\", \"brand\": \"BrandA\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.name", is("Device1")))
        ;
    }

    @Test
    void testGetDeviceById() throws Exception {
        DeviceDTO deviceDTO = DeviceDTO.create(1L, "Device1", "BrandA", null);

        when(deviceApplicationService.getDeviceById(1L)).thenReturn(deviceDTO);

        mockMvc.perform(get("/api/v1/devices/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.name", is("Device1")))
        ;
    }
}
