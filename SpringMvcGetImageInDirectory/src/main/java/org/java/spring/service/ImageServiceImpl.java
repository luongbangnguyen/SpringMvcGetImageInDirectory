package org.java.spring.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.java.spring.common.HandleImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService{

	@Autowired
	private ServletContext context;
	
	@Autowired
	private HandleImage handleIm;
	
	@Override
	public ResponseEntity<byte[]> getResponseImage(String urlImage) throws IOException {
		if(StringUtils.isBlank(urlImage)){
			return null;
		}
		File file = handleIm.getFileImage(urlImage);
		if(file == null){
			return null;
		}
		BufferedInputStream bf = new BufferedInputStream(new FileInputStream(file));
		byte[] data = IOUtils.toByteArray(bf);
		
		final HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_PNG);
	    headers.setContentLength(file.length());
	    headers.set("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
	    return new ResponseEntity<byte[]>(data, HttpStatus.OK);
	}

}
