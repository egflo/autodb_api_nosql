package com.autodb_api.controllers;


import com.autodb_api.models.Auto;
import com.autodb_api.services.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/auto")
public class AutoController {

    private static final String PATH_VARIABLE_PATTERN_SEARCH = "/search/**";

    @Autowired
    private AutoService autoService;


    @GetMapping("/{id}")
    public ResponseEntity<?> getAutoById(@PathVariable String id) {
        return new ResponseEntity<>(autoService.getAutoByListingId(id), HttpStatus.OK);
    }

    @RequestMapping(value = PATH_VARIABLE_PATTERN_SEARCH, method = RequestMethod.GET)
    public ResponseEntity<Page<Auto>> search(
            HttpServletRequest request,
            @RequestParam Optional<String> colorCode,
            @RequestParam Optional<String> bodyCode,
            @RequestParam Optional<String> drivetrainCode,
            @RequestParam Optional<String> fuelCode,
            @RequestParam Optional<String> transmissionCode,
            @RequestParam Optional<Integer> startYear,
            @RequestParam Optional<Integer> endYear,
            @RequestParam Optional<Double> mileage,
            @RequestParam Optional<Integer> postcode,
            @RequestParam Optional<Integer> radius,
            @RequestParam Optional<Double> priceMin,
            @RequestParam Optional<Double> priceMax,
            @RequestParam Optional<String> conditionCode,
            @RequestParam Optional<String> modelCode,
            @RequestParam Optional<Integer> limit,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<Integer> sortDirection,
            @RequestParam Optional<String> sortBy) throws URISyntaxException {


        Sort.Direction direction = Sort.Direction.ASC;
        if (sortDirection.isPresent() && sortDirection.get() == 1) {
            direction = Sort.Direction.DESC;
        }

        Pageable pageable =
                PageRequest.of(page.orElse(0), limit.orElse(10), direction, sortBy.orElse("id"));

        //Get URL from request
        String url = request.getRequestURL().toString();
        URI uri = new URI(url);
        String path = uri.getPath(); // split whatever you need
        String[] queries = path.split("/");

        //Slice the array to bypass search and get the actual query
        ArrayList<String> params = new ArrayList<>();
        for(String query : queries) {
            if(query.contains("search") || query.contains("auto") || query.isBlank()) {
                continue;
            } else {
                params.add(query);
            }
        }

        return new ResponseEntity<>(autoService.search(params, colorCode, bodyCode,
                drivetrainCode, fuelCode, transmissionCode, startYear, endYear, mileage,
                postcode, radius, priceMin, priceMax, conditionCode, modelCode,
                pageable), HttpStatus.OK);
    }

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


