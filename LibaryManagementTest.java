import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LibaryManagementTest {
	
	@Test 
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
	
	private Book book; 
	private Member member; 
	private Transaction transaction;
	
	@BeforeEach
	public void setUp() throws Exception {
		
		book = new Book(101, "Test Book");
		member = new Member(1, "Test Member");
		transaction = Transaction.getTransaction();
	}
	
	@Test 
	public void testBorrowReturn() {
		assertTrue(book.isAvailable(), "Book should be available intially");
		
		boolean borrow = transaction.borrowBook(book, member);
		assertTrue(borrow, "borrowing should work");
		assertFalse(book.isAvailable(), "book should no longer be available");
		
		boolean borrowTwo = transaction.borrowBook(book, member);
		assertFalse(borrowTwo, "should fail as book is borrowed");
		
		boolean returnBook = transaction.returnBook(book, member);
		assertTrue(returnBook, "return should be successful");
		assertTrue(book.isAvailable(), "book should be available");
		
		boolean returnTwo = transaction.returnBook(book, member);
		assertFalse(returnTwo, "returning same book should not work");
		
	}
	
	
}
