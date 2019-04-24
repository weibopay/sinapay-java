package com.sina.sinapaycore.java.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.sina.sinapaycore.java.exception.SDKException;

/**
 * csv操作工具类
  * @ClassName: CSVUtil
  * @Description: TODO(csv操作工具类)
  * @author user
  * @date 2019年4月11日
  *
 */
public class CSVUtil
{
	private static final Logger LOG = LoggerFactory.getLogger(CSVUtil.class);
	
	/**
	 * 读取csv转换成对象
	 * 
	 * @Title: csv2Bean
	 * @Description: TODO(csv转换成对象)
	 * @param @param
	 *            file 需要读取的文件
	 * @param @param
	 *            clazz 实体类类对象
	 * @param @param
	 *            charset 字符编码GBK
	 * @param @return
	 * @param @throws
	 *            IOException 参数
	 * @return List<T> 返回类型
	 */
	public static <T> List<T> csv2Bean(File file, Class<T> clazz, String charset) throws IOException
	{
		Reader fReader = new InputStreamReader(new FileInputStream(file), charset);
		List<T> pojos = new CsvToBeanBuilder<T>(fReader).withType(clazz).build().parse();
		fReader.close();
		return (List<T>) pojos;
	}

	/**
	 * 写出csv文件
	 * 
	 * @Title: writeWithBeans 
	 * @Description: TODO(写出csv文件) 
	 * @param @param pojos 对象列表 
	 * @param file 需要写入的csv文件
	 * @param charset 字符集编码格式GBK 
	 * @param @throws IOException 参数 
	 * @return void 返回类型 @throws
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> void writeWithBeans(List<T> pojos, File file, String charset) throws IOException
	{
		if (!file.getParentFile().exists())
		{
			file.getParentFile().mkdirs();
		}
		Writer writer = new OutputStreamWriter(new FileOutputStream(file), charset);
		StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
		try
		{
			beanToCsv.write(pojos);
		} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e)
		{
//			e.printStackTrace();
			LOG.error("【SDK 写入CSV文件异常】", e);
			throw new SDKException(e.getMessage());
		}
		writer.close();
	}

	/**
	 * 读取csv文件
	 * @Title: readFile
	 * @Description: TODO(读取csv文件)
	 * @param @param
	 *            file 读取的csv文件
	 * @param charset
	 *            字符集编码格式GBK
	 * @return void 返回类型
	 */
	@SuppressWarnings("resource")
	public static void readFile(File file, String charset) throws IOException
	{
		Reader fReader = new InputStreamReader(new FileInputStream(file), charset);
		CSVReader reader = new CSVReader(fReader);
		StringBuffer sb = new StringBuffer();
		String[] nextLine;
		while ((nextLine = reader.readNext()) != null)
		{
			for (String string : nextLine)
			{
				System.out.println(string);
				sb.append(string).append("      ");
			}
			sb.append("\n");
		}
		Writer r = new FileWriter(new File("D:/t交易关联号余额2.txt"));
		r.write(sb.toString());
//		r.flush();
		r.close();
		fReader.close();
	}
	
	/*public static void main(String[] args)
	{
		File file = new File("C:\\Users\\user\\Downloads\\交易关联号2.csv");
		try
		{
			readFile(file, "GBK");
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
