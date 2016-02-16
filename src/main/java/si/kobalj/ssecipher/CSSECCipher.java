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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Jure Kobal
 */
public class CSSECCipher implements ISSECipher {

    private final Base64.Decoder decoder = Base64.getDecoder();
    private final Base64.Encoder encoder = Base64.getEncoder();
    private final Cipher enc;
    private final Cipher dec;
    private final MessageDigest MD5enc;
    private final MessageDigest MD5dec;
    private final byte[] key;
    private final SecretKeySpec secretKeySpec;
    private final boolean hasIV;

    private final Object synObjEnc = new Object();
    private final Object synObjDec = new Object();

    protected CSSECCipher(ICipherBuilder pCipherBuilder) throws NoSuchAlgorithmException, NoSuchPaddingException {
        this.key = decoder.decode(pCipherBuilder.getBase64Key());
        this.secretKeySpec = new SecretKeySpec(key, pCipherBuilder.getAlgorithm());
        this.MD5enc = MessageDigest.getInstance("MD5");
        this.MD5dec = MessageDigest.getInstance("MD5");
        this.enc = Cipher.getInstance(pCipherBuilder.getTransformation());
        this.dec = Cipher.getInstance(pCipherBuilder.getTransformation());
        this.hasIV = pCipherBuilder.hasInitVector();
    }

    /**
     * Encryption of data. The method is synchronized since it uses objects that are not thread safe. The returned data 
     * is Base64 encoded.<br>
     * The method generates an MD5 sum that is used to check the integrity on decrypt. The MD5 sum is part of the 
     * encrypted data and not visible till decrypt of the whole data happens.
     * @param pData Data to be encrypted
     * @return Base64 encrypted password
     * @throws Exception Exception In case of errors in operations on Cipher instance or unsupported charset.
     */
    @Override
    public String encrypt(String pData) throws Exception {
        return encrypt(pData, "UTF-8");
    }

    /**
     * Encryption of data with defined charset. The method is synchronized since it uses objects that are not thread 
     * safe. The returned data is Base64 encoded. <br>
     * The method generates an MD5 sum that is used to check the integrity on decrypt. The MD5 sum is part of the 
     * encrypted data and not visible till decrypt of the whole data happens.
     * @param pData Data to be encrypted
     * @param pCharsete Charset to use
     * @return Base64 encrypted password
     * @throws Exception In case of errors in operations on Cipher instance or unsupported charset.
     */
    @Override
    public String encrypt(String pData, String pCharsete) throws Exception {
        synchronized (synObjEnc) {
            this.enc.init(Cipher.ENCRYPT_MODE, this.secretKeySpec);
            byte[] sdata = pData.getBytes(pCharsete);
            MD5enc.reset();
            MD5enc.update(sdata);
            byte[] md5Sum = MD5enc.digest();
            String val = System.currentTimeMillis() + "-" + encoder.encodeToString(md5Sum) + ";" + pData;
            byte[] odata = val.getBytes(pCharsete);
            byte[] data = new byte[odata.length + 1];
            System.arraycopy(odata, 0, data, 0, odata.length);
            StringBuilder sb = new StringBuilder();
            if (this.hasIV) {
                sb.append(this.encoder.encodeToString(this.enc.getIV()));
                sb.append(";");
            }
            sb.append(this.encoder.encodeToString(this.enc.doFinal(data)));

            return sb.toString();
        }
    }

    /**
     * Decryption of data. The method is synchronized since it uses objects that are not thread safe.<br>
     * The method decrypt the data, calculates MD5 hash and then checks with hte MD5 hash provided in the encrypted data
     * itself.
     * @param pData Encrypted and base64 encoded data
     * @return Decryptd data
     * @throws Exception In case of errors in operations on Cipher instance or unsupported charset. In case of invalid 
     * MD5 hash or invalid kay an IllegalArgumentException is thrown
     */
    @Override
    public String decrypt(String pData) throws Exception {
        return decrypt(pData, "UTF-8");
    }

    /**
     * Decryption of data with defined charset. The method is synchronized since it uses objects that are not thread 
     * safe.<br>
     * The method decrypt the data, calculates MD5 hash and then checks with hte MD5 hash provided in the encrypted data
     * itself.
     * @param pData Encrypted and base64 encoded data
     * @param pCharset Charset to use
     * @return Decryptd data
     * @throws Exception In case of errors in operations on Cipher instance or unsupported charset. In case of invalid 
     * MD5 hash or invalid kay an IllegalArgumentException is thrown
     */
    @Override
    public String decrypt(String pData, String pCharset) throws Exception {
        synchronized (synObjDec) {
            byte data[];
            if (this.hasIV) {
                String vals[] = pData.split(";");
                byte[] ival = decoder.decode(vals[0]);
                IvParameterSpec spec = new IvParameterSpec(ival);
                dec.init(Cipher.DECRYPT_MODE, this.secretKeySpec, spec);
                data = dec.doFinal(decoder.decode(vals[1]));
            } else {
                dec.init(Cipher.DECRYPT_MODE, this.secretKeySpec);
                data = dec.doFinal(decoder.decode(pData));
            }

            int offset = data.length;
            if (data[data.length - 1] == 0) {
                offset = data.length - 1;
            }
            String info = new String(data, 0, offset, pCharset);
            int pos = info.indexOf(';');
            String result = info.substring(pos + 1);
            String check[] = info.substring(0, pos).split("-");
            byte[] md5 = decoder.decode(check[1]);
            byte[] sdata = result.getBytes(pCharset);
            MD5dec.reset();
            MD5dec.update(sdata);
            byte[] md5Sum = MD5dec.digest();
            if (!Arrays.equals(md5, md5Sum)) {
                throw new IllegalArgumentException("Invalid MD5 hash/key");
            }
            return result;
        }
    }
}
