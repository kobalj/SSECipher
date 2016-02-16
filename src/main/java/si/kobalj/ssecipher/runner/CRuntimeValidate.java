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

import java.util.logging.Level;
import java.util.logging.Logger;
import si.kobalj.ssecipher.CAESCipherBuilder;
import si.kobalj.ssecipher.ISSECipher;

/**
 *
 * @author Jure Kobal
 */
public class CRuntimeValidate {

    private final String[] ciphers = {
        "AES/CBC/NoPadding",
        "AES/CBC/PKCS5Padding",
        "AES/ECB/PKCS5Padding",
        /*"RSA/ECB/PKCS1Padding",
        "RSA/ECB/OAEPWithSHA-1AndMGF1Padding",
        "RSA/ECB/OAEPWithSHA-256AndMGF1Padding"*/};

    public void validate() {
        for (String cipher : ciphers) {
            StringBuilder sb = new StringBuilder();
            sb.append("Support for: ").append(cipher).append(":");
            boolean isValid = true;
            String err = null;
            try {
                ISSECipher cipher1 = new CAESCipherBuilder().setBase64Key("xJWkeHio3VfzjyL8yOgZZ6f44q/6tz66eF5Jta+SKVc=").setMode(CAESCipherBuilder.Mode.CipherBlockChaining).build();
                cipher1.encrypt("test");
            } catch (Exception ex) {
                err = ex.getMessage();
                isValid = false;
            }
            sb.append((isValid) ? "OK": ("ERROR:" + err));
            System.out.println(sb.toString());
        }
    }
}
