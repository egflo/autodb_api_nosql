package com.autodb_api.repository;

import com.autodb_api.models.Location;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("LocationRepository")
public interface LocationRepository extends MongoRepository<Location, ObjectId> {

    Optional<Location> findById(Integer id);

    Page<Location> findAll (Pageable pageable);

    Optional<Location> findByPostcode(Integer postcode);


}