package com.autodb_api.controllers;


import com.autodb_api.services.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auto")
public class AutoController {

    @Autowired
    private AutoService autoService;

    //@PatchMapping("/search")
    @GetMapping("/all")
    public ResponseEntity<?> getAll(
            @RequestParam Optional<Integer> limit,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy
    ) {
        return new ResponseEntity<>(autoService.getAllAutos(
                PageRequest.of(
                        page.orElse(0),
                        limit.orElse(5),
                        Sort.Direction.ASC,
                        sortBy.orElse("id"))),
                HttpStatus.OK);
    }

    @GetMapping("/make/{make}")
    public ResponseEntity<?> getByMake(
            @PathVariable String make,
            @RequestParam Optional<Integer> limit,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy
    ) {
        return new ResponseEntity<>(autoService.getAutosByMake(
                make,
                PageRequest.of(
                        page.orElse(0),
                        limit.orElse(5),
                        Sort.Direction.ASC,
                        sortBy.orElse("id"))),
                HttpStatus.OK);
    }

    @GetMapping("/model/{model}")
    public ResponseEntity<?> getByModel(
            @PathVariable String model,
            @RequestParam Optional<Integer> limit,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy
    ) {
        return new ResponseEntity<>(autoService.getAutosByModel(
                model,
                PageRequest.of(
                        page.orElse(0),
                        limit.orElse(5),
                        Sort.Direction.ASC,
                        sortBy.orElse("id"))),
                HttpStatus.OK);
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<?> getByYear(
            @PathVariable Integer year,
            @RequestParam Optional<Integer> limit,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy
    ) {
        return new ResponseEntity<>(autoService.getAutosByYear(
                year,
                PageRequest.of(
                        page.orElse(0),
                        limit.orElse(5),
                        Sort.Direction.ASC,
                        sortBy.orElse("id"))),
                HttpStatus.OK);
    }


}


