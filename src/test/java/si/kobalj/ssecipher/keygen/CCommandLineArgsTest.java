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
package si.kobalj.ssecipher.keygen;

import si.kobalj.ssecipher.runner.CCommandLineArgs;
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
public class CCommandLineArgsTest {
    
    public CCommandLineArgsTest() {
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
     * Test of parse method, of class CCommandLineArgs.
     * TEst of help option.
     */
    @Test
    public void testParseHelp() {
        System.out.println("parseHelp");
        String[] args = new String[] {"-h"};
        
        
        CCommandLineArgs instance = new CCommandLineArgs();
        instance.parse(args);
        
        assertTrue("Help parameter is expected but not set", instance.displayHelp());
    }
    
    /**
     * Test of parse method, of class CCommandLineArgs.
     * Test of key size and algorithm.
     */
    @Test
    public void testParseKeyAndAlgorithm() {
        System.out.println("parseKeyAndAlgorithm");
        String[] args = new String[] {"-a", "AES", "-s", "128"};
        
        CCommandLineArgs instance = new CCommandLineArgs();
        instance.parse(args);
        
        assertEquals("Algorithm doesn't match expected.", instance.getAlgorithm(), "AES");
        assertEquals("Key size doesn't match expected.", instance.getKeySize(), 128);
        assertTrue("Result is not as expected", !instance.checkAlgorithms());
        assertTrue("Result is not as expected", !instance.hasErrors());
        assertTrue("Result is not as expected", instance.generateKey());
    }
    
    /**
     * Test of parse method, of class CCommandLineArgs.
     * Test of invalid key size (usage of characters)
     */
    @Test
    public void testInvalidKeySize() {
        System.out.println("parseKeyAndAlgorithm");
        String[] args = new String[] {"-s", "test"};
        
        CCommandLineArgs instance = new CCommandLineArgs();
        instance.parse(args);
        
        assertTrue("Result is not as expected", instance.hasErrors());
    }
    
    /**
     * Test of parse method, of class CCommandLineArgs.
     * Test of parameter -c for checking of algorithms
     */
    @Test
    public void testParseCheckAlgorithms() {
        System.out.println("parseKeyCheckAlgorithms");
        String[] args = new String[] {"-c"};
        
        CCommandLineArgs instance = new CCommandLineArgs();
        instance.parse(args);
        
        assertTrue("Result is not as expected", instance.checkAlgorithms());
    }
}
