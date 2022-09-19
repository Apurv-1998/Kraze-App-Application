package com.example.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void test_getCodes() throws Exception {
		
		String fileName = "mock-input.txt";
		MockMultipartFile sampleFile = new MockMultipartFile("DataFile", fileName, "text/plain", "Dehradun".getBytes());
		
		MockMultipartHttpServletRequestBuilder request = MockMvcRequestBuilders.multipart("/api/getCodes");
		
		request.with(new RequestPostProcessor() {
			
			@Override
			public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
				request.setMethod("PUT");
				return request;
			}
		});
		
		mockMvc.perform(request.file(sampleFile)).andExpect(status().isOk());
		
	}
	

}
