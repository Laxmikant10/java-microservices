package com.lax.rating.controller;

import com.lax.rating.entity.Rating;
import com.lax.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    //create Rating
    //@PreAuthorize("hasAuthority('Admin')") //only admin directly call this method
    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating rating){
        return new ResponseEntity<>(ratingService.create(rating), HttpStatus.CREATED);
    }

    //Get all Rating

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRating(){
        return new ResponseEntity<>(ratingService.getAllRating(),HttpStatus.OK);
    }

    //get Rating by user id
    //@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getAllRatingByUserId(@PathVariable String userId){
        return new ResponseEntity<>(ratingService.getAllRatingByUserId(userId),HttpStatus.OK);
    }

    //get Rating by hotel id
    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getAllRatingByHotelId(@PathVariable String hotelId){
        return new ResponseEntity<>(ratingService.getAllRatingByHotelId(hotelId),HttpStatus.OK);
    }
}
