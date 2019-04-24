package com.sina.sinapaycore.java.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取加密公钥
  * @ClassName: PublicKeyUtils
  * @Description: TODO(这里用一句话描述这个类的作用)
  * @author user
  * @date 2019年4月8日
  *
 */
public class KeyUtils
{
	private static final Logger LOG = LoggerFactory.getLogger(KeyUtils.class);
	
	private static KeyUtils intance = null;
	
	/**
	 * 加密密钥
	 */
	private String publicKey;
	
	/**
	 * 加签私钥
	 */
	private String privateSignKey;
	
	/**
	 * 验签公钥
	 */
	private String publicSignKey;
	
	private KeyUtils()
	{
		
	}
	
	public static synchronized KeyUtils getInstance()
	{
		if (intance == null)
		{
			intance = new KeyUtils();
			String publicKeyStr = Tools.getKey("rsa_public.pem");
			String publicSignKeyStr = Tools.getKey("rsa_sign_public.pem");
			String privateSignKeyStr = Tools.getKey("rsa_sign_private.pem");
			intance.setPrivateSignKey(privateSignKeyStr);
			intance.setPublicKey(publicKeyStr);
			intance.setPublicSignKey(publicSignKeyStr);
			LOG.debug("【加密公钥】{}", publicKeyStr);
			LOG.debug("【加签私钥】{}", privateSignKeyStr);
			LOG.debug("【验签公钥】{}", publicSignKeyStr);
		}
		return intance;
	}

	public String getPublicKey()
	{
		return publicKey;
	}

	public void setPublicKey(String publicKey)
	{
		this.publicKey = publicKey;
	}

	public String getPrivateSignKey()
	{
		return privateSignKey;
	}

	public void setPrivateSignKey(String privateSignKey)
	{
		this.privateSignKey = privateSignKey;
	}

	public String getPublicSignKey()
	{
		return publicSignKey;
	}

	public void setPublicSignKey(String publicSignKey)
	{
		this.publicSignKey = publicSignKey;
	}
	
}
