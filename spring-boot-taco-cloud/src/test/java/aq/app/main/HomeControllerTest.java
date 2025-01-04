package aq.app.main;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import aq.app.controllers.HomeController;

@WebMvcTest(controllers = HomeController.class)
public class HomeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testHomePage() throws Exception {
		mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(view().name("home.html"));
//			.andExpect(content().string(containsString("Welcome to...")));
	}
}
