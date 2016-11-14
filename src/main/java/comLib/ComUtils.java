package comLib;//com.hpe.gdc.

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import io.appium.java_client.android.AndroidDriver;


/**
 *
 *
 * //import org.apache.poi.ss.usermodel.WorkbookFactory; /**
 *
 */
public class ComUtils
{

	//
	public static boolean logWriter(final String log, final String filePathName, final Boolean isAppend)
	{
		boolean isSuccess = true;
		final String filePathConvert = filePathName.replaceAll("\\\\", "/");
		//
		final int index = filePathConvert.lastIndexOf("/");
		//
		final String filePath = filePathConvert.substring(0, index);
		//
		final File fileFolder = new File(filePath);
		fileFolder.mkdir();
		//
		File file = null;
		try
		{
			file = new File(filePathName);
			file.createNewFile();
		}
		catch (final IOException e)
		{
			isSuccess = false;
		}
		//
		FileWriter fileWriter = null;

		try
		{
			fileWriter = new FileWriter(file, isAppend);
			fileWriter.write(log);
			fileWriter.flush();
		}
		catch (final IOException e)
		{
			isSuccess = false;
		}
		finally
		{
			try
			{
				fileWriter.close();
			}
			catch (final IOException e)
			{
				//e.printStackTrace();
			}
		}
		return isSuccess;
	}

	//
	public static boolean createNewFile(final String filePathName)
	{
		boolean isSuccess = true;
		//
		final String filePathTurn = filePathName.replaceAll("\\\\", "/");
		//
		final int index = filePathTurn.lastIndexOf("/");
		final String dir = filePathTurn.substring(0, index);
		//
		final File fileForlder = new File(dir);
		isSuccess = fileForlder.mkdirs();
		//create the files
		final File file = new File(filePathTurn);
		try
		{
			isSuccess = file.createNewFile();
		}
		catch (final IOException e)
		{
			isSuccess = false;
			//e.printStackTrace();
		}
		return isSuccess;
	}


	// TODO Auto-generated method stub

	/**
	 * @param expectedString
	 * @param actualString
	 * @return
	 */
	@SuppressWarnings("javadoc")
	public static boolean compareValue(final String expectedString, final String actualString)
	{
		boolean result;
		final String exp = expectedString, act = actualString;

		if (exp.equals(act))
		{
			result = true;
		}
		else
		{
			result = false;
		}
		return result;

	}

