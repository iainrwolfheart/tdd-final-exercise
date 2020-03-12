
public class StockInformation {

	private String symbol;
	
	WebService service;
	
	private String companyName;
	private int currentPrice;
	private int numberOfSharesOutstanding;
	private int marketCapitalisationInMillions;
	
	boolean isValidPassword; 
	boolean isValidUserID; 

	public StockInformation(int userID, String password, String symbol, WebService service) throws Exception {

		this.service = service;
		this.isValidPassword = checkPassword(password);
		this.isValidUserID = checkUserID(userID);
		
		String pattern = "^[a-zA-Z0-9]*$";
		if (symbol == null || !symbol.matches(pattern)) {
			throw new Exception("Incorrect symbol format");
		}
		
		else if (!isValidPassword || !isValidUserID) {
			this.symbol = "NA";
			this.companyName = "Not Allowed";
			this.currentPrice = 0;
			this.numberOfSharesOutstanding = 0;
			this.numberOfSharesOutstanding = 0;
			this.marketCapitalisationInMillions = 0;
		}
		
		else {
			this.symbol = symbol;
			
			String stockInfoReturn = "AA1, Nationwide, 89, 4";
			String[] stockInfo = stockInfoReturn.split(", ");
			this.companyName = stockInfo[1];
			this.currentPrice = Integer.parseInt(stockInfo[2]);
			this.numberOfSharesOutstanding = Integer.parseInt(stockInfo[3]);
			
		}
	}
	
	public boolean checkPassword(String password) {
		if (password.matches("((?=.*[a-z])(?=.*d)(?=.*[@#$%])(?=.*[A-Z]).{6,16})") && (password.length() >= 8)) {
			return true;
		}
		return false;
	}

	public boolean isValidUserID() {
		return isValidUserID;
	}
	
	public void setValidUserID(boolean isValidUserID) {
		this.isValidUserID = isValidUserID;
	}

	public boolean checkUserID(int userID) {
		if (userID > 0 && userID <= 9999) {
			return true;
		}
		return false;
	}

	public String getSymbol() {
		return symbol.toUpperCase();
	}

	private void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public WebService getService() {
		return service;
	}

	public void setService(WebService service) {
		this.service = service;
	}

	public String getCompanyName() {
		return companyName;
	}

	private void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getCurrentPrice() {
		return currentPrice;
	}

	private void setCurrentPrice(int currentPrice) {
		this.currentPrice = currentPrice;
	}

	public int getNumberOfSharesOutstanding() {
		return numberOfSharesOutstanding;
	}

	private void setNumberOfSharesOutstanding(int numberOfSharesOutstanding) {
		this.numberOfSharesOutstanding = numberOfSharesOutstanding;
	}

	public int getMarketCapitalisationInMillions() {
		
		//Need to implement rounding down to nearest million
		
		return marketCapitalisationInMillions;
	}

	private void setMarketCapitalisationInMillions(int marketCapitalisationInMillions) {
		this.marketCapitalisationInMillions = marketCapitalisationInMillions;
	}

	@Override
	public String toString() {
		return this.companyName + ", " + this.symbol + ", " + this.currentPrice;
	}
	
	
}
