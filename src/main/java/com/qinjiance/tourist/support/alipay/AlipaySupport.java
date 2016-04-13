package com.qinjiance.tourist.support.alipay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import module.laohu.commons.util.DateUtils;
import module.laohu.commons.util.HttpClientUtil;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qinjiance.tourist.constants.Constants;
import com.qinjiance.tourist.support.alipay.constant.AlipayConstant;
import com.qinjiance.tourist.support.alipay.constant.AlipaySignType;
import com.qinjiance.tourist.support.alipay.model.AlipayAccountConfig;

/**
 * @author "Jiance Qin"
 * 
 * @date 2014年7月24日
 * 
 * @time 上午10:54:16
 * 
 * @desc 类名：AlipaySupport 功能：支付宝对接支持类 提示：如何获取安全校验码和合作身份者ID
 *       1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *       2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *       3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”
 */
public class AlipaySupport {

	protected static final Logger logger = LoggerFactory.getLogger(AlipaySupport.class);

	/**
	 * 
	 */
	public AlipaySupport() {

	}

	/**
	 * 构造PC即时到帐接口
	 * 
	 * @param params
	 *            请求参数集合
	 * @param syncNotifyUrl
	 *            同步通知地址
	 * @param asyncNotifyUrl
	 *            异步通知地址
	 * @param isForm
	 *            true：生成html自动post提交表单，false：生成重定向url
	 * @param alipayAccountConfig
	 *            支付宝账号配置
	 * @return form表单html字符串或重定向url地址字符串
	 * @return
	 */
	public static String createDerectPayRequest(Map<String, String> params, String syncNotifyUrl,
			String asyncNotifyUrl, boolean isForm, AlipayAccountConfig alipayAccountConfig) {

		if (StringUtils.isBlank(syncNotifyUrl)) {
			logger.warn("[AlipaySupport] parameter 'syncNotifyUrl' is blank.");
		}
		if (StringUtils.isBlank(asyncNotifyUrl)) {
			logger.warn("[AlipaySupport] parameter 'asyncNotifyUrl' is blank.");
		}

		// 增加基本参数配置
		params.put("service", AlipayConstant.DERECT_PAY_SERVICE);
		params.put("partner", alipayAccountConfig.getPartnerId());
		params.put("return_url", syncNotifyUrl);
		params.put("notify_url", asyncNotifyUrl);
		params.put("seller_id", alipayAccountConfig.getSellerId());
		params.put("_input_charset", Constants.CHARSET);
		// 增加业务参数配置
		params.put("payment_type", AlipayConstant.DERECT_PAY_PAYMENTTYPE);
		if (alipayAccountConfig.getAntiFlag() != null && alipayAccountConfig.getAntiFlag()) {
			params.put("anti_phishing_key", getAntiFishTimestamp(params.get("out_trade_no"), alipayAccountConfig));
		}
		if (isForm) {
			return buildRequestForm(params, "post", "DerectPay", alipayAccountConfig);
		} else {
			return buildRequestUrl(params, alipayAccountConfig);
		}
	}

	/**
	 * 构造即时到帐网银支付接口
	 * 
	 * @param params
	 *            请求参数集合
	 * @param syncNotifyUrl
	 *            同步通知地址
	 * @param asyncNotifyUrl
	 *            异步通知地址
	 * @param isForm
	 *            true：生成html自动post提交表单，false：生成重定向url
	 * @param alipayAccountConfig
	 *            支付宝账号配置
	 * @return form表单html字符串或重定向url地址字符串
	 */
	public static String createBankPayRequest(Map<String, String> params, String syncNotifyUrl, String asyncNotifyUrl,
			boolean isForm, AlipayAccountConfig alipayAccountConfig) {

		if (StringUtils.isBlank(syncNotifyUrl)) {
			logger.warn("[AlipaySupport] parameter 'syncNotifyUrl' is blank.");
		}
		if (StringUtils.isBlank(asyncNotifyUrl)) {
			logger.warn("[AlipaySupport] parameter 'asyncNotifyUrl' is blank.");
		}

		// 增加基本参数配置
		params.put("service", AlipayConstant.DERECT_PAY_SERVICE);
		params.put("partner", alipayAccountConfig.getPartnerId());
		params.put("return_url", syncNotifyUrl);
		params.put("notify_url", asyncNotifyUrl);
		params.put("seller_id", alipayAccountConfig.getSellerId());
		params.put("_input_charset", Constants.CHARSET);
		// 增加业务参数配置
		params.put("payment_type", AlipayConstant.DERECT_PAY_PAYMENTTYPE);
		params.put("paymethod", AlipayConstant.BANK_PAY_METHOD);
		if (isForm) {
			return buildRequestForm(params, "post", "DerectPay", alipayAccountConfig);
		} else {
			return buildRequestUrl(params, alipayAccountConfig);
		}
	}

