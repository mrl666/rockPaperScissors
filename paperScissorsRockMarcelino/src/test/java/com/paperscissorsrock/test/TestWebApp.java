package com.paperscissorsrock.test;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.paperscissorsrock.controllers.BetController;
import com.paperscissorsrock.model.BetResponse;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestWebApp extends PaperScissorsRockMarcelinoTest {
	
	@Autowired
	private BetController betController;

    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    public void getBetOk() throws Exception {
    	ResponseEntity<BetResponse> response = restTemplate.getForEntity(
			new URL("http://localhost:8080/yourBetIs/ROCK").toString(), BetResponse.class);
    	assertEquals(response.getStatusCode(), HttpStatus.OK);

    }
    
    @Test
    public void getBetError() throws Exception {
    	ResponseEntity<BetResponse> response = restTemplate.getForEntity(
			new URL("http://localhost:8080/yourBetIs/PLASTIC").toString(), BetResponse.class);
    	assertEquals(response.getBody().getErrorMsg(), "error-500.");

    }    

	@Test
	public void appBet() throws Exception {		
		Assert.assertThat(betController.appBet(), anyOf(is("ROCK"), is("PAPER"), is("SCISSORS")));
	}
	
	@Test
	public void checkBet() throws Exception {		
		assertEquals("Equals", betController.checkBet("ROCK", "ROCK"));    	
	}

}