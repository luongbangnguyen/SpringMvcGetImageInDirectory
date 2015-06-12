package org.java.spring.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.java.spring.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ImageController {
	@Autowired
	private ImageService imageSv;
	
	@RequestMapping("/image/{pathImage:.*}")
	public ResponseEntity<byte[]> testphoto(@PathVariable String pathImage,
			HttpServletResponse response) throws IOException {
		if(StringUtils.isBlank(pathImage)){
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		
		ResponseEntity<byte[]> responseEntity = imageSv.getResponseImage(pathImage);
		if(responseEntity == null){
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		return responseEntity;
	}
}
