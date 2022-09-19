package com.example.demo.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.response.dto.ResponseDTO;
import com.example.demo.response.dto.ResponseObject;

import okhttp3.Request;

@Component
public class RequestUtility {
	
	@Value("${gecoding.api.key}")
	private String apiKey;
	
	public Request buildRequest(String address) {
		return new Request.Builder()
				.url("http://api.positionstack.com/v1/forward?access_key="+apiKey+"&query="+address).get().build();
	}

	public void simulateBackendDelay() {
		 try {
	            System.out.println("------------- Going to sleep for 5 seconds to simulate Backend Delay -----------");
	            Thread.sleep(5 * 1000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
		
	}

	public String convertListToString(List<ResponseDTO> responses) {
		
		String coordinates = "";
		
		for(ResponseDTO response:responses) {
			for(ResponseObject object: response.getData()) {
				coordinates+=object.getLatitude()+" "+object.getLongitude()+"\n";
			}
		}
		
		return coordinates.trim();
	}

	public MultipartFile convertFileToMultipart(File outputFile) throws FileNotFoundException, IOException {
		return new MockMultipartFile(outputFile.getName(), outputFile.getName(), "text/plain", IOUtils.toByteArray(new FileInputStream(outputFile)));
	}

}
