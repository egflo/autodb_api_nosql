package com.autodb_api.repository;

import com.autodb_api.models.Auto;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("AutoRepository")
public interface AutoRepository extends MongoRepository<Auto, ObjectId> {


    Optional<Auto> findById(Integer id);

    @Query("{}")
    Page<Auto> findAll (Pageable pageable);

    @Query("{year: ?0}")
    Page<Auto> findByYear(Integer year, Pageable pageable);

    @Query("{make: ?0}")
    Page<Auto> findByMake(String name, Pageable pageable);

    //@Query("{model: {$regex: ?0, $options: 'i'}, year: ?1}")
    @Query("{model: ?0, year: ?1}")
    Page<Auto> findByModelAndYear(String name, Pageable pageable);

    Page<Auto> findByModel(String name, Pageable pageable);

}