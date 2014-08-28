package com.tools.des;

import java.net.URLEncoder;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.tools.base64.Base64;

/**
 * DES加密解密
 * @author zhaohuaan
 *
 */
public class DesUtil
{
    public static final String ALGORITHM_DES = "DES/CBC/PKCS5Padding";
    private static byte[] ivs = {-116, -90, 77, -23, -63, -79, 35, -89};

    /**
     * DES算法，加密
     * @param key 加密私钥，长度不能够小于8位
     * @param data 待加密字符串
     * @return 加密后的字节数组，一般结合Base64编码使用
     * @throws CryptException 异常
     */
    public static String encode(String key,byte[] data) throws Exception
    {
        try{
        	//根据给定的字节数组构造一个密钥
        	DESKeySpec dks = new DESKeySpec(key.getBytes());
        	SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            //key的长度不能够小于8位字节
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
            IvParameterSpec iv = new IvParameterSpec(ivs);
            AlgorithmParameterSpec paramSpec = iv;
            cipher.init(Cipher.ENCRYPT_MODE, secretKey,paramSpec);

            byte[] bytes = cipher.doFinal(data);
            return Base64.encodeToString(bytes, true);
        } catch (Exception e){
            throw new Exception(e);
        }
    }
    /**
     * DES算法，解密
     * @param key  解密私钥，长度不能够小于8位
     * @param data 待解密字符串
     * @return 解密后的字节数组
     * @throws Exception 异常
     */
    public static byte[] decode(String key,byte[] data) throws Exception
    {
        try{
            DESKeySpec dks = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            //key的长度不能够小于8位字节
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
            IvParameterSpec iv = new IvParameterSpec(ivs);
            AlgorithmParameterSpec paramSpec = iv;
            cipher.init(Cipher.DECRYPT_MODE, secretKey,paramSpec);
            return cipher.doFinal(data);
        } catch (Exception e){
            throw new Exception(e);
        }
    }
    
    /**
     * DES base64加密 
     * @param encryptKey 加密密钥
     * @param encryptString 需要加密的字符串
     * @return 加密后数据
     * @throws Exception
     */
    public static String encryptDES(String encryptKey,String encryptString) throws Exception {
        IvParameterSpec zeroIv = new IvParameterSpec(ivs);
        //根据给定的字节数组构造一个密钥
        SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
        //加密或解密取决于上一步的设置
        byte[] encryptedData = cipher.doFinal(encryptString.getBytes("utf-8"));
        return Base64.encodeToString(encryptedData,true);
    }
    
    /**
     * base64 DES解密
     * @param decryptKey 解密密钥
     * @param decryptString 需要解密的字符串
     * @return
     * @throws Exception
     */
    public static String decryptDES(String decryptKey,String decryptString) throws Exception {
    	// base64解码
        byte[] byteMi = Base64.decode(decryptString);
        //以下为DES解密
        IvParameterSpec zeroIv = new IvParameterSpec(ivs);
        //根据给定的字节数组构造一个密钥
        SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
        cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
        byte decryptedData[] = cipher.doFinal(byteMi);
        return new String(decryptedData, "utf-8");
    }
    
    public static void main(String[] args) throws Exception {
    	//加密测试
		String key = "19890925";
		String data = "中华人民共和国";
		//方法1
		String mi = encode(key, data.getBytes("UTF-8"));
		//方法2
		String mi2 = encryptDES(key,data);
		System.out.println(mi);
		System.out.println(mi2);
		
		//解密测试
		byte[] mb = Base64.decode(mi);
		//方法1
		byte[] ming = decode(key, mb);
		System.out.println(new String(ming, "UTF-8"));
		//方法2
		String ming2 = decryptDES(key, mi);
		System.out.println(ming2);
		
		String a = URLEncoder.encode("", "UTF-8");
		System.out.println(a);
	}
}