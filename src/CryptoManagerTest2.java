/*
 * For this JUnit test, I copied over the test methods from the CryptoManagerTest JUnit test and added additional
 * tests for the extra methods that I added into the CryptoManger class.
 */

import static org.junit.Assert.*;

import org.junit.Test;

public class CryptoManagerTest2 {
	
	String testString1 = ("FIND THE MILLENIUM FALCON");
	String testString2 = ("NO DISINTEGRATIONS");
	String testString3 = ("X");
	String testKey1 = ("CMSC203");
	String testKey2 = ("PROJECT 3");
	String testKey3 = ("TEST KEY 3");

	@Test
	public void testStringInBounds() {
		assertTrue(CryptoManager.stringInBounds("THIS TEST SHOULD SUCCEED"));
		assertFalse(CryptoManager.stringInBounds("TEST SHOULD FAIL BECAUSE ~ IS OUTSIDE THE RANGE"));
		assertFalse(CryptoManager.stringInBounds("This test should fail because of lowercase letters"));
	}

	@Test
	public void testEncryptCaesar() {
		assertEquals("WKLV#LV#DQRWKHU#WHVW",CryptoManager.encryptCaesar("THIS IS ANOTHER TEST", 3));
		assertEquals("RFGQ^RCQR^QFMSJB^QSAACCB", CryptoManager.encryptCaesar("THIS TEST SHOULD SUCCEED", 190));
		assertEquals("DAHHK\\SKNH@", CryptoManager.encryptCaesar("HELLO WORLD", 444));
	}

	@Test
	public void testEncryptBellaso() {
		assertEquals("WU\\VR9F#N!RF88U-'HED", CryptoManager.encryptBellaso("THIS IS ANOTHER TEST", "CMSC203"));
		assertEquals("PR%UKP6K_\\VF=4V", CryptoManager.encryptBellaso("MERRY CHRISTMAS", "CMSC203"));
		assertEquals("ZF&ZJX4US][EE2 ", CryptoManager.encryptBellaso("MERRY CHRISTMAS", "MATH181")); 
		                                                                                      
	}

	@Test
	public void testDecryptCaesar() {
		assertEquals("THIS IS ANOTHER TEST", CryptoManager.decryptCaesar("WKLV#LV#DQRWKHU#WHVW", 3));
		assertEquals("THIS TEST SHOULD SUCCEED", CryptoManager.decryptCaesar("RFGQ^RCQR^QFMSJB^QSAACCB", 190));
		assertEquals("HELLO WORLD", CryptoManager.decryptCaesar("DAHHK\\SKNH@", 444));
	}

	@Test
	public void testDecryptBellaso() {
		assertEquals("THIS IS ANOTHER TEST", CryptoManager.decryptBellaso("WU\\VR9F#N!RF88U-'HED", "CMSC203"));
		assertEquals("MERRY CHRISTMAS", CryptoManager.decryptBellaso("PR%UKP6K_\\VF=4V", "CMSC203"));
		assertEquals("MERRY CHRISTMAS", CryptoManager.decryptBellaso("ZF&ZJX4US][EE2 ", "MATH181")); 
	}

	@Test
	public void testEqualize() {
		char[] test1 = CryptoManager.equalize(testString1.toCharArray(), testKey1.toCharArray());
		char[] test2 = CryptoManager.equalize(testString2.toCharArray(), testKey2.toCharArray());
		char[] test3 = CryptoManager.equalize(testString3.toCharArray(), testKey3.toCharArray());
		assertEquals(25, test1.length);
		assertEquals(18, test2.length);
		assertEquals(1, test3.length);
	}

	@Test
	public void testPutInBounds() {
		char[] test1 = {'`'};
		char[] test2 = {'~'};
		char[] test3 = {'\t'};
		assertEquals(' ', CryptoManager.putInBounds(test1)[0]);
		assertEquals('>', CryptoManager.putInBounds(test2)[0]);
		assertEquals('I', CryptoManager.putInBounds(test3)[0]);
	}

}
