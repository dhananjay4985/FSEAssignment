package com.springboot.integration.testing.student_service;

import java.util.Arrays;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.restfulapi.application.SubjectBookApplication;
import com.restfulapi.application.repository.BookRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SubjectBookApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentServiceApplicationTests {

	/*@LocalServerPort
	private int localServerPort;
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	
	HttpHeaders headers = new HttpHeaders();
	
	@Test
	public void testRetriveStudentDetails() {
		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(createUriWithPort("/students/707/courses/777"), HttpMethod.GET, entity, String.class);
		//Course course2 = new Course(777,"Java","Language",Arrays.asList("learn OOPS","code sample programs","u r ready"));
		String expected="{\"courseId\": \"777\",\"courseName\": \"Java\",\"description\": \"Language\",\"steps\": [\"learn OOPS\",\"code sample programs\",\"u r ready\"]}";
		
		try {			
			JSONAssert.assertEquals(expected, responseEntity.getBody(),false);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	*/
	
	@Test
	public void contextLoads() {
	}
	
	/*public String createUriWithPort(String uri) {
		return "http://localhost:" + localServerPort + uri;
	}
*/
}
