/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tcp.server;

/**
 * Simple service that receives data in a byte array,
 * perform action based on the input from server.
 */
public class EchoService {

	/** Perform the logic for the game.
	 * 
	 * @param input
	 * @return message
	 */
	public String execute(String input) {

		final String START = "Start";
		final String EXIT = "Exit";
		String message = "";
		int number = 0;
		int divisor = 3;
		int options[] = {-1, 0, 1};
		
		/** Request Player 1 (TCPServer) to start the game.
		 */
		if (input.equals(START)) {
			input = getRandomNumerAsString();
			System.out.println("Game started!!");
			return input;
		
		/** Close the connection if Player 1 (TCPServer) wins.
		 */
		} else if (input.equals(EXIT)) {
			System.out.println("Game finished!!");
			System.exit(0);
		
		/** The Player 2 can choose between adding one of {­1,0,1} to get to a number that is divisible by 3.
		 *  Divide it by three. The resulting whole number is then sent back to player 1 (TCPServer).
		 */	
		} else {
			number = Integer.parseInt(input);
			System.out.println("Player 2 sent: "+ number);
			
			for(int i: options) {
				if((number + i) % divisor == 0) {
					System.out.println("Player 1 played: "+ i);
					
					if((number + i) / divisor == 1) {
						System.out.println("Player 1 wins!!");
						System.out.println("Game finished!!");
						message = EXIT;
						break;
						
					} else {
						number = (number + i) / divisor;
						message = String.valueOf(number);
						break;
					} 
				
				}	
			}
		}
		return message;
	}
	
	/** Generate random number as a string.
	 */
	public String getRandomNumerAsString() {
		return String.valueOf(Math.round((Math.random() * 100)));
	}
}