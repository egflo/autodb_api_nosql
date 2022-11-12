package com.autodb_api.services;


import com.autodb_api.dto.WatchlistDTO;
import com.autodb_api.models.Bookmark;
import com.autodb_api.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookmarkService {

    @Autowired
    private BookmarkRepository repository;


    public Page<Bookmark> findAll(PageRequest pageable) {
        Page<Bookmark> watchlists = repository.findAll(pageable);
        return watchlists;
    }

    public Optional<Bookmark> findById(String id) {
        return repository.findById(id);
    }

    public List<Bookmark> findByUserId(String id) {
        return repository.findAllByUserId(id);
    }

    public Page<Bookmark> findByUserId(String id, PageRequest pageable) {
        return repository.findByUserId(id, pageable);
    }

    public Page<Bookmark> findByAutoId(String id, PageRequest pageRequest) {
        return repository.findByAutoId(id, pageRequest);
    }


    public Page<Bookmark> findByUserIdAndAutoIdAndCreated(String userId, String autoId, LocalDate created, PageRequest pageRequest) {
        return repository.findByUserIdAndAutoIdAndCreated(userId, autoId, created, pageRequest);
    }

    public Boolean existsByUserIdAndAutoId(String userId, String autoId) {
        return repository.existsByUserIdAndAutoId(userId, autoId);
    }

    public ResponseEntity<?> findByUserIdAndAutoId(String userId, String autoId) {
        Optional<Bookmark> bookmark = repository.findByUserIdAndAutoId(userId, autoId);
        if (bookmark.isPresent()) {
            return ResponseEntity.ok(bookmark.get());
        } else {
            return new ResponseEntity<>("Bookmark not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> add(WatchlistDTO request) {
        Bookmark bookmark = new Bookmark();
        bookmark.setAutoId(request.getAutoId());
        bookmark.setUserId(request.getUserId());
        bookmark.setCreated(new Date());

        return new ResponseEntity<>(repository.save(bookmark), HttpStatus.CREATED);
    }

    public ResponseEntity<?>  update(WatchlistDTO request) {
        Optional<Bookmark> update = repository.findByUserIdAndAutoId(request.getUserId(), request.getAutoId());
        if (update.isPresent()) {
            delete(update.get().getId());
            return ResponseEntity.ok(update.get());

        } else {
            return add(request);
        }
    }

    public ResponseEntity<?> delete(String id) {

        Optional<Bookmark> delete = repository.findById(id);
        if (delete.isPresent()) {
            repository.delete(delete.get());
            return ResponseEntity.ok("Bookmark deleted");
        } else {
            return ResponseEntity.badRequest().body("Bookmark not found");
        }
    }
}
