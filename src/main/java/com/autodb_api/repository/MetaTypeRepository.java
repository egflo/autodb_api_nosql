package com.autodb_api.repository;

import com.autodb_api.models.Meta;
import com.autodb_api.models.MetaType;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MetaTypeRepository extends MongoRepository<MetaType, ObjectId> {

    List<MetaType> findAll();
}