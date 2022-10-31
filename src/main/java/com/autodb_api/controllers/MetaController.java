package com.autodb_api.controllers;

import com.autodb_api.models.Meta;
import com.autodb_api.services.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/meta")
public class MetaController {
    @Autowired private MetaService metaService;




    @GetMapping("/all")
    public ResponseEntity<?> getAllMetas(
            @RequestParam Optional<Integer> limit,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy

    ) {
        /**
         *
         *         return new ResponseEntity<>(metaService.getAllMetas(
         *                 PageRequest.of(
         *                         page.orElse(0),
         *                         limit.orElse(5),
         *                         Sort.Direction.ASC,
         *                         sortBy.orElse("id"))),
         *                 HttpStatus.OK);
         *
         *
         */

        return new ResponseEntity<>(metaService.getAllMetas(), HttpStatus.OK);

    }

    @GetMapping("/{type}")
    public ResponseEntity<?> getMetasByType(@PathVariable String type) {
        System.out.println("type: " + type);
        return new ResponseEntity<>(metaService.getMetasByType(type), HttpStatus.OK);
    }

}
