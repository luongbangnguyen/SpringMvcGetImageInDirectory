# SpringMvcGetImageInDirectory
get image in directory with spring mvc
#####To run project with gradle
```
gradle bootrun
```
#####Controller
```
	@RequestMapping("/image/{pathImage:.*}")
	public ResponseEntity<byte[]> testphoto(@PathVariable String pathImage,
			HttpServletResponse response) throws IOException {
		
		if(StringUtils.isBlank(pathImage)){
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		ResponseEntity<byte[]> responseEntity = imageSv.getResponseImage(pathImage);
		if(responseEntity.getStatusCode().equals(HttpStatus.NOT_FOUND)){
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		return responseEntity;
	}
```
#####In service implement
```
@Override
	public ResponseEntity<byte[]> getResponseImage(String urlImage) throws IOException {
		
		if(StringUtils.isBlank(urlImage)){
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		}
		File file = handleIm.getFileImage(urlImage);
		if(file == null){
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		}
		BufferedInputStream bf = new BufferedInputStream(new FileInputStream(file));
		byte[] data = IOUtils.toByteArray(bf);
		
		final HttpHeaders headers = extractHeader(file);
	    return new ResponseEntity<byte[]>(data,headers, HttpStatus.OK);
	}

	private HttpHeaders extractHeader(File file) {
		final HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_PNG);
	    headers.setContentLength(file.length());
	    headers.set("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
		return headers;
	}
```
#####_url exaple : http://localhost:8080/image/test.img_
