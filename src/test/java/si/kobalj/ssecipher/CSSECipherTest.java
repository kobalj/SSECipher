package si.kobalj.ssecipher;

/*
 * Copyright (C) 2016 Jure Kobal
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jure Kobal
 */
public class CSSECipherTest {

    private String invladiPass = "4+HEb9KWEYd2nxLOs7q8MM6BVJlteRJop4giPjCXQRNj3XMJxaBbc/1qJein+zI1xai+Cb6M2cYLuKiPqP7NNQ==";

    public CSSECipherTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of encrypt and decript of string. Using AES with padding and CBC mode.
     *
     * @throws Exception
     */
    @Test
    public void testCypherAESCBCPadding() throws Exception {
        System.out.println("cypherAESCBCPadding");
        String pass = "testPass";
        ISSECipher cipher = new CAESCipherBuilder().setBase64Key("xJWkeHio3VfzjyL8yOgZZ6f44q/6tz66eF5Jta+SKVc=").
                usePadding(true).setMode(CAESCipherBuilder.Mode.CipherBlockChaining).build();

        String encPass = cipher.encrypt(pass);
        String decPass = cipher.decrypt(encPass);
        assertEquals("Original and decoded pass are different.", pass, decPass);
    }

    /**
     * Test of encrypt and decript of string. Using AES with padding and ECB mode.
     *
     * @throws Exception
     */
    @Test
    public void testCypherAESECBPadding() throws Exception {
        System.out.println("cypherAESECBPadding");
        String pass = "testPass";
        ISSECipher cipher = new CAESCipherBuilder().setBase64Key("2w5rIVebO5KiOlzAXiGAIA").
                usePadding(true).setMode(CAESCipherBuilder.Mode.ElectronicCodeBook).build();

        String encPass = cipher.encrypt(pass);
        String decPass = cipher.decrypt(encPass);
        assertEquals("Original and decoded pass are different.", pass, decPass);
    }

    /**
     * Test of encrypt and decript of string. Using AES with no padding and CBC mode.
     *
     * @throws Exception
     */
    @Test
    public void testCypherAESCBCNoPadding() throws Exception {
        System.out.println("cypherAESCBCNoPadding");
        String pass = "testPass";
        ISSECipher cipher = new CAESCipherBuilder().setBase64Key("xJWkeHio3VfzjyL8yOgZZ6f44q/6tz66eF5Jta+SKVc=").
                usePadding(false).setMode(CAESCipherBuilder.Mode.CipherBlockChaining).build();

        String encPass = cipher.encrypt(pass);
        String decPass = cipher.decrypt(encPass);
        assertEquals("Original and decoded pass are different.", pass, decPass);
    }

    /**
     * Test of exception in case of invalid MD5Hash
     *
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCypherInvalidMD5Hash() throws Exception {
        System.out.println("cypherInvalidMD5Hash");
        String pass = "testPass";
        ISSECipher cipher = new CAESCipherBuilder().setBase64Key("xJWkeHio3VfzjyL8yOgZZ6f44q/6tz66eF5Jta+SKVc=").
                usePadding(true).setMode(CAESCipherBuilder.Mode.CipherBlockChaining).build();
        
        String encPass = "KK6K5jvMtuUV5S7Gh6wRkQ==;FpaIzwYpw35UsdEQbxAbUD8jLiLxA1YJk0l4El9Ogvqo85HeIPrk5q3rsdv9q21jbN/F/NUS3zT08vCB+wHVUw==";
        cipher.decrypt(encPass);
    }
}
