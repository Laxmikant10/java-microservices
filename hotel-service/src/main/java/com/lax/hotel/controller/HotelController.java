package com.lax.hotel.controller;

import com.lax.hotel.entity.Hotel;
import com.lax.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    //create

    //@PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        //return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotel(hotel));
        return new ResponseEntity<>(hotelService.createHotel(hotel), HttpStatus.CREATED);
    }

    //get single
    //@PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping("{hotelId}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable String hotelId) {
        //return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.getById(hotelId));
        return new ResponseEntity<>(hotelService.getById(hotelId), HttpStatus.CREATED);
    }

    //get all

    //@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels() {
        //return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.getById(hotelId));
        return new ResponseEntity<>(hotelService.getAll(), HttpStatus.CREATED);
    }
}
