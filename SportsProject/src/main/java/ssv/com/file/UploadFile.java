package ssv.com.file;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public class UploadFile {
	public static String saveFile(MultipartFile multipartFile) throws Exception {
		   String destination = System.getProperty("user.dir") + "/src/main/webapp/images/" + multipartFile.getOriginalFilename();
		   File file = new File(destination);
		   multipartFile.transferTo(file);
		   return "images/"+multipartFile.getOriginalFilename();
		}

}
