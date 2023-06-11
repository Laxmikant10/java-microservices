package com.lax.hotel.service;

import com.lax.hotel.entity.Hotel;

import java.util.List;

public interface HotelService {

    Hotel createHotel(Hotel hotel);

    List<Hotel> getAll();

    Hotel getById(String id);
}
