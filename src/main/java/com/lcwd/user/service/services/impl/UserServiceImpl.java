package com.lcwd.user.service.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwd.user.service.client.HotelService;
import com.lcwd.user.service.client.RatingService;
import com.lcwd.user.service.entity.Hotel;
import com.lcwd.user.service.entity.Rating;
import com.lcwd.user.service.entity.User;
import com.lcwd.user.service.repository.UserRepository;
import com.lcwd.user.service.services.UserService;
import com.lcwd.user.service.services.exceptions.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private RatingService rating;

	@Autowired
	private UserRepository userRepository;
	private Optional<User> findById;

	@Autowired
	private RestTemplate restTemplate;

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		String randomUserId = UUID.randomUUID().toString();

		user.setUid(randomUserId);

		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub

		return (List<User>) userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		// TODO Auto-generated method stub

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user with given id is not found on server" + userId));

		// fetch rating from the above Rating Service
		// http://localhost:8082/ratings/users/f8604214-a76a-414e-9a07-5ec547aa6176
	//RestTempalte
		String url = "http://RATING-SERVICE/ratings/users/" + user.getUid() + "";

		Rating[] ratingsOfUser = restTemplate.getForObject(url, Rating[].class);

		logger.info(" {} ", ratingsOfUser
				);

		List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
		List<Rating> ratingList = ratings.stream().map(rating -> {
			// 1. api call to hotelSevice to get hotel
			// http://localhost:8081/hotel/73364cd8-d236-4daa-826a-3e4169e32bbb
			System.out.println("in service");
		String url2 = "http://HOTEL-SERVICE/hotel/" + rating.getHotelId() + "";
			//ResponseEntity<Hotel> forEntity = restTemplate.getForEntity(url2, Hotel.class);
			//Hotel hotel = forEntity.getBody();
			//logger.info("Response Status Code: { }", forEntity.getStatusCode());

			//FeignClient 
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
				System.out.println(hotel);	

			//2. set hotel to rating

			rating.setHotel(hotel);
			// 3. return rating
			return rating;
		}).collect(Collectors.toList());

		user.setRatings(ratingList);

		return user;
	}

	@Override
	public User deleteUser(String userId) {
		// TODO Auto-generated method stub
		return userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user with given id is not found on server" + userId));
	}

	@Override
	public User updateUser(String userId) {

		return null;
	}

}
