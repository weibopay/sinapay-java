package test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sdk.SinaSDK;

public class MemberTest
{
	private static final Logger LOG = LoggerFactory.getLogger(MemberTest.class);
	
	private static String uid;
	
	private static String requestTime;
	
	static
	{
		uid = String.valueOf(System.currentTimeMillis());
		requestTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}

	@Test
	public void createMember()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "create_activate_member");
		map.put("version", "1.2");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("_input_charset", "utf-8");
		map.put("sign", "");
		map.put("sign_type", "RSA");
		map.put("sign_version", "1.0");
		map.put("encrypt_version", "1.0");
		map.put("notify_url", "");
		map.put("return_url", "");
		map.put("memo", "创建会员");
		map.put("risk_info", "");
		map.put("device_info", "");
		map.put("identity_id", "bilbd0g5r9393fcu8nv0");
		map.put("identity_type", "UID");
		map.put("member_type", "1");
		map.put("client_ip", "127.0.0.1");
		map.put("extend_param", "");
		Map<String, Object> result = SinaSDK.mgsSDK(map);
		LOG.debug("【result】 = {}", result);
	}

	@Test
	public void setRealName()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "set_real_name");
		map.put("version", "1.2");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("_input_charset", "utf-8");
//		map.put("sign", "");
		map.put("sign_type", "RSA");
//		map.put("sign_version", "1.0");
//		map.put("encrypt_version", "1.0");
//		map.put("notify_url", "");
//		map.put("return_url", "");
//		map.put("memo", "设置实名信息");
//		map.put("risk_info", "");
//		map.put("device_info", "");
		map.put("identity_id", "bilbd0g5r9393fcu8nv0");
		map.put("identity_type", "UID");
		map.put("real_name", "李勇");
		map.put("cert_type", "IC");
		map.put("cert_no", "610111197109100110");
//		map.put("need_confirm", "Y");
		map.put("client_ip", "127.0.0.1");
