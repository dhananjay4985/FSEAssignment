package com.springboot.integration.testing;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.restfulapi.application.model.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restfulapi.application.SubjectBookApplication;
import com.restfulapi.application.model.Subject;
import com.restfulapi.application.controller.SubjectController;
import com.restfulapi.application.service.SubjectService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SubjectBookApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SubjectServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SubjectService subjectService;

	@InjectMocks
	private SubjectController subjectController;

	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders
				.standaloneSetup(subjectController)                
				.build();
	}



	@Test
	public void testGetAllSubjects() throws Exception {
		Set<Book> references = new HashSet<Book>();
		List<Subject> subjectList = new ArrayList<Subject>();
		Date mockDate = BookServiceApplicationTests.getPublishDate();

		Book mockPythonBook = new Book();		
		mockPythonBook.setBookId(1);
		mockPythonBook.setTitle("PythonTutorial");
		mockPythonBook.setPrice(250.0);
		mockPythonBook.setVolume(15);
		mockPythonBook.setSubjectId(1235L);
		mockPythonBook.setPublishDate(mockDate);

		references.add(mockPythonBook);

		Subject subject = new Subject(1235L,"Python",12, references);
		subjectList.add(subject);

		when(subjectService.getSubject()).thenReturn(subjectList);
		mockMvc.perform(get("/library/allSubjects"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$[0].subjectId", is(1235)))
		.andExpect(jsonPath("$[0].subjectTitle", is("Python")))
		.andExpect(jsonPath("$[0].durationInHours", is(12)));
		assertEquals("$[0].references", references);

		verify(subjectService, times(1)).getSubject();
		verifyNoMoreInteractions(subjectService);
	}

	@Test
	public void testGetSubjectById() throws Exception {
		Date mockDate = BookServiceApplicationTests.getPublishDate();

		Set<Book> references = new HashSet<Book>();		

		Book mockPythonBook = new Book();		
		mockPythonBook.setBookId(1);
		mockPythonBook.setTitle("PythonTutorial");
		mockPythonBook.setPrice(250.0);
		mockPythonBook.setVolume(15);
		mockPythonBook.setSubjectId(1235L);
		mockPythonBook.setPublishDate(mockDate);

		references.add(mockPythonBook);

		Subject subject = new Subject(1235L,"Python",12, references);

		System.out.println("hiii " +subject.toString());
		when(subjectService.findById(1235L)).thenReturn(subject);
		mockMvc.perform(get("/library/allSubjects/{subjectId}",1235))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))	
		.andExpect(jsonPath("$.subjectId", is(1235)))
		.andExpect(jsonPath("$.subjectTitle", is("Python")))
		.andExpect(jsonPath("$.durationInHours", is(12)));
		assertEquals(references, references);

		verify(subjectService, times(1)).findById(1235L);
		verifyNoMoreInteractions(subjectService);

	}
	@Test
	public void testAddSubject() throws Exception{
		Date mockDate = BookServiceApplicationTests.getPublishDate();

		Set<Book> references = new HashSet<Book>();		

		Book mockJavaBook = new Book();		
		mockJavaBook.setBookId(1);
		mockJavaBook.setTitle("JavaTutorials");
		mockJavaBook.setPrice(300.0);
		mockJavaBook.setVolume(15);
		mockJavaBook.setSubjectId(123L);
		mockJavaBook.setPublishDate(mockDate);

		references.add(mockJavaBook);

		Subject mockSubject = new Subject(123L,"Java",12,references);

		when(subjectService.createSubject(mockSubject)).thenReturn(mockSubject);

		mockMvc.perform(
				post("/library/allSubjects")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(mockSubject)))
		.andExpect(status().isCreated())
		.andExpect(header().string("location", containsString("http://localhost/library/allSubjects")));

		verify(subjectService, times(1)).createSubject(mockSubject);
		verifyNoMoreInteractions(subjectService);

	}

	@Test
	public void testUpdateSubject() throws Exception{
		Date mockDate = BookServiceApplicationTests.getPublishDate();

		Set<Book> references = new HashSet<Book>();		

		Book mockJavaBook = new Book();		
		mockJavaBook.setBookId(1);
		mockJavaBook.setTitle("JavaTutorials");
		mockJavaBook.setPrice(300.0);
		mockJavaBook.setVolume(15);
		mockJavaBook.setSubjectId(123L);
		mockJavaBook.setPublishDate(mockDate);

		references.add(mockJavaBook);

		Subject mockSubject = new Subject(123L,"Java",12,references);

		when(subjectService.findById(mockSubject.getSubjectId())).thenReturn(mockSubject);
		doNothing().when(subjectService).update(mockSubject,mockSubject.getSubjectId());

		mockMvc.perform(
				put("/library/allSubjects/{subjectId}",mockSubject.getSubjectId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(mockSubject)))
		.andExpect(status().isOk());


		verify(subjectService, times(1)).findById(mockSubject.getSubjectId());
		verify(subjectService, times(1)).update(mockSubject,mockSubject.getSubjectId());
		verifyNoMoreInteractions(subjectService);
	}

	@Test
	public void testDeleteBookById() throws Exception {
		Date mockDate = BookServiceApplicationTests.getPublishDate();
		Set<Book> references = new HashSet<Book>();		
		Book mockAngularBook = new Book();		
		mockAngularBook.setBookId(3);
		mockAngularBook.setTitle("Angular7 Beta");
		mockAngularBook.setPrice(350.0);
		mockAngularBook.setVolume(15);
		mockAngularBook.setSubjectId(123L);
		mockAngularBook.setPublishDate(mockDate);
		
		references.add(mockAngularBook);
		
		Subject mockSubject = new Subject(123L,"Java",12,references);

		
		when(subjectService.findById(mockSubject.getSubjectId())).thenReturn(mockSubject);
		doNothing().when(subjectService).deleteSubjectById(mockSubject.getSubjectId());

		mockMvc.perform(
				delete("/library/allSubjects/{subjectId}", mockSubject.getSubjectId()))
		.andExpect(status().isOk());

		verify(subjectService, times(1)).findById(mockSubject.getSubjectId());
		verify(subjectService, times(1)).deleteSubjectById(mockSubject.getSubjectId());
		verifyNoMoreInteractions(subjectService);
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*@Test
	public void contextLoads() {
	}*/


}
