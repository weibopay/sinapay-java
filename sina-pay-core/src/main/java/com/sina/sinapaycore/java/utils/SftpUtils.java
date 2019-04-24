package com.sina.sinapaycore.java.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.sina.sinapaycore.java.exception.SDKException;
/**
 * sftp上传下载工具类 
  * @ClassName: SftpUtils
  * @Description: TODO(sftp上传下载工具类 )
  * @author user
  * @date 2019年4月11日
  *
 */

public class SftpUtils
{
	private static final Logger LOG = LoggerFactory.getLogger(SftpUtils.class);
	protected String host = XmlUtils.getInstance().getHost();// sftp服务器ip
	protected String username = XmlUtils.getInstance().getUsername();// 用户名，即商户号
	protected String password;// 密码，不使用，用的是密钥登录的方式
	protected String privateKey = SftpUtils.class.getResource("/").getPath().substring(1) + "id_rsa";// 密钥文件路径，联调环境密钥在网盘里key压缩包内
	protected String passphrase;// 密钥口令
	protected int port = XmlUtils.getInstance().getPort();// 默认的sftp端口号是22

	/**
	 * 获取连接
	 * 
	 * @return channel
	 */
	public ChannelSftp connectSFTP()
	{
		JSch jsch = new JSch();
		Channel channel = null;
		try
		{
			if (privateKey != null && !"".equals(privateKey))
			{
				// 使用密钥验证方式，密钥可以使有口令的密钥，也可以是没有口令的密钥
				if (passphrase != null && "".equals(passphrase))
				{
					jsch.addIdentity(privateKey, passphrase);
				} else
				{
					jsch.addIdentity(privateKey);
				}
			}
			Session session = jsch.getSession(username, host, port);
			if (password != null && !"".equals(password))
			{
				session.setPassword(password);
			}
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");// do not verify host key
			session.setConfig(sshConfig);
			// session.setTimeout(timeout);
			session.setServerAliveInterval(92000);
			session.connect();
			// 参数sftp指明要打开的连接是sftp连接
			channel = session.openChannel("sftp");
			channel.connect();
			LOG.debug("【sftp-host】", host);
			LOG.debug("【sftp-username】", username);
			LOG.debug("【sftp-port】", port);
			LOG.debug("【sftp-密钥】", privateKey);
			LOG.debug("sftp连接成功");
		} catch (JSchException e)
		{
//			e.printStackTrace();
			LOG.error("【SDK sftp连接异常】", e);
			throw new SDKException(e.getMessage());
		}
		return (ChannelSftp) channel;
	}

	/**
	 * 上传文件
	 * 
	 * @param directory
	 *            上传的目录
	 * @param uploadFile
	 *            要上传的文件
	 * @param sftp
	 */
	public void upload(String directory, String uploadFile, ChannelSftp sftp)
	{
		try
		{
			sftp.cd(directory);
			File file = new File(uploadFile);
			sftp.put(new FileInputStream(file), file.getName());
			LOG.debug("上传完成!");
		} catch (Exception e)
		{
//			e.printStackTrace();
			LOG.error("【SDK sftp上传异常】", e);
			throw new SDKException(e.getMessage());
		}
	}

	/**
	 * @throws SftpException
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public void ls(String directory, ChannelSftp sftp) throws SftpException
	{
		sftp.cd(directory);
		Vector v = sftp.ls("*.*");
		for (int i = 0; i < v.size(); i++)
		{
			System.out.print(v.get(i));
		}
	}

	/**
	 * 下载文件
	 * 
	 * @param directory
	 *            下载目录
	 * @param downloadFile
	 *            下载的文件
	 * @param saveFile
	 *            存在本地的路径
	 * @param sftp
	 */
	public void download(String directory, String downloadFile, String saveFile, ChannelSftp sftp)
	{
		try
		{
			sftp.cd(directory);
			sftp.get(downloadFile, saveFile);
			LOG.debug("【sftp下载成功】");
		} catch (Exception e)
		{
//			e.printStackTrace();
			LOG.error("【SDK sftp下载异常】", e);
			throw new SDKException(e.getMessage());
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param directory
	 *            要删除文件所在目录
	 * @param deleteFile
	 *            要删除的文件
	 * @param sftp
	 */
	public void delete(String directory, String deleteFile, ChannelSftp sftp)
	{
		try
		{
			sftp.cd(directory);
			sftp.rm(deleteFile);
		} catch (Exception e)
		{
			LOG.error("【SDK sftp删除异常】", e);
			throw new SDKException(e.getMessage());
		}
	}

	public void disconnected(ChannelSftp sftp)
	{
		if (sftp != null)
		{
			try
			{
				sftp.getSession().disconnect();
			} catch (JSchException e)
			{
				LOG.error("【SDK sftp断开连接异常】", e);
				throw new SDKException(e.getMessage());
			}
			sftp.disconnect();
		}
	}
	
	/*public static void main(String args[]) throws SftpException 
    { 
    	System.out.println("连接sftp");
    	SftpUtils s=new SftpUtils();
    	s.upload("/upload/", "d:/20190329_zhsyhz-yh-cqg-1.zip",s.connectSFTP());
    	 s.ls("/upload/",s.connectSFTP());
    	 s.download("/upload/busiexport", "20190329_zhsyhz-yh-cqg.zip", "d:/", s.connectSFTP());
    } */

}