	/**
	 * 调用支付宝防钓鱼时间戳接口获取防钓鱼key
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getAntiFishTimestamp(String orderId, AlipayAccountConfig alipayAccountConfig) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("service", AlipayConstant.ANTI_FISH_TIMESTAMP_SERVICE);
		params.put("partner", alipayAccountConfig.getPartnerId());
		params.put("_input_charset", Constants.CHARSET);
		// 生成要请求地址
		String requestUrl = buildRequestUrl(params, alipayAccountConfig);
		// 解析结果
		Document doc = null;

		// 统计性能开始
		Date startTime = new Date();
		try {
			String response = HttpClientUtil.invokeGet(requestUrl, new HashMap<String, String>(), Constants.CHARSET,
					Constants.HTTP_TIMEOUT, Constants.SO_TIMEOUT);
			doc = DocumentHelper.parseText(response);
		} catch (Exception e) {
			logger.error("[AlipaySupport] Get anti fish timestamp error: ", e);
			return "";
		} finally {
			// 统计性能结束
			StringBuilder content = new StringBuilder();
			content.append("[AlipaySupport#GetFishTimestamp] orderId=").append(orderId);
			logPerformance(content.toString(), startTime, new Date());
		}
		if (doc != null) {
			List<Node> nodeList = doc.selectNodes("//alipay/*");
			StringBuffer buf1 = new StringBuffer();
			for (Node node : nodeList) {
				// 截取部分不需要解析的信息
				if (node.getName().equals("is_success") && node.getText().equals(AlipayConstant.IS_SUCCESS)) {
					// 判断是否有成功标示
					List<Node> nodeList1 = doc.selectNodes("//response/timestamp/*");
					for (Node node1 : nodeList1) {
						buf1.append(node1.getText());
					}
				}
			}
			return buf1.toString();
		}
		return "";
	}

	/**
	 * 验证消息是否是支付宝发出的合法消息
	 * 
	 * @param params
	 *            通知返回来的参数数组
	 * @return 验证结果
	 */
	public static boolean verifyNotify(Map<String, String> params, AlipayAccountConfig alipayAccountConfig) {

		// isSign不是true，与安全校验码、请求时的参数格式（如：带自定义参数等）、编码格式有关
		String sign = params.get("sign");
		String notify_id = params.get("notify_id");
		if (StringUtils.isBlank(sign) || StringUtils.isBlank(notify_id)) {
			return false;
		}
		if (!verifySign(params, sign, alipayAccountConfig.getSignType(), alipayAccountConfig.getSignKey(),
				alipayAccountConfig.getServerPublicKey())) {
			return false;
		}
		// response的结果不是true，与服务器设置问题、合作身份者ID、notify_id一分钟失效有关
		return verifyResponse(notify_id, alipayAccountConfig);
	}

