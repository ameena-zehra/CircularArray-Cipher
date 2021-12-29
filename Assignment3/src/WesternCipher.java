import java.util.*;


public class WesternCipher {
	private CircularArrayQueue <Character> encodingQueue;;
	private CircularArrayQueue <Character> decodingQueue;
	
	/**
	* Constructor
	* For cases where the capacity is not given
	* Initializes the encodingQueue and decodingQueue as CircularArrayQueue with a capacity of 10
	*/
	public WesternCipher() {
		encodingQueue= new CircularArrayQueue<Character>(10);
		decodingQueue= new CircularArrayQueue<Character>(10);
	}
	
	/**
	* Constructor
	* For cases where the givenCapacity is provided
	* Initializes the encodingQueue and decodingQueue as CircularArrayQueue with the capacity set to the parameter
	* @param int givenCapacity
	*/
	public WesternCipher(int givenCapacity) {
		encodingQueue= new CircularArrayQueue<Character>(givenCapacity);
		decodingQueue= new CircularArrayQueue<Character>(givenCapacity);
	}
	
	/**
	* Public encode method
	* Takes a string as input and splits the string into individual characters
	* Enqueues every character into the encoding queue
	* Uses helper methods to apply the WesternCipher algorithm
	* Dequeues the original characters while enqueueing the output of the Cipher
	* Rejoins new characters into a string and returns it
	* @param String input
	* @return String newst being the new string once encoded and rejoined
	*/
	public String encode(String input) {
		// Enqueues every character of the original string on the queue
		for (int i=0; i< input.length();i++) {
			char originalch = input.charAt(i);
			encodingQueue.enqueue(originalch);
		}
		String newst = "";
		for (int i=0; i< input.length();i++) {
			// Originally dequeues the characters of the original string on the queue
			char originalch = encodingQueue.dequeue();
			// Sets the previous character to the previous and 'f' if it is the front
			char previousch ='f';
			if (i>0) {
				previousch = input.charAt(i-1);
			}
			char newch;
			// STEP 1- Calls helper method FirstStepEncode
			newch = FirstStepEncode(originalch);
			// STEP 2- Calls helper method SecondStepEncode
			newch = SecondStepEncode(newch, i);
			// STEP 3 & 5- Calls helper methods ThirdStepEncode and FifthStepEncode
			if (isVowel(originalch)== true) {
				if (isVowel(previousch)== true) {
					newch = FifthStepEncode(originalch);
				}
				else {
					newch = ThirdStepEncode(originalch);
				}
			// STEP 4- Calls helper method FourthStepEncode
			} else if (isVowel(previousch)== true) {
				char k = newst.charAt(i-1);
				int x = k - '0';
				newch = FourthStepEncode(newch, x);
			}
			// If the originalch is a space the new one remains a space
			if (originalch==' ') {
				newch=' ';
			}
			// Enqueues the final newch to the end of the encodingQueue
			encodingQueue.enqueue(newch);
			// Adds the newch to the newst
			newst+=newch;
		}
		// Calls a helper method to empty the encoding queue
		emptyencodingQueue();
		return newst;
	}
	
	/**
	* Private helper method called by the encode method
	* If the character provided is equal to A, E, I, O, U, Y then it returns true
	* Used in the algorithm in steps 3, 4, and 5
	* @param char character
	* @return boolean determining if its a vowel or not
	*/
	private boolean isVowel(char character) {
		return ((character=='A')||(character=='E')||(character=='I')||(character=='O')||(character=='U')||(character=='Y'));
	}
	
	/**
	* Private helper method called by the encode method
	* Completes the first step of the algorithm 
	* Given character is moved 5 steps forward
	* @param char originalcharacter
	* @return newch moved forward 5 steps
	*/
	private char FirstStepEncode(char originalch){
		char newch;
		if ((originalch+5)>'Z'){
			newch = (char) ((((originalch + 5) - 65) - 26) + 'A'); // helps it wrap around the alphabet
		}
		else {
			newch = (char) (originalch+5);
		}
		return newch;
	}
	
	/**
	* Private helper method called by the encode method
	* Completes the second step of the algorithm 
	* Given character is moved based on the index given
	* @param char newch
	* @param int i index of the newch in the String
	* @return char newch moved forward 2 multiplied by its index in the String
	*/
	private char SecondStepEncode(char newch, int i) {
		if ((newch+2*i)>'Z'){
			newch = (char) ((((newch + 2*i) - 65) - 26) + 'A'); // helps it wrap around the alphabet
		}
		else {
			newch = (char) (newch+2*i);
		}
		return newch;
	}
	
