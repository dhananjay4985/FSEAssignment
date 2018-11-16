package com.springboot.integration.testing;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restfulapi.application.SubjectBookApplication;
import com.restfulapi.application.controller.BookController;
import com.restfulapi.application.model.Book;
import com.restfulapi.application.repository.BookRepository;
import com.restfulapi.application.service.BookService;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@RunWith(SpringRunner.class)
@SpringBootTest(classes=SubjectBookApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BookServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookService bookService;

	@InjectMocks
	private BookController bookController;

	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders
				.standaloneSetup(bookController)                
				.build();
	}

	@Test
	public void testGetAllBooks() throws Exception {

		Date mockDate = getPublishDate();

		Book mockPythonBook = new Book();		
		mockPythonBook.setBookId(1);
		mockPythonBook.setTitle("PythonTutorial");
		mockPythonBook.setPrice(250.0);
		mockPythonBook.setVolume(15);
		mockPythonBook.setSubjectId(1235L);
		mockPythonBook.setPublishDate(mockDate);

		Book mockJavaBook = new Book();		
		mockJavaBook.setBookId(2);
		mockJavaBook.setTitle("HeadFirstJava");
		mockJavaBook.setPrice(200.0);
		mockJavaBook.setVolume(10);
		mockJavaBook.setSubjectId(1234L);
		mockJavaBook.setPublishDate(mockDate);		

		List<Book> bookList = new ArrayList<Book>();
		bookList.add(mockPythonBook);
		bookList.add(mockJavaBook);				

		when(bookService.getBook()).thenReturn(bookList);

		mockMvc.perform(get("/library/allbooks"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$[0].bookId", is(1)))
		.andExpect(jsonPath("$[0].title", is("PythonTutorial")))
		.andExpect(jsonPath("$[0].price", is(250.0)))
		.andExpect(jsonPath("$[0].volume", is(15)))
		.andExpect(jsonPath("$[0].publishDate", is(10-10-2017)))
		.andExpect(jsonPath("$[0].subjectId", is(1235)))
		.andExpect(jsonPath("$[1].bookId", is(2)))
		.andExpect(jsonPath("$[1].title", is("HeadFirstJava")))
		.andExpect(jsonPath("$[1].price", is(200.0)))
		.andExpect(jsonPath("$[1].volume", is(10)))
		.andExpect(jsonPath("$[1].publishDate", is(10-10-2017)))
		.andExpect(jsonPath("$[1].subjectId", is(1234))); 

		verify(bookService, times(1)).getBook();
		verifyNoMoreInteractions(bookService);

		/*Mockito.when(bookService.getBook()).thenReturn(bookList);		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/library/allbooks").accept(MediaType.APPLICATION_JSON_UTF8_VALUE);		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println("Result ..." +result.getResponse());
		// String expected ="{\"bookId\":\"123\",\"title\":\"HeadFirstJava\",\"price\":\"200.0\",\"volume\":\"10\",\"subjectId\":\"1234\",\"publishDate\":\"2017-10-9\"}";
		String expected = "{bookId:2,title:HeadFirstJava,price:200.0,volume:10,subjectId:1234,publishDate:2017-10-9}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);*/

	}

	@Test
	public void testGetBook() throws Exception {

		Date mockDate = getPublishDate();

		Book mockBook = new Book();		
		mockBook.setBookId(1);
		mockBook.setTitle("PythonTutorial");
		mockBook.setPrice(250.0);
		mockBook.setVolume(15);
		mockBook.setSubjectId(1235L);
		mockBook.setPublishDate(mockDate);


		when(bookService.findById(1)).thenReturn(mockBook);
		mockMvc.perform(get("/library/allbooks/{bookId}", 1))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$.bookId", is(1)))
		.andExpect(jsonPath("$.title", is("PythonTutorial")))
		.andExpect(jsonPath("$.price", is(250.0)))
		.andExpect(jsonPath("$.volume", is(15)))
		.andExpect(jsonPath("$.publishDate", is(10-10-2017)))		  
		.andExpect(jsonPath("$.subjectId", is(1235)));

		verify(bookService, times(1)).findById(1);
		verifyNoMoreInteractions(bookService);

		/*Mockito.when(bookService.findById(1235)).thenReturn(mockBook);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/library/allbooks/{bookId}").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "{bookId:1,title:PythonTutorial,price:250.0,volume:15,subjectId:1235,publishDate:2017-10-09T18:30:00.000+0000}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);*/
	}

	@Test
	public void testAddBook() throws Exception{

		Date mockDate = getPublishDate();

		Book mockAngularBook = new Book();		
		mockAngularBook.setBookId(3);
		mockAngularBook.setTitle("Angular6");
		mockAngularBook.setPrice(350.0);
		mockAngularBook.setVolume(20);
		mockAngularBook.setSubjectId(123L);
		mockAngularBook.setPublishDate(mockDate);	
		
		
		//when(bookService.exists(mockAngularBook)).thenReturn(false);
       //ssdoNothing().when(bookService).createBook(mockAngularBook);
		
        when(bookService.createBook(mockAngularBook)).thenReturn(mockAngularBook);
     
        mockMvc.perform(
                post("/library/allbooks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(mockAngularBook)))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", containsString("http://localhost/library/allbooks")));
       
        verify(bookService, times(1)).createBook(mockAngularBook);
        verifyNoMoreInteractions(bookService);
		
	/*	Mockito.when(bookService.createBook(Mockito.any(Book.class))).thenReturn(mockBook);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/library/allbooks").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		assertEquals("http://localhost:8080/library/allbooks/3",response.getHeader(HttpHeaders.LOCATION));*/

	}

	@Test
	public void testUpdateBook() throws Exception {

		Date mockDate = getPublishDate();

		Book mockAngularBook = new Book();		
		mockAngularBook.setBookId(3);
		mockAngularBook.setTitle("Angular7 Beta");
		mockAngularBook.setPrice(350.0);
		mockAngularBook.setVolume(15);
		mockAngularBook.setSubjectId(123L);
		mockAngularBook.setPublishDate(mockDate);

        when(bookService.findById(mockAngularBook.getBookId())).thenReturn(mockAngularBook);
        doNothing().when(bookService).update(mockAngularBook,mockAngularBook.getBookId());
		
        mockMvc.perform(
        				put("/library/allbooks/{bookId}", mockAngularBook.getBookId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(mockAngularBook)))
                .andExpect(status().isOk());

        verify(bookService, times(1)).findById(mockAngularBook.getBookId());
        verify(bookService, times(1)).update(mockAngularBook,mockAngularBook.getBookId());
        verifyNoMoreInteractions(bookService);
		
        /*Mockito.when(bookService.update((Mockito.any(Book.class)),Mockito.anyInt())).thenReturn(mockBook);
		//Mockito.when(bookService.createBook(Mockito.any(Book.class))).thenReturn(mockBook);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/library/allbooks/{bookId}").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		assertEquals("http://localhost:8080/library/allbooks/3",response.getHeader(HttpHeaders.LOCATION));*/
	}
	
	// =========================================== Delete User ============================================

    @Test
    public void testDeleteBookById() throws Exception {
    	Date mockDate = getPublishDate();

		Book mockAngularBook = new Book();		
		mockAngularBook.setBookId(3);
		mockAngularBook.setTitle("Angular7 Beta");
		mockAngularBook.setPrice(350.0);
		mockAngularBook.setVolume(15);
		mockAngularBook.setSubjectId(123L);
		mockAngularBook.setPublishDate(mockDate);

        when(bookService.findById(mockAngularBook.getBookId())).thenReturn(mockAngularBook);
      doNothing().when(bookService).deleteBookById(mockAngularBook.getBookId());

        mockMvc.perform(
                delete("/library/allbooks/{bookId}", mockAngularBook.getBookId()))
                .andExpect(status().isOk());

       verify(bookService, times(1)).findById(mockAngularBook.getBookId());
        verify(bookService, times(1)).deleteBookById(mockAngularBook.getBookId());
        verifyNoMoreInteractions(bookService);
    }

    /*@Test
    public void test_delete_user_fail_404_not_found() throws Exception {
        User user = new User(UNKNOWN_ID, "book not found");

        when(bookService.findById(user.getId())).thenReturn(null);

        mockMvc.perform(
                delete("/users/{id}", user.getId()))
                .andExpect(status().isNotFound());

        verify(bookService, times(1)).findById(user.getId());
        verifyNoMoreInteractions(bookService);
    }*/

	public static Date getPublishDate(){
		DateFormat dateFormat;
		Date mockDate = null;
		try {
			dateFormat = new SimpleDateFormat("MM-dd-yyyy");
			mockDate = dateFormat.parse("10-10-2017");// for example, today's date
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return mockDate;
	}


	/*
	 * converts a Java object into JSON representation
	 */
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*	@Test
	public void contextLoads() {
	}
	 */

}
