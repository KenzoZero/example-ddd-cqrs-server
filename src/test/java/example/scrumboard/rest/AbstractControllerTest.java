package example.scrumboard.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Test(singleThreaded = true)
public abstract class AbstractControllerTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private ObjectMapper objectMapper;

	private MockMvc mockMvc;

	protected MockMvc getMockMvc() {
		return mockMvc;
	}

	protected String toJson(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Could not create JSON representation for " + object + ".", e);
		}
	}

	@BeforeMethod
	protected void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
		// TODO: inject objectMapper
		objectMapper = new ObjectMapper();
	}

}
