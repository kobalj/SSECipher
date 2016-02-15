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

/**
 *
 * @author Jure Kobal
 */
public interface ICipherBuilder {

    /**
     * Method returns base64 encoded key for encryption/decrytion of data.
     *
     * @return Base64 encoded key.
     */
    public String getBase64Key();

    /**
     * Returns the algorithm used for encryption/decrytion (AES or RSA)
     *
     * @return Algorithm (AES/RSA)
     */
    public String getAlgorithm();

    /**
     * The transformer to use (It's composed of algorithm, mode and padding).
     *
     * @return Transformer used for encryption/decrytion (for example AES/CBC/PKCS5Padding)
     */
    public String getTransformation();

    /**
     * If the transformer uses initial vector or not.
     *
     * @return Usage of initial vector (true if yes, else no)
     */
    public boolean hasInitVector();
}
