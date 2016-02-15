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
public interface ISSECipher {

    /**
     * Encryption of the provided data. This method assumes the charset is UTF-8 and is base64 encoded.
     *
     * @param pData Data to be encrypted
     * @return Encrypted and base64 encoded data.
     * @throws Exception In case of errors in the encryption process
     */
    public String encrypt(String pData) throws Exception;

    /**
     * Encryption of the provided data with the provided charset. Method assumes the data is base64 encoded.
     *
     * @param pData Data to be encrypted
     * @param pCharsete Charset to use
     * @return Encrypted and base64 encoded data.
     * @throws Exception In case of errors in the encryption process
     */
    public String encrypt(String pData, String pCharsete) throws Exception;

    /**
     * Decryption of the provided data. This method assumes the charset is UTF-8 and is base64 encoded.
     *
     * @param pData Base64 data to be decrypted.
     * @return Decrypted data.
     * @throws Exception In case of errors in the decryption process
     */
    public String decrypt(String pData) throws Exception;

    /**
     * Decryption of the provided data. with the provided charset. Method assumes the data is base64 encoded.
     *
     * @param pData Base64 data to be decrypted.
     * @param pCharset Charset to use
     * @return Decrypted data.
     * @throws Exception In case of errors in the decryption process
     */
    public String decrypt(String pData, String pCharset) throws Exception;
}
