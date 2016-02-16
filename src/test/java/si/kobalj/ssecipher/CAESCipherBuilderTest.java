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
package si.kobalj.ssecipher;

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
public class CAESCipherBuilderTest {

    public CAESCipherBuilderTest() {
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
     * Test of setMode method, of class CAESCipherBuilder. Test of CBC and no padding, we expect to get
     * AES/CBC/NoPadding
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testAESCBCNoPadding() throws Exception {
        System.out.println("testAESCBCNoPadding");
        CAESCipherBuilder instance = new CAESCipherBuilder();
        instance.setBase64Key("test").setMode(CAESCipherBuilder.Mode.CipherBlockChaining).usePadding(false).build();

        assertEquals("Algorithm != AES", instance.getAlgorithm(), "AES");
        assertEquals("", instance.getTransformation(), "AES/CBC/NoPadding");
        assertEquals("Base64Key not equals to expected", instance.getBase64Key(), "test");
    }

    /**
     * Test of setMode method, of class CAESCipherBuilder. Test of CBC with padding, we expect to get
     * AES/CBC/PKCS5Padding
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testAESCBCPadding() throws Exception {
        System.out.println("testAESCBCPadding");
        CAESCipherBuilder instance = new CAESCipherBuilder();
        instance.setBase64Key("test").setMode(CAESCipherBuilder.Mode.CipherBlockChaining).usePadding(true).build();

        assertEquals("Algorithm != AES", instance.getAlgorithm(), "AES");
        assertEquals("Transformation != AES/CBC/PKCS5Padding", instance.getTransformation(), "AES/CBC/PKCS5Padding");
        assertEquals("Base64Key not equals to expected", instance.getBase64Key(), "test");
    }

    /**
     * Test of setMode method, of class CAESCipherBuilder. Test of ECB with padding, we expect to get
     * AES/ECB/PKCS5Padding
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testAESECBPadding() throws Exception {
        System.out.println("testAESECBPadding");
        CAESCipherBuilder instance = new CAESCipherBuilder();
        instance.setBase64Key("test").setMode(CAESCipherBuilder.Mode.ElectronicCodeBook).usePadding(true).build();

        assertEquals("Algorithm != AES", instance.getAlgorithm(), "AES");
        assertEquals("Transformation != AES/ECB/PKCS5Padding", instance.getTransformation(), "AES/ECB/PKCS5Padding");
        assertEquals("Base64Key not equals to expected", instance.getBase64Key(), "test");
    }
}
