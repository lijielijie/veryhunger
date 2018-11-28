package com.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HDFSUtil {
	private static Logger logger = LoggerFactory.getLogger(HDFSUtil.class);
	private static Configuration conf = new Configuration();
	private static String hadoopBaseUrl = "hdfs://192.168.62.138:8020/user/";
	private static FileSystem fs=HDFSUtil.getFS(hadoopBaseUrl);
	public static void main(String[] args) throws Exception {
		HDFSUtil.readFile("image/lj.jpg");
	}

	/**
	 * 创建文件
	 * 
	 * @param configuration
	 * @param filePath
	 * @param data
	 * @throws IOException
	 */
	public static void createFile(String filePath, byte[] data) throws IOException {
		FSDataOutputStream fsDataOutputStream = fs.create(new Path(hadoopBaseUrl + filePath));
		fsDataOutputStream.write(data);
		fsDataOutputStream.close();
		fs.close();
	}

	/**
	 * 删除文件 true 为递归删除 否则为非递归
	 */
	public static void deleteFile(String filePath, boolean isReturn) throws IOException {
		boolean delete = fs.delete(new Path(hadoopBaseUrl + filePath), isReturn);
		if (!delete) {
			logger.error("删除失败");
		}
		fs.close();
	}

	/**
	 * 读取文件内容
	 *
	 * @author fulei.yang
	 */
	public static String readFile(String filePath) throws Exception {
		FSDataInputStream inputStream = null;
		ByteArrayOutputStream outputStream = null;
		try {
			inputStream = fs.open(new Path(hadoopBaseUrl + filePath));
			outputStream = new ByteArrayOutputStream(inputStream.available());
			IOUtils.copyBytes(inputStream, outputStream, 4096, false);
			logger.info("用户新头像已上传："+hadoopBaseUrl + filePath);
			return outputStream.toString();
		} finally {
			if (inputStream != null) {
				IOUtils.closeStream(inputStream);
			}
			if (outputStream != null) {
				IOUtils.closeStream(outputStream);
			}
		}
	}

	/**
	 * 读取文件内容,返回到前端页面
	 *
	 * @author fulei.yang
	 */
	public static OutputStream readFile(String filePath, HttpServletResponse res) throws IOException {
		FSDataInputStream inputStream = null;
		OutputStream out = null;
		try {
			inputStream = fs.open(new Path(hadoopBaseUrl + filePath));
			// 输出流
			out = res.getOutputStream();
			IOUtils.copyBytes(inputStream, out, conf);
			return out;
		} finally {
			if (inputStream != null)
				IOUtils.closeStream(inputStream);
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 从本地上传文件到HDFS
	 *
	 * @param configuration
	 * @throws IOException
	 */
	public static void uploadFile(String localFilePath, String hdfsFilePath) throws IOException {
		FSDataOutputStream out = fs.create(new Path(hadoopBaseUrl + hdfsFilePath));
		FileInputStream in = new FileInputStream(localFilePath); // 上传的文件
		IOUtils.copyBytes(in, out, 4096, true);
	}

	/**
	 * url为hadoop:/user下的子目录
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static FileSystem getFS(String url) {
		try {
			FileSystem fs = FileSystem.get(URI.create(url), conf);
			return fs;// 每次都重新创建
		}catch(IOException e){
			logger.error("获取hdfs实例失败");
		}
		return null;
	}
}
