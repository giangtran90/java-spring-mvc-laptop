package vn.hoidanit.laptopshop.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;

@Service
public class UploadFileService {
	
	private final ServletContext servletContext;

	public UploadFileService(ServletContext servletContext) {
		super();
		this.servletContext = servletContext;
	}

	public String handleSaveUploadFile(MultipartFile file, String folderUpload) {
		if (file.isEmpty()) {
			return "";
		}
		// relative path: absolute path
		String rootPath = this.servletContext.getRealPath("/resources/images");
		String finalName = "";
		try {
			byte[] bytes = file.getBytes();
			File dir = new File(rootPath + File.separator + folderUpload);
			if (!dir.exists())
				dir.mkdirs();
			// Create the file on server
			finalName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
			File serverFile = new File(dir.getAbsolutePath() + File.separator + finalName);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return finalName;
	}
}
