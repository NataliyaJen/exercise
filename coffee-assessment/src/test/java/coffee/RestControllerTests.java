package coffee;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import com.fasterxml.jackson.databind.ObjectMapper;

import coffee.controller.CoffeeRestController;
import coffee.service.CoffeeData;

@RunWith(SpringRunner.class)
@WebMvcTest(CoffeeRestController.class)
public class RestControllerTests {
	
	@Autowired
    private MockMvc mvc;
	
	@Autowired
	ObjectMapper objectMapper;
 
    @MockBean
    private CoffeeData service;

	@Test
	public void test() throws Exception{
		

/*	    
		mvc.perform(
			get("/coffee/accounts/rochelle")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.*", hasSize(1)))
			.andExpect(jsonPath("$[0].user", is("rochelle")))
			;		
	    
*/	
					
   
	}

}

