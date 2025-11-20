package com.example.vehicleapi.service;

import com.example.vehicleapi.model.Vehicle;
import com.example.vehicleapi.repository.VehicleRepository;
import com.example.vehicleapi.exception.VehicleNotFoundException;
import com.example.vehicleapi.exception.DuplicatePlacaException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(String id) {
        Optional<Vehicle> vehicleOpt = vehicleRepository.findById(id);
        if (!vehicleOpt.isPresent()) {
            throw new VehicleNotFoundException("Vehículo no encontrado con ID: " + id);
        }
        return vehicleOpt.get();
    }

    public Vehicle getVehicleByPlaca(String placa) {
        String normalizedPlaca = placa.toUpperCase();
        Optional<Vehicle> vehicleOpt = vehicleRepository.findByPlaca(normalizedPlaca);
        if (!vehicleOpt.isPresent()) {
            throw new VehicleNotFoundException("Vehículo no encontrado con placa: " + placa);
        }
        return vehicleOpt.get();
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        String normalizedPlaca = vehicle.getPlaca() != null ? vehicle.getPlaca().toUpperCase() : null;

        if (normalizedPlaca != null) {
            boolean placaExists = vehicleRepository.existsByPlaca(normalizedPlaca);
            if (placaExists) {
                throw new DuplicatePlacaException("Ya existe un vehículo con la placa: " + vehicle.getPlaca());
            }
        }

        this.normalizeVehicleData(vehicle);
        LocalDateTime now = LocalDateTime.now();
        vehicle.setFechaCreacion(now);
        vehicle.setFechaActualizacion(now);

        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicle(String id, Vehicle vehicleDetails) {
        Vehicle existingVehicle = this.getVehicleById(id);

        String newPlaca = vehicleDetails.getPlaca();
        if (newPlaca != null) {
            String normalizedNewPlaca = newPlaca.toUpperCase();
            String existingPlaca = existingVehicle.getPlaca();

            boolean placaChanged = !normalizedNewPlaca.equalsIgnoreCase(existingPlaca);
            if (placaChanged && vehicleRepository.existsByPlaca(normalizedNewPlaca)) {
                throw new DuplicatePlacaException("Ya existe un vehículo con la placa: " + newPlaca);
            }
        }

        this.applyUpdates(existingVehicle, vehicleDetails);
        existingVehicle.setFechaActualizacion(LocalDateTime.now());

        return vehicleRepository.save(existingVehicle);
    }

    public void deleteVehicle(String id) {
        Vehicle vehicle = this.getVehicleById(id);
        vehicleRepository.delete(vehicle);
    }

    public List<Vehicle> getVehiclesByMarca(String marca) {
        return vehicleRepository.findByMarcaIgnoreCase(marca);
    }

    public List<Vehicle> getVehiclesByMarcaAndModelo(String marca, String modelo) {
        return vehicleRepository.findByMarcaIgnoreCaseAndModeloIgnoreCase(marca, modelo);
    }

    public List<Vehicle> getVehiclesByYearRange(Integer startYear, Integer endYear) {
        return vehicleRepository.findByAñoBetween(startYear, endYear);
    }

    public List<Vehicle> getVehiclesByTipoCombustible(String tipoCombustible) {
        return vehicleRepository.findByTipoCombustibleIgnoreCase(tipoCombustible);
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        return vehicleRepository.findByColorIgnoreCase(color);
    }

    public List<Vehicle> getVehiclesByDisponibilidad(Boolean disponible) {
        return vehicleRepository.findByDisponible(disponible);
    }

    public List<Vehicle> getVehiclesByPriceRange(Double precioMin, Double precioMax) {
        return vehicleRepository.findByPrecioBetween(precioMin, precioMax);
    }

    public List<Vehicle> getVehiclesByMaxKilometraje(Double maxKilometraje) {
        return vehicleRepository.findByKilometrajeLessThanEqual(maxKilometraje);
    }

    public List<Vehicle> searchVehicles(String marca, String modelo, Integer año, String tipoCombustible) {
        return vehicleRepository.findByMultipleCriteria(marca, modelo, año, tipoCombustible);
    }

    public long countVehiclesByMarca(String marca) {
        return vehicleRepository.countByMarcaIgnoreCase(marca);
    }

    public boolean existsByPlaca(String placa) {
        String normalizedPlaca = placa.toUpperCase();
        return vehicleRepository.existsByPlaca(normalizedPlaca);
    }

    private void normalizeVehicleData(Vehicle vehicle) {
        this.updateIfNotNull(vehicle.getPlaca(), val -> vehicle.setPlaca(val.toUpperCase()));
        this.updateIfNotNull(vehicle.getMarca(), val -> vehicle.setMarca(val.toUpperCase()));
        this.updateIfNotNull(vehicle.getModelo(), val -> vehicle.setModelo(val.toUpperCase()));
        this.updateIfNotNull(vehicle.getColor(), val -> vehicle.setColor(val.toUpperCase()));
        this.updateIfNotNull(vehicle.getTipoCombustible(), val -> vehicle.setTipoCombustible(val.toUpperCase()));
    }

    private void applyUpdates(Vehicle target, Vehicle source) {
        this.updateIfNotNull(source.getMarca(), val -> target.setMarca(val.toUpperCase()));
        this.updateIfNotNull(source.getModelo(), val -> target.setModelo(val.toUpperCase()));
        this.updateIfNotNull(source.getAño(), target::setAño);
        this.updateIfNotNull(source.getColor(), val -> target.setColor(val.toUpperCase()));
        this.updateIfNotNull(source.getPlaca(), val -> target.setPlaca(val.toUpperCase()));
        this.updateIfNotNull(source.getTipoCombustible(), val -> target.setTipoCombustible(val.toUpperCase()));
        this.updateIfNotNull(source.getKilometraje(), target::setKilometraje);
        this.updateIfNotNull(source.getPrecio(), target::setPrecio);
        this.updateIfNotNull(source.getDescripcion(), target::setDescripcion);
        this.updateIfNotNull(source.getDisponible(), target::setDisponible);
    }

    private <T> void updateIfNotNull(T value, Consumer<T> updater) {
        if (value != null) {
            updater.accept(value);
        }
    }
}
