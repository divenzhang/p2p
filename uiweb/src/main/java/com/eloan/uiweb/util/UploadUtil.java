package com.eloan.uiweb.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 上传工具
 * @author Administrator
 *
 */
public class UploadUtil {

	public static String upload(MultipartFile file, String basePath) {
		String orgFileName = file.getOriginalFilename();
		String fileName = UUID.randomUUID().toString() + "."
				+ FilenameUtils.getExtension(orgFileName);
		try {
			FileUtils.writeByteArrayToFile(new File(basePath, fileName),
					file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}

}
