package com.example.demo.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.exceptions.InvalidAPICallException;
import com.example.demo.exceptions.InvalidFilePathException;
import com.example.demo.response.dto.ResponseDTO;
import com.example.demo.response.dto.ResponseObject;
import com.example.demo.utility.RequestUtility;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;
import okhttp3.Request;

@Service
public class GeoCodeService {
	
	@Autowired
	private RequestUtility utility;
	
	@Cacheable(cacheNames = {"geoCodeCache"}, key="#data")
	public List<ResponseDTO>  getCodesFromData(String data) throws InvalidAPICallException {
		
		//simulate backend delay
		utility.simulateBackendDelay();
		
		String[] addresses = data.trim().split("\\R");
		List<ResponseDTO> responses = new ArrayList<>();
		
		for(String address:addresses) {
			
			Request request = utility.buildRequest(address);
			try {
				OkHttpClient client = new OkHttpClient();
				String response = client.newCall(request).execute().body().string();
				
				ObjectMapper mapper = new ObjectMapper();
				
				ResponseDTO apiResponse = mapper.readValue(response, ResponseDTO.class);
				if(apiResponse.getData().size()>1) {
					ResponseObject object = apiResponse.getData().get(0);
					apiResponse.getData().clear();
					apiResponse.setData(Arrays.asList(object));
					responses.add(apiResponse);
				}
				else {
					responses.add(apiResponse);
				}
			}
			catch(Exception e) {
				throw new InvalidAPICallException("Invalid API Call");
			}
			
		}
		
		return responses;
		
	}

	public File writeToFile(List<ResponseDTO> response) throws InvalidFilePathException {
		
		String coordinates = utility.convertListToString(response);
		File outputFile = null;
		
		try {
			
			Path source = Paths.get(this.getClass().getResource("/").getPath());
			Path DataFolder = Paths.get(source.toAbsolutePath()+"/DataFolder");
			Files.createDirectories(DataFolder);
			
			
			outputFile = new File(source.toAbsolutePath().toString()+"/DataFolder/output.txt");
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
			writer.write(coordinates);
			writer.close();
		}
		catch(Exception e) {
			throw new InvalidFilePathException("The File Path Entered Doesnot Exist");
		}
		
		return outputFile;
		
	}

	public MultipartFile convertFileToMultipart(File outputFile) throws FileNotFoundException, IOException {
		return utility.convertFileToMultipart(outputFile);
	}
	
	

}
