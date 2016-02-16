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

/**
 *
 * @author Jure Kobal
 */
public class CCommandLineArgs {

    private boolean errors = false;
    private String algorithm = "AES";
    private int keysize = 256;
    private boolean help = false;
    private boolean chechalgorithms = false;
    private boolean generateKey = true;

    public void parse(String... args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-h": {
                    this.help = true;
                    this.generateKey = false;
                    break;
                }
                case "-a": {
                    this.algorithm = args[i + 1];
                    break;
                }
                case "-s": {
                    try {
                        this.keysize = Integer.parseInt(args[i + 1]);
                    } catch (NumberFormatException nfe) {
                        System.err.println("Invalid keysize. " + nfe.getMessage());
                        this.errors = true;
                    }
                    break;
                }
                case "-c": {
                    this.chechalgorithms = true;
                    this.generateKey = false;
                    break;
                }
            }
        }
    }

    public String getAlgorithm() {
        return this.algorithm;
    }

    public int getKeySize() {
        return this.keysize;
    }

    public boolean displayHelp() {
        return this.help;
    }

    public boolean hasErrors() {
        return this.errors;
    }

    public boolean checkAlgorithms() {
        return this.chechalgorithms;
    }

    public boolean generateKey() {
        return this.generateKey;
    }

    public void printHelp(PrintStream stream) {
        stream.print("SSECipher: command line arguments\n");
        stream.print("-a\talgorithm to generate key (default AES)\n");
        stream.print("-s\tsize of the key (default 256)\n");
        stream.print("-c\tcheck for supported algorithms\n");
        stream.print("-h\tdisplays this help");
    }
}
