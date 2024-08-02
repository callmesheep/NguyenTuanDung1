package com.example.demo.Service;

import com.example.demo.Entity.Manufacturer;
import com.example.demo.Repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerService {
    @Autowired
    private ManufacturerRepository manufacturerRepository;

    public List<Manufacturer> getAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    public Manufacturer addManufacturer(Manufacturer manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }

    public Optional<Manufacturer> updateManufacturer(Long id, Manufacturer manufacturerDetails) {
        return manufacturerRepository.findById(id).map(manufacturer -> {
            manufacturer.setName(manufacturerDetails.getName());
            manufacturer.setOrigin(manufacturerDetails.getOrigin());
            manufacturer.setDescription(manufacturerDetails.getDescription());
            return manufacturerRepository.save(manufacturer);
        });
    }

    public boolean deleteManufacturer(Long id) {
        return manufacturerRepository.findById(id).map(manufacturer -> {
            if (manufacturer.getProducts().isEmpty()) {
                manufacturerRepository.delete(manufacturer);
                return true;
            }
            return false;
        }).orElse(false);
    }
}
