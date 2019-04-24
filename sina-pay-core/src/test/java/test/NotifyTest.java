package test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import sdk.SinaSDK;

public class NotifyTest
{

	@Test
	public void notifyTest()
	{
		Map<String, String> map = new HashMap<>();
		map.put("gmt_create", "20190410173733");
		map.put("gmt_payment", "20190410173749");
		map.put("notify_time", "20190410173750");
		map.put("fee", "0.00");
		map.put("_input_charset", "utf-8");
		map.put("sign", "7fKaZ2xmnn9tJgo4mkIiofHnS/+Lreg38yUPDvODYw8BhWZN8SCvMX4ha40KfAMNafzS/Lp+8iKpJ3EBefIlJdporAxT4trvXEj74EKwS6ksnGdv7vCX8s6wV6kthnXnkEuxtVL/YufHg1Tl/a6z8PbAy/9WFAByZWzKPvLm2ho=");
		map.put("notify_id", "201904100131219561");
		map.put("version", "1.0");
		map.put("notify_type", "b2c_trade_status_sync");
		map.put("out_trade_no", "5cadb95b80126c2d082bcd7c");
		map.put("trade_amount", "500.00");
		map.put("inner_trade_no", "111554889053698354003");
		map.put("trade_status", "TRADE_FINISHED");
		map.put("sign_type", "RSA");
		boolean result = SinaSDK.notifySDK(map);
		System.out.println(result);
		assertEquals(true, result);
	}
}
