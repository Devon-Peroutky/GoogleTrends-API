package main;

import java.net.URISyntaxException;

public class GoogleTrend {
	static String term;
	
	static int startM;
	static int startY;
	static int duration;
	
	static int year;
	
	static String recent;
	
	static String URL;
	static int choice =0;

	public GoogleTrend(String keyword) {
		this.term = keyword;
		choice = 1;
	}

	public GoogleTrend(String keyword, int specificYear) {
		this.term = keyword;
		this.year = specificYear;
		choice = 2;
	}

	public GoogleTrend(String keyword, String last) {
		this.term = keyword;
		this.recent =last.split(" ")[0];
		choice = 3;
	}

	public GoogleTrend(String keyword, String startDate, String endDate) {
		// 			4/2010, 9/2013
		this.term = keyword;
		int startMonth = Integer.parseInt(startDate.split("/")[0]);
		int startYear = Integer.parseInt(startDate.split("/")[1]);
		int endMonth = Integer.parseInt(endDate.split("/")[0]);
		int endYear = Integer.parseInt(endDate.split("/")[1]);
		
		this.duration = ((endYear-startYear)*12)+(endMonth-startMonth);
		this.startM=startMonth;
		this.startY=startYear;
		choice = 4;
	}
	
	public static void generateURL() {
		String baseURL="http://www.google.com/trends/trendsReport?hl=en-US&q="+term;
		
		switch (choice) {
			case 1:
				break;
			case 2:
				baseURL+="&date=1%2F"+year+"%2012m";
				break;
			case 3:
				baseURL+="&date=today%20"+recent+"-m";
			case 4:
				baseURL+="&date="+startM+"%2F"+startY+"%20"+duration+"m";
		}
		URL=baseURL+"&content=1&export=1";		
	}
	
	public String toString() {
		return URL;
	}

	public static void main(String[] args) throws URISyntaxException {
		System.out.println("----- Tests -----");
		/*
		Tab tab;
		Request request;
		Response response = null;
		
		String html = null;
	    byte[] fileContents = null;
	    */ 

		GoogleTrend gT1 = new GoogleTrend("Economics");
		gT1.generateURL();
		System.out.println(gT1.toString());
		/*
		GoogleTrend gT2 = new GoogleTrend("Numbers", 2011);
		gT2.generateURL();
		System.out.println(gT2.toString());
		
		GoogleTrend gT3 = new GoogleTrend("Sports", "9 months");
		gT3.generateURL();
		System.out.println(gT3.toString());
		
		GoogleTrend gT4 = new GoogleTrend("Paper", "7/2011", "5/2013");
		gT4.generateURL();
		System.out.println(gT4.toString());
		
		
		try {
			tab = new Prowser().createTab();
			request = new Request("http://www.google.com/trends/trendsReport?hl=en-US&q=Economics&content=1&export=1&key=AIzaSyAzgdOiFagRujMB4kr-vyRTbPw3V6d1bWw");
			response = tab.go(request);
			html = response.getPageSource(); // The html source code
			fileContents = response.getPageBytes();		
		} catch (URISyntaxException e) {
			System.out.println("Failed to created a request!!!");
			e.printStackTrace();
		}
		System.out.println(html);
		for (byte i: fileContents) { 
			System.out.println(i);
		}
		*/
	}

}