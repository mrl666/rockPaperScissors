package com.paperscissorsrock.controllers;

import java.text.MessageFormat;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Logger;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paperscissorsrock.model.BetResponse;
import com.paperscissorsrock.model.Choices;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping({ "/yourBetIs/" })
public class BetController {
	
	Logger log = Logger.getLogger(BetController.class.getName());

	@GetMapping(path = { "/{bet}" }, produces = "application/json")
	public ResponseEntity<BetResponse> getBet(@PathVariable("bet") String bet) {

		log.info("Into getbet - bet: " + bet);

		BetResponse betResponse = new BetResponse();
		HttpHeaders resHeader = new HttpHeaders();

		resHeader.set("Accept", "application/json");
		Optional<Choices> opt = Choices.getIfPresent(bet);

		if (!opt.isPresent()) {
			throw new IllegalArgumentException(String.format("Unsupported type %s.", bet));
		}

		System.out.println(MessageFormat.format("The player move is: {0}", opt.get()));

		betResponse.setAppBet(appBet());
		betResponse.setResultBet(checkBet(bet, betResponse.getAppBet()));

		log.info("Exiting getbet - betResponse: " + betResponse);

		return ResponseEntity.ok().headers(resHeader).body(betResponse);
	}

	public String appBet() {
		
		log.info("Into appBet");

		Choices compBet;
		Random random = new Random();
		int in = random.nextInt(3) + 1;
		if (in == 1) {
			compBet = Choices.ROCK;
		} else if (in == 2) {
			compBet = Choices.PAPER;
		} else {
			compBet = Choices.SCISSORS;
		}

		log.info("Exiting appBet - App bet is: " + compBet);
		return compBet.toString();

	}

	public String checkBet(String bet, String appBet) {
		
		log.info("Into checkBet - player bet: " + bet + " machine bet: " + appBet);

		String winner = null;

		if (appBet.equals(Choices.ROCK.name())
				&& bet.equals(Choices.SCISSORS.name())) {
			winner = "Machine";
		} else if (bet.equals(Choices.ROCK.name())
				&& appBet.equals(Choices.SCISSORS.name())) {
			winner = "Player";
		}

		if (appBet.equals(Choices.PAPER.name())
				&& bet.equals(Choices.ROCK.name())) {
			winner = "Machine";
		} else if (bet.equals(Choices.PAPER.name())
				&& appBet.equals(Choices.ROCK.name())) {
			winner = "Player";
		}

		if (appBet.equals(Choices.SCISSORS.name())
				&& bet.equals(Choices.PAPER.name())) {
			winner = "Machine";
		} else if (bet.equals(Choices.SCISSORS.name())
				&& appBet.equals(Choices.PAPER.name())) {
			winner = "Player";
		}

		if (appBet.equals(bet)) {
			winner = "Equals";
		}

		log.info("Exiting checkBet - winner is: " + winner);
		return winner;
	}

}