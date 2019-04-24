package com.sina.sinapaycore.java.exception;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
  * @ClassName: OutException
  * @Description: TODO(外部异常用户展示给用户)
  * @author user
  * @date 2018年10月21日
  *
 */
public class SDKException extends RuntimeException
{

	/**
	  * @Fields field:field:{todo}(用一句话描述这个变量表示什么)
	  */
	    
	private static final long serialVersionUID = 4197752648899743507L;
	
	/**
	 * 错误码
	 */
	private String resultCode;
	
	/**
	 * 错误描述
	 */
	private String message;

	public SDKException(String resultCode, String message)
	{
		super();
		this.resultCode = resultCode;
		this.message = message;
	}

	public SDKException(String message)
	{
		super();
		this.message = message;
	}

	public String getResultCode()
	{
		return resultCode;
	}

	public void setResultCode(String resultCode)
	{
		this.resultCode = resultCode;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
	
	
}
