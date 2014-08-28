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
 * DES���ܽ���
 * @author zhaohuaan
 *
 */
public class DesUtil
{
    public static final String ALGORITHM_DES = "DES/CBC/PKCS5Padding";
    private static byte[] ivs = {-116, -90, 77, -23, -63, -79, 35, -89};

    /**
     * DES�㷨������
     * @param key ����˽Կ�����Ȳ��ܹ�С��8λ
     * @param data �������ַ���
     * @return ���ܺ���ֽ����飬һ����Base64����ʹ��
     * @throws CryptException �쳣
     */
    public static String encode(String key,byte[] data) throws Exception
    {
        try{
        	//���ݸ������ֽ����鹹��һ����Կ
        	DESKeySpec dks = new DESKeySpec(key.getBytes());
        	SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            //key�ĳ��Ȳ��ܹ�С��8λ�ֽ�
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
     * DES�㷨������
     * @param key  ����˽Կ�����Ȳ��ܹ�С��8λ
     * @param data �������ַ���
     * @return ���ܺ���ֽ�����
     * @throws Exception �쳣
     */
    public static byte[] decode(String key,byte[] data) throws Exception
    {
        try{
            DESKeySpec dks = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            //key�ĳ��Ȳ��ܹ�С��8λ�ֽ�
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
     * DES base64���� 
     * @param encryptKey ������Կ
     * @param encryptString ��Ҫ���ܵ��ַ���
     * @return ���ܺ�����
     * @throws Exception
     */
    public static String encryptDES(String encryptKey,String encryptString) throws Exception {
        IvParameterSpec zeroIv = new IvParameterSpec(ivs);
        //���ݸ������ֽ����鹹��һ����Կ
        SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
        //���ܻ����ȡ������һ��������
        byte[] encryptedData = cipher.doFinal(encryptString.getBytes("utf-8"));
        return Base64.encodeToString(encryptedData,true);
    }
    
    /**
     * base64 DES����
     * @param decryptKey ������Կ
     * @param decryptString ��Ҫ���ܵ��ַ���
     * @return
     * @throws Exception
     */
    public static String decryptDES(String decryptKey,String decryptString) throws Exception {
    	// base64����
        byte[] byteMi = Base64.decode(decryptString);
        //����ΪDES����
        IvParameterSpec zeroIv = new IvParameterSpec(ivs);
        //���ݸ������ֽ����鹹��һ����Կ
        SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
        cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
        byte decryptedData[] = cipher.doFinal(byteMi);
        return new String(decryptedData, "utf-8");
    }
    
    public static void main(String[] args) throws Exception {
    	//���ܲ���
		String key = "19890925";
		String data = "�л����񹲺͹�";
		//����1
		String mi = encode(key, data.getBytes("UTF-8"));
		//����2
		String mi2 = encryptDES(key,data);
		System.out.println(mi);
		System.out.println(mi2);
		
		//���ܲ���
		byte[] mb = Base64.decode(mi);
		//����1
		byte[] ming = decode(key, mb);
		System.out.println(new String(ming, "UTF-8"));
		//����2
		String ming2 = decryptDES(key, mi);
		System.out.println(ming2);
		
		String a = URLEncoder.encode("", "UTF-8");
		System.out.println(a);
	}
}