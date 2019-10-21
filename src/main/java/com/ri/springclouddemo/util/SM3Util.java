package com.ri.springclouddemo.util;

import com.sgitg.sgcc.sm.SM3Utils;
import com.ri.springclouddemo.util.StringUtil;


/**
 * 本类主要提供国密SM3算法 使用样例， 
 * 此样例包含国密SM3算法的加密方法的使用；
 * 
 */
public class SM3Util {

	/**
	 * 国密SM3算法加密
	 * @param plainText 需要进行加密的数据
	 * @return 返回加密后hex编码字符
	 */
	public final static String SM3Digest(String plainText) {
		final  SM3Utils SM3_UTILS = new SM3Utils();
		// 需要加密的数据转化为byte数组
		byte[] byteArray = StringUtil.toUTF8ByteArray(plainText);
		SM3_UTILS.SG_SM3UpDate(byteArray);
		byte[] sg_SM3Final = null;
		try {
			sg_SM3Final = SM3_UTILS.SG_SM3Final();
		} catch (Exception e) {
			throw new RuntimeException("国密SM3算法加密失败",e);
		}
		return (HexEncode.byteToHex(sg_SM3Final)).toLowerCase();
	}
	
	public static void main(String[] args) {
		System.out.println("************************SM3摘要***************************");
		//需要加密数据
		String plainText = "国密SM3算法加密测试数据";
		//使用国密SM3算法进行加密
		String sm3Digest = SM3Util.SM3Digest(plainText);
		System.out.println("国密SM3加密后为：" + sm3Digest);
	}
}
