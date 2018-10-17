import java.util.ArrayList;

public class CryptoManager {
	
	private static final char LOWER_BOUND = ' ';
	private static final char UPPER_BOUND = '_';
	private static final int RANGE = UPPER_BOUND - LOWER_BOUND + 1; 
	
	//String to display when input is out of bounds
	private static final String FAIL_STRING = ("Some characters in your entry are out of bounds, "
			+ "please re-enter");
	
	//String to display if someone tries to push forward with a failed out-of-bound input
	private static final String FAIL_DECRYPT_STRING = ("Your input was invalid. Please re-enter!");

	/**
	 * This method determines if a string is within the allowable bounds of ASCII codes 
	 * according to the LOWER_BOUND and UPPER_BOUND characters
	 * @param plainText a string to be encrypted, if it is within the allowable bounds
	 * @return true if all characters are within the allowable bounds, false if any character is outside
	 */
	public static boolean stringInBounds (String plainText) {
		char[] textArray = plainText.toCharArray();
		for (char c : textArray) {
			if (!(c >= LOWER_BOUND && c <= UPPER_BOUND)) return false;
		}
		return true;
		
	}

	/**
	 * Encrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in plainText is replaced by the character \"offset\" away from it 
	 * @param plainText an uppercase string to be encrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the encrypted string
	 */
	public static String encryptCaesar(String plainText, int key) {
		
		//Return error message if input text is out of bounds.
		//This will not stop the program from running, though.
		if (!stringInBounds(plainText)) {
			return FAIL_STRING;
		}
		
		char[] charArray = plainText.toCharArray(); //convert the string to an array of characters
		
		//Add the value of the key to each character
		//If the result is out of bounds, subtract the upper bound until it is in range
		for(int i = 0; i < charArray.length; i++) {
			charArray[i]+=key;
		}
		
		//Check the array for any characters out of bounds, and move them back in bounds
		charArray = putInBounds(charArray);
		
		
		//Convert the character array back to a string to return back to method caller
		String str = new String(charArray);
		return str;
	}
	
	/**
	 * Encrypts a string according the Bellaso Cipher.  Each character in plainText is offset 
	 * according to the ASCII value of the corresponding character in bellasoStr, which is repeated
	 * to correspond to the length of plainText
	 * @param plainText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the encrypted string
	 */
	public static String encryptBellaso(String plainText, String bellasoStr) {
		
		//Return error message if input text is out of bounds.
		//This will not stop the program from running, though.
		if (!stringInBounds(plainText)) {
			return FAIL_STRING;
		}
		
		
		char[] charArray = plainText.toCharArray(); //Convert message to char array
		char[] keyArray = bellasoStr.toCharArray(); //Convert the key to char array
		
		//If the two arrays aren't equal in length, call the equalize() method
		if (charArray.length != keyArray.length) {
			keyArray = equalize(charArray, keyArray);
		}
		
		//Now that the two arrays are equal, add the two together
		for (int i = 0; i < charArray.length; i++) {
			charArray[i] += keyArray[i];
		}
		
		//If any altered characters are not out of range, move them back into range
		charArray = putInBounds(charArray);

		//return modified array as a String
		return new String(charArray);
		
	}
	
	/**
	 * Decrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in encryptedText is replaced by the character \"offset\" characters before it.
	 * This is the inverse of the encryptCaesar method.
	 * @param encryptedText an encrypted string to be decrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the plain text string
	 */
	public static String decryptCaesar(String encryptedText, int key) {
		//If user got a failed input message and tries to decrypt anyway, they get the next error.
		if(encryptedText.equals(FAIL_STRING)) {
			return FAIL_DECRYPT_STRING;
		}
		
		//Otherwise, proceed with algorithm:
		
		char[] charArray = encryptedText.toCharArray(); //convert the string to an array of characters
		
		//Subtract the value of the key from each character
		//If the result is out of bounds, add the lower bound until it is in range
		for(int i = 0; i < charArray.length; i++) {
			charArray[i]-=key;
		}
		
		//If any characters were moved out of bounds, put them back in bounds
		charArray = putInBounds(charArray);
		
		//Convert the character array back to a string to return back to method caller
		String str = new String(charArray);
		return str;
	}
	
	/**
	 * Decrypts a string according the Bellaso Cipher.  Each character in encryptedText is replaced by
	 * the character corresponding to the character in bellasoStr, which is repeated
	 * to correspond to the length of plainText.  This is the inverse of the encryptBellaso method.
	 * @param encryptedText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the decrypted string
	 */
	public static String decryptBellaso(String encryptedText, String bellasoStr) {
		//If user got a failed input message and tries to decrypt anyway, they get the next error.
		if(encryptedText.equals(FAIL_STRING)) {
			return FAIL_DECRYPT_STRING;
		}
		
		//Otherwise, proceed with algorithm:
		char[] charArray = encryptedText.toCharArray(); //Convert message to char array
		char[] keyArray = bellasoStr.toCharArray(); //Convert the key to char array
		
		//If the two arrays aren't equal in length, call the equalize() method
		if (charArray.length != keyArray.length) {
			keyArray = equalize(charArray, keyArray);
		}
		
		//Now that the two arrays are equal, subtract key characters from encrypted characters
		for (int i = 0; i < charArray.length; i++) {
			charArray[i] -= keyArray[i];
		}
		
		//If any characters wound up out of range, move them back into range
		charArray = putInBounds(charArray);
		
		//return modified array as a String
		return new String(charArray);
	}
	
	/**
	 * Takes in two arrays of characters. The first array is not altered, but is used as a comparison
	 * for the second array. The second array is lengthened or shortened in order to match the first array.
	 * 
	 * @param charArray The initial, comparison array
	 * @param keyArray The array to be shortened or lengthened
	 * @return the modified key array
	 */
	public static char[] equalize(char[] charArray, char[] keyArray) {
		//Change the keyArray from an array with static length to an ArrayList with dynamic length
				ArrayList<Character> keyAL = new ArrayList<>();
				for (char c : keyArray) {
					keyAL.add(c);
				}
								
				//If the key is shorter than the character array, extend the key around as
				//many times as needed until the two lengths are equal
				if(keyArray.length < charArray.length) {
					for (int i = keyArray.length; i < charArray.length; i++) {
						
						//determine which character of key to append to the end of the key when extending
						int j = i;
						while(j > keyArray.length-1) {
							j -= keyArray.length;
						}
						//add character j to the key ArrayList
						keyAL.add(keyArray[j]);
					}
				}
				
				//If the key is longer than the character array, remove the extra characters from the end
				//until the key and character arrays are equal in length
				else if(keyArray.length > charArray.length) {
					while (keyAL.size() > charArray.length) {
						keyAL.remove((keyAL.size()-1));
					}
				}
						
				//Convert the ArrayList to a String. Then convert the String to a char[]. Then return char[]
				//TODO: This is probably a sloppy way to implement, but I can't think of any other way to do it
				String str = "";
				for (int i = 0; i < keyAL.size(); i++) {
					str += keyAL.get(i);
				}
				return str.toCharArray();
		
	}
	
	/**
	 * Receive a character array and check it against ASCII character constraints. Any characters out of
	 * bounds are moved back within bounds
	 * @param charArray The array to examine
	 * @return modified character array
	 */
	public static char[] putInBounds(char[] charArray) {
		for (int i = 0; i < charArray.length; i++) {
			while(charArray[i] < LOWER_BOUND) {
				charArray[i]+=RANGE; 
			}
			while(charArray[i] > UPPER_BOUND) {
				charArray[i]-=RANGE; 
			}
		}
		return charArray;
	}
}