//		map.put("extend_param", "UID");
		Map<String, Object> result = SinaSDK.mgsSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	
	
	@Test
	public void setPayPassword()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "set_pay_password");
		map.put("identity_id", "1555051737809");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("memo", "");
		map.put("notify_url", "");
		map.put("version", "1.2");
		map.put("withhold_param", "withhold_auth_type^ALL,ACCOUNT|is_check^Y");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("return_url", "http://bk.xxxxx.com/demo/zjtg_php_demo/view/pay_password.html");
		map.put("identity_type", "UID");
		map.put("sign_type", "RSA");
		Map<String, Object> result = SinaSDK.mgsSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void modifyPayPassword()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "modify_pay_password");
		map.put("identity_id", "1555051737809");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("memo", "");
		map.put("notify_url", "");
		map.put("version", "1.2");
		map.put("withhold_param", "");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("return_url", "http://bk.xxxxx.com/demo/zjtg_php_demo/view/pay_password.html");
		map.put("identity_type", "UID");
		map.put("sign_type", "RSA");
		Map<String, Object> result = SinaSDK.mgsSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void findPayPassword()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "find_pay_password");
		map.put("identity_id", "1555051737809");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("memo", "");
		map.put("notify_url", "");
		map.put("version", "1.2");
		map.put("withhold_param", "");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("return_url", "http://bk.xxxxx.com/demo/zjtg_php_demo/view/pay_password.html");
		map.put("identity_type", "UID");
		map.put("sign_type", "RSA");
		Map<String, Object> result = SinaSDK.mgsSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void queryIsSetPayPassword()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "query_is_set_pay_password");
		map.put("identity_id", "1555051737809");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("memo", "");
		map.put("notify_url", "");
		map.put("version", "1.2");
		map.put("withhold_param", "");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("return_url", "http://bk.xxxxx.com/demo/zjtg_php_demo/view/pay_password.html");
		map.put("identity_type", "UID");
		map.put("sign_type", "RSA");
		Map<String, Object> result = SinaSDK.mgsSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void bindingBankCard()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "binding_bank_card");
		map.put("version", "1.2");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("_input_charset", "utf-8");
		map.put("sign", "");
		map.put("sign_type", "RSA");
		map.put("sign_version", "1.0");
		map.put("encrypt_version", "1.0");
		map.put("notify_url", "");
		map.put("return_url", "");
		map.put("memo", "绑定银行卡");
		map.put("risk_info", "");
		map.put("device_info", "");
		map.put("identity_id", "1555051737809");
		map.put("identity_type", "UID");
		map.put("request_no", UUID.randomUUID().toString().replaceAll("-", ""));
		map.put("bank_code", "ICBC");
		map.put("bank_account_no", "6212264100011335373");
		map.put("account_name", "");
		map.put("card_type", "DEBIT");
		map.put("card_attribute", "C");
		map.put("cert_type", "IC");
		map.put("cert_no", "");
		map.put("phone_no", "18796261167");
		map.put("validity_period", "");
		map.put("verification_value", "");
		map.put("province", "上海市");
		map.put("city", "上海市");
		map.put("bank_branch", "");
		map.put("verify_mode", "SIGN");
		map.put("client_ip", "127.0.0.1");
		map.put("extend_param", "");
		Map<String, Object> result = SinaSDK.mgsSDK(map);
		LOG.debug("【result】 = {}", result.toString());
	}
	@Test
	public void bindingBankCardAdvance()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "binding_bank_card_advance");
		map.put("ticket", "0413af017b3143c597951d1cddcd5ded");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("memo", "");
		map.put("notify_url", "");
		map.put("version", "1.2");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("valid_code", "629545");
		map.put("return_url", "http://bk.xxxxx.com/demo/zjtg_php_demo/view/binding_bank_card_advance.html");
		map.put("client_ip", "101.231.34.38");
		map.put("sign_type", "RSA");
		Map<String, Object> result = SinaSDK.mgsSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void queryBankCard()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "query_bank_card");
		map.put("identity_id", "1555051737809");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("memo", "");
		map.put("notify_url", "");
		map.put("version", "1.2");
		map.put("card_id", "");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("return_url", "http://bk.xxxxx.com/demo/zjtg_php_demo/view/query_bank_card.html");
		map.put("identity_type", "UID");
		map.put("sign_type", "RSA");
		Map<String, Object> result = SinaSDK.mgsSDK(map);
		LOG.debug("【result】 = {}", result.toString());
	}
	
	@Test
	public void unbindingBankCard()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "unbinding_bank_card");
		map.put("identity_id", "1555051737809");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("memo", "");
		map.put("notify_url", "");
		map.put("version", "1.2");
		map.put("card_id", "398603");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("advance_flag", "Y");
		map.put("return_url", "http://bk.xxxxx.com/demo/zjtg_php_demo/view/unbinding_bank_card.html");
		map.put("client_ip", "101.231.34.38");
		map.put("identity_type", "UID");
		map.put("sign_type", "RSA");
		Map<String, Object> result = SinaSDK.mgsSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void unbindingBankCardAdvance()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "unbinding_bank_card_advance");
		map.put("ticket", "5678a662444c44969d282abbf7045b52");
		map.put("identity_id", "1555051737809");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("memo", "");
		map.put("notify_url", "");
		map.put("version", "1.2");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("valid_code", "889534");
		map.put("return_url", "http://bk.xxxxx.com/demo/zjtg_php_demo/view/unbinding_bank_card_advance.html");
		map.put("client_ip", "101.231.34.38");
		map.put("identity_type", "UID");
		map.put("sign_type", "RSA");
		Map<String, Object> result = SinaSDK.mgsSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	
	@Test
	public void queryBalance()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "query_balance");
		map.put("account_type", "SAVING_POT");
		map.put("identity_id", "1555051737809");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("memo", "");
		map.put("notify_url", "");
		map.put("version", "1.2");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("return_url", "http://bk.xxxxx.com/demo/zjtg_php_demo/view/query_balance.html");
		map.put("identity_type", "UID");
		map.put("sign_type", "RSA");
		Map<String, Object> result = SinaSDK.mgsSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void queryAccountDetails()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "query_account_details");
		map.put("account_type", "SAVING_POT");
		map.put("identity_id", "1555051737809");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("end_time", "20190412151846");
		map.put("memo", "");
		map.put("page_no", "1");
		map.put("notify_url", "");
		map.put("version", "1.2");
		map.put("start_time", "20190411151846");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("return_url", "http://bk.xxxxx.com/demo/zjtg_php_demo/view/query_account_details.html");
		map.put("identity_type", "UID");
		map.put("sign_type", "RSA");
		map.put("extend_param", "");
		map.put("page_size", "5");
		Map<String, Object> result = SinaSDK.mgsSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void balanceFreeze()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "balance_freeze");
		map.put("summary", "非法占用");
		map.put("account_type", "SAVING_POT");
		map.put("amount", "2");
		map.put("identity_id", "1555051737809");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("memo", "");
		map.put("notify_url", "");
		map.put("version", "1.2");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("return_url", "http://bk.xxxxx.com/demo/zjtg_php_demo/view/balance_freeze.html");
		map.put("out_freeze_no", "freeze20190409103634");
		map.put("client_ip", "101.231.34.38");
		map.put("identity_type", "UID");
		map.put("sign_type", "RSA");
		Map<String, Object> result = SinaSDK.mgsSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void balanceUnfreeze()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "balance_unfreeze");
		map.put("out_unfreeze_no", "unfreeze20190409103817");
		map.put("summary", "事件已处理");
		map.put("amount", "2");
		map.put("identity_id", "1555051737809");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("memo", "");
		map.put("notify_url", "");
		map.put("version", "1.2");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("return_url", "http://bk.xxxxx.com/demo/zjtg_php_demo/view/balance_unfreeze.html");
		map.put("out_freeze_no", "freeze20190409103634");
		map.put("client_ip", "101.231.34.38");
		map.put("identity_type", "UID");
		map.put("sign_type", "RSA");
		Map<String, Object> result = SinaSDK.mgsSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void queryCtrlResult()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "query_ctrl_result");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("out_ctrl_no", "freeze20190409103634");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("return_url", "http://bk.xxxxx.com/demo/zjtg_php_demo/view/query_ctrl_result.html");
		map.put("memo", "");
		map.put("notify_url", "");
		map.put("version", "1.2");
		map.put("sign_type", "RSA");
		Map<String, Object> result = SinaSDK.mgsSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void queryMemberInfos()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "query_member_infos");
		map.put("identity_id", "20190411175426");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("memo", "");
		map.put("notify_url", "");
		map.put("version", "1.2");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("is_mask", "N");
		map.put("return_url", "http://bk.xxxxx.com/demo/zjtg_php_demo/view/query_member_infos.html");
		map.put("identity_type", "UID");
		map.put("sign_type", "RSA");
		Map<String, Object> result = SinaSDK.mgsSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	
	@Test
	public void auditMemberInfos()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "audit_member_infos");
		map.put("member_type", "2");
		map.put("bank_branch", "中国工商银行深圳南山支行");
		map.put("cert_type", "IC");
		map.put("fileName", "20190409104807.zip");
		map.put("license_address", "上海");
		map.put("city", "上海市");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("cert_effect_date", "20151002");
		map.put("cert_invalid_date", "forever");
		map.put("memo", "");
		map.put("card_attribute", "B");
		map.put("legal_person_phone", "18956236585");
		map.put("license_no", "20000000000002");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("province", "上海市");
		map.put("digest", "29880fa0c06ac9df31059d8e84d252a4");
		map.put("return_url", "http://localhost:8080/java_demo_zhanghu/index.html");
		map.put("client_ip", "101.231.34.38");
		map.put("identity_type", "UID");
		map.put("sign_type", "RSA");
		map.put("organization_no", "200010000009");
		map.put("extend_param", "");
		map.put("email", "ms@weibopay.com");
		map.put("summary", "企业简介");
		map.put("bank_code", "ICBC");
		map.put("website", "sssss");
		map.put("legal_person", "李某某");
		map.put("address", "测试公司的企业地址");
		map.put("cert_no", "140428200105314950");
		map.put("identity_id", "20190412152732");
		map.put("digestType", "MD5");
		map.put("bank_account_no", "6212264100011335373");
		map.put("license_expire_date", "20201002");
		map.put("telephone", "4009218887");
		map.put("notify_url", "http://10.65.106.83:8080/java_demo_zhanghu/Notify");
		map.put("card_type", "DEBIT");
		map.put("version", "1.2");
		map.put("audit_order_no", "audit_member_20190409104642");
		map.put("business_scope", "软件开发，软件测试，金融");
		map.put("company_name", "测试公司的全称");
		Map<String, Object> result = SinaSDK.mgsSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void queryAuditResult()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "query_audit_result");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("identity_id", "20190411175426");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("return_url", "http://bk.xxxxx.com/demo/zjtg_php_demo/view/query_audit_result.html");
		map.put("memo", "");
		map.put("notify_url", "");
		map.put("identity_type", "UID");
		map.put("version", "1.2");
		map.put("sign_type", "RSA");
		Map<String, Object> result = SinaSDK.mgsSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void changeBankMobile()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "change_bank_mobile");
		map.put("phone_no", "18796265852");
		map.put("identity_id", "1555051737809");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("request_no", "bil_bancart20190409104856");
		map.put("memo", "");
		map.put("notify_url", "http://10.65.106.83:8080/java_demo_zhanghu/Notify");
		map.put("version", "1.2");
		map.put("card_id", "152369");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("return_url", "http://localhost:8080/java_demo_zhanghu/index.html");
		map.put("identity_type", "UID");
		map.put("sign_type", "RSA");
		Map<String, Object> result = SinaSDK.mgsSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void changeBankMobileAdvance()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "change_bank_mobile_advance");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("valid_code", "026302");
		map.put("ticket", "e1b0c41da7954fac8aea8b9ab912b091");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("return_url", "http://localhost:8080/java_demo_zhanghu/index.html");
		map.put("memo", "");
		map.put("notify_url", "http://10.65.106.83:8080/java_demo_zhanghu/Notify");
		map.put("version", "1.2");
		map.put("sign_type", "RSA");
		Map<String, Object> result = SinaSDK.mgsSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void smtFundAgentBuy()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "smt_fund_agent_buy");
		map.put("agent_name", "夏黎珊");
		map.put("identity_id", "20190411175426");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("memo", "");
		map.put("agent_mobile", "15695263254");
		map.put("notify_url", "http://10.65.106.83:8080/java_demo_zhanghu/Notify");
		map.put("version", "1.2");
		map.put("license_type_code", "ID");
		map.put("license_no", "330100199910163443");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("return_url", "http://localhost:8080/java_demo_zhanghu/index.html");
		map.put("client_ip", "101.231.34.38");
		map.put("identity_type", "UID");
		map.put("sign_type", "RSA");
		map.put("email", "");
		Map<String, Object> result = SinaSDK.mgsSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void queryFundAgentBuy()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "query_fund_agent_buy");
		map.put("identity_id", uid);
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("memo", "");
		map.put("notify_url", "http://10.65.106.83:8080/java_demo_zhanghu/Notify");
		map.put("version", "1.2");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("return_url", "http://localhost:8080/java_demo_zhanghu/index.html");
		map.put("identity_type", "UID");
		map.put("sign_type", "RSA");
		map.put("extend_param", "");
		Map<String, Object> result = SinaSDK.mgsSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void modifyVerifyMobile()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "modify_verify_mobile");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("identity_id", "20190409104630");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("return_url", "http://localhost:8080/java_demo_zhanghu/index.html");
		map.put("memo", "");
		map.put("notify_url", "http://10.65.106.83:8080/java_demo_zhanghu/Notify");
		map.put("identity_type", "UID");
		map.put("version", "1.2");
		map.put("sign_type", "RSA");
		Map<String, Object> result = SinaSDK.mgsSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void findVerifyMobile()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "find_verify_mobile");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("identity_id", "20190409104630");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("return_url", "http://localhost:8080/java_demo_zhanghu/index.html");
		map.put("memo", "");
		map.put("notify_url", "http://10.65.106.83:8080/java_demo_zhanghu/Notify");
		map.put("identity_type", "UID");
		map.put("version", "1.2");
		map.put("sign_type", "RSA");
		Map<String, Object> result = SinaSDK.mgsSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void handleWithholdAuthority()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "handle_withhold_authority");
		map.put("identity_id", "20190409104630");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("memo", "");
		map.put("auth_type_whitelist", "ALL,ACCOUNT");
		map.put("notify_url", "http://10.65.106.83:8080/java_demo_zhanghu/Notify");
		map.put("version", "1.2");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("quota", "2000");
		map.put("return_url", "http://localhost:8080/java_demo_zhanghu/index.html");
		map.put("identity_type", "UID");
		map.put("sign_type", "RSA");
		map.put("extend_param", "");
		map.put("day_quota", "4000");
		Map<String, Object> result = SinaSDK.mgsSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void modifyWithholdAuthority()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "modify_withhold_authority");
		map.put("identity_id", "20190409104630");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("memo", "");
		map.put("notify_url", "http://10.65.106.83:8080/java_demo_zhanghu/Notify");
		map.put("version", "1.2");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("quota", "3000");
		map.put("return_url", "http://localhost:8080/java_demo_zhanghu/index.html");
		map.put("identity_type", "UID");
		map.put("sign_type", "RSA");
		map.put("day_quota", "3800");
		Map<String, Object> result = SinaSDK.mgsSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void queryWithholdAuthority()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "query_withhold_authority");
		map.put("auth_type", "ALL");
		map.put("identity_id", "20190409104630");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("memo", "");
		map.put("notify_url", "http://10.65.106.83:8080/java_demo_zhanghu/Notify");
		map.put("version", "1.2");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("return_url", "http://localhost:8080/java_demo_zhanghu/index.html");
		map.put("identity_type", "UID");
		map.put("is_detail_disp", "Y");
		map.put("sign_type", "RSA");
		Map<String, Object> result = SinaSDK.mgsSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void relieveWithholdAuthority()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "relieve_withhold_authority");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("identity_id", "1555051737809");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("return_url", "http://localhost:6070/sinaPayJavaDemo/index.html");
		map.put("memo", "");
		map.put("notify_url", "http://10.65.106.27:6070/sinaPayJavaDemo/Notify");
		map.put("identity_type", "UID");
		map.put("version", "1.2");
		map.put("sign_type", "RSA");
		Map<String, Object> result = SinaSDK.mgsSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void setMerchantConfig()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "set_merchant_config");
		map.put("config_value", "MEMBER_INFO_GENERAL_NOTIFY_URL");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("memo", "");
		map.put("notify_url", "http://10.65.106.83:8080/java_demo_zhanghu/Notify");
		map.put("version", "1.2");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("config_key", "MEMBER_INFO_GENERAL_NOTIFY_URL");
		map.put("return_url", "http://localhost:8080/java_demo_zhanghu/index.html");
		map.put("sign_type", "RSA");
		map.put("extend_param", "");
		Map<String, Object> result = SinaSDK.mgsSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void queryMerchantConfig()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "query_merchant_config");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("_input_charset", "utf-8");
		map.put("config_key", "MEMBER_INFO_GENERAL_NOTIFY_URL");
		map.put("cashdesk_addr_category", "");
		map.put("return_url", "http://localhost:8080/java_demo_zhanghu/index.html");
		map.put("memo", "");
		map.put("notify_url", "http://10.65.106.83:8080/java_demo_zhanghu/Notify");
		map.put("version", "1.2");
		map.put("sign_type", "RSA");
		Map<String, Object> result = SinaSDK.mgsSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void merchantReport()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "merchant_report");
		map.put("contact_name", "sss");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("memo", "");
		map.put("merchant_name", "sss");
		map.put("notify_url", "http://10.65.106.83:8080/java_demo_zhanghu/Notify");
		map.put("mcc", "");
		map.put("version", "1.2");
		map.put("contact_identity", "02");
		map.put("custom_telephone", "0102523685");
		map.put("contact_type", "LEGAL_PERSON");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("merchant_identitiy_type", "UID");
		map.put("return_url", "http://localhost:8080/java_demo_zhanghu/index.html");
		map.put("merchant_identity_id", "20181012100627");
		map.put("short_name", "ss");
		map.put("report_org", "TENPAY");
		map.put("client_ip", "127.0.0.1");
		map.put("sign_type", "RSA");
		map.put("app_id", "sssssssssssssw");
		map.put("extend_param", "");
		Map<String, Object> result = SinaSDK.mgsSDK(map);
		LOG.debug("【result】 = {}", result);
	}
	@Test
	public void smerchantUrlGet()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("service", "smerchant_url_get");
		map.put("member_type", "1");
		map.put("identity_id", "201904091107458");
		map.put("_input_charset", "utf-8");
		map.put("cashdesk_addr_category", "");
		map.put("memo", "");
		map.put("notify_url", "http://10.65.106.83:8080/java_demo_zhanghu/Notify");
		map.put("version", "1.2");
		map.put("request_time", requestTime);
		map.put("partner_id", "2000091666666");
		map.put("return_url", "http://localhost:8080/java_demo_zhanghu/index.html");
		map.put("client_ip", "101.231.34.38");
		map.put("identity_type", "UID");
		map.put("sign_type", "RSA");
		Map<String, Object> result = SinaSDK.mgsSDK(map);
		LOG.debug("【result】 = {}", result);
	}
}
