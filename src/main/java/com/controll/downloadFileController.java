package com.controll;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.base.BaseSet;
import com.entity.User;
import com.util.HDFSUtil;

/**
 * 接收文件上传
 * 
 * @author lijie
 */
@Controller
@RequestMapping("/download")
public class downloadFileController {
	private static Logger logger = LoggerFactory.getLogger(downloadFileController.class);
	
	/**
	 * 下载用户头像
	 */
	@RequestMapping(value = "/userImage")
	public void reciveImage(HttpServletRequest req, HttpServletResponse res) {
		User user = (User) req.getSession().getAttribute(BaseSet.USER);
		res.reset();// 清空输出流
		String storeFileName = "image/" + user.getName() + ".jpg";
		// 如果文件不为空，写入上传路径
		try {
			HDFSUtil.readFile(storeFileName, res);
		} catch (Exception e) {
			logger.error("下载失败：" + e);
			// 如果初始用户没有设置，返回默认头像
			OutputStream out = null;
			FileInputStream in = null;
			try {
				out = res.getOutputStream();
				in = new FileInputStream(new File(req.getServletContext().getRealPath("/img/") + "/pig.gif"));
				byte[] bt = new byte[1024];
				int bytes = 0;
				while ((bytes = in.read(bt)) != -1) {
					out.write(bt, 0, bytes);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				try {
					out.close();
					in.close();
				} catch (IOException e1) {

				}
			}
		}
	}

	/**
	 * 下载文章图片
	 */
	@RequestMapping("/downArticleImage/{imageName}")
	public void downArticleImage(HttpServletRequest req, HttpServletResponse res,
			@PathVariable("imageName") String imageName) {
		res.reset();// 清空输出流
		// 如果初始用户没有设置，返回默认头像
		OutputStream out = null;
		FileInputStream in = null;
		try {
			out = res.getOutputStream();
			in = new FileInputStream(new File(BaseSet.ARTICLE_IMAGEPATH + File.separator + imageName));
			byte[] bt = new byte[1024];
			int bytes = 0;
			while ((bytes = in.read(bt)) != -1) {
				out.write(bt, 0, bytes);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				out.close();
				in.close();
			} catch (IOException e1) {

			}
		}
	}
}
