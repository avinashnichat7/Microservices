package com.lcwd.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lcwd.user.service.client.RatingService;
import com.lcwd.user.service.entity.Rating;

@SpringBootTest
class UserService1ApplicationTests {
	@Autowired
	private RatingService ratingService;

	@Test
	void contextLoads() {
	}
	/*
	 * @Test
	 *  void createRating() { Rating rating =
	 * Rating.builder().rating(10).userId("").hotelId("").
	 * feedback("this is using feign client") .build(); ratingService.createRating(
	 * rating); System.err.println(" new Rating Created"); }
	 */
}
