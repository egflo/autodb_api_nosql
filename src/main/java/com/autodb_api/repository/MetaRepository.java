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


    @Query("aggregate([{$group: {_id: '$type', count: {$sum: 1}}}, {$match: {count: {$gt: 1}}}])")
    Iterable<Meta> getAllMetaGroupByType();

    Iterable<String> findDistinctByType();

    Iterable<Meta> findAllByName(String name);

    Iterable<Meta> findAllByType(String type);

    Iterable<Meta> findMetaByAux(String aux);
}