	/**
	* Private helper method called by the encode method
	* Completes the third step of the algorithm 
	* Given character is converted to a number based on its value
	* @param char originalch
	* @return char newch converted to a number based on its value
	*/
	private char ThirdStepEncode(char originalch) {
		char newch =0;
		if ((originalch=='A')) {
			newch = '1';
		}
		else if ((originalch=='E')) {
			newch = '2';
		}
		else if ((originalch=='I')) {
			newch = '3';
		}
		else if ((originalch=='O')) {
			newch = '4';
		}
		else if ((originalch=='U')) {
			newch = '5';
		}
		else if ((originalch=='Y')) {
			newch = '6';
		}
		return newch;
	}
	
	/**
	* Private helper method called by the encode method
	* Completes the fourth step of the algorithm
	* Given character is moved backward 2 times the value of the previous character
	* @param int x the value of the previous character
	* @param char newch
	* @return char newch moved backwards based on the value of x
	*/
	private char FourthStepEncode(char newch, int x) {
		if ((newch+-2*x)>'Z'){
			newch = (char) ((((newch + -2*x) - 65) - 26) + 'A');
		}
		else {
			newch = (char) (newch+-2*x);
		}
		return newch;
	}
	
	/**
	* Private helper method called by the encode method
	* Completes the fifth step of the algorithm
	* Given character is converted to a number based on its value
	* @param char originalch
	* @return char newch converted to a number based on its value
	*/
	private char FifthStepEncode(char originalch) {
		char newch = 0;
		if ((originalch=='A')) {
			newch  = '3';
		}
		else if ((originalch=='E')) {
			newch = '4';
		}
		else if ((originalch=='I')) {
			newch = '5';
		}
		else if ((originalch=='O')) {
			newch = '6';
		}
		else if ((originalch=='U')) {
			newch = '1';
		}
		else if ((originalch=='Y')) {
			newch = '2';
		}
		return newch;
	}
	
	/**
	* Private helper method called by the encode method
	* Used to empty an encodingQueue at the end of its use
	*/
	private void emptyencodingQueue(){
		while (encodingQueue.isEmpty()==false) {
			encodingQueue.dequeue();
		}
	}
	
	/**
	* Public decode method
	* Takes a string as input and splits the string into individual characters
	* Enqueues every character into the decoding queue
	* Uses helper methods to undo the WesternCipher algorithm
	* Dequeues the original characters while enqueueing the reversed output of the Cipher
	* Rejoins new characters into a string and returns it
	* @param String input
	* @return String codedmessage being the new string once decoded and rejoined
	*/
	public String decode(String input) {
		// Enqueues every character of the original string on the queue
		for (int i=0; i< input.length();i++) {
			char originalch = input.charAt(i);
			decodingQueue.enqueue(originalch);
		}
		String codedmessage = "";
		for (int i=0; i< input.length();i++) {
			char originalch = decodingQueue.dequeue();
			// Sets the previous character to the previous and 'f' if it is the front
			char previousch ='f';
			if (i>0) {
				previousch = input.charAt(i-1);
			}
			char newch = originalch;
			// Reversal of STEP 3 & STEP 5
			if (isNumeric(originalch)==true) {
				if (isNumeric(previousch)==true) {
					newch = FifthStepReversal(originalch);
				}
				else {
					newch = ThirdStepReversal(originalch);
				}
			}
			// Reversal of STEP 4
			else {
				if (isNumeric(previousch)==true) {
					int x = previousch - '0';
					newch = FourthStepReversal(originalch, x);
				}	
			// Reversal of STEP 2
			newch = SecondStepReversal(newch, i);
			// Reversal of STEP 1
			newch = FirstStepReversal(newch);
			}
			// If the originalch is a space the new one remains a space
			if (originalch==' '){
				newch = ' ';
			}
			// Enqueues the final newch to the end of the decodingQueue
			decodingQueue.enqueue(newch);
			// Adds the newch to the codedmessage
			codedmessage+=newch;
		}
		// Calls a helper method to empty the decoding queue
		emptydecodingQueue();
		return codedmessage;
	}
	
	/**
	* Private helper method called by the decode method
	* If the character provided is equal to 1, 2, 3, 4, 5, 6 then it returns true
	* Used in the reversal of the algorithm in steps 3, 4, and 5
	* @param char character
	* @return boolean determining if its a number between 1 to 6 or not
	*/
	private boolean isNumeric(char character) {
		return (character=='1')||(character=='2')||(character=='3')||(character=='4')||(character=='5')||(character=='6');
	}
	
	/**
	* Private helper method called by the decode method
	* Completes the reversal of the fifth step of the algorithm
	* Given character is converted to A, E, I, O, U, Y
	* @param char originalch
	* @return char newch converted to A, E, I, O, U, Y
	*/
	private char FifthStepReversal(char originalch) {
		char newch =0;
		if ((originalch=='3')) {
			newch = 'A';
		}
		else if ((originalch=='4')) {
			newch = 'E';
		}
		else if ((originalch=='5')) {
			newch = 'I';
		}
		else if ((originalch=='6')) {
			newch = 'O';
		}
		else if ((originalch=='1')) {
			newch = 'U';
		}
		else if ((originalch=='2')) {
			newch = 'Y';
		}
		return newch;
	}
	
