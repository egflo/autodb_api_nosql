package com.autodb_api.repository;

import com.autodb_api.models.Meta;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MetaRepository extends MongoRepository<Meta, ObjectId> {

    @Query("{}")
    Iterable<Meta> getAllMeta();

    //Page<Meta> findAll(Pageable pageable);

    Iterable<Meta> findAllByName(String name);

    Iterable<Meta> findAllByType(String type);
}