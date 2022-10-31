package com.autodb_api.services;

import com.autodb_api.models.Auto;
import com.autodb_api.models.Meta;
import com.autodb_api.repository.AutoRepository;
import com.autodb_api.repository.MetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MetaService {

    @Autowired
    private MetaRepository metaRepository;

    //public Page<Meta> getAllMetas(Pageable pageable) {
    //    return metaRepository.findAll(pageable);
   // }

    public Iterable<Meta> getAllMetas() {
        return metaRepository.getAllMeta();
    }

    public Iterable<Meta> getMetasByName(String name) {
        return metaRepository.findAllByName(name);
    }

    public Iterable<Meta> getMetasByType(String type) {
        return metaRepository.findAllByType(type);
    }


}



