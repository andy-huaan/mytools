package com.tools.rsa;

import java.io.ByteArrayOutputStream;  
import java.security.Key;  
import java.security.KeyFactory;  
import java.security.KeyPair;  
import java.security.KeyPairGenerator;  
import java.security.PrivateKey;  
import java.security.PublicKey;  
import java.security.Signature;  
import java.security.interfaces.RSAPrivateKey;  
import java.security.interfaces.RSAPublicKey;  
import java.security.spec.PKCS8EncodedKeySpec;  
import java.security.spec.X509EncodedKeySpec;  
import java.util.HashMap;  
import java.util.Map;  
  
import javax.crypto.Cipher;

import com.tools.base64.Base64Utils; 

/**
 *  java RSA���ܽ���ʵ��
 *  ע�⣺
 * RSA����������󳤶�117�ֽڣ�����Ҫ��������󳤶�Ϊ128�ֽڣ������ڼ��ܺͽ��ܵĹ�������Ҫ�ֿ���С�
 * RSA���ܶ����ĵĳ����������Ƶģ�����������ݹ�����׳������쳣��
 * javax.crypto.IllegalBlockSizeException: Data must not be longer than 117 bytes
 * @author admin
 *
 */
/**
 * RSA��Կ/˽Կ/ǩ�����߰� 
 * ���ɵ¡���ά˹�أ�Ron [R]ivest�������ϡ���Ī����Adi [S]hamir�������ɵ¡���������Leonard [A]dleman�� 
 * �ַ�����ʽ����Կδ������˵������¶�ΪBASE64�����ʽ
 * ���ڷǶԳƼ����ٶȼ��仺����һ���ļ���ʹ���������ܶ���ʹ�öԳƼ���
 * �ǶԳƼ����㷨���������ԶԳƼ��ܵ���Կ���ܣ�������֤��Կ�İ�ȫҲ�ͱ�֤�����ݵİ�ȫ 
 */ 
public class RSAUtils {
	//�����㷨:RSA
	public static final String KEY_ALGORITHM = "RSA";
	//ǩ���㷨 
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA"; //SHA1WithRSA
	//RSA���������Ĵ�С
    private static final int MAX_ENCRYPT_BLOCK = 117;  
    //RSA���������Ĵ�С  
    private static final int MAX_DECRYPT_BLOCK = 128;  
	
	//�洢��Կ��KEY  
    private static final String PUBLIC_KEY = "PUBLIC_KEY"; 
    //�洢˽Կ��KEY
    private static final String PRIVATE_KEY = "PRIVATE_KEY";  
	
