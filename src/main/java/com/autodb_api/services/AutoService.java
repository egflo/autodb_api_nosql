package com.autodb_api.services;

import com.autodb_api.dao.AutoDao;
import com.autodb_api.models.Auto;
import com.autodb_api.repository.AutoRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AutoService {

    @Autowired
    private AutoDao autoDao;

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

    public Page<Auto> search(ArrayList<String> queries,
                             Optional<String> color_code,
                             Optional<String> body_code,
                             Optional<String> drivetrain_code,
                             Optional<String> fuel_code,
                             Optional<String> transmission_code,
                             Optional<Integer> start_year,
                             Optional<Integer> end_year,
                             Optional<Double> mileage,
                             Optional<String> postcode,
                             Optional<Integer> radius,
                             Optional<Double> price_min,
                             Optional<Double> price_max,
                             Optional<String> condition_code,
                             Optional<String> model_code,
                             Pageable pageable) {

        //AutoDao autoDao = new AutoDao(entityManager, bodyTypeRepository, makeRepository, locationRepository);
        return autoDao.findAutoByParams(queries, color_code, body_code, drivetrain_code,
                fuel_code, transmission_code, start_year, end_year, mileage, postcode, radius,
                price_min, price_max, condition_code, model_code, pageable);
    }

    public Optional<Auto> getAutoByListingId(String listing_id) {
        return autoRepository.findByListingId(listing_id);
    }

}

