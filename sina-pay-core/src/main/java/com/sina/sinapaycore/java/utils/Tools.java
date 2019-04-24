package com.sina.sinapaycore.java.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sina.sinapaycore.java.exception.SDKException;

public class Tools
{
	private static final Logger LOG = LoggerFactory.getLogger(Tools.class);
	
	// 签名版本
	public static final String SIGN_VERSION_NAME = "sign_version";

	// 签名类型
	public static final String SIGN_TYPE_NAME = "sign_type";

	// 签名值
	public static final String SIGN_NAME = "sign";

	/**
	 * 创建http post发送数据的url连接
	 *
	 * @param params
	 *            转换数据 map
	 * @param encode
	 *            是否做urlencode
	 * @return url
	 */
	public static String createLinkString(Map<String, String> params, boolean encode)
	{
		// 获取map中的key值
		List<String> keys = new ArrayList<String>(params.keySet());
		// 对集合进行排序
		Collections.sort(keys);
		String prestr = "";
		// 获取参数中的字符集
		String charset = params.get("_input_charset");
		for (int i = 0; i < keys.size(); i++)
		{
			String key = keys.get(i);
			String value = params.get(key);
			if (encode)
			{
				try
				{
					value = URLEncoder.encode(URLEncoder.encode(value, charset), charset);
				} catch (UnsupportedEncodingException e)
				{
					LOG.error("【SDK 异常】", e);
					throw new SDKException(e.getMessage());
				}
			}
			// 将map转换为key=value&key=value这种形式的url链接
			if (i == keys.size() - 1)
			{
				prestr = prestr + key + "=" + value;
			} else
			{
				prestr = prestr + key + "=" + value + "&";
			}
		}

		return prestr;
	}

	/**
	 * 将HttpServletRequest中的ParameterMap转换为map(String[]转为逗号分隔的参数)
	 * 
	 * @Title: getParameterMap
	 * @Description: TODO(将HttpServletRequest中的getParameterMap转换为map(String[]转为逗号分隔的参数))
	 * @param request HttpServletRequest
	 * @param isFilter 是否过滤掉sign、sign_type、sign_version
	 * @return 参数
	 * @return Map 返回类型
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map getParameterMap(HttpServletRequest request, boolean isFilter)
	{
		// 参数Map
		Map properties = request.getParameterMap();

		// 返回值Map
		Map returnMap = new HashMap();
		// 使用迭代器遍历map，将ParameterMap中的类型为string数组的值转为逗号分隔的string字符串
		Iterator entries = properties.entrySet().iterator();
		Map.Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext())
		{
			entry = (Map.Entry) entries.next();
			name = (String) entry.getKey();
			if (isFilter)
			{
				if (!name.equals("sign") && !name.equals("sign_type") && !name.equals("sign_version"))
				{
					Object valueObj = entry.getValue();
					if (null == valueObj)
					{
						value = "";
					} else if (valueObj instanceof String[])
					{
						String[] values = (String[]) valueObj;
						for (int i = 0; i < values.length; i++)
						{
							value = values[i] + ",";
						}
						// 去掉末尾的逗号
						value = value.substring(0, value.length() - 1);
					} else
					{
						value = valueObj.toString();
					}
					returnMap.put(name, value);
				}
			} else
			{
				Object valueObj = entry.getValue();
				if (null == valueObj)
				{
					value = "";
				} else if (valueObj instanceof String[])
				{
					String[] values = (String[]) valueObj;
					for (int i = 0; i < values.length; i++)
					{
						value = values[i] + ",";
					}
					value = value.substring(0, value.length() - 1);
				} else
				{
					value = valueObj.toString();
				}
				returnMap.put(name, value);
			}
		}
		return returnMap;
	}

	/**
	 * 计算文件的MD5摘要值
	 * 
	 * @param file
	 *            文件路劲
	 * @return 32位的MD5摘要
	 */
	public static String getFileMD5(File file)
	{
		if (!file.isFile())
		{
			return null;
		}
		MessageDigest digest = null;
		FileInputStream in = null;
		byte buffer[] = new byte[1024];
		int len;
		try
		{
			digest = MessageDigest.getInstance("MD5");
			in = new FileInputStream(file);
			while ((len = in.read(buffer, 0, 1024)) != -1)
			{
				digest.update(buffer, 0, len);
			}
			in.close();
		} catch (Exception e)
		{
			LOG.error("【SDK 异常】", e);
			throw new SDKException(e.getMessage());
		}
		String bytes2hex03 = bytes2hex03(digest.digest());
		return bytes2hex03;

	}

	public static String bytes2hex03(byte[] bytes)
	{
		final String HEX = "0123456789abcdef";
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		for (byte b : bytes)
		{
			// 取出这个字节的高4位，然后与0x0f与运算，得到一个0-15之间的数据，通过HEX.charAt(0-15)即为16进制数
			sb.append(HEX.charAt((b >> 4) & 0x0f));
			// 取出这个字节的低位，与0x0f与运算，得到一个0-15之间的数据，通过HEX.charAt(0-15)即为16进制数
			sb.append(HEX.charAt(b & 0x0f));
		}

		return sb.toString();
	}

