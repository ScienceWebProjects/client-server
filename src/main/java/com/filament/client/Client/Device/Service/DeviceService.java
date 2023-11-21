package com.filament.client.Client.Device.Service;

import com.filament.client.Client.UDP.UDP;
import org.springframework.stereotype.Service;

import java.net.DatagramPacket;
import java.util.HashMap;
import java.util.Map;

@Service
public class DeviceService {
    private final UDP udp;

    public DeviceService(UDP udp) {
        this.udp = udp;
    }

    public Map<String,Object> addAddingDevice() {
        DatagramPacket receivePacket = udp.scanLocalHost(
                "dnhevwauior78q49nvfpoidbnklgfdoY%^73ciovmlfds",
                6842,
                40,
                "Adding device not found.");
        Map<String,Object> responseMap = new HashMap<>();
        responseMap.put("password",new String(receivePacket.getData(), 0, receivePacket.getLength()));
        responseMap.put("ip",receivePacket.getAddress());
        responseMap.put("port",receivePacket.getPort());
        return responseMap;
    }

    public Map<String,Object> connect() {
        DatagramPacket receivePacket = udp.scanLocalHost(
                "436bq57896bn47v80cgmbuiwshmxipojUVY4W785263Q9B",
                3984,
                40,
                "Measuring device not found.");
        Map<String,Object> responseMap = new HashMap<>();
        responseMap.put("ip",receivePacket.getAddress());
        responseMap.put("port",receivePacket.getPort());
        return responseMap;
    }
}
