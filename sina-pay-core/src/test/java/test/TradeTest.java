package test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;

import sdk.SinaSDK;

public class TradeTest
{
	private static final Logger LOG = LoggerFactory.getLogger(TradeTest.class);
	
	private static String requestTime;
	
	static
	{
		requestTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}
	
	@Test
	public void createB2cOrder()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("return_url", "http://localhost:8080/java_demo_zhanghu/index.html");
		map.put("service", "create_b2c_order");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		// 注意  如果数据为空list中加入空的字符串
		List<String> splitList = new ArrayList<>();
		splitList.add("20181012100705");
		splitList.add("UID");
		splitList.add("BASIC");
		splitList.add("20181019152253");
		splitList.add("UID");
		splitList.add("BASIC");
		splitList.add("5");
		splitList.add("备注");
		List<String> splitList2 = new ArrayList<>();
		splitList2.add("20181012100705");
		splitList2.add("UID");
		splitList2.add("BASIC");
		splitList2.add("20181123102807");
		splitList2.add("UID");
		splitList2.add("BASIC");
		splitList2.add("5");
		splitList2.add("");
		List<List<String>> list = new ArrayList<>();
		list.add(splitList);
		list.add(splitList2);
//		map.put("split_list", list);
		map.put("buyer_identity_type", "UID");
		map.put("seller_account_type", "BASIC");
		map.put("reported_identity_id", "");
		map.put("settlement_time", "15m");
		map.put("product_desc", "");
		Map<String, String> riskInfo = new HashMap<>();
		riskInfo.put("goods_code", "12999");
		riskInfo.put("goods_id", "1812191000000");
		riskInfo.put("goods_name", "订单支付");
		riskInfo.put("goods_num", "1");
		riskInfo.put("goods_price", "1");
		map.put("risk_info", riskInfo);
		map.put("sign_type", "RSA");
		map.put("extend_param", "");
		map.put("amount", "50");
		map.put("goods_nnum", "50");
		map.put("seller_identity_type", "UID");
		map.put("device_id", "");
		map.put("pay_method", "online_bank^50^SINAPAY,DEBIT,C");
		map.put("notify_url", "http://10.65.106.83:8080/java_demo_zhanghu/Notify");
		map.put("version", "1.2");
		map.put("payer_ip", "192.168.1.4");
		map.put("out_trade_no", UUID.randomUUID().toString().replaceAll("-", ""));
		Map<String, String> deviceInfo = new HashMap<>();
		deviceInfo.put("device_no", "3529370000000");
		deviceInfo.put("device_type", "MOBILE");
		deviceInfo.put("access_type", "APP");
		map.put("device_info", deviceInfo);
		map.put("buyer_identity_id", "20181012100627");
		map.put("reported_identity_type", "UID");
		map.put("trade_close_time", "");
//		map.put("split_code", "dianshang");
		List<String> obj = new ArrayList<>();
		List<String> obj2 = new ArrayList<>();
		List<List<String>> objList = new ArrayList<>();
		obj.add("20181019152253");
		obj.add("UID");
		obj.add("supplier");
		obj2.add("20181019153434");
		obj2.add("UID");
		obj2.add("supplier_1");
		objList.add(obj);
//		objList.add(obj2);
//		map.put("split_role_list", objList);
		map.put("seller_identity_id", "20181012100705");
		Map<String, Object> result = SinaSDK.masSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void payOrder()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "pay_order");
		map.put("device_id", "");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("memo", "");
		map.put("pay_method", "online_bank^50^SINAPAY,DEBIT,C");
		map.put("notify_url", "http://10.65.106.83:8080/java_demo_zhanghu/Notify");
		map.put("out_pay_no", "paytrade20190409111242");
		map.put("version", "1.2");
		map.put("payer_ip", "192.168.1.4");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("out_trade_no", "74d2983055bb48e1b7c5a6e3c7394b65");
		map.put("return_url", "http://localhost:8080/java_demo_zhanghu/index.html");
		map.put("sign_type", "RSA");
		Map<String, Object> result = SinaSDK.masSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void advanceHostingPay()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "advance_hosting_pay");
		map.put("user_ip", "101.231.34.38");
		map.put("ticket", "324e3bfecb3b4ffb94c9000bf73021ba");
		map.put("validate_code", "025689");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("memo", "");
		map.put("notify_url", "http://10.65.106.83:8080/java_demo_zhanghu/Notify");
		map.put("version", "1.2");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("out_advance_no", "advance20190409111330");
		map.put("return_url", "http://localhost:8080/java_demo_zhanghu/index.html");
		map.put("sign_type", "RSA");
		map.put("extend_param", "");
		Map<String, Object> result = SinaSDK.masSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void settleB2cOrder()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "settle_b2c_order");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("memo", "");
		map.put("notify_url", "http://10.65.106.83:8080/java_demo_zhanghu/Notify");
		map.put("version", "1.2");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("out_trade_no", "6ed38ef477da490eaaacd191e6d4b4ca");
		Map<String, String> deviceInfo = new HashMap<>();
		deviceInfo.put("device_no", "3529370000000");
		deviceInfo.put("device_type", "MOBILE");
		deviceInfo.put("access_type", "APP");
		map.put("device_info", deviceInfo);
		map.put("return_url", "http://localhost:8080/java_demo_zhanghu/index.html");
		Map<String, String> riskInfo = new HashMap<>();
		riskInfo.put("goods_code", "12999");
		riskInfo.put("goods_id", "1812191000000");
		riskInfo.put("goods_name", "订单支付");
		riskInfo.put("goods_num", "1");
		riskInfo.put("goods_price", "1");
		map.put("risk_info", riskInfo);
		map.put("sign_type", "RSA");
		map.put("out_request_no", UUID.randomUUID().toString().replaceAll("-", ""));
		map.put("extend_param", "");
		Map<String, Object> result = SinaSDK.masSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void closeB2cOrder()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "close_b2c_order");
		map.put("summary", "交易关闭");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("memo", "");
		map.put("notify_url", "http://10.65.106.83:8080/java_demo_zhanghu/Notify");
		map.put("version", "1.2");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("out_trade_no", "collecttrade20190409110849");
		map.put("return_url", "http://localhost:8080/java_demo_zhanghu/index.html");
		map.put("sign_type", "RSA");
		map.put("out_request_no", "request20190409111746");
		map.put("extend_param", "");
		Map<String, Object> result = SinaSDK.masSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void cancelB2cOrder()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "cancel_b2c_order");
		map.put("summary", "交易撤销");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("memo", "");
		map.put("notify_url", "http://10.65.106.83:8080/java_demo_zhanghu/Notify");
		map.put("version", "1.2");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("out_trade_no", "6ed38ef477da490eaaacd191e6d4b4ca");
		map.put("return_url", "http://localhost:8080/java_demo_zhanghu/index.html");
		map.put("sign_type", "RSA");
		map.put("out_request_no", "request_20190409111806");
		map.put("extend_param", "");
		Map<String, Object> result = SinaSDK.masSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void extendSettleTime()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "extend_settle_time");
		map.put("summary", "延长交易结算时间");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("memo", "");
		map.put("notify_url", "http://10.65.106.83:8080/java_demo_zhanghu/Notify");
		map.put("version", "1.2");
		map.put("extend_time", "15d");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("out_trade_no", "6e88539f55f5403c8034b92c0bb21f20");
		map.put("return_url", "http://localhost:8080/java_demo_zhanghu/index.html");
		map.put("sign_type", "RSA");
		map.put("out_request_no", "request_20190409111838");
		map.put("extend_param", "");
		Map<String, Object> result = SinaSDK.masSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void queryHostingTrade()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "query_hosting_trade");
		map.put("identity_id", "20190409104630");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("end_time", "");
		map.put("memo", "");
		map.put("page_no", "");
		map.put("notify_url", "http://10.65.106.83:8080/java_demo_zhanghu/Notify");
		map.put("version", "1.2");
		map.put("start_time", "");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("out_trade_no", "8e7ed3821d3d4711a051b2e8dcfe0708");
		map.put("return_url", "http://localhost:8080/java_demo_zhanghu/index.html");
		map.put("identity_type", "UID");
		map.put("sign_type", "RSA");
		map.put("extend_param", "");
		map.put("page_size", "");
		Map<String, Object> result = SinaSDK.masSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void createHostingRefund()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "create_hosting_refund");
		map.put("summary", "交易退款");
		map.put("user_ip", "101.231.34.38");
		map.put("refund_split_strategy", "");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("memo", "");
		map.put("notify_url", "http://10.65.106.83:8080/java_demo_zhanghu/Notify");
		map.put("version", "1.2");
		List<String> splitList = new ArrayList<>();
		splitList.add("20181012100705");
		splitList.add("UID");
		splitList.add("BASIC");
		splitList.add("20181019152253");
		splitList.add("UID");
		splitList.add("BASIC");
		splitList.add("5");
		splitList.add("备注");
		List<String> splitList2 = new ArrayList<>();
		splitList2.add("20181012100705");
		splitList2.add("UID");
		splitList2.add("BASIC");
		splitList2.add("20181123102807");
		splitList2.add("UID");
		splitList2.add("BASIC");
		splitList2.add("5");
		splitList2.add("");
		List<List<String>> list = new ArrayList<>();
		list.add(splitList);
		list.add(splitList2);
		map.put("refund_split_list", list);
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("out_trade_no", UUID.randomUUID().toString().replaceAll("-", ""));
		map.put("return_url", "http://localhost:8080/java_demo_zhanghu/index.html");
		map.put("refund_amount", "2");
		map.put("orig_outer_trade_no", "8e7ed3821d3d4711a051b2e8dcfe0708");
		map.put("sign_type", "RSA");
		map.put("extend_param", "");
		Map<String, Object> result = SinaSDK.masSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void queryHostingRefund()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "query_hosting_refund");
		map.put("identity_id", "20181012100627");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("end_time", "");
		map.put("memo", "");
		map.put("page_no", "");
		map.put("notify_url", "http://10.65.106.83:8080/java_demo_zhanghu/Notify");
		map.put("version", "1.2");
		map.put("start_time", "");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("out_trade_no", "1ae798fe4bb24a279a0cbcbed4868480");
		map.put("return_url", "http://localhost:8080/java_demo_zhanghu/index.html");
		map.put("identity_type", "UID");
		map.put("sign_type", "RSA");
		map.put("extend_param", "");
		map.put("page_size", "");
		Map<String, Object> result = SinaSDK.masSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void createBatchPay2bank()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "create_batch_pay2bank");
		map.put("account_type", "");
		map.put("batch_no", UUID.randomUUID().toString().replaceAll("-", ""));
		map.put("_input_charset", "utf-8");
		List<List<String>> objList = new ArrayList<>();
		List<String> obj = new ArrayList<>();
		obj.add(UUID.randomUUID().toString().replaceAll("-", ""));
		obj.add("西门吹雪");
		obj.add("");
		obj.add("6222404000032961530");
		obj.add("中国工商银行");
		obj.add("ICBC");
		obj.add("浙江省");
		obj.add("杭州市");
		obj.add("中国农业银行深圳南山支行");
		obj.add("10.00");
		obj.add("B");
		obj.add("DEBIT");
		obj.add("");
		List<String> obj2 = new ArrayList<>();
		obj2.add(UUID.randomUUID().toString().replaceAll("-", ""));
		obj2.add("西门吹雪");
		obj2.add("");
		obj2.add("6222404000032961531");
		obj2.add("中国工商银行");
		obj2.add("ICBC");
		obj2.add("浙江省");
		obj2.add("宁波市");
		obj2.add("中国农业银行宁波支行");
		obj2.add("20.00");
		obj2.add("B");
		obj2.add("DEBIT");
		obj2.add("");
		objList.add(obj);
		objList.add(obj2);
		map.put("detail_list", objList);
		map.put("cashdesk_addr_category", "");
		map.put("memo", "");
		map.put("notify_url", "http://10.65.106.83:8080/java_demo_zhanghu/Notify");
		map.put("version", "1.2");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("return_url", "http://localhost:8080/java_demo_zhanghu/index.html");
		map.put("sign_type", "RSA");
		map.put("payto_type", "");
		map.put("extend_param", "");
		Map<String, Object> result = SinaSDK.masSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void queryB2cBatchFundoutOrder()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "query_b2c_batch_fundout_order");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("return_url", "http://localhost:8080/java_demo_zhanghu/index.html");
		map.put("memo", "");
		map.put("out_batch_no", "d62fe726de1c446a94d947f2dbdb78ed");
		map.put("notify_url", "http://10.65.106.83:8080/java_demo_zhanghu/Notify");
		map.put("version", "1.2");
		map.put("sign_type", "RSA");
		Map<String, Object> result = SinaSDK.masSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	
	
	@Test
	public void receiptSplit()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "receipt_split");
		map.put("split_type", "");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("memo", "");
		map.put("notify_url", "http://10.65.106.83:8080/java_demo_zhanghu/Notify");
		map.put("version", "1.2");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		List<String> obj = new ArrayList<>();
		obj.add("20181012100705");
		obj.add("UID");
		obj.add("BASIC");
		obj.add("20181019152253");
		obj.add("UID");
		obj.add("BASIC");
		obj.add("2");
		obj.add("备注");
		obj.add(UUID.randomUUID().toString().replaceAll("-", ""));
		List<String> obj2 = new ArrayList<>();
		obj2.add("20181012100705");
		obj2.add("UID");
		obj2.add("BASIC");
		obj2.add("20181123102807");
		obj2.add("UID");
		obj2.add("BASIC");
		obj2.add("1");
		obj2.add("");
		obj2.add(UUID.randomUUID().toString().replaceAll("-", ""));
		List<List<String>> objList = new ArrayList<>();
		objList.add(obj);
		objList.add(obj2);
		map.put("split_list", objList);
		map.put("return_url", "http://localhost:8080/java_demo_zhanghu/index.html");
		map.put("sign_type", "RSA");
		map.put("orig_out_trade_no", "b4affebc9b7b49e783e67e868bac4a8f");
		Map<String, Object> result = SinaSDK.masSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void queryReceiptSplit()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "query_receipt_split");
		map.put("identity_id", "20181012100705");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("notify_url", "http://10.65.106.83:8080/java_demo_zhanghu/Notify");
		map.put("version", "1.2");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("return_url", "http://localhost:8080/java_demo_zhanghu/index.html");
		map.put("identity_type", "UID");
		map.put("sign_type", "RSA");
		map.put("out_split_no", "a07d5d98ca0a4fdf993c1bfb91b15e71");
		map.put("start_time", "");
		map.put("end_time", "");
		map.put("page_size", "");
		map.put("page_no", "");
		Map<String, Object> result = SinaSDK.masSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	
	@Test
	public void queryReceiptAgreementSplit()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "query_receipt_agreement_split");
		map.put("identity_id", "20181012100705");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("notify_url", "http://10.65.106.83:8080/java_demo_zhanghu/Notify");
		map.put("version", "1.2");
		map.put("start_time", "");
		map.put("end_time", "");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("out_trade_no", "a07d5d98ca0a4fdf993c1bfb91b15e71");
		map.put("return_url", "http://localhost:8080/java_demo_zhanghu/index.html");
		map.put("identity_type", "UID");
		map.put("sign_type", "RSA");
		map.put("page_size", "");
		map.put("page_no", "");
		Map<String, Object> result = SinaSDK.masSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void createBatchPay2account()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "create_batch_pay2account");
		map.put("account_type", "");
		map.put("batch_no", UUID.randomUUID().toString().replaceAll("-", ""));
		map.put("_input_charset", "utf-8");
		List<String> obj = new ArrayList<>();
		List<List<String>> objList = new ArrayList<>();
		obj.add(UUID.randomUUID().toString().replaceAll("-", ""));
		obj.add("20181012100705");
		obj.add("UID");
		obj.add("BASIC");
		obj.add("10.00");
		obj.add("");
		obj.add("");
		List<String> obj2 = new ArrayList<>();
		obj2.add(UUID.randomUUID().toString().replaceAll("-", ""));
		obj2.add("20181012100705");
		obj2.add("UID");
		obj2.add("BASIC");
		obj2.add("10.00");
		obj2.add("");
		obj2.add("a^b|c^d");
		objList.add(obj);
		objList.add(obj2);
		map.put("detail_list", objList);
		map.put("cashdesk_addr_category", "");
		map.put("memo", "");
		map.put("notify_url", "http://10.65.106.83:8080/java_demo_zhanghu/Notify");
		map.put("version", "1.2");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("return_url", "http://localhost:8080/java_demo_zhanghu/index.html");
		map.put("sign_type", "RSA");
		map.put("extend_param", "");
		Map<String, Object> result = SinaSDK.masSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void queryHostingBatchTrade()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "query_hosting_batch_trade");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("memo", "");
		map.put("out_batch_no", "80638610c6ce4bbbb1af859fbb7fdef6");
		map.put("page_no", "");
		map.put("notify_url", "http://10.65.106.83:8080/java_demo_zhanghu/Notify");
		map.put("version", "1.2");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("return_url", "http://localhost:8080/java_demo_zhanghu/index.html");
		map.put("sign_type", "RSA");
		map.put("page_size", "");
		Map<String, Object> result = SinaSDK.masSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void createHostingDeposit()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "create_hosting_deposit");
		map.put("summary", "某某账户充值");
		map.put("account_type", "SAVING_POT");
		map.put("amount", "10");
		map.put("device_id", "");
		map.put("identity_id", "20190411175426");
		map.put("deposit_close_time", "18m");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("memo", "");
		map.put("pay_method", "online_bank^10^SINAPAY,DEBIT,C");
		map.put("notify_url", "http://10.65.106.62:6070/sinaPayJavaDemo/Notify");
		map.put("version", "1.2");
		map.put("payer_ip", "114.218.183.139");
		map.put("user_fee", "");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("out_trade_no", "order20190412100028");
		map.put("return_url", "http://localhost:6070/sinaPayJavaDemo/index.html");
		map.put("identity_type", "UID");
		map.put("sign_type", "RSA");
		map.put("extend_param", "");
		Map<String, Object> result = SinaSDK.masSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void queryHostingDeposit()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "query_hosting_deposit");
		map.put("account_type", "SAVING_POT");
		map.put("identity_id", "20190411175426");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("end_time", "20190412100222");
		map.put("memo", "");
		map.put("page_no", "");
		map.put("notify_url", "http://10.65.106.62:6070/sinaPayJavaDemo/Notify");
		map.put("version", "1.2");
		map.put("start_time", "20190411100222");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("out_trade_no", "");
		map.put("return_url", "http://localhost:6070/sinaPayJavaDemo/index.html");
		map.put("identity_type", "UID");
		map.put("sign_type", "RSA");
		map.put("page_size", "");
		Map<String, Object> result = SinaSDK.masSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void createHostingWithdraw()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "create_hosting_withdraw");
		map.put("summary", "余额提现");
		map.put("withdraw_mode", "CASHDESK");
		map.put("user_ip", "114.218.183.139");
		map.put("account_type", "SAVING_POT");
		map.put("amount", "5");
		map.put("identity_id", "20190411175426");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("memo", "");
		map.put("notify_url", "http://10.65.106.62:6070/sinaPayJavaDemo/Notify");
		map.put("version", "1.2");
		map.put("card_id", "");
		map.put("user_fee", "");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("out_trade_no", "withdraw20190412100308");
		map.put("return_url", "http://localhost:6070/sinaPayJavaDemo/index.html");
		map.put("identity_type", "UID");
		map.put("sign_type", "RSA");
		map.put("withdraw_close_time", "10m");
		map.put("payto_type", "FAST");
		map.put("extend_param", "customNotify^Y");
		Map<String, Object> result = SinaSDK.masSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void queryHostingWithdraw()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "query_hosting_withdraw");
		map.put("account_type", "SAVING_POT");
		map.put("identity_id", "20190411175426");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("end_time", "20190412100402");
		map.put("memo", "");
		map.put("page_no", "");
		map.put("notify_url", "http://10.65.106.62:6070/sinaPayJavaDemo/Notify");
		map.put("version", "1.2");
		map.put("start_time", "20190411100402");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("out_trade_no", "");
		map.put("return_url", "http://localhost:6070/sinaPayJavaDemo/index.html");
		map.put("identity_type", "UID");
		map.put("sign_type", "RSA");
		map.put("page_size", "");
		Map<String, Object> result = SinaSDK.mgsSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	
	@Test
	public void queryFundYield()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "query_fund_yield");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("return_url", "http://localhost:8080/java_demo_zhanghu/index.html");
		map.put("memo", "");
		map.put("notify_url", "http://10.65.106.83:8080/java_demo_zhanghu/Notify");
		map.put("version", "1.2");
		map.put("sign_type", "RSA");
		map.put("fund_code", "000330");
		Map<String, Object> result = SinaSDK.masSDK(map);
		LOG.debug("【result】 = {}", result);
	}

}