	public static String[] get_need_RSA()
	{
		String[] array = { "real_name", "cert_no", "verify_entity", "bank_account_no", "account_name", "phone_no",
				"validity_period", "verification_value", "telephone", "email", "organization_no", "legal_person",
				"legal_person_phone", "agent_name", "license_no", "agent_mobile" };

		return array;
	}

	/** 一次性压缩多个文件，文件存放至一个文件夹中 */
	public static void ZipMultiFile(String filepath, String zippath)
	{
		try
		{
			File file = new File(filepath);// 要被压缩的文件夹
			File zipFile = new File(zippath);
			InputStream input = null;
			ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
			if (file.isDirectory())
			{
				File[] files = file.listFiles();
				for (int i = 0; i < files.length; ++i)
				{
					input = new FileInputStream(files[i]);
					zipOut.putNextEntry(new ZipEntry(file.getName() + File.separator + files[i].getName()));
					int temp = 0;
					while ((temp = input.read()) != -1)
					{
						zipOut.write(temp);
					}
					input.close();
				}
			}
			zipOut.close();
		} catch (Exception e)
		{
			LOG.error("【SDK 异常】", e);
			throw new SDKException(e.getMessage());
		}
	}

	/***
	 * 去掉字符串前后的空格，中间的空格保留
	 * 
	 * @param str
	 * @return
	 */
	public static String trimInnerSpaceStr(String str)
	{
		str = str.trim();
		while (str.startsWith(" "))
		{
			str = str.substring(1, str.length()).trim();
		}
		while (str.endsWith(" "))
		{
			str = str.substring(0, str.length() - 1).trim();
		}

		return str;
	}

	/**
	 * 将文本文件中的内容读入到buffer中
	 * 
	 * @param buffer
	 *            buffer
	 * @param filePath
	 *            文件路径
	 * @throws IOException
	 *             异常
	 * @author cn.outofmemory
	 * @date 2013-1-7
	 */
	public static void readToBuffer(StringBuffer buffer, String filePath) throws IOException
	{
		InputStream is = new FileInputStream(filePath);
		String line; // 用来保存每行读取的内容
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		line = reader.readLine(); // 读取第一行
		while (line != null)
		{ // 如果 line 为空说明读完了
			buffer.append(line); // 将读到的内容添加到 buffer 中
			buffer.append("\n"); // 添加换行符
			line = reader.readLine(); // 读取下一行
		}
		reader.close();
		is.close();
	}

	/**
	 * 读取文本文件内容
	 * 
	 * @param filePath
	 *            文件所在路径
	 * @return 文本内容
	 * @throws IOException
	 *             异常
	 * @author cn.outofmemory
	 * @date 2013-1-7
	 */
	public static String readFile(String filePath) throws IOException
	{
		StringBuffer sb = new StringBuffer();
		Tools.readToBuffer(sb, filePath);
		return sb.toString();
	}

	public static String getKey(String keyName)
	{
		String str = null;
		try
		{
			str = Tools.readFile(Tools.class.getResource("/").getPath().substring(1) + keyName);
		} catch (IOException e)
		{
			LOG.error("【SDK文件读取失败】" + keyName, e);
//			e.printStackTrace();
			throw new SDKException(e.getMessage());
		}
		String a[] = str.split("-----");
		String str2 = a[2];
		str2 = str2.replace("\n", "");
		return str2;
	}

	/**
	 * 生成log跟踪号
	 * 
	 * @return
	 */
	public static String getUUID()
	{
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 对需要加密的字段做加密处理，如果字段为空则去除
	 * 
	 * @param request
	 * @param param1
	 *            数据map
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map toBeEncrypt(HttpServletRequest request, Map param1) throws IOException
	{
		// 获取加密密钥
		String encrypt = Tools.getKey("rsa_public.pem"); 
		// 定义一个接收parameterMap的变量
		Map properties = request.getParameterMap();
		// 遍历map，对需要加密的非空参数值进行加密
		Iterator entries = properties.entrySet().iterator();
		Map.Entry entry;
		String name = "";// 数据的key
		String value = "";// 数据的内容
		// 需要加密key（参数名）的数组
		String[] arr = Tools.get_need_RSA();
		// 对数组进行排序
		Arrays.sort(arr);
		while (entries.hasNext())
		{
			entry = (Map.Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			String base64_value_encrypt = null;
			try
			{
				if (null == valueObj)
				{
					value = "";
				} else if (valueObj instanceof String[])
				{
					String[] values = (String[]) valueObj;
					for (int j = 0; j < values.length; j++)
					{
						value = values[j] + ",";
					}
					// 对value头尾去空格，保留字符串中间的空格
					value = Tools.trimInnerSpaceStr(value.substring(0, value.length() - 1));
					// 如果key（前端参数）在数组内则对其做加密
					if (Arrays.binarySearch(arr, name) >= 0)
					{
						byte[] value_encrypt = null;
						// 对参数值进行加密
						value_encrypt = RSA.encryptByPublicKey(value.getBytes("utf-8"), encrypt);
						// 将加密获得的字节数组转换为base64字符串
						base64_value_encrypt = Base64.encodeBase64String(value_encrypt);
						// 将加密好的value放到map中替换原有值
						param1.put(name, base64_value_encrypt.toString());
					} else
					{
						value = valueObj.toString();
					}

				}
			} catch (Exception e)
			{
				LOG.error("【SDK 异常】", e);
				throw new SDKException(e.getMessage());
			}

		}
		return param1;
	}

}
