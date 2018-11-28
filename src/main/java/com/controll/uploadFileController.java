package com.controll;

import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.base.BaseSet;
import com.entity.User;
import com.util.HDFSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 接收文件上传
 * 
 * @author lijie
 */
@Controller
@RequestMapping("/upload")
public class uploadFileController {
	private static Logger logger = LoggerFactory.getLogger(uploadFileController.class);

	/**
	 * 接收用户头像
	 */
	@RequestMapping(value = "/userImage", method = RequestMethod.POST)
	@ResponseBody
	public int reciveImage(HttpServletRequest req, @RequestParam("file") MultipartFile file) {
		User user = (User) req.getSession().getAttribute(BaseSet.USER);
		// 如果文件不为空，写入上传路径
		if (!file.isEmpty()) {
			// 上传文件路径
			String path = req.getServletContext().getRealPath("/img/");
			// 上传文件名
			String filename = user.getName() + "." + file.getOriginalFilename().split("\\.")[1];
			File filepath = new File(path, filename);
			// 判断路径是否存在，如果不存在就创建一个
			if (!filepath.getParentFile().exists()) {
				filepath.getParentFile().mkdirs();
			}
			// 将上传文件保存到一个目标文件当中,再上传到hadoop，删除本地文件
			try {
				String localFileName = path + File.separator + filename;
				File localFile = new File(localFileName);
				file.transferTo(localFile);// 保存到一个本地文件
				HDFSUtil.uploadFile(localFileName, "image/" + filename);
				localFile.delete();
			} catch (IllegalStateException e) {
				logger.error("非法参数");
				return 0;
			} catch (IOException e) {
				logger.error("io错误");
				return 0;
			}
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * 接收文章图片
	 */
	@RequestMapping(value = "/uploadArticleImage", method = RequestMethod.POST)
	@ResponseBody
	public String reciveArticleImage(HttpServletRequest req, @RequestParam("file") MultipartFile file) {
		// 如果文件不为空，写入上传路径
		if (!file.isEmpty()) {
			// 上传文件路径
			File filepath = new File(BaseSet.ARTICLE_IMAGEPATH);// 本地img文件夹
			// 判断路径是否存在，如果不存在就创建一个
			if (!filepath.exists()) {
				filepath.mkdirs();
			}
			// 上传文件名
			String filename = "image_" + System.currentTimeMillis();
			// 将上传文件保存到文件夹当中
			try {
				String localFileName = BaseSet.ARTICLE_IMAGEPATH + File.separator + filename;
				file.transferTo(new File(localFileName));// 保存到一个本地文件
				return "download/downArticleImage/" + filename;
			} catch (IllegalStateException e) {
				logger.error("非法参数");
			} catch (IOException e) {
				logger.error("io错误");
			}
		}
		return "";
	}

}
