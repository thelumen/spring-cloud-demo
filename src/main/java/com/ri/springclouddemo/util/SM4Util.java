package com.ri.springclouddemo.util;

import java.security.SecureRandom;
import com.sgitg.sgcc.sm.SM4Utils;
import com.ri.springclouddemo.util.StringUtil;

/**
 * 本类主要提供国密SM4算法使用样例， 
 * *********注意事项*********
 * 1) 以下SM4Util针对字符串进行算法操作，
 * 		若项目组需要进行文件加密操作请使用SM4Utils对外方法进行byte数组算法运算处理
 */

public class SM4Util {

	private final static SM4Utils SM4_UTILS = new SM4Utils();

	/**
	 * generate a secret key.
	 * @return a random key or iv with hex code type.
	 */
    public final static String generateKey () {  
        String val = "";  
        //使用security PRNG对象
        SecureRandom random = new SecureRandom();  
        //参数length，表示生成几位随机数  
        for(int i = 0; i < 16; i++) {  
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
            //输出字母还是数字  
            if( "char".equalsIgnoreCase(charOrNum) ) {  
                //输出是大写字母还是小写字母  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
                val += (char)(random.nextInt(26) + temp);  
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));  
            }  
        }  
        return val;  
}  


	/**
	 * 使用国密SM4算法ECB模式进行加密数据
	 * @param plainText 需要加密的明文数据
	 * @param key 加密需要的hex编码的秘钥
	 * @return 返回加密后的hex编码的密文
	 */
	public final static String encryptBySM4(String plainText, String key){
		//调用国密SM4算法的ecb模式对明文数据进行加密
		byte[] sg_EncECBData =SM4_UTILS.SG_EncECBData(StringUtil.toUTF8ByteArray(key), StringUtil.toUTF8ByteArray(plainText));
		return (HexEncode.byteToHex(sg_EncECBData));
	}
	
	/**
	 * 使用国密	SM4算法ECB模式进行解密数据
	 * @param cipherText 需要进行解密hex编码密文
	 * @param key 解密需要的hex编码的秘钥
	 * @return 返回解密后的明文信息
	 */
	public final static String decryptBySM4(String cipherText, String key){
		//调用国密SM4算法ECB模式对密文进行解密
		byte[] sg_EncECBData = SM4_UTILS.SG_DecECBData(StringUtil.toUTF8ByteArray(key), HexEncode.hexToByte(cipherText));
		return StringUtil.fromUTF8ByteArray(sg_EncECBData);
	}
	
	
	public static void main(String[] args) {
		String plaintext = "国密SM4算法加密测试数据";
		//生成国密SM4算法秘钥
		String key = SM4Util.generateKey();
		//调用国密sm4算法加密方法
		String cipherText = SM4Util.encryptBySM4(plaintext, key);
		System.out.println("国密SM4算法加密后："+cipherText);
		String decryptBySM4 = SM4Util.decryptBySM4(cipherText, key);
		System.out.println("国密SM4算法解密后："+decryptBySM4);
	}

}
