import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LibaryManagementTest {
	public void testBookId() {
		try {
			Book low = new Book(100, "book is low");
			assertEquals(100, low.getId());
			assertEquals("book is low", low.getTitle());
			assertTrue(low.isAvailable(), "should be available");
			
			Book high = new Book(999, "book is high");
			assertEquals(999, high.getId());
			assertEquals("book is high", high.getTitle());
			assertTrue(high.isAvailable(), "should be available");
			
		} catch (Exception e) {
			fail("Exception should be thrown");
			
		}
		
		Exception toLow = assertThrows(Exception.class, () -> {
			new Book(99, "invalid ID to low must be within 100-999");
		});
		assertEquals("Invalid Book ID", toLow.getMessage());
		
		Exception toHigh = assertThrows(Exception.class, () -> {
			new Book(1000, "invalid ID to low must be within 100-999");
		});
		assertEquals("Invalid Book ID", toHigh.getMessage());
		
	}
	
}
