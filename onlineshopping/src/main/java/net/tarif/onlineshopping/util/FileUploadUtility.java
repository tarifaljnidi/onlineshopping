package net.tarif.onlineshopping.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtility {

	
	private static final String ABS_PATH = "F:\\projects\\spring\\online-shopping\\onlineshopping\\src\\main\\webapp\\assets\\images\\";
	private static String REAL_PATH = null;
	public static void uploadFile(HttpServletRequest request,
			MultipartFile file, String code) {
		
		// get the real server path
				REAL_PATH = request.getSession().getServletContext().getRealPath("/assets/images/");
				
							
				// create the directories if it does not exist
				
				if(!new File(REAL_PATH).exists()) {
					new File(REAL_PATH).mkdirs();
				}
				
				if(!new File(ABS_PATH).exists()) {
					new File(ABS_PATH).mkdirs();
				}
				try {
					//transfer the file to both the location
					file.transferTo(new File(REAL_PATH + code + ".jpg"));
					file.transferTo(new File(ABS_PATH + code + ".jpg"));
				}
				catch(IOException ex) {
					ex.printStackTrace();
				}
				
	}
}
