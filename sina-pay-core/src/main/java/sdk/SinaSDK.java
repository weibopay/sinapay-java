package sdk;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sina.sinapaycore.java.exception.SDKException;
import com.sina.sinapaycore.java.utils.SendUtils;
import com.sina.sinapaycore.java.utils.XmlUtils;

/**
 * 新浪sdk统一入口
  * @ClassName: SinaSDK
  * @Description: TODO(这里用一句话描述这个类的作用)
  * @author user
  * @date 2019年4月8日
  *
 */
public class SinaSDK
{
	private static final Logger LOG = LoggerFactory.getLogger(SinaSDK.class);
	
	/**
	 * 将接口参数发送新浪
	  * @Title: request
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param req
	  * @return    参数
	  * @return String    返回类型
	 */
	public static Map<String, Object> masSDK(Map<String, Object> params)
	{
		String url;
		String requestTime = (String) params.get("request_time");
		Map<String, Object> map = new HashMap<>();
		try
		{
			LOG.debug("【SDK商户原始请求[" + requestTime + "]】 = {}", params);
			url = XmlUtils.getInstance().getMas();
			SendUtils.checkUrl(url);
			return SendUtils.dealRequest(params, url);
		} catch (SDKException e) {
			LOG.error("【SDK异常[" + requestTime + "]】", e);
			map.put("SDK_EXCEPTION", e.getMessage());
			return map;
		} catch (Exception e)
		{
			LOG.error("【SDK异常[" + requestTime + "]】", e);
			map.put("SDK_EXCEPTION", e.getMessage());
			return map;
		}
		
	}
	
	/**
	 * 将接口参数发送新浪
	  * @Title: request
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param req
	  * @return    参数
	  * @return String    返回类型
	 */
	public static Map<String, Object> mgsSDK(Map<String, Object> params)
	{
		String url;
		String requestTime = (String) params.get("request_time");
		Map<String, Object> map = new HashMap<>();
		try
		{
			LOG.debug("【SDK商户原始请求[" + requestTime + "]】 = {}", params);
			url = XmlUtils.getInstance().getMgs();
			SendUtils.checkUrl(url);
			return SendUtils.dealRequest(params, url);
		} catch (SDKException e) {
			LOG.error("【SDK异常[" + requestTime + "]】", e);
			map.put("SDK_EXCEPTION", e.getMessage());
			return map;
		} catch (Exception e)
		{
			LOG.error("【SDK异常[" + requestTime + "]】", e);
			map.put("SDK_EXCEPTION", e.getMessage());
			return map;
		}
	}
	
	/**
	 * 异步通知验签
	  * @Title: notifySDK
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param params
	  * @return    参数
	  * @return boolean    返回类型
	 */
	public static boolean notifySDK(Map<String, String> params)
	{
		LOG.debug("【SDK异步通知请求参数】 = {}", params);
		try
		{
			boolean flag = SendUtils.checkSign(params);
			return flag;
		} catch (SDKException e) {
			LOG.error("【SDK验签异常】", e);
			return false;
		} catch (Exception e)
		{
			LOG.error("【SDK验签异常】", e);
			return false;
		}
	}
}
