package org.java.spring.common;

import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HandleImage{
	
	public static final int DEFAULT_BUFFER_SIZE = 10240;
	
	@Value("${"+SystemConstant.IMAGE_DOCROOT+"}")
	private String rootbaseImg;
	
	public String getPathImg(String urlImage){
		return rootbaseImg + File.separator + urlImage;
	}
	
	public String getBaseUrl(){
		return rootbaseImg;
	}
	
	public File getFileImage(String urlImage) throws IOException{
		String pathImage = getPathImg(urlImage);
		File file = new File(pathImage);
		if(!file.exists()){
			return null;
		}
		return file;
	}
}
