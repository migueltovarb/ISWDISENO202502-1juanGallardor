package com.example.vehicleapi.controller;

import com.example.vehicleapi.model.Vehicle;
import com.example.vehicleapi.service.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vehicles")
@Validated
@CrossOrigin(origins = "*")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return this.buildOkResponse(vehicleService.getAllVehicles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable String id) {
        return this.buildOkResponse(vehicleService.getVehicleById(id));
    }

    @GetMapping("/placa/{placa}")
    public ResponseEntity<Vehicle> getVehicleByPlaca(@PathVariable @NotBlank String placa) {
        return this.buildOkResponse(vehicleService.getVehicleByPlaca(placa));
    }

    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@Valid @RequestBody Vehicle vehicle) {
        Vehicle created = vehicleService.createVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable String id, @Valid @RequestBody Vehicle vehicleDetails) {
        return this.buildOkResponse(vehicleService.updateVehicle(id, vehicleDetails));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Vehicle> partialUpdateVehicle(@PathVariable String id, @RequestBody Vehicle vehicleDetails) {
        return this.buildOkResponse(vehicleService.updateVehicle(id, vehicleDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteVehicle(@PathVariable String id) {
        vehicleService.deleteVehicle(id);
        return this.buildOkResponse(this.createDeletionResponse(id));
    }

    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<Vehicle>> getVehiclesByMarca(@PathVariable String marca) {
        return this.buildOkResponse(vehicleService.getVehiclesByMarca(marca));
    }

    @GetMapping("/marca/{marca}/modelo/{modelo}")
    public ResponseEntity<List<Vehicle>> getVehiclesByMarcaAndModelo(@PathVariable String marca, @PathVariable String modelo) {
        return this.buildOkResponse(vehicleService.getVehiclesByMarcaAndModelo(marca, modelo));
    }

    @GetMapping("/years")
    public ResponseEntity<List<Vehicle>> getVehiclesByYearRange(@RequestParam Integer startYear, @RequestParam Integer endYear) {
        return this.buildOkResponse(vehicleService.getVehiclesByYearRange(startYear, endYear));
    }

    @GetMapping("/combustible/{tipo}")
    public ResponseEntity<List<Vehicle>> getVehiclesByTipoCombustible(@PathVariable String tipo) {
        return this.buildOkResponse(vehicleService.getVehiclesByTipoCombustible(tipo));
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<Vehicle>> getVehiclesByColor(@PathVariable String color) {
        return this.buildOkResponse(vehicleService.getVehiclesByColor(color));
    }

    @GetMapping("/disponibilidad/{disponible}")
    public ResponseEntity<List<Vehicle>> getVehiclesByDisponibilidad(@PathVariable Boolean disponible) {
        return this.buildOkResponse(vehicleService.getVehiclesByDisponibilidad(disponible));
    }

    @GetMapping("/precio")
    public ResponseEntity<List<Vehicle>> getVehiclesByPriceRange(@RequestParam Double min, @RequestParam Double max) {
        return this.buildOkResponse(vehicleService.getVehiclesByPriceRange(min, max));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Vehicle>> searchVehicles(
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String modelo,
            @RequestParam(required = false) Integer año,
            @RequestParam(required = false) String tipoCombustible) {
        List<Vehicle> results = vehicleService.searchVehicles(marca, modelo, año, tipoCombustible);
        return this.buildOkResponse(results);
    }

    @GetMapping("/stats/marca/{marca}")
    public ResponseEntity<Map<String, Object>> getStatsByMarca(@PathVariable String marca) {
        long total = vehicleService.countVehiclesByMarca(marca);
        return this.buildOkResponse(this.createStatsResponse(marca, total));
    }

    @GetMapping("/check-placa/{placa}")
    public ResponseEntity<Map<String, Object>> checkPlacaAvailability(@PathVariable String placa) {
        boolean exists = vehicleService.existsByPlaca(placa);
        return this.buildOkResponse(this.createPlacaCheckResponse(placa, exists));
    }

    private <T> ResponseEntity<T> buildOkResponse(T body) {
        return ResponseEntity.ok(body);
    }

    private Map<String, String> createDeletionResponse(String id) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Vehículo eliminado exitosamente");
        response.put("id", id);
        return response;
    }

    private Map<String, Object> createStatsResponse(String marca, long total) {
        Map<String, Object> stats = new HashMap<>();
        stats.put("marca", marca);
        stats.put("total", total);
        return stats;
    }

    private Map<String, Object> createPlacaCheckResponse(String placa, boolean exists) {
        Map<String, Object> response = new HashMap<>();
        String normalizedPlaca = placa.toUpperCase();
        response.put("placa", normalizedPlaca);
        response.put("disponible", !exists);

        String mensaje = exists ? "Placa ya está en uso" : "Placa disponible";
        response.put("mensaje", mensaje);

        return response;
    }
}
