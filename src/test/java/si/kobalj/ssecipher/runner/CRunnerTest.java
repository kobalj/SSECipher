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
package si.kobalj.ssecipher.runner;

import java.io.PrintStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Jure Kobal
 */
public class CRunnerTest {

    public CRunnerTest() {
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
     * Test of run method, of class CRunner. We test if help is invocated when we have displayHelp = true.
     */
    @Test
    public void testRunDisplayHelp() {
        System.out.println("runDisplayHelp");
        CCommandLineArgs cmdArgs = mock(CCommandLineArgs.class);
        when(cmdArgs.displayHelp()).thenReturn(true);

        CRunner instance = new CRunner();
        instance.run(cmdArgs);

        verify(cmdArgs, times(1)).printHelp(System.out);
    }

    /**
     * Test of run method, of class CRunner. We test if help is invocated when we have hasErrors = true.
     */
    @Test
    public void testRunDisplayHelpOnError() {
        System.out.println("runDisplayHelpOnError");
        CCommandLineArgs cmdArgs = mock(CCommandLineArgs.class);
        when(cmdArgs.hasErrors()).thenReturn(true);

        CRunner instance = new CRunner();
        instance.run(cmdArgs);

        verify(cmdArgs, times(1)).printHelp(System.out);
    }
}
