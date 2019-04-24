# sina-pay
新浪支付sdk</br>
1.项目说明</br>
2. 环境要求</br>
本项目为Java语言的SDK项目，打包方式为maven，使用时需要安装maven3.5以上版本，jdk1.8及以上</br>
3.使用说明</br>
3.1.下载sdk导入开发工具或在命令行执行 maven clean install将sdk打包并部署到本地仓库</br>
3.2.在项目的pom.xml文件中引入依赖</br>
```
<dependency>
	<groupId>com.sina</groupId>
	<artifactId>sina-pay-core</artifactId>
	<version>0.0.1-RELEASE</version>
</dependency>
```
3.3. sdk.SinaSDK.java是sdk方法的入口 该类中提供三个方法</br>

方法名|方法说明|请求入参|返回参数
--|:--:|:--:|:--:
SinaSDK.masSDK()|交易类网关请求入口|Map<String, Object>|Map<String, Object>
SinaSDK.mgsSDK()|会员类网关请求入口|Map<String, Object>|Map<String, Object>
SinaSDK.notifySDK()|商户通知的入口|Map<String, Object>|ture/false  验签成功/验签失败</br>

3.4.配置文件说明</br>

文件名|文件说明|放置目录
--|:--:|:--:
setting.xml|该文件中存放了商户的网关地址，sftp的连接信息，项目初次调用相关接口的时候会读取该配置文件获取相关信息|项目根路径（也可以自己修改代码来控制文件的路径）
log4j2.xml| 日志配置文件 sdk中使用的是log4j2来管理日志，控制日志的输出，输出的日志文件有两种，一种是请求响应日志，一种是错误日志，商户也可以自定义日志的生成|项目的根路径、或者application.properties，application.yaml文件中自定义
id_rsa|sftp密钥|项目根路径（也可以自己修改代码来控制文件的路径）
rsa_public.pem|加密密钥|项目根路径（也可以自己修改代码来控制文件的路径）
rsa_sign_private.pem|加签私钥|项目根路径（也可以自己修改代码来控制文件的路径）
rsa_sign_public.pem|验签公钥|项目根路径（也可以自己修改代码来控制文件的路径）</br>

日志文件和加密密钥、加签私钥、验签公钥所在目录为**src/test/resources**</br>
注意：日志一定要打印</br>
