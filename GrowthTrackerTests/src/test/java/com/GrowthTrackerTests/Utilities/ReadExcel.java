package com.GrowthTrackerTests.Utilities;

import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import com.Constants.*;
import com.GrowthTrackerTests.Base.CommonToAllTests;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class ReadExcel extends CommonToAllTests

{

	public String path;
	public FileInputStream fis = null;
	public FileInputStream fout = null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;
	String baseDir;
	String XLFilePath;
	String sheetName;
	static Properties properties;
	Integer rowCount, colCount;
	String[][] arrayExcelData = null;
	public ReadExcel(String path) {

		properties = new Properties();
		String baseDir = System.getProperty("user.dir");
		System.out.println(baseDir);
		XLFilePath = baseDir + IConstants.XLFilePath;

		this.path = XLFilePath;
		try {
			fis = new FileInputStream(this.path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
		} catch (Exception e) {

			e.printStackTrace();
		}

		System.out.println(sheet.getSheetName());

	}

	public ReadExcel() {
		properties = new Properties();
		baseDir = System.getProperty("user.dir");
		System.out.println(baseDir);
		XLFilePath = baseDir + IConstants.XLFilePath;

		this.path = XLFilePath;
		try {
			fis = new FileInputStream(this.path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
		} catch (Exception e) {

			e.printStackTrace();
		}

		sheetName=sheet.getSheetName();

	
	}

	
	// Excel reading
	
	@DataProvider (name = "data-provider-ForUsernameAndPassword")	
		public Object[][] getUserNamePass() 
		{
		
			
			
			List<String> data = null;
			data = new ArrayList<String>();
			for (int i = 0; i < getColumnCount(sheetName) - 1; i++) {

				for (int j = 2; j <= getRowCount(sheetName); j++) {

					// Adding elements to the Map
					// using standard put() method
					// map.put("vishal", 10);

					data.add(getCellData(sheet.getSheetName(), i, j));

				}
			}

			int size = data.size();
			// Creating new list and inserting values which is
			// returned by List.subList() method
			List<String> first = new ArrayList<>(data.subList(0, (size) / 2));
			List<String> second = new ArrayList<>(data.subList((size) / 2, size));
			// Returning an List of array
//		        return new List[] { first, second };
			
			 int i = 0;
			 System.out.println(first.size());			 
		        // If variable value is lesser than
		        // value indicating size of List
		        while (i < first.size()) {
		 
		            // Print element of list
		            System.out.println(first.get(i));
		 
		            // Increase variable count by 1
		            i++;
		        }
		        int rowCount=getRowCount(sheetName);
		        int colCount=getColumnCount(sheetName)-1;
		        System.out.println("Col count - "+colCount);
		        arrayExcelData = new String[rowCount][colCount];
		    
			for(int j=0;j<first.size();j++)
			{
				arrayExcelData[j][0]=first.get(j).toString();
				System.out.println(arrayExcelData[j][0]);
				
			}
			
			
			for(int j=0;j<second.size();j++)
			{
				arrayExcelData[j][1]=second.get(j).toString();
				System.out.println(arrayExcelData[j][1]);
				
			}
			
			System.out.println(arrayExcelData);
			
			return arrayExcelData;

		}

	
	public Map<String, String> getPRFXLData(String sheetName, String testName) 
	{
		Map<String, String> mapTestData = new HashMap<String, String>();
	  
		try 
		{
			Fillo fillo = new Fillo();
			
			
			String query = "SELECT * FROM "+sheetName+" WHERE Test_Scenario_Name='"+testName+"'";
			String Role,ProspectDescription,PitchDescription,WonWithoutPitchDescription,
			ProspectPageExpectedTitle,PitchPageExpectedTitle,WonWithoutPitchPageTitle,TooltipsOnForm,
			Country,City,Agency,GHCName,LevelOfOpportunity,Relationship,Industry,SubIndustry,B2B,Status,
			Scope,SourceOfBillings,SourceOfOpportunity,WPPAgency;
			
			System.out.println(query);
			Connection connection = fillo.getConnection(XLFilePath);
			Recordset recordset = connection.executeQuery(query);
			int numberOfRows = recordset.getCount();
			System.out.println("Size total rows fetched from XL: " + numberOfRows);			
			
			while(recordset.next())
			{
				ProspectDescription=recordset.getField("ProspectDescription");
				PitchDescription= recordset.getField("PitchDescription");			
				WonWithoutPitchDescription=recordset.getField("WonWithoutPitchDescription");
				
				ProspectPageExpectedTitle=recordset.getField("ProspectPageExpectedTitle");
				PitchPageExpectedTitle=recordset.getField("PitchPageExpectedTitle");
				WonWithoutPitchPageTitle=recordset.getField("WonWithoutPitchPageTitle");
				TooltipsOnForm=recordset.getField("TooltipsOnForm");								
				Role=recordset.getField("UserRole");
				
				
				
			 
				mapTestData.put("ProspectDescription", ProspectDescription);
				mapTestData.put("PitchDescription", PitchDescription);
				mapTestData.put("WonWithoutPitchDescription", WonWithoutPitchDescription);
				
				mapTestData.put("ProspectPageExpectedTitle", ProspectPageExpectedTitle);
				mapTestData.put("PitchPageExpectedTitle", PitchPageExpectedTitle);
				mapTestData.put("WonWithoutPitchPageTitle", WonWithoutPitchPageTitle);
				
				mapTestData.put("Role", Role);
				mapTestData.put("TooltipsOnForm", TooltipsOnForm);
				
			}
			
			recordset.close();
			connection.close();
			
			
			
			
		} catch (FilloException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapTestData;		
		

	}
	
	
	
	
	
	public Map<String, String> getAddOpportunityXLData(String sheetName, String testName) {
		Map<String, String> mapTestData = new HashMap<String, String>();

		try {
			Fillo fillo = new Fillo();

			String query = "SELECT * FROM " + sheetName + " WHERE Test_Scenario_Name='" + testName + "'";
			String Role, Country, City, Agency, GHCName, LevelOfOpportunity, Relationship, Industry, SubIndustry, B2B,
					Status, Scope, SourceOfBillings, SourceOfOpportunity, WPPAgency,EstimatedAnnualBillingsLocal;

			System.out.println(query);
			Connection connection = fillo.getConnection(XLFilePath);
			Recordset recordset = connection.executeQuery(query);
			int numberOfRows = recordset.getCount();
			System.out.println("Size total rows fetched from XL: " + numberOfRows);

			while (recordset.next()) {
				Country = recordset.getField("Country");
				City = recordset.getField("City");
				Agency = recordset.getField("Agency");

				GHCName = recordset.getField("GHCName");
				LevelOfOpportunity = recordset.getField("LevelOfOpportunity");
				Relationship = recordset.getField("Relationship");
				Industry = recordset.getField("Industry");
				SubIndustry = recordset.getField("SubIndustry");
				B2B = recordset.getField("B2B");
				Status = recordset.getField("Status");
				Scope = recordset.getField("Scope");
				SourceOfBillings = recordset.getField("SourceOfBillings");
				SourceOfOpportunity = recordset.getField("SourceOfOpportunity");
				WPPAgency = recordset.getField("WPPAgency");
				EstimatedAnnualBillingsLocal = recordset.getField("EstimatedAnnualBillingsLocal");
				
				
				Role = recordset.getField("UserRole");

				mapTestData.put("Country", Country);
				mapTestData.put("City", City);
				mapTestData.put("Agency", Agency);

				mapTestData.put("GHCName", GHCName);
				mapTestData.put("LevelOfOpportunity", LevelOfOpportunity);
				mapTestData.put("Relationship", Relationship);

				mapTestData.put("Role", Role);
				mapTestData.put("Industry", Industry);
				mapTestData.put("SubIndustry", SubIndustry);
				mapTestData.put("B2B", B2B);
				mapTestData.put("Status", Status);
				mapTestData.put("Scope", Scope);
				mapTestData.put("SourceOfBillings", SourceOfBillings);
				mapTestData.put("SourceOfOpportunity", SourceOfOpportunity);
				mapTestData.put("WPPAgency", WPPAgency);
				mapTestData.put("EstimatedAnnualBillingsLocal", EstimatedAnnualBillingsLocal);

			}

			recordset.close();
			connection.close();

		} catch (FilloException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapTestData;

	}
	  
	 	
	
	public Map<String, String> getUserXLData(String sheetName, String testName) 
	{
		Map<String, String> mapTestData = new HashMap<String, String>();
	  
		try 
		{
			Fillo fillo = new Fillo();
			
			
			String query = "SELECT * FROM "+sheetName+" WHERE Test_Scenario_Name='"+testName+"'";
			String Name, email, userType, clients,toastMessage,ConfirmationMsg;
			System.out.println(query);
			Connection connection = fillo.getConnection(XLFilePath);
			Recordset recordset = connection.executeQuery(query);
			int numberOfRows = recordset.getCount();
			System.out.println("Size: " + numberOfRows);			
			
			while(recordset.next())
			{
			 userType=recordset.getField("UserType");
			 Name= recordset.getField("Name");
				
				
			 email= recordset.getField("Email");
			
			 clients= recordset.getField("Client");
			 toastMessage=recordset.getField("ToastMessage");
			 toastMessage=recordset.getField("ToastMessage");
			 ConfirmationMsg=recordset.getField("ConfirmationMsg");
				mapTestData.put("UserType", userType);
				mapTestData.put("Name", Name);
				mapTestData.put("Email", email);
				mapTestData.put("Client", clients);
				mapTestData.put("ToastMessage", toastMessage);
				mapTestData.put("ConfirmationMsg", ConfirmationMsg);
			 
			}
			recordset.close();
			connection.close();
			
			
			
			
		} catch (FilloException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapTestData;
	
		
		

	}
	
	
	
	public Map<String, String> getSpecifiedUserFromRole(String sheetName, String userRole) 
	{
		Map<String, String> mapTestData = new HashMap<String, String>();
	  
		try 
		{
			Fillo fillo = new Fillo();
			
			
			String query = "SELECT * FROM "+sheetName+" WHERE Role='"+userRole+"'";
			String userName, password;
			System.out.println(query);
			Connection connection = fillo.getConnection(XLFilePath);
			Recordset recordset = connection.executeQuery(query);
			int numberOfRows = recordset.getCount();
			System.out.println("Size: " + numberOfRows);			
			
			while(recordset.next())
			{
			 userName=recordset.getField("UserName");
			 password= recordset.getField("Password");
				
			

			 mapTestData.put("userName", userName);
			 mapTestData.put("password", password);

			 
			}
			recordset.close();
			connection.close();
			
			
			
			
		} catch (FilloException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapTestData;
	
		
		

	}
	

	
		// returns the data from a cell
		public String getCellData(String sheetName, int colNum, int rowNum) {
			try {
				if (rowNum <= 0)
					return "";

				int index = workbook.getSheetIndex(sheetName);

				if (index == -1)
					return "";

				sheet = workbook.getSheetAt(index);
				row = sheet.getRow(rowNum - 1);
				if (row == null)
					return "";
				cell = row.getCell(colNum);
				if (cell == null)
					return "";

			} catch (Exception e) {

				e.printStackTrace();
				return "row " + rowNum + " or column " + colNum + " does not exist  in xls";
			}
			return cell.toString();
		}

		public int getRowCount(String sheetName) {
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1)
				return 0;
			else {
				sheet = workbook.getSheetAt(index);
				int number = sheet.getLastRowNum() + 1;

				return number;

			}

		}

		public int getColumnCount(String sheetName) {
			// check if sheet exists
			if (!isSheetExist(sheetName))
				return -1;

			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(0);

			if (row == null)
				return -1;

			return row.getLastCellNum();

		}

		// find whether sheets exists
		public boolean isSheetExist(String sheetName) {
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1) {
				index = workbook.getSheetIndex(sheetName.toUpperCase());
				if (index == -1)
					return false;
				else
					return true;
			} else
				return true;
		}


}
