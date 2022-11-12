package com.autodb_api.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.autodb_api.dto.WatchlistDTO;
import com.autodb_api.services.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/bookmark")
public class BookmarkController {
    @Autowired
    private BookmarkService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(
            @RequestHeader(required = false) HttpHeaders headers, @PathVariable("id") String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/auto/{autoId}")
    public ResponseEntity<?> getByAutoId(
            @RequestHeader(required = false) HttpHeaders headers,
            @PathVariable("autoId") String autoId) {
        return new ResponseEntity<>(service.findByAutoId(autoId, PageRequest.of(0, 10, Sort.by("created").descending())), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(
            @RequestHeader(required = true) HttpHeaders headers,
            @RequestParam Optional<Integer> limit,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy
    ) {
        // This returns a JSON or XML with the movies
        String token = headers.get("authorization").get(0).split(" ")[1].trim();
        DecodedJWT jwt = JWT.decode(token);
        String subject = jwt.getSubject();

        System.out.println("subject: " + subject);

        return new ResponseEntity<>(service.findByUserId(
                subject,
                PageRequest.of(
                        page.orElse(0),
                        limit.orElse(5),
                        Sort.Direction.ASC,
                        sortBy.orElse("id"))),
                HttpStatus.OK);

    }

    @GetMapping("/watchlist/all")
    public ResponseEntity<?> getAllWatchlist(
            @RequestHeader(required = true) HttpHeaders headers
    ) {
        // This returns a JSON or XML with the movies
        String token = headers.get("authorization").get(0).split(" ")[1].trim();
        DecodedJWT jwt = JWT.decode(token);
        String subject = jwt.getSubject();

        return new ResponseEntity<>(service.findByUserId(subject), HttpStatus.OK);
    }

    @GetMapping("/exists/{autoId}")
    public ResponseEntity<?> exists(
            @RequestHeader(required = true) HttpHeaders headers,
            @PathVariable("autoId") String autoId) {

        String token = headers.get("authorization").get(0).split(" ")[1].trim();

        DecodedJWT jwt = JWT.decode(token);
        String subject = jwt.getSubject();

        //return new ResponseEntity<>(service.findByUserIdAndAutoId(subject,autoId), HttpStatus.OK);
        return service.findByUserIdAndAutoId(subject, autoId);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @RequestHeader(required = true) HttpHeaders headers,
            @PathVariable("id") String id) {

        //return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
        return service.delete(id);
    }
    @PostMapping("/")
    public ResponseEntity<?> add(
            @RequestHeader HttpHeaders headers,
            @RequestBody WatchlistDTO request) {

        String token = headers.get("authorization").get(0).split(" ")[1].trim();

        DecodedJWT jwt = JWT.decode(token);
        request.setUserId(jwt.getSubject());

        return service.update(request);
    }



}
