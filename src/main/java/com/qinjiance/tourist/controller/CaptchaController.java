package com.qinjiance.tourist.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import module.laohu.commons.model.ResponseResult;
import module.laohu.commons.util.EncryptUtils;
import module.laohu.commons.util.StringHelper;
import module.laohu.commons.util.captcha.CaptchaGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qinjiance.tourist.constants.Constants;
import com.qinjiance.tourist.manager.IEhCacheManager;
import com.qinjiance.tourist.manager.impl.EhCacheManager.CacheType;

/**
 * @author "Jiance Qin"
 * 
 * @date 2014年7月5日
 * 
 * @time 下午4:55:00
 * 
 * @desc
 * 
 */
@Controller
public class CaptchaController extends BaseTouristController {

	@Autowired
	private IEhCacheManager ehCacheManager;

	/**
	 * 生成验证码一次性键-值.
	 * 
	 * @param captchaType
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/captcha/key")
	@ResponseBody
	public ResponseResult<String> getCapchaKey() {

		ResponseResult<String> rr = new ResponseResult<String>();
		rr.setCode(Constants.CODE_FAIL);
		try {
			String captchaText = CaptchaGenerator.generateCaptchaText();
			if (StringHelper.isNotBlank(captchaText)) {
				String uuidKey = EncryptUtils.getUUID();
				ehCacheManager.putToCache(CacheType.MIN3, getCaptchaCacheKey(uuidKey), captchaText);
				rr.setCode(Constants.CODE_SUCC);
				rr.setResult(uuidKey);
			} else {
				rr.setMessage("生成验证码Key错误,请重试");
			}
		} catch (Exception e) {
			logger.error("Generate captcha key failed！", e);
			rr.setMessage("生成验证码Key错误,请重试");
		}

		return rr;
	}

	/**
	 * 验证码图片生成服务.
	 * 
	 * @param captchaType
	 * @param captchaKey
	 * @param session
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/captcha/img")
	public void getCaptcha(@RequestParam String captchaKey, HttpServletResponse response) throws IOException {

		String captchaText = ehCacheManager.getFromCache(CacheType.MIN3, getCaptchaCacheKey(captchaKey));
		if (StringHelper.isNotBlank(captchaText)) {
			byte[] captchaImg = CaptchaGenerator.generateCaptchaImage(captchaText);
			response.setContentType(CaptchaGenerator.DEFAULT_IMAGE_MIME_TYPE);
			OutputStream os = null;
			try {
				os = response.getOutputStream();
				os.write(captchaImg);
			} catch (IOException e) {
				logger.error("Generate captcha failed！", e);
			} finally {
				if (os != null) {
					os.flush();
					os.close();
				}
			}
		}
	}

	public String getCaptchaCacheKey(String captchaKey) {

		return "CapthaKey#" + captchaKey;
	}
}