	/**
	* Private helper method called by the decode method
	* Completes the reversal of the fourth step of the algorithm
	* Given character is moved forward 2 times the value of the previous character
	* @param int x the value of the previous character
	* @param char newch
	* @return char newch moved forwards based on the value of x
	*/
	private char FourthStepReversal(char originalch, int x) {
		char newch =0;
		if ((originalch+2*x)>'Z'){
			newch = (char) ((((originalch + 2*x) - 65) - 26) + 'A');
		} else {
			newch = (char) (originalch+2*x);
		}
		return newch;
	}
	
	/**
	* Private helper method called by the decode method
	* Completes the reversal of the third step of the algorithm
	* Given character is converted to A, E, I, O, U, Y based on its value
	* @param char originalch
	* @return char newch converted to A, E, I, O, U, Y
	*/
	private char ThirdStepReversal(char originalch) {
		char newch =0;
		if ((originalch=='1')) {
			newch = 'A';
		}
		else if ((originalch=='2')) {
			newch = 'E';
		}
		else if ((originalch=='3')) {
			newch = 'I';
		}
		else if ((originalch=='4')) {
			newch = 'O';
		}
		else if ((originalch=='5')) {
			newch = 'U';
		}
		else if ((originalch=='6')) {
			newch = 'Y';
		}
		return newch;
	}
	
	/**
	* Private helper method called by the decode method
	* Completes the reversal of the second step of the algorithm 
	* Given character is moved backward based on the index given
	* @param char newch
	* @param int i index of the newch in the String
	* @return char newch moved backward 2 multiplied by its index in the String
	*/
	private char SecondStepReversal(char newch, int i) {
		if (((newch-2*i)+1)<'A'){
			newch = (char) (((((newch - 2*i)+1) - 65)) + 'Z');
			
		} else {
			newch = (char) (newch-2*i);
		}
		return newch;
	}
	
	/**
	* Private helper method called by the decode method
	* Completes the reversal of first step of the algorithm 
	* Given character is moved 5 steps backward
	* @param char originalcharacter
	* @return newch moved backward 5 steps
	*/
	private char FirstStepReversal(char newch) {
		if ((newch-5)<'A'){
			newch = (char) ((((newch - 4) - 65)) + 'Z');
		}
		else {
			newch = (char) (newch-5);
		}
		return newch;
	}
	
	/**
	* Private helper method called by the decode method
	* Used to empty an encodingQueue at the end of its use
	*/
	private void emptydecodingQueue(){
		while (decodingQueue.isEmpty()==false) {
			decodingQueue.dequeue();
		}
	}
	
	/**
	* Main method
	* Prompt the user about whether it would like to encode or decode a string
	* Takes the string and encode/decode as appropriate
	* Prompt the user if they would like to enter another string
	* If no is selected the program exits
	*/
	public static void main(String[] args) {
		Scanner userinput= new Scanner(System.in);
		System.out.println("Please enter 'e' if you would like to encode or 'd' if you would like to decode");
		String encodevsdecode = userinput.nextLine();
		Scanner userinput2 = new Scanner(System.in);
		System.out.println("Enter a string");
		String givenString = userinput2.nextLine();
		if (encodevsdecode.equals("d")) {
			WesternCipher wc = new WesternCipher();
			String output = wc.decode(givenString);
			System.out.println(output);
		} else if (encodevsdecode.equals("e")) {
			WesternCipher wc = new WesternCipher();
			String output = wc.encode(givenString);
			System.out.println(output);
		}
		Scanner userinput3 = new Scanner(System.in);
		System.out.println("Please enter 'y' if you would like to enter another string or 'n' if you would like to quit");
		String yesorno = userinput3.nextLine();
		while (yesorno.equals("y")) {
			userinput= new Scanner(System.in);
			System.out.println("Please enter 'e' if you would like to encode or 'd' if you would like to decode");
			encodevsdecode = userinput.nextLine();
			userinput2 = new Scanner(System.in);
			System.out.println("Enter a string");
			givenString = userinput2.nextLine();
			if (encodevsdecode.equals("d")) {
				WesternCipher wc = new WesternCipher();
				String output = wc.decode(givenString);
				System.out.println(output);
			} else if (encodevsdecode.equals("e")) {
				WesternCipher wc = new WesternCipher();
				String output = wc.encode(givenString);
				System.out.println(output);
			}
			userinput3 = new Scanner(System.in);
			System.out.println("Please enter 'y' if you would like to enter another string or 'n' if you would like to quit");
			yesorno = userinput3.nextLine();
		}
	}
}