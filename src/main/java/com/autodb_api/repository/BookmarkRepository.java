package com.autodb_api.repository;

import com.autodb_api.models.Bookmark;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository("BookmarkRepository")
public interface BookmarkRepository extends MongoRepository<Bookmark, ObjectId> {


    Optional<Bookmark> findById(String id);

    Optional<Bookmark> findByUserIdAndAutoId(String userId, String autoId);


    Page<Bookmark> findAll(Pageable pageable);


    //@Query("SELECT a as auto, b as bookmark FROM Bookmark b LEFT JOIN Auto a ON a.id = b.autoId WHERE b.userId = ?1")
    //For Projection
    Page<Bookmark> findByUserId(String userId, Pageable pageable);

    Page<Bookmark> findByAutoId(String autoId, Pageable pageable);


    Page<Bookmark> findByUserIdAndAutoIdAndCreated(String userId, String autoId, LocalDate created, Pageable pageable);

    Boolean existsByUserIdAndAutoId(String userId, String autoId);

    List<Bookmark> findAllByUserId(String userId);
}