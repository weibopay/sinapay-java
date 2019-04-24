package com.sina.sinapaycore.java.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sina.sinapaycore.java.exception.SDKException;

public class SendUtils
{
	private static final Logger LOG = LoggerFactory.getLogger(SendUtils.class);

	/**
	 * 需要加密的参数列表
	 */
	private static final String[] NEED_TO_ENCRYPT_PARAMS = { "real_name", "cert_no", "verify_entity", "bank_account_no",
			"account_name", "phone_no", "validity_period", "verification_value", "telephone", "email",
			"organization_no", "legal_person", "legal_person_phone", "agent_name", "license_no", "agent_mobile" };

	private static final Map<String, String> NEED_DEAL_PARAMS;

	/**
	 * 批量付款到银行卡交易列表中需要加密的收款方银行账户名称的下标
	 */
	private static final int NAME = 1;

	/**
	 * 批量付款到银行卡交易列表中需要加密的收款方身份证号的下标
	 */
	private static final int ID_CARD_NO = 2;

	/**
	 * 批量付款到银行卡交易列表中需要加密的收款方银行账号的下标
	 */
	private static final int BANK_CARD_NO = 3;

	/**
	 * 批量付款到银行卡交易列表长度
	 */
	private static final int LENG_PAY2BANK_DETAIL = 13;

	static
	{
		NEED_DEAL_PARAMS = new HashMap<String, String>();
		NEED_DEAL_PARAMS.put("create_b2c_order", "split_list,split_role_list,risk_info,device_info");
		NEED_DEAL_PARAMS.put("settle_b2c_order", "risk_info,device_info");
		NEED_DEAL_PARAMS.put("create_hosting_refund", "refund_split_list");
		NEED_DEAL_PARAMS.put("create_batch_pay2bank", "detail_list");
		NEED_DEAL_PARAMS.put("receipt_split", "split_list");
		NEED_DEAL_PARAMS.put("create_batch_pay2account", "detail_list");
	}

	/**
	 * 处理相关的请求
	 * 
	 * @Title: dealRequest
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param params
	 * @return 参数
	 * @return String 返回类型
	 */
	public static Map<String, Object> dealRequest(Map<String, Object> paramsObj, String url)
	{
		if (paramsObj == null || paramsObj.isEmpty())
		{
			return null;
		}
		// params带有空参数
		Map<String, String> params = dealParam(paramsObj);
		// 签名类型
		String signType = params.get("sign_type");
		String inputCharset = params.get("_input_charset");
		String requestTime = params.get("request_time");
		// String service = params.get("service");
		// String url = UrlUtils.getInstance().getMas();
		// 获取加密后的请求参数（如果请求中的参数需要加密）
		Map<String, String> encParams = SendUtils.encryptParams(params);
		// 获取签名原文 过滤调空参数 sign\sign_type\sign_version
		String signContent = SendUtils.sortParam(encParams);
		LOG.debug("【SDK签名原文[" + requestTime + "]】 = {}", signContent);
		// 加签
		String sign = SendUtils.addSign(signContent, signType, inputCharset);
		encParams.put("sign", sign);

		// 对参数进行urlencode处理
		String encodeParam = SendUtils.encodeParam(encParams, inputCharset);
		LOG.debug("【SDK网关请求参数[" + requestTime + "]】 = {}", encodeParam);
		LOG.debug("【SDK网关请求地址[" + requestTime + "]】 = {}", url);
		// 发送post请求，并获取同步响应
		String result = SendUtils.getResult(url, encodeParam, inputCharset);
		LOG.debug("【SDK网关响应参数[" + requestTime + "]】 = {}", result);
		// 验签
		boolean flag = SendUtils.checkSign(result);
		Map<String, Object> resultMap = JSON.parseObject(result);
		if (!flag)
		{
			LOG.error("【SDK网关响应参数[" + requestTime + "]验签失败】");
			resultMap.put("SDK_CHECK_SIGN_ERROR", "响应报文验签失败，请检查KEY或者报文");
		}
		return resultMap;
	}

