package com.lax.user.service.controller;

import com.lax.user.service.entity.User;
import com.lax.user.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    //Create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User user1 = userService.saveUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    int retryCount = 1;

    @GetMapping("/{userId}")
    //@CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
    //@Retry(name = "ratingHotelService",fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUserById(@PathVariable String userId) {
        logger.info("Get Single User Handler: UserController");
        //logger.info("Retry count {}",retryCount);
        //retryCount++;
        User user = userService.getUserById(userId);

        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }

    //creating fall back method for circuitbreaker
    //Note -
    // 1. return type of actual method where we used @CircuitBreaker and fallbackMethod need to be same
    // 2. Check if parameter is matching or not
    // 3. same method same which we declare in fallbackMethod name

    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex) {
        // logger.info("Fallback is executed because service is down");
        ex.printStackTrace();
        User user = User.builder()
                .userId("1234")
                .name("Lalla")
                .email("lalla@outlook.com")
                .about("This user we created Dummy because some service is down")
                .build();

        return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
    }
}
