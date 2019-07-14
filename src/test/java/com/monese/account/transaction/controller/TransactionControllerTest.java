package com.monese.account.transaction.controller;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.monese.account.transaction.controller.TransactionController;
import com.monese.account.transaction.model.Account;
import com.monese.account.transaction.model.Customer;
import com.monese.account.transaction.model.Statement;
import com.monese.account.transaction.model.Transaction;
import com.monese.account.transaction.service.TransactionService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TransactionController.class,secure=false)
public class TransactionControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	/**
	 * This stipulate to have a exception assertion rule .
	 */
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	/**
	 * Invoke the before advice of the Mockito for initiation
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

	}
	
	@MockBean
	private TransactionService service;
	
	private ObjectMapper mapper = null;
	
	private Customer mockCustomer = new Customer(1000L,"Bipa","Mumbai","75232691212","");


    private Account account =
            new Account("1000",BigDecimal.valueOf(1000.00),mockCustomer,new Transaction(BigDecimal.valueOf(100.00),new Date() ,"DR"));
	    
	@Test
    public void makeTransferTest() throws Exception {
        String transferJson = "{\"amount\":100,\"dstAccountId\":\"1000\",\"srcAccountId\":\"1001\"}";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/accounts/transfer")
				.accept(MediaType.APPLICATION_JSON).content(transferJson)
				.contentType(MediaType.APPLICATION_JSON);
        MvcResult result = null;
		try {
			result = mockMvc.perform(requestBuilder).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}

		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(result.getResponse().getContentAsString(), "");
    }
	
	@Test
    public void accountStatementTest() throws Exception {
		ModelMapper mapperbuilder = new ModelMapper();
        Statement statement =mapperbuilder.map(account, Statement.class);
        Mockito.when(
        		service.statement(Mockito.any(String.class))).thenReturn(statement);
        String json = mapper.writeValueAsString(statement);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/accounts/1000/statement")
				.contentType(MediaType.APPLICATION_JSON);
        MvcResult result = null;
		try {
			result = mockMvc.perform(requestBuilder).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}

		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(result.getResponse().getContentAsString(), json);
    }

}
