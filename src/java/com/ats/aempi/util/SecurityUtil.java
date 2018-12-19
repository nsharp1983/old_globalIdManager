package com.ats.aempi.util;

public class SecurityUtil {
    private SecurityUtil() {
    }

    public static final String CHARSET = "UTF-8";

    /**
     * BASE64解码
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static final byte[] decryptBASE64(String key) {
        try {
            return new BASE64Encoder().decode(key);
        } catch (Exception e) {
            throw new RuntimeException("解密错误，错误信息：", e);
        }
    }

    /**
     * BASE64编码
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static final String encryptBASE64(byte[] key) {
        try {
            return new BASE64Encoder().encode(key);
        } catch (Exception e) {
            throw new RuntimeException("加密错误，错误信息：", e);
        }
    }


    /**
     * 数据解密，算法（DES）
     *
     * @param cryptData
     *            加密数据
     * @return 解密后的数据
     */
    public static final String decryptDes(String cryptData, byte[] key) {
        String decryptedData = null;
        try {
            // 把字符串解码为字节数组，并解密
            decryptedData = new String(DESCoder.decrypt(decryptBASE64(cryptData), key));
        } catch (Exception e) {
            throw new RuntimeException("解密错误，错误信息：", e);
        }
        return decryptedData;
    }

    /**
     * 数据加密，算法（DES）
     *
     * @param data
     *            要进行加密的数据
     * @return 加密后的数据
     */
    public static final String encryptDes(String data, byte[] key) {
        String encryptedData = null;
        try {
            // 加密，并把字节数组编码成字符串
            encryptedData = encryptBASE64(DESCoder.encrypt(data.getBytes(), key));
        } catch (Exception e) {
            throw new RuntimeException("加密错误，错误信息：", e);
        }
        return encryptedData;
    }
}
