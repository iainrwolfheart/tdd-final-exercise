
public interface WebService {

	boolean authenticate(int userID, String password);
	
	String getStockInfo(String symbol);
}
