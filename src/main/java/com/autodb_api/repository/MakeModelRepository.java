package com.autodb_api.repository;

import com.autodb_api.models.MakeModel;
import com.autodb_api.models.MetaType;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MakeModelRepository extends MongoRepository<MakeModel, ObjectId> {

    List<MakeModel> findAll();

    List<MakeModel> findByMake(String make);

    List<MakeModel> findByModel(String model);
}