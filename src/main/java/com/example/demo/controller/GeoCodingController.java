package com.example.demo.controller;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.enumeration.FileProperties;
import com.example.demo.response.dto.ResponseDTO;
import com.example.demo.service.GeoCodeService;

@RestController
@RequestMapping("/api")
public class GeoCodingController {
	
	
	@Autowired
	private GeoCodeService geoCodeService;
	
	
	@RequestMapping(value="/getCodes",method = RequestMethod.PUT)
	public ResponseEntity<?> getGeoCodes(
			@RequestParam(name="DataFile") MultipartFile multiPartFile){
		
		Resource resource = multiPartFile.getResource();
		
		try {
			InputStream inputStream = resource.getInputStream();
			byte[] bData = FileCopyUtils.copyToByteArray(inputStream);
			String data = new String(bData, StandardCharsets.UTF_8);
			
			List<ResponseDTO>  response = geoCodeService.getCodesFromData(data);
			
			File outputFile = geoCodeService.writeToFile(response);
			Resource outputResource = geoCodeService.convertFileToMultipart(outputFile).getResource();
			
			return ResponseEntity.ok()
					.contentType(MediaType.parseMediaType(FileProperties.CONTENT_TYPE.getValue()))
					.header(HttpHeaders.CONTENT_DISPOSITION, FileProperties.HEADER_VALUE+outputResource.getFilename()+"\"")
					.body(outputResource);
					
		
		}catch(Exception e) {
			return ResponseEntity.noContent().build();
		}
		
	}

}
