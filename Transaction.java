import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
	private static Transaction instance;
	
	private Transaction() {
		
	}
	
	static {
		instance = new Transaction();
	}
	
	public static Transaction getTransaction() {
	
		return instance;
	}

    // Perform the borrowing of a book
    public boolean borrowBook(Book book, Member member) {
        if (book.isAvailable()) {
            book.borrowBook();
            member.borrowBook(book); 
            String transactionDetails = getCurrentDateTime() + " - Borrowing: " + member.getName() + " borrowed " + book.getTitle();
            System.out.println(transactionDetails);
            saveTransaction(transactionDetails);
            return true;
        } else {
            System.out.println("The book is not available.");
            return false;
        }
    }

    // Perform the returning of a book
    public boolean returnBook(Book book, Member member) {
        if (member.getBorrowedBooks().contains(book)) {
            member.returnBook(book);
            book.returnBook();
            String transactionDetails = getCurrentDateTime() + " - Returning: " + member.getName() + " returned " + book.getTitle();
            saveTransaction(transactionDetails);
            System.out.println(transactionDetails);
            
            return true; 
        } else {
            System.out.println("This book was not borrowed by the member.");
            
            return false;
        }
    }

    // Get the current date and time in a readable format
    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
    
    public void saveTransaction(String transactionDetails) {
    	File f = new File("transactions.txt");
    	
    	try(BufferedWriter writer = new BufferedWriter(new FileWriter(f, true))) {
    		writer.write(transactionDetails);
    		writer.newLine();
    		
		} catch (FileNotFoundException e) {
			
			e.printStackTrace(); 
		} catch (IOException e) {
	
			e.printStackTrace();
		}
    	
    }
    
    public void displayTransactionHistory() {
		String file = "./transactions.txt";
		
		try {
			List<String> lines = Files.readAllLines(Paths.get(file));
					
			for(String line : lines) {
				System.out.print(line);
				System.out.print("\n");
				
			}
		} catch(IOException e) {
			e.printStackTrace();
			
		}
    	
    }
    
}