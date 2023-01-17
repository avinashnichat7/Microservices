package com.lcwd.user.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.lcwd.user.service.entity.Rating;

@Service
@FeignClient(name = "RATING-SERVICE")

public interface RatingService {
	/*
	 * @PostMapping("/ratings") public Rating createRating(Map<String, Objects>);
	 */ 

	@PostMapping("/ratings")
	public Rating createRating(Rating values);

	@PutMapping("/rating{ratingId}")
	public Rating updateRating(@PathVariable("ratingId") String ratingId, Rating rating);

	@DeleteMapping("/ratitng/{ratingId}")
	public void deleteRating(@PathVariable("ratingId") String ratingId);
}
