
public class StockInformation {
	
	//I removed some unused fields and setters specified in the exercise outline and kept testing
	//to the fields and methods passed between the StockInformation class and the WebService interface.


	private String symbol;
	private String companyName;
	private int currentPrice;
	private int numberOfSharesOutstanding;
	//My first use of the 'protected' access modifier, just saying.
	protected WebService service;
	//added these boolean fields to clean up the validation code in the constructor
	boolean isValidPassword; 
	boolean isValidUserID; 

	public StockInformation(int userID, String password, String symbol, WebService service) throws Exception {

		this.service = service;
		this.isValidPassword = checkPassword(password);
		this.isValidUserID = checkUserID(userID);

		//Checks symbol pattern and throws exception if it isn't
		String pattern = "^[a-zA-Z0-9]*$";
		if (symbol == null || !symbol.matches(pattern)) {
			throw new Exception("Incorrect symbol format");
		}

		//Runs if symbol pattern is ok but either password or userID is invalid
		else if (!isValidPassword || !isValidUserID) {
			this.symbol = "NA";
			this.companyName = "Not Allowed";
			this.currentPrice = 0;
			this.numberOfSharesOutstanding = 0;
			this.numberOfSharesOutstanding = 0;
		}

		//Runs if symbol, password and userID are all valid
		else {
			this.symbol = symbol;

			//stockInfoReturn is a placeholder variable intended to mimic the return of 
			//... calling the WebService getStockInfo implementation in order to assign proper
			//... values to the fields below.
			//For the purpose of the exercise, I didn't feel this needed over-complicating!
			String stockInfoReturn = "AA1, Nationwide, 89, 4";
			String[] stockInfo = stockInfoReturn.split(", ");
			this.companyName = stockInfo[1];
			this.currentPrice = Integer.parseInt(stockInfo[2]);
			this.numberOfSharesOutstanding = Integer.parseInt(stockInfo[3]);
		}
	}

	//alternate setter method, called and assigned in constructor
	public boolean checkPassword(String password) {
		if (password.matches("((?=.*[a-z])(?=.*d)(?=.*[@#$%])(?=.*[A-Z]).{6,16})") && (password.length() >= 8)) {
			return true;
		}
		return false;
	}
	
	//alternate setter method, called and assigned in constructor
	public boolean checkUserID(int userID) {
		if (userID > 0 && userID <= 9999) {
			return true;
		}
		return false;
	}
	//alternate getter method
	public boolean isValidUserID() {
		return isValidUserID;
	}

	public String getSymbol() {
		return symbol.toUpperCase();
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

	public int getCurrentPrice() {
		return currentPrice;
	}

	public int getNumberOfSharesOutstanding() {
		return numberOfSharesOutstanding;
	}

	//toString method implemented in specified format. See 'testGoodToString()' in test class.
	@Override
	public String toString() {
		return this.companyName + ", [" + this.symbol + "], " + this.currentPrice;
	}


}
