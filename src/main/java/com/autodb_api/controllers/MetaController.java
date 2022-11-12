package com.autodb_api.controllers;

import com.autodb_api.models.Meta;
import com.autodb_api.services.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
@RequestMapping("/meta")
public class MetaController {
    @Autowired private MetaService metaService;

    private static final String PATH_VARIABLE_PATTERN_SEARCH = "/types/**";

    @GetMapping("make/{make}")
    public ResponseEntity<?> getMake(@PathVariable String make) {
        return new ResponseEntity<>(metaService.getModelsByMake(make), HttpStatus.OK);
    }

    @GetMapping("aux/{aux}")
    public ResponseEntity<?> getAux(@PathVariable String aux) {
        return new ResponseEntity<>(metaService.getMetasByAux(aux), HttpStatus.OK);
    }

    @RequestMapping(value = PATH_VARIABLE_PATTERN_SEARCH, method = RequestMethod.GET)
    public ResponseEntity<?> getAllMetas(
            HttpServletRequest request,
            @RequestParam Optional<Integer> limit,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy

    ) throws URISyntaxException {
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
        //Get URL from request
        String url = request.getRequestURL().toString();
        URI uri = new URI(url);
        String path = uri.getPath(); // split whatever you need
        String[] queries = path.split("/");

        //Slice the array to bypass search and get the actual query
        ArrayList<String> params = new ArrayList<>();
        for(String query : queries) {
            if(query.contains("type") || query.contains("meta") || query.contains("all") || query.isBlank()) {
                continue;
            } else {
                params.add(query);
            }
        }

        return new ResponseEntity<>(metaService.getAllMetasMap(params), HttpStatus.OK);

    }

    @GetMapping("/{type}")
    public ResponseEntity<?> getMetasByType(@PathVariable String type) {
        System.out.println("type: " + type);
        return new ResponseEntity<>(metaService.getMetasByType(type), HttpStatus.OK);
    }

}
