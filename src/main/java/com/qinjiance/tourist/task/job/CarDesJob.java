/**
 * 
 */
package com.qinjiance.tourist.task.job;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import module.laohu.commons.util.DateUtils;

/**
 * @author Administrator
 *
 * @datetime 2015年12月24日 上午2:21:45
 *
 * @desc
 */
public class CarDesJob {

	protected static final Logger logger = LoggerFactory.getLogger(CarDesJob.class);

	private static FTPClient ftpClient = new FTPClient();

	public String localPath = null;
	public String ftpHost = null;
	public Integer ftpPort = null;
	public String ftpPath = null;
	public String ftpFilename = null;
	public String ftpUsername = null;
	public String ftpPassword = null;

	public static FTPClient getFtpClient() {
		return ftpClient;
	}

	public static void setFtpClient(FTPClient ftpClient) {
		CarDesJob.ftpClient = ftpClient;
	}

	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	public String getFtpHost() {
		return ftpHost;
	}

	public void setFtpHost(String ftpHost) {
		this.ftpHost = ftpHost;
	}

	public Integer getFtpPort() {
		return ftpPort;
	}

	public void setFtpPort(Integer ftpPort) {
		this.ftpPort = ftpPort;
	}

	public String getFtpPath() {
		return ftpPath;
	}

	public void setFtpPath(String ftpPath) {
		this.ftpPath = ftpPath;
	}

	public String getFtpFilename() {
		return ftpFilename;
	}

	public void setFtpFilename(String ftpFilename) {
		this.ftpFilename = ftpFilename;
	}

	public String getFtpUsername() {
		return ftpUsername;
	}

	public void setFtpUsername(String ftpUsername) {
		this.ftpUsername = ftpUsername;
	}

	public String getFtpPassword() {
		return ftpPassword;
	}

	public void setFtpPassword(String ftpPassword) {
		this.ftpPassword = ftpPassword;
	}

	public static Logger getLogger() {
		return logger;
	}

	/**
	 * 
	 */
	public CarDesJob() {

	}

	public void work() {
		logger.info("Start car destination scan job, downloading cvs file...");
		File file = downFile(ftpHost, ftpPort, ftpUsername, ftpPassword, ftpPath, ftpFilename, localPath);
		if (file == null) {
			logger.info("Stop car destination scan job, download failed.");
		}
		logger.info("Updating databases with cvs file...");

	}

	/**
	 * Description: 从FTP服务器下载文件
	 *
	 * @Version1.0
	 * 
	 * @param host
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param remotePath
	 *            FTP服务器上的相对路径
	 * @param fileName
	 *            要下载的文件名
	 * @param localPath
	 *            下载后保存到本地的路径
	 * @return
	 */
	public static File downFile(String host, int port, String username, String password, String remotePath,
			String fileName, String localPath) {
		File result = null;
		OutputStream os = null;
		try {
			int reply;
			ftpClient.setControlEncoding("UTF-8");

			/*
			 * 为了上传和下载中文文件，有些地方建议使用以下两句代替 new
			 * String(remotePath.getBytes(encoding),"iso-8859-1")转码。 经过测试，通不过。
			 */
			// FTPClientConfig conf = new
			// FTPClientConfig(FTPClientConfig.SYST_NT);
			// conf.setServerLanguageCode("zh");

			ftpClient.connect(host, port);
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			boolean loginRet = ftpClient.login(username, password);// 登录
			logger.info("loginRet = " + loginRet);
			// 设置文件传输类型为二进制
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			// 获取ftp登录应答代码
			reply = ftpClient.getReplyCode();
			logger.info("reply = " + reply);
			// 验证是否登陆成功
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				logger.warn("FTP server refused connection.");
				return result;
			}
			// 转移到FTP服务器目录至指定的目录下
			boolean changeRet = ftpClient
					.changeWorkingDirectory(new String(remotePath.getBytes("UTF-8"), "iso-8859-1"));
			logger.info("changeRet to " + new String(remotePath.getBytes("UTF-8"), "iso-8859-1") + " = " + changeRet);
			// 获取文件列表
			String[] names = ftpClient.listNames();
			logger.info("Names[] = " + (names == null ? "null" : Arrays.toString(names)));
			FTPFile[] fs = ftpClient.listFiles();
			logger.info("FTPFile[] = " + (fs == null ? "null" : fs.length));
			for (FTPFile ff : fs) {
				logger.info(ff.getName());
				if (ff.getName().equals(fileName)) {
					File dir = new File(localPath);
					if (!dir.exists()) {
						logger.info("result.mkdirs() = " + dir.mkdirs());
					}
					result = new File(localPath + fileName);
					if (!result.exists()) {
						logger.info("result.createNewFile() = " + result.createNewFile());
					}
					os = new FileOutputStream(result);
					boolean retrTet = ftpClient.retrieveFile(fileName, os);
					logger.info("fileName = " + fileName + ", retrieveFileRet = " + retrTet);
					os.close();
				}
			}
			ftpClient.logout();
			return result;
		} catch (Exception e) {
			logger.error("Exception: ", e);
		} finally {
			try {
				if (os != null) {
					os.close();
				}
				if (ftpClient.isConnected()) {
					ftpClient.disconnect();
				}
			} catch (IOException ioe) {
				logger.error("IOException: ", ioe);
			}
		}
		return result;
	}

	public String getFilePath() {
		String filepath = localPath + "carDes";
		String filename = DateUtils.formatAsString(new Date(), "yyyyMMddHHmmss");
		return filepath + filename + ".zip";
	}

	public static void main(String[] args) {
		File f = downFile("ftp.touricoholidays.com", 21, "THFDataPDS", "THFDataPass", "CarInfo",
				"PDS2_CarStationsList_20151027_T1024.csv.zip", "E:\\workspace\\tourist\\");
		System.out.println(f == null ? null : f.getName());
	}
}
