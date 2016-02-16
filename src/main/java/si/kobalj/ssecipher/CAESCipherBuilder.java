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

import java.security.NoSuchAlgorithmException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Jure Kobal
 */
public class CAESCipherBuilder implements ICipherBuilder {

    public enum Mode {
        CipherBlockChaining("CBC"),
        ElectronicCodeBook("ECB");

        private final String name;

        private Mode(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    };
    
    private Mode mode;
    private String base64key;
    private boolean padding = true;
    private final String algorithm = "AES";
    private String transformation;
    
    public CAESCipherBuilder setMode(Mode pMode) {
        this.mode = pMode;
        return this;
    }
    
    public CAESCipherBuilder setBase64Key(String pBase64Key) {
        this.base64key = pBase64Key;
        return this;
    }
    
    public CAESCipherBuilder usePadding(boolean pUsePadding) {
        this.padding = pUsePadding;
        return this;
    }

    public ISSECipher build() throws NoSuchAlgorithmException, NoSuchPaddingException {
        this.transformation = this.algorithm + "/" + this.mode.getName() 
                + "/" + ((this.padding) ? "PKCS5Padding" : "NoPadding");
        
        return new CSSECCipher(this);
    }
    
    @Override
    public String getBase64Key() {
        return this.base64key;
    }

    @Override
    public String getAlgorithm() {
        return this.algorithm;
    }

    @Override
    public String getTransformation() {
        return this.transformation;
    }
    
    @Override
    public boolean hasInitVector() {
        return this.mode.equals(Mode.CipherBlockChaining);
    }
}
