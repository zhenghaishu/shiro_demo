package com.z.util;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DecriptUtil {

    /** 
     * @author���޹�� 
     * @date�� 2015��12��17�� ����9:16:22 
     * @description�� AES����
     * @parameter�� str��������ַ�secretKeyBase�����������Կ�Ļ��ַ�
     * @return��  �����ֽ�����
    **/
    public static byte[] encryptAES(String str, String secretKeyBase) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(secretKeyBase.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// ����������
            byte[] byteContent = str.getBytes("UTF-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// ��ʼ��
            byte[] result = cipher.doFinal(byteContent);
            return result; // ����
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** 
     * @author���޹�� 
     * @date�� 2015��12��17�� ����9:16:22 
     * @description�� AES����
     * @parameter�� strByteArray��������ֽ����飬
     * @parameter�� secretKeyBase�����������Կ�Ļ��ַ� ��Ҫע�����EAS�ǶԳƼ��ܣ�����secretKeyBase�ڼ��ܽ���ʱҪһ���
     * @return��  ���ܺ��ַ�
    **/
    public static String decryptAES(byte[] strByteArray, String secretKeyBase) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(secretKeyBase.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// ����������
            cipher.init(Cipher.DECRYPT_MODE, key);// ��ʼ��
            String result = new String(cipher.doFinal(strByteArray),"UTF-8");
            return result; // ����
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** 
     * @author���޹�� 
     * @date�� 2015��12��17�� ����9:24:43 
     * @description�� SHA��SHA1����
     * @parameter��   str��������ַ�
     * @return��  ���ܴ�
    **/
    public static String SHA1(String str) {
        try {
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("SHA-1"); //�����SHA����ֻ��Ҫ��"SHA-1"�ĳ�"SHA"����
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexStr = new StringBuffer();
            // �ֽ�����ת��Ϊ ʮ����� ��
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexStr.append(0);
                }
                hexStr.append(shaHex);
            }
            return hexStr.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** 
     * @author���޹�� 
     * @date�� 2015��12��17�� ����9:24:43 
     * @description�� MD5����
     * @parameter��   str��������ַ�
     * @return��  ���ܴ�
    **/
    public static String MD5(String str) {
        try {
            // ���MD5ժҪ�㷨�� MessageDigest ����
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // ʹ��ָ�����ֽڸ���ժҪ
            mdInst.update(str.getBytes());
            // �������
            byte[] md = mdInst.digest();
            // ������ת����ʮ����Ƶ��ַ���ʽ
            StringBuffer hexString = new StringBuffer();
            // �ֽ�����ת��Ϊ ʮ����� ��
            for (int i = 0; i < md.length; i++) {
                String shaHex = Integer.toHexString(md[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /** 
     * @author���޹�� 
     * @date�� 2015��12��17�� ����9:24:43 
     * @description�� MD5���� ���ض�����
     * @parameter��   str��������ַ�
     * @return��  ���ܶ�����
    **/
    public static byte[] MD5ToByteArray(String str) {
        try {
            // ���MD5ժҪ�㷨�� MessageDigest ����
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // ʹ��ָ�����ֽڸ���ժҪ
            mdInst.update(str.getBytes());
            // �������
            byte[] md = mdInst.digest();
            return md;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}