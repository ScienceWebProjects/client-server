package com.filament.client.Client.Filament.Request;

import lombok.Data;

import java.net.InetAddress;

@Data
public class FilamentAddingRequest {
    private int port;
    private String ip;
}
