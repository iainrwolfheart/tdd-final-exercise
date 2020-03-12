import static org.junit.Assert.*;
import org.easymock.EasyMock;
import static org.easymock.EasyMock.createMock;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class StockInformationTest {
	
	WebService service;
	StockInformation stock;

	@Before
	public void setUp() throws Exception {
		service = createMock(WebService.class);
		
	}

	@Test
	public void testPasswordValid() throws Exception {
		stock = new StockInformation(7, "P@ssw0rd", "AA1", service);
		EasyMock.expect(service.authenticate(7, "P@ssw0rd")).andReturn(true);
		EasyMock.expect(service.getStockInfo("AA1")).andReturn("YY, Nationwide, 89, 4");
		EasyMock.replay(service);
		assertEquals(true, stock.service.authenticate(7, "P@ssw0rd")); 
//		EasyMock.verify(service);
	}
	
	@Test
	public void testPasswordValidPasswordFieldsSet() throws Exception {
		stock = new StockInformation(7, "P@ssw0rd", "AA1", service);
		EasyMock.expect(service.authenticate(7, "P@ssw0rd")).andReturn(true).anyTimes();
		EasyMock.expect(service.getStockInfo("AA1")).andReturn("YY, Nationwide, 89, 4");
		EasyMock.replay(service);
		assertEquals("YY, Nationwide, 89, 4", stock.getService().getStockInfo("AA1"));
		EasyMock.verify(service);
	}

	@Test 
	public void testPasswordNotValidFieldsSet() throws Exception {
		stock = new StockInformation(7, "password", "AA1", service);
		EasyMock.expect(service.authenticate(7, "password")).andReturn(false).anyTimes();
		EasyMock.expect(service.getStockInfo("AA1")).andReturn("Not Allowed");
		EasyMock.replay(service);
		assertEquals("Not Allowed", stock.getCompanyName());
	}

	@Test
	public void testUserIDValid() throws Exception {
		stock = new StockInformation(7, "P@ssw0rd", "AA1", service);
		EasyMock.expect(service.authenticate(7, "P@ssw0rd")).andReturn(true).anyTimes();
		EasyMock.expect(service.getStockInfo("AA1")).andReturn("YY, Nationwide, 89, 4");
		EasyMock.replay(service);
		assertEquals(true, stock.isValidUserID());
	}

	@Test 
	public void testUserIDNotValidMessageAppears() throws Exception {
		stock = new StockInformation(0, "P@ssw0rd", "AA1", service);
		EasyMock.expect(service.authenticate(0, "P@ssw0rd")).andReturn(false).anyTimes();
//		EasyMock.expect(service.getStockInfo("AA1")).andReturn("Not Allowed");
		EasyMock.replay(service);
		assertEquals(false, stock.isValidUserID());
	}

	@Test 
	public void testSymbolCorrectlyFormatted() throws Exception {
		stock = new StockInformation(7, "P@ssw0rd", "AA1", service);
		String pattern = "^[a-zA-Z0-9]*$";
		assertTrue(stock.getSymbol().matches(pattern));
	}

	@Test 
	public void testInvalidSymbolFormatExceptionThrown() throws Exception {
		EasyMock.expect(service.authenticate(7, "P@ssw0rd")).andStubReturn(true);
		EasyMock.replay(service);
		
		try {
			stock = new StockInformation(7, "P@ssw0rd", "AA&", service);
			fail();
		}
		catch (Exception e){
			assertEquals("Incorrect symbol format", e.getMessage());
		}
	}

	@Test 
	public void testWebServiceFormattingException() throws Exception {
		stock = new StockInformation(7, "P@ssw0rd", "AA1", service);
		EasyMock.expect(service.authenticate(7, "P@ssw0rd")).andReturn(true).anyTimes();
		EasyMock.expect(service.getStockInfo("AA1")).andReturn("YY, Nationwide, 89, 4");
	}
	
	@Test 
	public void testGoodToString() throws Exception {
		stock = new StockInformation(7, "P@ssw0rd", "AA1", service);
		EasyMock.expect(service.authenticate(7, "P@ssw0rd")).andReturn(true).anyTimes();
		EasyMock.expect(service.getStockInfo("AA1")).andReturn("AA1, Nationwide, 89, 4");
		assertEquals("Nationwide, AA1, 89", stock.toString());
	}

}
