package com.lax.rating.service;

import com.lax.rating.entity.Rating;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RatingService {

    //create
    Rating create(Rating rating);

    //get all rating
    List<Rating> getAllRating();

    //get all rating by User ID
    List<Rating> getAllRatingByUserId(String userId);

    //get All rating by hotel ID
    List<Rating> getAllRatingByHotelId(String hotelId);
}