	/**
	 * 将map<String, Object> 转换成功Map<String, String>
	 * 
	 * @Title: dealParam
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param source
	 * @return 参数
	 * @return Map<String,String> 返回类型
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> dealParam(Map<String, Object> source)
	{
		String service = (String) source.get("service");
		Map<String, String> returnMap = new HashMap<>();
		for (Map.Entry<String, Object> entry : source.entrySet())
		{
			if ("create_b2c_order".equals(service))
			{
				if ("risk_info".equals(entry.getKey()))
				{
					returnMap.put("risk_info", JSON.toJSONString(entry.getValue()));
				} else if ("device_info".equals(entry.getKey()))
				{
					returnMap.put("device_info", JSON.toJSONString(entry.getValue()));
				} else if ("split_list".equals(entry.getKey()))
				{
					returnMap.put("split_list", connectParam((List<List<String>>) entry.getValue()));
				} else if ("split_role_list".equals(entry.getKey()))
				{
					returnMap.put("split_role_list", connectParam((List<List<String>>) entry.getValue()));
				} else
				{
					returnMap.put(entry.getKey(), (String) entry.getValue());
				}
			} else if ("settle_b2c_order".equals(service))
			{
				if ("risk_info".equals(entry.getKey()))
				{
					returnMap.put("risk_info", JSON.toJSONString(entry.getValue()));
				} else if ("device_info".equals(entry.getKey()))
				{
					returnMap.put("device_info", JSON.toJSONString(entry.getValue()));
				} else
				{
					returnMap.put(entry.getKey(), (String) entry.getValue());
				}
			} else if ("create_hosting_refund".equals(service))
			{
				if ("refund_split_list".equals(entry.getKey()))
				{
					returnMap.put("refund_split_list", connectParam((List<List<String>>) entry.getValue()));
				} else
				{
					returnMap.put(entry.getKey(), (String) entry.getValue());
				}
			} else if ("create_batch_pay2bank".equals(service))
			{
				if ("detail_list".equals(entry.getKey()))
				{
					returnMap.put("detail_list", dealPay2Bank((List<List<String>>) entry.getValue()));
				} else
				{
					returnMap.put(entry.getKey(), (String) entry.getValue());
				}
			} else if ("receipt_split".equals(service))
			{
				if ("split_list".equals(entry.getKey()))
				{
					returnMap.put("split_list", connectParam((List<List<String>>) entry.getValue()));
				} else
				{
					returnMap.put(entry.getKey(), (String) entry.getValue());
				}
			} else if ("create_batch_pay2account".equals(service))
			{
				if ("detail_list".equals(entry.getKey()))
				{
					returnMap.put("detail_list", connectParamForPay2Account((List<List<String>>) entry.getValue()));
				} else
				{
					returnMap.put(entry.getKey(), (String) entry.getValue());
				}
			} else
			{
				returnMap.put(entry.getKey(), (String) entry.getValue());
			}

		}

		// 判读参数是否需要特殊处理
		/*
		 * for (Map.Entry<String, String> entry : NEED_DEAL_PARAMS.entrySet()) { if
		 * ("".equals(service)) { String params = entry.getValue(); if
		 * (StringUtils.isNotBlank(params) && params.contains(",")) { String[] param =
		 * params.split(","); for (int i = 0; i < param.length; i++) { List<String>
		 * values = (List<String>) source.get(param[i]); } } } }
		 */
		return returnMap;
	}

	/**
	 * 处理批量付款到银行卡参数
	 * 
	 * @Title: dealPay2Bank
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param list
	 * @return 参数
	 * @return String 返回类型
	 * @throws Exception
	 * @throws UnsupportedEncodingException
	 */
	public static String dealPay2Bank(List<List<String>> list)
	{
		StringBuffer sb = new StringBuffer();
		for (List<String> obj : list)
		{
			if (obj.size() != LENG_PAY2BANK_DETAIL)
			{
				for (int i = 0; i < obj.size(); i++)
				{
					sb.append(obj.get(i)).append("^");
				}
				sb.subSequence(0, sb.length() - 1);
			} else
			{
				// 获取加密密钥
				String encryptKey = KeyUtils.getInstance().getPublicKey();
				for (int i = 0; i < obj.size(); i++)
				{
					if (i == NAME || i == ID_CARD_NO || i == BANK_CARD_NO)
					{
						if (StringUtils.isEmpty(obj.get(i)))
						{
							sb.append(obj.get(i)).append("^");
						} else
						{
							try
							{
								byte[] valueEncrypt = null;
								// 对参数值进行加密
								valueEncrypt = RSAUtils.encryptByPublicKey(obj.get(i).getBytes("utf-8"), encryptKey);
								// 将加密获得的字节数组转换为base64字符串
								String base64ValueEncrypt = Base64.encodeBase64String(valueEncrypt);
								sb.append(base64ValueEncrypt).append("^");
							} catch (Exception e)
							{
								LOG.error("【SDK加密异常】", e);
								throw new SDKException(e.getMessage());
							}
						}
					} else
					{
						sb.append(obj.get(i)).append("^");
					}
				}
				sb = new StringBuffer(sb.substring(0, sb.length() - 1));
			}
			sb.append("|");
		}
		sb = new StringBuffer(sb.substring(0, sb.length() - 1));
		return sb.toString();
	}

	/**
	 * 处理^ | 的拼接
	 * 
	 * @Title: connectParam
	 * @Description: TODO(处理^ | 的拼接)
	 * @param list
	 * @return 参数
	 * @return String 返回类型
	 */
	public static String connectParam(List<List<String>> list)
	{
		StringBuffer sb = new StringBuffer();
		for (List<String> obj : list)
		{
			for (String pro : obj)
			{
				sb.append(pro).append("^");
			}
			sb = new StringBuffer(sb.substring(0, sb.length() - 1));
			sb.append("|");
		}
		sb = new StringBuffer(sb.substring(0, sb.length() - 1));
		return sb.toString();
	}

	/**
	 * 处理~ $ 的拼接 批量付款到银行卡
	 * 
	 * @Title: connectParamForPay2Account
	 * @Description: TODO(处理^ | 的拼接)
	 * @param list
	 * @return 参数
	 * @return String 返回类型
	 */
	public static String connectParamForPay2Account(List<List<String>> list)
	{
		StringBuffer sb = new StringBuffer();
		for (List<String> obj : list)
		{
			for (String pro : obj)
			{
				sb.append(pro).append("~");
			}
			sb = new StringBuffer(sb.substring(0, sb.length() - 1));
			sb.append("$");
		}
		sb = new StringBuffer(sb.substring(0, sb.length() - 1));
		return sb.toString();
	}

	/**
	 * 将request转换成map<String, String>
	 * 
	 * @Title: requestMap2Map
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param request
	 * @return 参数
	 * @return Map<String,String> 返回类型
	 */
	public static Map<String, String> requestMap2Map(HttpServletRequest request)
	{
		Map<String, String> paramMap = new HashMap<>();

		Map<String, String[]> params = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : params.entrySet())
		{
			String val = "";
			if (entry.getValue() != null && entry.getValue().length != 0)
			{
				for (String value : entry.getValue())
				{
					val = value + "," + val;
				}
				val = val.substring(0, val.length() - 1).trim();
			}

			if (StringUtils.isNotBlank(val))
			{
				paramMap.put(entry.getKey(), val);
			}
		}
		LOG.debug("【SDK非空请求参数】{}", paramMap.toString());
		return paramMap;
	}

	/**
	 * 将request转换成map<String, Object>
	 * 
	 * @Title: requestMap2Map
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param request
	 * @return 参数
	 * @return Map<String,String> 返回类型
	 */
	public static Map<String, Object> requestMap2MapObj(HttpServletRequest request)
	{
		Map<String, Object> paramMap = new HashMap<>();

		Map<String, String[]> params = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : params.entrySet())
		{
			String val = "";
			if (entry.getValue() != null && entry.getValue().length != 0)
			{
				for (String value : entry.getValue())
				{
					val = value + "," + val;
				}
				val = val.substring(0, val.length() - 1).trim();
			}

			if (StringUtils.isNotBlank(val))
			{
				paramMap.put(entry.getKey(), val);
			}
		}
		LOG.debug("【SDK非空请求参数】{}", paramMap.toString());
		return paramMap;
	}

	/**
	 * javabean 转map
	 * 
	 * @Title: obj2map
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param object
	 * @return 参数
	 * @return Map<?,?> 返回类型
	 */
	public static Map<?, ?> obj2map(Object object)
	{
		if (object == null)
		{
			return null;
		}

		return new BeanMap(object);
	}

	/**
	 * 对需要加密的参数进行加密处理
	 * 
	 * @Title: encryptParams
	 * @Description: TODO(对需要加密的参数进行加密处理)
	 * @param params
	 * @return 参数
	 * @return Map<String,String> 返回类型
	 */
	public static Map<String, String> encryptParams(Map<String, String> params)
	{
		try
		{
			// 获取加密密钥
			String encryptKey = KeyUtils.getInstance().getPublicKey();

			for (Map.Entry<String, String> entry : params.entrySet())
			{
				//经办人信息接口邮箱不需要加密
				if ("smt_fund_agent_buy".equals(params.get("service")) && "email".equals(entry.getKey()))
				{
					continue;
				}
				if (Arrays.asList(NEED_TO_ENCRYPT_PARAMS).contains(entry.getKey()))
				{
					byte[] valueEncrypt = null;
					// 对参数值进行加密
					valueEncrypt = RSAUtils.encryptByPublicKey(entry.getValue().getBytes("utf-8"), encryptKey);
					// 将加密获得的字节数组转换为base64字符串
					String base64ValueEncrypt = Base64.encodeBase64String(valueEncrypt);
					// 将加密好的value放到map中替换原有值
					params.put(entry.getKey(), base64ValueEncrypt.toString());
				}
			}
			return params;
		} catch (Exception e)
		{
//			e.printStackTrace();
			LOG.error("【SDK加密失败】", e);
			throw new SDKException(e.getMessage());
		}
	}

	/**
	 * 获取签名原文
	 * 
	 * @Title: getSignContent
	 * @Description: TODO(获取签名原文)
	 * @param params
	 * @return 参数
	 * @return String 返回类型
	 */
	public static String sortParam(Map<String, String> params)
	{
		// 获取map中的key值
		List<String> keys = new ArrayList<String>(params.keySet());
		// 对集合进行排序
		Collections.sort(keys);

		String content = "";
		for (int i = 0; i < keys.size(); i++)
		{
			String key = keys.get(i);
			// 除去空参数
			if (StringUtils.isNotBlank(params.get(key)) && !"sign_type".equals(key) && !"sign_version".equals(key)
					&& !"sign".equals(key))
			{
				content = content + key + "=" + params.get(key).trim() + "&";
			}
		}
		if (content.length() > 0)
		{
			content = content.substring(0, content.length() - 1);
		}
		LOG.debug("【SDK签名原文】 {}", content);
		return content;
	}

	/**
	 * 获取带签名的key=value字符串
	 * 
	 * @Title: getSignContent
	 * @Description: TODO(获取签名原文)
	 * @param params
	 * @return 参数
	 * @return String 返回类型
	 */
	public static String sortParamWithSign(Map<String, String> params)
	{
		// 获取map中的key值
		List<String> keys = new ArrayList<String>(params.keySet());
		// 对集合进行排序
		Collections.sort(keys);

		String content = "";
		for (int i = 0; i < keys.size(); i++)
		{
			String key = keys.get(i);
			// 除去空参数
			if (StringUtils.isNotBlank(params.get(key)))
			{
				content = content + key + "=" + params.get(key).trim() + "&";
			}
		}
		if (content.length() > 0)
		{
			content = content.substring(0, content.length() - 1);
		}
		LOG.debug("【SDK签名原文】 {}", content);
		return content;
	}

	/**
	 * 对参数进行加签
	 * 
	 * @Title: addSign
	 * @Description: TODO(对参数进行加签)
	 * @param content
	 * @param signType
	 * @param charset
	 * @return 参数
	 * @return String 返回类型
	 */
	public static String addSign(String content, String signType, String charset)
	{
		LOG.debug("【SDK加签-签名原文】{}", content);
		try
		{
			// 加签私钥 私钥加签
			String signKey = KeyUtils.getInstance().getPrivateSignKey();
			if (StringUtils.isBlank(signKey) || StringUtils.isBlank(signKey.trim()))
			{
				throw new SDKException("加签私钥为空");
			}
			// 计算签名
			String sign = SignUtils.sign(content, signType, signKey, charset);
			return sign;
		} catch (Exception e)
		{
//			e.printStackTrace();
			LOG.error("【SDK加签失败】", e);
			throw new SDKException(e.getMessage());
		}
	}

	public static String encodeParam(Map<String, String> params, String charset)
	{
		String encodeParam = "";
		try
		{
			// 获取map中的key值
			List<String> keys = new ArrayList<String>(params.keySet());
			// 对集合进行排序
			Collections.sort(keys);

			String content = "";
			for (int i = 0; i < keys.size(); i++)
			{
				String key = keys.get(i);
				if (StringUtils.isNotBlank(params.get(key)))
				{
					encodeParam = URLEncoder.encode(URLEncoder.encode(params.get(key), charset), charset);
					content = content + key + "=" + encodeParam + "&";
				}
			}
			content = content.substring(0, content.length() - 1);
			LOG.debug("【SDK签名原文】 {}", content);
			return content;
		} catch (UnsupportedEncodingException e)
		{
//			e.printStackTrace();
			LOG.error("【SDK urlEncode失败】", e);
			throw new SDKException(e.getMessage());
		}
	}

	public static String getResult(String url, String encodeParam, String charset)
	{
		String result = "";
		try
		{
			LOG.debug("【SDK请求网关参数】 {}", encodeParam);
			result = URLDecoder.decode(CallServiceUtils.sendPost(url, encodeParam), charset);
			LOG.debug("【SDK网关响应原文】 {}", result);
			return result;
		} catch (UnsupportedEncodingException e)
		{
//			e.printStackTrace();
			LOG.error("【SDK post请求失败】", e);
			throw new SDKException(e.getMessage());
		}
	}

	/**
	 * 对新浪返回的同步响应进行验签处理
	 * 
	 * @Title: checkSign
	 * @Description: TODO(对新浪返回的同步响应进行验签处理)
	 * @param result
	 *            参数
	 * @return void 返回类型
	 */
	@SuppressWarnings("unchecked")
	public static boolean checkSign(String result)
	{
		// 将返回的json字符串转换为map类型的数据
		// Map<String, String> resultMap = GsonUtil.fronJson2Map(result);
		Map<String, String> resultMap = (Map<String, String>) JSONObject.parse(result);

		return checkSign(resultMap);
	}

	/**
	 * 验签
	 * 
	 * @Title: checkSign
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param resultMap
	 * @return 参数
	 * @return boolean 返回类型
	 */
	public static boolean checkSign(Map<String, String> resultMap)
	{
		try
		{
			// 获取验签公钥 公钥验签
			String key = KeyUtils.getInstance().getPublicSignKey();
			if (StringUtils.isBlank(key) || StringUtils.isBlank(key.trim()))
			{
				throw new SDKException("验签公钥为空");
			}
			// 获取签名字符串
			String sign = resultMap.get("sign");
			// 获取签名类型
			String signType = resultMap.get("sign_type");
			// 获取字符集
			String charset = resultMap.get("_input_charset");
			// 签名原文中不包含sign、sign_type、sign_version
			resultMap.remove("sign");
			resultMap.remove("sign_type");
			resultMap.remove("sign_version");
			String param = sortParam(resultMap);
			LOG.debug("【SDK验签签名原文】 {}", param);
			LOG.debug("【SDK sign】 {}", sign);
			if (sign == null || StringUtils.isEmpty(sign))
			{
				return false;
			}
			boolean flag = SignUtils.Check_sign(param, sign, signType, key, charset);
			return flag;
		} catch (Exception e)
		{
//			e.printStackTrace();
			LOG.error("【SDK验签异常】", e);
			throw new SDKException(e.getMessage());
		}
	}

	/**
	 * 将map转换的String字符串转换成key=value&key=value形式
	 * 
	 * @Title: stringMap2KeyValue
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param stringMap
	 * @return 参数
	 * @return String 返回类型
	 */
	public static String stringMap2KeyValue(String stringMap)
	{
		stringMap = stringMap.replace("{", "").replace("}", "");
		String[] keyValues = stringMap.split(",");
		Map<String, String> map = new HashMap<>();
		for (String keyValue : keyValues)
		{
			String[] data = keyValue.split("=");
			if (data != null && data.length == 2)
			{
				map.put(data[0].trim(), data[1].trim());
			}
		}
		return sortParamWithSign(map);
	}
	
	/**
	 * 校验网关地址是否为空  为空则抛出异常
	  * @Title: checkUrl
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param url    参数
	  * @return void    返回类型
	 */
	public static void checkUrl(String url)
	{
		if (StringUtils.isBlank(url))
		{
			throw new SDKException("网关地址为空，请检查配置文件setting.xml");
		}
	}
}
