
public interface WebService {

	//abstract interface methods, who's implementation is decided by user,
	//... only parameters and returns need be adhered to. Useful for TDD as
	//... these specifications are then easy to test in a granular fashion.
	
	boolean authenticate(int userID, String password);
	
	String getStockInfo(String symbol);
}
