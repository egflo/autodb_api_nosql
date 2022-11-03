package com.autodb_api.services;

import com.autodb_api.models.Auto;
import com.autodb_api.models.Meta;
import com.autodb_api.models.MetaType;
import com.autodb_api.repository.AutoRepository;
import com.autodb_api.repository.MetaRepository;
import com.autodb_api.repository.MetaTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MetaService {

    @Autowired
    private MetaRepository metaRepository;

    @Autowired
    private MetaTypeRepository metaTypeRepository;

    public Map<String, List<Meta>> getAllMetasMap() {


        Map<String, List<Meta>> metaMap = new HashMap<>();
        List<MetaType> types = metaTypeRepository.findAll();

        for (MetaType type : types) {

            System.out.println("type: " + type.getType());

           List<Meta> metaType = (List<Meta>) metaRepository.findAllByType(type.getType());
            metaMap.put(type.getType(), metaType);
        }

        return metaMap;

    }

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



