package com.lax.user.service.services.impl;

import com.lax.user.service.entity.Hotel;
import com.lax.user.service.entity.Rating;
import com.lax.user.service.entity.User;
import com.lax.user.service.exception.ResourceNotFoundException;
import com.lax.user.service.external.service.HotelService;
import com.lax.user.service.repository.UserRepository;
import com.lax.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Override
    public User saveUser(User user) {
        String randomUserID = UUID.randomUUID().toString();
        user.setUserId(randomUserID);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    //get single user
    @Override
    public User getUserById(String userId) {
        //get user from database with the help of userRepository
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with Given Id is not found on server !! : " + userId));
        //fetch rating of the above user from rating service
        //http://localhost:8083/ratings/users/6ceabafa-cafe-447c-bd17-747ae5556696

        /*Note - RATING-SERVICE this is service name taken from eureka server
        and need to mention @LoadBalanced in rest-template bean*/
        Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);

        List<Rating> ratings = Arrays.stream(ratingOfUser).toList();


        List<Rating> ratingList = ratings.stream().map(rating -> {
            //api call to hotel service to get the hotel
            //http://localhost:8082/hotels/59c8459f-5b6e-454a-932b-d986e8a65695

            //using RestTemplate
            /*ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
            Hotel hotel = forEntity.getBody();

            logger.info("response status code {} ", forEntity.getStatusCode());*/

           Hotel hotel =  hotelService.getHotel(rating.getHotelId());

            //set the hotel rating
            rating.setHotel(hotel);

            //return the rating
            return rating;
        }).collect(Collectors.toList());


        user.setRating(ratingList);

        return user;
    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public User updateUser(User user) {
        return null;
    }
}
