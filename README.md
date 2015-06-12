# SpringMvcGetImageInDirectory
get image in directory with spring mvc
#####To run project with gradle
```
gradle bootrun
```
Controller
```
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
```
In service implement
```
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
```
