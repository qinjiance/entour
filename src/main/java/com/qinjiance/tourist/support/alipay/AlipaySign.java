package com.qinjiance.tourist.support.alipay;

import java.io.UnsupportedEncodingException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import module.laohu.commons.security.RSAEncrypt;
import module.laohu.commons.security.RSAKeyUtils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qinjiance.tourist.constants.Constants;
import com.qinjiance.tourist.support.alipay.constant.AlipaySignType;

/**
 * @author "Jiance Qin"
 * 
 * @date 2014年7月24日
 * 
 * @time 上午11:16:18
 * 
 * @desc 支付宝签名处理
 * 
 */
public class AlipaySign {

	protected static final Logger logger = LoggerFactory.getLogger(AlipaySign.class);

	/**
	 * MD5签名.
	 * 
	 * @param content
	 * @param alipayAccountConfig
	 * @return
	 */
	public static String getSign(String content, AlipaySignType signType, String md5Key, String privateKey) {
		if (StringUtils.isBlank(content)) {
			return null;
		}
		switch (signType) {
		case MD5:
			return getSignMd5(content, md5Key);
		case DSA:
			return getSignDsa(content, privateKey);
		case RSA:
			return getSignRsa(content, privateKey);
		default:
			logger.error("Not supported sign type: " + signType.getSignParam());
			return null;
		}
	}

	/**
	 * 验证签名
	 * 
	 * @param content
	 *            需要签名的字符串
	 * @param sign
	 *            签名结果
	 * @return
	 */
	public static boolean verify(String content, String sign, AlipaySignType signType, String md5Key, String publicKey) {
		if (StringUtils.isBlank(content)) {
			return false;
		}
		switch (signType) {
		case MD5:
			return verifySignMd5(content, sign, md5Key);
		case DSA:
			return verifySignDsa(content, sign, publicKey);
		case RSA:
			return verifySignRsa(content, sign, publicKey);
		default:
			logger.error("Not supported sign type: " + signType.getSignType());
			return false;
		}
	}

	/**
	 * MD5签名.
	 * 
	 * @param content
	 *            需要签名的字符串
	 * @return 签名结果
	 */
	private static String getSignMd5(String content, String md5Key) {
		content = content + md5Key;
		return DigestUtils.md5Hex(getContentBytes(content));
	}

	/**
	 * 验证MD5签名
	 * 
	 * @param content
	 *            需要签名的字符串
	 * @param sign
	 *            签名结果
	 * @return
	 */
	private static boolean verifySignMd5(String content, String sign, String md5Key) {
		content = content + md5Key;
		String mysign = DigestUtils.md5Hex(getContentBytes(content));
		if (mysign.equals(sign)) {
			return true;
		} else {
			StringBuilder bs = new StringBuilder();
			bs.append("[md5 sign] srcContent=").append(content).append(", calcSign=").append(mysign)
					.append(", recvSign=").append(sign);
			logger.info(bs.toString());
			return false;
		}
	}

	/**
	 * RSA签名.
	 * 
	 * @param content
	 *            需要签名的字符串
	 * @return 签名结果
	 */
	private static String getSignRsa(String content, String privateKey) {
		try {
			RSAPrivateKey rsaPrivateKey = RSAKeyUtils.loadRSAPrivateKey(privateKey);
			// 实例化Signature
			Signature signature = Signature.getInstance(RSAEncrypt.SIGN_ALGORITHM_SHA1WITHRSA);
			// 初始化Signature
			signature.initSign(rsaPrivateKey);
			// 更新
			signature.update(getContentBytes(content));
			return Base64.encodeBase64String(signature.sign());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 验证RSA签名
	 * 
	 * @param content
	 *            需要签名的字符串
	 * @param sign
	 *            签名结果
	 * @return
	 */
	private static boolean verifySignRsa(String content, String sign, String publicKey) {
		try {
			RSAPublicKey rsaPublicKey = RSAKeyUtils.loadRSAPublicKey(publicKey);
			// 实例化Signature
			Signature signature = Signature.getInstance(RSAEncrypt.SIGN_ALGORITHM_SHA1WITHRSA);
			// 初始化Signature
			signature.initVerify(rsaPublicKey);
			// 更新
			signature.update(getContentBytes(content));
			boolean ret = signature.verify(Base64.decodeBase64(sign));
			if (!ret) {
				StringBuilder bs = new StringBuilder();
				bs.append("[rsa sign] srcContent=").append(content).append(", recvSign=").append(sign)
						.append(", verify=").append(ret);
				logger.info(bs.toString());
			}
			return ret;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * DSA签名.
	 * 
	 * @param content
	 *            需要签名的字符串
	 * @return 签名结果
	 */
	private static String getSignDsa(String content, String privateKey) {
		try {
			RSAPrivateKey rsaPrivateKey = RSAKeyUtils.loadRSAPrivateKey(privateKey);
			// 实例化Signature
			Signature signature = Signature.getInstance(RSAEncrypt.SIGN_ALGORITHM_SHA1WITHDSA);
			// 初始化Signature
			signature.initSign(rsaPrivateKey);
			// 更新
			signature.update(getContentBytes(content));
			return Base64.encodeBase64String(signature.sign());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 验证DSA签名
	 * 
	 * @param content
	 *            需要签名的字符串
	 * @param sign
	 *            签名结果
	 * @return
	 */
	private static boolean verifySignDsa(String content, String sign, String publicKey) {
		try {
			RSAPublicKey rsaPublicKey = RSAKeyUtils.loadRSAPublicKey(publicKey);
			// 实例化Signature
			Signature signature = Signature.getInstance(RSAEncrypt.SIGN_ALGORITHM_SHA1WITHDSA);
			// 初始化Signature
			signature.initVerify(rsaPublicKey);
			// 更新
			signature.update(getContentBytes(content));
			boolean ret = signature.verify(Base64.decodeBase64(sign));
			if (!ret) {
				StringBuilder bs = new StringBuilder();
				bs.append("[dsa sign] srcContent=").append(content).append(", recvSign=").append(sign)
						.append(", verify=").append(ret);
				logger.info(bs.toString());
			}
			return ret;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * @param content
	 * @return
	 * @throws SignatureException
	 * @throws UnsupportedEncodingException
	 */
	private static byte[] getContentBytes(String content) {
		if (StringUtils.isBlank(Constants.CHARSET)) {
			return content.getBytes();
		}
		try {
			return content.getBytes(Constants.CHARSET);
		} catch (UnsupportedEncodingException e) {
			logger.error("UnsupportedEncodingException: " + Constants.CHARSET);
			throw new RuntimeException("UnsupportedEncodingException: " + Constants.CHARSET);
		}
	}

}