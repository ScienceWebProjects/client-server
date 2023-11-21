package com.filament.client.Client.Filament.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.filament.client.Client.Filament.Request.FilamentAddingRequest;
import com.filament.client.Client.Filament.Request.FilamentSubtractionRequest;
import com.filament.client.Client.UDP.UDP;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

@Service
public class FilamentService {
    @Value("${server.url}")
    private String serverURL;
    private final UDP udp;

    public FilamentService(UDP udp) {
        this.udp = udp;
    }

    public Object getUid(FilamentAddingRequest form) throws IOException {
        DatagramPacket uid = udp.send(
                "adding",
                form.getPort(),
                form.getIp(),
                10000
        );
        return Long.parseLong(new String(uid.getData(), 0, uid.getLength()));
    }

    public void subtraction(FilamentSubtractionRequest form) throws IOException, InterruptedException {
        HashMap<String,Object> data = new HashMap<>();
        data.put("uid",form.getUid());
        data.put("ip",form.getIp());
        data.put("hours",form.getHours());
        data.put("quantity",form.getQuantity());
        data.put("company",1);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(data);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create(serverURL+"filaments/add/"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonData))
                .build();
        HttpResponse<String> postResponse = client.send(postRequest, HttpResponse.BodyHandlers.ofString());

    }
}
