package com.filament.client.Client.Filament.Controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.filament.client.Client.Filament.Request.FilamentAddingRequest;
import com.filament.client.Client.Filament.Request.FilamentSubtractionRequest;
import com.filament.client.Client.Filament.Service.FilamentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/filaments/")
public class FilamentController {
    private final FilamentService filamentService;

    public FilamentController(FilamentService filamentService) {
        this.filamentService = filamentService;
    }

    @PostMapping("add/")
    public ResponseEntity<?> addFilament(@RequestBody FilamentAddingRequest form) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(filamentService.getUid(form));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("Timeout");
        }
    }

    @PutMapping("subtraction/")
    public ResponseEntity<Void> subtraction(@RequestBody FilamentSubtractionRequest form) throws IOException, InterruptedException {
        filamentService.subtraction(form);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
