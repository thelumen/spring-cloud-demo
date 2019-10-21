package com.ri.springclouddemo.util;

import java.io.IOException;
import java.util.Arrays;
import com.sgitg.sgcc.sm.SM2Utils;
import com.ri.springclouddemo.util.StringUtil;
/**
 * 本类主要提供国密SM2算法 使用样例，
 * 此样例包含国密SM2算法的加密、解密、签名、验签方法的使用；
 * 
 * *********注意事项*********
 * 1) 所使用的包名为com.sgitg.sgcc.sm.*;
 * 2) 此算法使用只能依赖bcprov-jdk16-1.46.jar,项目中不能同时含有bouncy castle的其他版本的jar，
 * 		否则使用过程中算法运算会抛出异常
 * 3) 以下SM2Util针对String字符串进行算法操作，
 * 		若项目组需要进行文件加密操作请使用SM2Utils对外方法进行byte数组算法运算处理
 */
public class SM2Util{
	
	private final static SM2Utils SM2_UTILS  = new SM2Utils();
	
	/**
	 * 国密SM2算法加密方法
	 * @param plainText 需要加密的数据
	 * @param pubKey 加密所需要的hex编码公钥
	 * @return 返回加密后hex编码字符串
	 */
	public final static String  encryptBySM2(String plainText, String pubKey){
		//将需要加密的信息进行utf8编码转换为byte数组
		byte[] msg= StringUtil.toUTF8ByteArray(plainText);
		//hex编码的公钥进行转换为byte数组
		byte[] key = HexEncode.hexToByte(pubKey);
		byte [] cipherByteArray = null; 
		try {
			cipherByteArray = 	SM2_UTILS.SG_SM2EncData(key,msg);
		} catch (IOException e) {
			throw new RuntimeException("国密SM2算法加密失败",e);
		}
		//将加密后的byte数组进行hex编码，以String字符形式输出
		return (HexEncode.byteToHex(cipherByteArray));
	}
	
	/**
	 * 国密SM2算法解密方法
	 * @param cipherText 加密后的hex编码数据
	 * @param priKey 解密需要的hex 编码的私钥
	 * @return 加密后明文数据
	 */
	public final static String decryptBySM2(String cipherText,  String priKey){
		//加密后的密文先进行hex解码为byte数组
		byte[] cipher = HexEncode.hexToByte(cipherText);
		//私钥进行hex解码为byte数组
		byte [] prikey = HexEncode.hexToByte(priKey);
		byte[] plainByteArray = null;
		 try {
			 plainByteArray = SM2_UTILS.SG_SM2DecData(prikey, cipher);
		} catch (IOException e) {
			throw new RuntimeException("国密SM2算法解密失败",e);
		}
		 //解密后进行字符转为字符串形式
		return StringUtil.fromUTF8ByteArray(plainByteArray);
	}
	
	public final static String[] generateKeyPairs(){
		byte[] keyPair = SM2_UTILS.SG_generateKeyPair();
		String priKeyHex= (HexEncode.byteToHex(Arrays.copyOfRange(keyPair, 0, 32)));
		String pubKeyHex = (HexEncode.byteToHex(Arrays.copyOfRange(keyPair, 32, 97)));
		return new String[]{priKeyHex,pubKeyHex};
	}
	
	
	public static void main(String[] args) {
		System.out.println("************************SM2加密/解密***************************");
		//需要加密的数据；
		String plainText = "国密SM2加解密测试数据";
		/**
		 * 调用生成公钥私钥方法，生成hex编码公钥和私钥；
		 * 以下为生成秘钥代码，可以使用此方法生成项目所需要的秘钥对；
		 * 将公钥的放到前段/客户端，供SM2加密使用；
		 * 将私钥以参数方式配置到配置文件中，注意：不能使用硬编码方式使用私钥！
		 * 私钥可能涉及二次加密情况，具体情况请参考各自项目安全要求。
		 */
		//生成密钥对
		String[] generateKeyPairs = SM2Util.generateKeyPairs();
		// 通过生成的公钥进行加密
		String encryptBySM2 = SM2Util.encryptBySM2(plainText, generateKeyPairs[1]);
		System.out.println("SM2算法加密后为: " + encryptBySM2);
		//使用私钥对加密后的密文解密
		String decryptBySM2 = SM2Util.decryptBySM2(encryptBySM2, generateKeyPairs[0]);
		System.out.println("SM2算法解密后为: " + decryptBySM2);
	}
}
