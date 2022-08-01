import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { betResponse } from './betResponse';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Paper Rock Scissors';
  player: boolean = false;
  machine: boolean = false;
  tied: boolean = false;
  playerBet: string;
  machineBet: string;  
  playerPoints: number = 0;
  machinePoints: number = 0;
  score: boolean = false;
  errorMsg: boolean = false;
  errorMessage: string;  
 
  betResponse: betResponse = new betResponse;
  constructor(private http: HttpClient) { }
    
  sendBet(bet: string) {
        this.http.get<betResponse>('http://localhost:8080/yourBetIs/' + bet).subscribe((data: betResponse) => {
            this.betResponse = data;
            this.player = false;
		    this.machine = false;
		    this.tied = false;
		    this.score = true;
            if (this.betResponse.errorMsg != null) {
					    this.score = false;
					    this.errorMsg = true;
			}
            if (this.betResponse.resultBet == 'Player') {
				this.player = true;
				this.playerPoints = this.playerPoints + 1;
			} else if (this.betResponse.resultBet == 'Machine') {
				this.machine = true;
				this.machinePoints = this.machinePoints + 1;
			} else if (this.betResponse.resultBet == 'Equals') {
				this.tied = true;
			}
	        this.playerBet = bet;
	        this.machineBet = this.betResponse.appBet;
        },
        (error: string) => {
		  this.errorMessage = error;
          this.errorMsg = true;
          this.score = false;
          console.log (this.errorMessage);
        }
      )
  }
  
  resetGame() {
	this.playerPoints = 0;
	this.machinePoints = 0;
	this.score = false;
	this.errorMsg = false;
  }
  
}
