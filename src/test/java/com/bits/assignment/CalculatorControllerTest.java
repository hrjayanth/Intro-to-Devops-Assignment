package com.bits.assignment;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CalculatorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void Test() throws Exception {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string("Working!!"));
	}

	@Test
	void addTest() throws Exception {
		this.mockMvc.perform(get("/add/1/2")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string("3"));
	}

	@Test
	void subTest() throws Exception {
		this.mockMvc.perform(get("/sub/1/2")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string("-1"));
	}

	@Test
	void multiplyTest() throws Exception {
		this.mockMvc.perform(get("/multiply/1/2")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string("2"));
	}

	@Test
	void divideTest() throws Exception {
		this.mockMvc.perform(get("/divide/1/2")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string("0.5"));
	}

	@Test
	void divideByZeroTest() throws Exception {
		this.mockMvc.perform(get("/divide/1/0")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string("Divisor cannot be 0"));
	}
}
