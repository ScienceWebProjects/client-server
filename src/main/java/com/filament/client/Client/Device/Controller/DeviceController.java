package com.filament.client.Client.Device.Controller;

import com.filament.client.Client.Device.Service.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/device/")
public class DeviceController {
    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }
    @GetMapping("adding/add/")
    public ResponseEntity<Map<String, Object>> addAddingDevice(){
        return ResponseEntity.status(HttpStatus.CREATED).body(deviceService.addAddingDevice());
    }

    @GetMapping("connect")
    public ResponseEntity<Map<String,Object>> connectToPrinter(){
        return ResponseEntity.status(HttpStatus.OK).body(deviceService.connect());
    }
}