	//create the excel workbook
	/**
	 * @param sheetName
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("javadoc")
	public static Workbook OpenWorkook(final InputStream inPutStream1, final String fileName) throws IOException
	{
		Workbook wb = null;
		if (fileName.endsWith("xlsx"))
		{
			wb = new XSSFWorkbook(inPutStream1);
		}
		else
		{
			wb = new HSSFWorkbook(inPutStream1);//Excel 2003
		}
		return wb;
	}

	public static Map<String, GUIObj> createWorkBook(final String sheetName, final String filePath) throws IOException
	{
		//
		final GUIObj guiObj = new GUIObj();
		//final FileOutputStream fileOut = new FileOutputStream("excel/workbook.xls");
		//read the external files
		final InputStream inPutStream = new FileInputStream(filePath);
		//read the properties file function
		//		Properties  prop = new Properties();
		//		prop.load(inPutStream);
		//convert the filestream to file system
		//final POIFSFileSystem fileSystem = new POIFSFileSystem(inPutStream);
		//convert the POI file stream to workbook
		final Workbook wb1 = OpenWorkook(inPutStream, filePath);

		Row row = null;
		//final Cell cell = null;
		//get the first workbook
		final Sheet sheet = wb1.getSheet(sheetName);
		//get the count of collumn the first row
		//final int totalCollumns = sheet.getRow(0).getPhysicalNumberOfCells();
		//get the number of the final row
		final int lasterRowNumber = sheet.getLastRowNum();

		//final String xPath = null;
		//read the first Sheet write to the Map
		//define a Map, Key is Name，Value is ObjectList
		final Map<String, GUIObj> sheetMap = new HashMap<String, GUIObj>();
		//instantiate a, save the first row of obj in
		for (int i = 1; i <= lasterRowNumber; i++)
		{
			//iteration from the first row
			row = sheet.getRow(i);
			//get the type and expression for each row
			//guiObj.setId(row.getCell(0).getStringCellValue().trim());
			guiObj.setName(row.getCell(1).getStringCellValue().trim());
			guiObj.setType(row.getCell(2).getStringCellValue().trim());
			guiObj.setActionType(row.getCell(3).getStringCellValue().trim());
			guiObj.setExpressText(row.getCell(4).getStringCellValue().trim());

			//
			//			final String[] key = new String[lasterRowNumber];
			//			key[i] = guiObj.getName();
			sheetMap.put(guiObj.getName(), guiObj);
		}
		wb1.close();
		return sheetMap;
	}

	public static Map<String, GUIObj> createHSSFWorkBook(final String sheetName, final String filePath) throws IOException
	{
		//
		final GUIObj guiObj = new GUIObj();
		//final FileOutputStream fileOut = new FileOutputStream("excel/workbook.xls");
		//read the external file
		final InputStream inPutStream = new FileInputStream(filePath);
		//
		final POIFSFileSystem fileSystem = new POIFSFileSystem(inPutStream);
		//
		final Workbook wb1 = new HSSFWorkbook();
		Row row = null;
		//final Cell cell = null;
		//
		final Sheet sheet = wb1.getSheet(sheetName);
		//
		//final int totalCollumns = sheet.getRow(0).getPhysicalNumberOfCells();
		//
		final int lasterRowNumber = sheet.getLastRowNum();

		//final String xPath = null;
		//read the first sheet to the map
		//define a Map, Key is Name，Value is ObjectList
		final Map<String, GUIObj> sheetMap = new HashMap<String, GUIObj>();
		//instantiate a , save the first row of obj in
		for (int i = 2; i <= lasterRowNumber; i++)
		{
			//
			row = sheet.getRow(i);
			//get the type and expression for each row
			guiObj.setId(row.getCell(1).getStringCellValue().trim());
			guiObj.setName(row.getCell(2).getStringCellValue().trim());
			guiObj.setActionType(row.getCell(3).getStringCellValue().trim());
			guiObj.setActionType(row.getCell(4).getStringCellValue().trim());
			guiObj.setExpressText(row.getCell(5).getStringCellValue().trim());

			//
			//			final String[] key = new String[lasterRowNumber];
			//			key[i] = guiObj.getName();
			sheetMap.put(guiObj.getName(), guiObj);
		}
		wb1.close();
		return sheetMap;
	}

	public static Map<String, String> connectDB(final String conString, final String user, final String pass,
			final String selectSql)
	{
		try
		{
			Connection con = null; //
			Class.forName("com.mysql.jdbc.Driver"); //MYSQL driver
			// conString= "jdbc:mysql://16.187.126.128:3306/hive", "hive", "hive"
			con = (Connection) DriverManager.getConnection(conString, user, pass); //链接本地MYSQL

			Statement stmt; //create the statement
			stmt = (Statement) con.createStatement();

			//
			// String selectSql = "SELECT * FROM user";
			final ResultSet selectRes = stmt.executeQuery(selectSql);
			final Map<String, String> dbMap = new HashMap<String, String>();
			while (selectRes.next())
			{ //output the result
				final String username = selectRes.getString("name");
				final String password = selectRes.getString("email");
				System.out.print("\r\n\r\n");
				System.out.print("username:" + username + "password:" + password);
			}


		}
		catch (final Exception e)
		{
			System.out.print("MYSQL ERROR:" + e.getMessage());
		}
		return null;

	}

	public static void Reporting() throws FileNotFoundException
	{
		final StringBuilder sb = new StringBuilder();
		//		final Properties fileProperties = System.getProperties("file");
		//		final Properties sqlProperties = System.getProperties().;
		final java.io.PrintStream printStream = new java.io.PrintStream(new FileOutputStream("report.html"));
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title>每日运营报表</title>");
		sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		sb.append("<style type=\"text/css\">");
		sb.append("TABLE{border-collapse:collapse;border-left:solid 1 #000000; border-top:solid 1 #000000;padding:5px;}");
		sb.append("TH{border-right:solid 1 #000000;border-bottom:solid 1 #000000;}");
		sb.append("TD{font:normal;border-right:solid 1 #000000;border-bottom:solid 1 #000000;}");
		sb.append("</style></head>");
		sb.append("<body bgcolor=\"#FFF8DC\">");
		sb.append("<div align=\"center\">");
		sb.append("<br/>");
		sb.append("<br/>");
		//		final List<Map<String, Object>> result1 = getRpt(sqlProperties.getProperty("sql1"));
		//		for (final Map.Entry<String, Object> m : result1.get(0).entrySet())
		//		{
		//			sb.append(fileProperties.getProperty("file1"));
		//			sb.append(m.getValue());
		//		}
		sb.append("<br/><br/>");
	}

	//screenshot function
	public static void snapshot(final TakesScreenshot driver, final String filename)
	{
		final String currentpath = System.getProperty("user.dir"); // get current work folder
		//getScreenshotAs generate a screenshot file
		final File scrFile = driver.getScreenshotAs(OutputType.FILE);
		try
		{
			System.out.println("save snapshot path is:" + currentpath + "\\" + filename + "\n");
			//name the file and save
			FileUtils.copyFile(scrFile, new File(currentpath + "\\" + "screenshot" + "\\" + filename)); // save screenshot
		}
		catch (final IOException e)
		{
			System.out.println("Cannot take screenshot");
			e.printStackTrace();
		}
		finally
		{
			System.out.println("already saved screenshot in:" + currentpath);
		}
	}

	public static WebElement getObjElement(final AndroidDriver driver, final String objName, final Map<String, GUIObj> objMap)
	{

		WebElement element = null;
		objName.trim();
		final String objType = objMap.get(objName).getType().trim();
		final String objText = objMap.get(objName).getExpressText().trim();
		switch (objType)
		{
			case "id":
				element = driver.findElementById(objText);
				break;

			case "name":
				element = driver.findElementByName(objText);
				break;
		}
		return element;

	}

	public static WebElement getObjElementByName(final AndroidDriver driver, final String objText, final int objType)
	{
		final WebElement element = null;

		return element;
	}

	public static void getObjFromFile(final String objNmae, final String fileName, final String filePath)
	{

	}
}
