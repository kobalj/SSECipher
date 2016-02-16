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

import java.security.NoSuchAlgorithmException;
import si.kobalj.ssecipher.keygen.CKeyGenerator;

/**
 *
 * @author Jure Kobal
 */
public class CRunner {
    
    public void run(CCommandLineArgs cmdArgs) {
        
        if (cmdArgs.displayHelp() || cmdArgs.hasErrors()) {
            cmdArgs.printHelp(System.out);
        }
        
        if (cmdArgs.checkAlgorithms()) {
            CRuntimeValidate rv = new CRuntimeValidate();
            rv.validate();
        }
        
        if (cmdArgs.generateKey()) {
            CKeyGenerator kg = new CKeyGenerator();
            try {
            kg.generate(cmdArgs.getAlgorithm(), cmdArgs.getKeySize());
            } catch (NoSuchAlgorithmException ne) {
                System.err.println("Secret key generating failed: " + ne.getMessage());
            }
        }
    }
}
