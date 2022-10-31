package com.autodb_api.services;

import com.autodb_api.models.Auto;
import com.autodb_api.repository.AutoRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutoService {

    @Autowired
    private AutoRepository autoRepository;

    public Optional<Auto> getAutoById(Integer id) {
        return autoRepository.findById(id);
    }

    public Auto saveAuto(Auto auto) {
        return autoRepository.save(auto);
    }

    public void deleteAuto(ObjectId id) {
        autoRepository.deleteById(id);
    }

    public Page<Auto> getAllAutos(Pageable pageable) {
        return autoRepository.findAll(pageable);
    }

    public Page<Auto> getAutosByMake(String make, Pageable pageable) {
        return autoRepository.findByMake(make, pageable);
    }

    public Page<Auto> getAutosByYear(Integer year, Pageable pageable) {
        return autoRepository.findByYear(year, pageable);
    }

    public Page<Auto> getAutosByModel(String model, Pageable pageable) {
        return autoRepository.findByModel(model, pageable);
    }
}