	//������Կ��(��Կ��˽Կ)
	public static Map<String, Object> genKeyPair() throws Exception {  
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);  
        keyPairGen.initialize(1024);  
        KeyPair keyPair = keyPairGen.generateKeyPair();  
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();  
        Map<String, Object> keyMap = new HashMap<String, Object>(2);  
        keyMap.put(PUBLIC_KEY, publicKey);  
        keyMap.put(PRIVATE_KEY, privateKey);  
        return keyMap;  
    }
	
	/**
	 * ˽Կ���� 
	 * @param encryptedData �Ѽ�������
	 * @param privateKey ˽Կ(BASE64����) 
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey)  
            throws Exception {  
        byte[] keyBytes = Base64Utils.decode(privateKey);  
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);  
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
        cipher.init(Cipher.DECRYPT_MODE, privateK);  
        int inputLen = encryptedData.length;  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        int offSet = 0;  
        byte[] cache;  
        int i = 0;  
        // �����ݷֶν���  
        while (inputLen - offSet > 0) {  
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {  
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);  
            } else {  
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);  
            }  
            out.write(cache, 0, cache.length);  
            i++;  
            offSet = i * MAX_DECRYPT_BLOCK;  
        }  
        byte[] decryptedData = out.toByteArray();  
        out.close();  
        return decryptedData;  
    } 
	
	/**
	 * ��Կ����
     * @param encryptedData �Ѽ������� 
     * @param publicKey ��Կ(BASE64����) 
     * @return 
     * @throws Exception 
     */  
    public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey)  
            throws Exception {  
        byte[] keyBytes = Base64Utils.decode(publicKey);  
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
        Key publicK = keyFactory.generatePublic(x509KeySpec);  
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
        cipher.init(Cipher.DECRYPT_MODE, publicK);  
        int inputLen = encryptedData.length;  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        int offSet = 0;  
        byte[] cache;  
        int i = 0;  
        // �����ݷֶν���  
        while (inputLen - offSet > 0) {  
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {  
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);  
            } else {  
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);  
            }  
            out.write(cache, 0, cache.length);  
            i++;  
            offSet = i * MAX_DECRYPT_BLOCK;  
        }  
        byte[] decryptedData = out.toByteArray();  
        out.close();  
        return decryptedData;  
    }
    
    /**
     * ��Կ����
     * @param data Դ���� 
     * @param publicKey ��Կ(BASE64����) 
     * @return 
     * @throws Exception 
     */  
    public static byte[] encryptByPublicKey(byte[] data, String publicKey)  
            throws Exception {  
        byte[] keyBytes = Base64Utils.decode(publicKey);  
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
        Key publicK = keyFactory.generatePublic(x509KeySpec);  
        // �����ݼ���  
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
        cipher.init(Cipher.ENCRYPT_MODE, publicK);  
        int inputLen = data.length;  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        int offSet = 0;  
        byte[] cache;  
        int i = 0;  
        // �����ݷֶμ���  
        while (inputLen - offSet > 0) {  
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {  
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);  
            } else {  
                cache = cipher.doFinal(data, offSet, inputLen - offSet);  
            }  
            out.write(cache, 0, cache.length);  
            i++;  
            offSet = i * MAX_ENCRYPT_BLOCK;  
        }  
        byte[] encryptedData = out.toByteArray();  
        out.close();  
        return encryptedData;  
    }  
  
    /**
     * ˽Կ����
     * @param data Դ���� 
     * @param privateKey ˽Կ(BASE64����) 
     * @return 
     * @throws Exception 
     */  
    public static byte[] encryptByPrivateKey(byte[] data, String privateKey)  
            throws Exception {  
        byte[] keyBytes = Base64Utils.decode(privateKey);  
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);  
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
        cipher.init(Cipher.ENCRYPT_MODE, privateK);  
        int inputLen = data.length;  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        int offSet = 0;  
        byte[] cache;  
        int i = 0;  
        // �����ݷֶμ���  
        while (inputLen - offSet > 0) {  
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {  
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);  
            } else {  
                cache = cipher.doFinal(data, offSet, inputLen - offSet);  
            }  
            out.write(cache, 0, cache.length);  
            i++;  
            offSet = i * MAX_ENCRYPT_BLOCK;  
        }  
        byte[] encryptedData = out.toByteArray();  
        out.close();  
        return encryptedData;  
    }  
  
    /**
     * ��ȡ˽Կ 
     * @param keyMap ��Կ�� 
     * @return 
     * @throws Exception 
     */  
    public static String getPrivateKey(Map<String, Object> keyMap)  
            throws Exception {  
        Key key = (Key) keyMap.get(PRIVATE_KEY);  
        return Base64Utils.encode(key.getEncoded());  
    }  
  
    /**
     * ��ȡ��Կ
     * @param keyMap ��Կ�� 
     * @return 
     * @throws Exception 
     */  
    public static String getPublicKey(Map<String, Object> keyMap)  
            throws Exception {  
        Key key = (Key) keyMap.get(PUBLIC_KEY);  
        return Base64Utils.encode(key.getEncoded());  
    }  
    
    /**
	 * ��˽Կ   ����Ϣ��������ǩ��
	 * @param data ��ǩ������
	 * @param privateKey ˽Կ(BASE64����) 
	 * @return
	 * @throws Exception
	 */
	public static String sign(byte[] data, String privateKey) throws Exception {  
        byte[] keyBytes = Base64Utils.decode(privateKey);  
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);  
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);  
        signature.initSign(privateK);  
        signature.update(data);  
        return Base64Utils.encode(signature.sign());  
    } 
	
	/**
	 * �ù�Կ У������ǩ��
	 * @param data ��ǩ������
	 * @param publicKey ��Կ(BASE64����)
	 * @param sign ����  ����ǩ�� 
	 * @return
	 * @throws Exception
	 */
	public static boolean verify(byte[] data, String publicKey, String sign)  
            throws Exception {  
        byte[] keyBytes = Base64Utils.decode(publicKey);  
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
        PublicKey publicK = keyFactory.generatePublic(keySpec);  
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);  
        signature.initVerify(publicK);  
        signature.update(data);  
        return signature.verify(Base64Utils.decode(sign));  
    }
}
