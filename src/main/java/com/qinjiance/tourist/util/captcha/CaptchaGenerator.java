/**
 * 
 */
package com.qinjiance.tourist.util.captcha;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

/**
 * @author Jiance Qin
 * 
 * @Revision revision
 * 
 * @Date 2013-4-2
 * 
 * @Time 下午9:08:28
 * 
 */
public class CaptchaGenerator {

	public static final String DEFAULT_IMAGE_MIME_TYPE = "image/png";
	public static final String DEFAULT_IMAGE_TYPE = "png";

	private static DefaultKaptcha captchaProducer;

	/**
	 * 
	 */
	public CaptchaGenerator() {

	}

	/**
	 * @return the captchaProducer
	 */
	public static DefaultKaptcha getCaptchaProducer() {

		if (captchaProducer == null) {
			captchaProducer = new DefaultKaptcha();

			// Set image parameters
			Properties captchaProp = new Properties();
			captchaProp.put("kaptcha.image.width", "120");
			captchaProp.put("kaptcha.image.height", "40");
			captchaProp.put("kaptcha.textproducer.font.size", "30");
			captchaProp.put("kaptcha.noise.color", "255,0,0");
			captchaProp.put("kaptcha.noise.impl",
					"com.google.code.kaptcha.impl.NoNoise");
			captchaProp.put("kaptcha.background.clear.from", "127,127,127");
			captchaProp.put("kaptcha.background.clear.to", "255,255,255");
			captchaProp
					.put("kaptcha.textproducer.char.string",
							"0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
			captchaProp.put("kaptcha.textproducer.char.length", "5");
			captchaProp.put("kaptcha.textproducer.char.space", "2");
			captchaProp.put("kaptcha.textproducer.impl",
					"com.google.code.kaptcha.text.impl.DefaultTextCreator");
			captchaProp.put("kaptcha.obscurificator.impl",
					"com.google.code.kaptcha.impl.ShadowGimpy");

			// Set captcha configure
			captchaProducer.setConfig(new Config(captchaProp));
		}
		return captchaProducer;
	}

	/**
	 * @param captchaProducer
	 *            the captchaProducer to set
	 */
	public static void setCaptchaProducer(DefaultKaptcha captchaProducer) {

		CaptchaGenerator.captchaProducer = captchaProducer;
	}

	/**
	 * Generate a captcha text.
	 * 
	 * @return a string of captcha text
	 */
	public static String generateCaptchaText() {

		return getCaptchaProducer().createText();
	}

	/**
	 * Generate a captcha image using com.google.code.kaptcha.kaptcha lib.
	 * 
	 * @param captchaText
	 *            captcha text string used to generate image
	 * @return a byte array containing the image data
	 */
	public static byte[] generateCaptchaImage(String captchaText) {

		if (captchaText != null && !captchaText.isEmpty()) {

			// Generate captcha image
			BufferedImage captchaImage = getCaptchaProducer().createImage(
					captchaText);

			ByteArrayOutputStream os = new ByteArrayOutputStream();

			try {
				ImageIO.write(captchaImage, DEFAULT_IMAGE_TYPE, os);
			} catch (IOException e) {
				e.printStackTrace();
			}

			return os.toByteArray();
		} else {
			return null;
		}
	}
}
