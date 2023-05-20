package com.dbt.circuitbreakerresiliance4j;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/joke")
@RestController
public class LController {

	private RestTemplate restTemplate;
	
	LController(RestTemplate restTemplate){
		this.restTemplate = restTemplate;
	}
	
	@GetMapping
	@CircuitBreaker(name = "jokeCB", fallbackMethod = "fallBackJoke")
	public String getRandomJoke(){
		ResponseEntity<Joke> responseEntity = restTemplate.getForEntity(
				"https://official-joke-api.appspot.com/random_joke",
				Joke.class);
		
		Joke joke = responseEntity.getBody();
		System.out.println(joke);
		return joke.setup() + "...." + joke.punchline();
	}
	
	private String fallBackJoke(Throwable t){
		System.out.println("Fallback:"+t.getMessage());
		return "What do you get hanging from Apple trees?" + "..." + "Sore arms.";
	}
}
