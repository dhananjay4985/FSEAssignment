package user.note.usernotewithcloudoauth2;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import user.library.librarymanagementusingbasicauth.model.Book;
import user.library.librarymanagementusingbasicauth.model.Subject;
import user.library.librarymanagementusingbasicauth.service.BookService;
import user.library.librarymanagementusingbasicauth.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=ApplicationTests.class)
@AutoConfigureMockMvc
public class ApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookService bookService;
	
	@MockBean
	private UserService userService;
	
	Set<Book> bookSet = new HashSet();
	
	Subject subject = new Subject(2L, "Java",12, bookSet);
	Book mockBook = new Book();
	//Book mockBook = new Book(123456L, 120D, 12/12/2018, "Java8", 12L, 2L, subject);
	
	String exampleJSON = "{\"bookId\":\"123456L\",\"bookName\":\"Java8\",\"author\":\"Oracle\",\"genre\":\"dfdfd\",\"isbn\":\"sdfsdfsdf\"}"; 
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void retrieveBookDetails() throws Exception {
		Mockito.when(bookService.findById(Mockito.anyLong())).thenReturn(mockBook);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/allbooks/123456L").accept(MediaType.APPLICATION_ATOM_XML.APPLICATION_JSON);
					
		//ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/allbooks/123456L").accept(MediaType.APPLICATION_ATOM_XML.APPLICATION_JSON));
		//resultActions.andDo(MockMvcResultHandlers.print());
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String expected = "{bookId:123456L,bookName:Java8,author:Oracle,genre:dfdfd,isbn:sdfsdfsdf}";
		System.out.println("Hii "+result.getResponse().getContentAsString());
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		
	}

}