	/**
	 * 获取支付宝服务器的验证结果
	 * 
	 * @param notify_id
	 *            通知校验ID
	 * @param alipayAccountConfig
	 * @return 服务器验证结果 验证结果集： 1.invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空,
	 *         2.true 返回正确信息, 3.false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
	 */
	private static boolean verifyResponse(String notify_id, AlipayAccountConfig alipayAccountConfig) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("service", AlipayConstant.NOTIFY_VERIFY_SERVICE);
		params.put("partner", alipayAccountConfig.getPartnerId());
		params.put("notify_id", notify_id);
		String response;
		try {
			response = HttpClientUtil.invokeGet(AlipayConstant.ALIPAY_GATEWAY, params, Constants.CHARSET,
					HttpClientUtil.HTTP_TIMEOUT, HttpClientUtil.SO_TIMEOUT);
			if (StringUtils.isNotBlank(response) && response.equals("true")) {
				return true;
			}
		} catch (Exception e) {
			logger.error("[AlipaySupport] Exception: ", e);
		}
		return false;
	}

	/**
	 * 建立请求，以请求url形式构造
	 * 
	 * @param params
	 *            请求参数数组
	 * @param alipayAccountConfig
	 * @return
	 */
	private static String buildRequestUrl(Map<String, String> params, AlipayAccountConfig alipayAccountConfig) {
		// 生成要请求给支付宝的参数数组
		Map<String, String> requestParams = buildRequestParam(params, alipayAccountConfig);
		StringBuilder desSb = new StringBuilder();
		desSb.append(AlipayConstant.ALIPAY_GATEWAY).append("?");
		int i = 0;
		try {
			for (Entry<String, String> entry : requestParams.entrySet()) {
				if (i > 0) {
					desSb.append("&");
				}
				desSb.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), Constants.CHARSET));

				i++;
			}
			logger.info("[AlipaySupport] Build request url string: " + desSb.toString());
			return desSb.toString();
		} catch (UnsupportedEncodingException e) {
			logger.error("[AlipaySupport] UnsupportedEncodingException: ", e);
		}
		return null;
	}

	/**
	 * 建立请求，以HTML form形式构造
	 * 
	 * @param params
	 *            请求参数数组
	 * @param strMethod
	 *            提交方式。两个值可选：post、get
	 * @param strButtonName
	 *            确认按钮显示文字
	 * @param alipayAccountConfig
	 * @return 提交表单HTML文本
	 */
	private static String buildRequestForm(Map<String, String> params, String strMethod, String strButtonName,
			AlipayAccountConfig alipayAccountConfig) {
		// 生成要请求给支付宝的参数数组，加入签名
		Map<String, String> requestParams = buildRequestParam(params, alipayAccountConfig);

		StringBuilder htmlSb = new StringBuilder();
		htmlSb.append("<form id=\"alipaySubmit\" name=\"alipaySubmit\" action=\"" + AlipayConstant.ALIPAY_GATEWAY
				+ "?_input_charset=" + Constants.CHARSET + "\" method=\"" + strMethod + "\">");

		for (Entry<String, String> entry : requestParams.entrySet()) {
			htmlSb.append("<input type=\"hidden\" name=\"" + entry.getKey() + "\" value=\"" + entry.getValue() + "\"/>");
		}

		// submit按钮控件请不要含有name属性
		htmlSb.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");
		htmlSb.append("<script>document.forms['alipaySubmit'].submit();</script>");

		logger.info("[AlipaySupport] Build request form html string: " + htmlSb.toString());
		return htmlSb.toString();
	}

	/**
	 * 生成要请求给支付宝的参数数组
	 * 
	 * @param srcParams
	 *            请求前的参数数组
	 * @param alipayAccountConfig
	 * @return 要请求的参数数组
	 */
	private static Map<String, String> buildRequestParam(Map<String, String> srcParams,
			AlipayAccountConfig alipayAccountConfig) {
		// 过滤空值
		Map<String, String> desParams = filterEmptyParams(srcParams);
		if (desParams == null || desParams.isEmpty()) {
			return null;
		}
		String sign = getSign(desParams, alipayAccountConfig.getSignType(), alipayAccountConfig.getSignKey(),
				alipayAccountConfig.getClientPrivateKey());
		// 签名结果与签名方式加入请求提交参数组中
		desParams.put("sign", sign);
		// 签名类型
		desParams.put("sign_type", alipayAccountConfig.getSignType().getSignParam());

		return desParams;
	}

	/**
	 * 根据参数完成签名.
	 * 
	 * @param srcParams
	 * @return
	 */
	private static String getSign(Map<String, String> srcParams, AlipaySignType signType, String md5Key,
			String privateKey) {
		// 生成签名字符串
		String content = sortLinkParams(srcParams);
		// 生成签名结果
		return AlipaySign.getSign(content, signType, md5Key, privateKey);
	}

	/**
	 * 验证签名.
	 * 
	 * @param srcParams
	 * @param sign
	 * @return
	 */
	private static boolean verifySign(Map<String, String> srcParams, String sign, AlipaySignType signType,
			String md5Key, String publicKey) {
		// 生成签名字符串
		String content = sortLinkParams(srcParams);
		// 验证
		return AlipaySign.verify(content, sign, signType, md5Key, publicKey);
	}

	/**
	 * 过滤空值参数.
	 * 
	 * @return
	 */
	private static Map<String, String> filterEmptyParams(Map<String, String> srcParams) {
		// 除去数组中的空值和签名参数
		if (srcParams == null || srcParams.isEmpty()) {
			return null;
		}
		Map<String, String> desParams = new HashMap<String, String>();
		for (Entry<String, String> srcEntry : srcParams.entrySet()) {
			if (StringUtils.isBlank(srcEntry.getValue()) || srcEntry.getKey().equalsIgnoreCase("sign")
					|| srcEntry.getKey().equalsIgnoreCase("sign_type")) {
				continue;
			}
			desParams.put(srcEntry.getKey(), srcEntry.getValue());
		}
		return desParams;
	}

	/**
	 * 按key排序和连接参数.
	 * 
	 * @return
	 */
	private static String sortLinkParams(Map<String, String> srcParams) {
		// 过滤空值
		srcParams = filterEmptyParams(srcParams);
		if (srcParams == null || srcParams.isEmpty()) {
			return null;
		}
		// 排序，连接
		Map<String, String> desParams = new TreeMap<String, String>(srcParams);
		StringBuilder desSb = new StringBuilder();
		int i = 0;
		for (Entry<String, String> entry : desParams.entrySet()) {
			if (!entry.getKey().equals("sign")) {
				if (i > 0) {
					desSb.append("&");
				}
				// 默认每个参数名只有一个值，如果为数组，则需要修改这里的值的拼接，遍历所有值来拼接
				desSb.append(entry.getKey()).append("=").append(entry.getValue());
				i++;
			}
		}
		return desSb.toString();
	}

	/**
	 * 打印性能日志
	 * 
	 * @param content
	 * @param startTime
	 * @param endTime
	 */
	protected static void logPerformance(String content, Date startTime, Date endTime) {
		try {
			StringBuilder logSb = new StringBuilder();
			logSb.append(content).append(", startTime=").append(DateUtils.formatAsString(startTime))
					.append(", endTime=").append(DateUtils.formatAsString(endTime)).append(", processMillis=")
					.append((endTime.getTime() - startTime.getTime()));
			logger.info(logSb.toString());
		} catch (Exception e) {
			logger.warn("Exception: ", e);
		}
	}
}
