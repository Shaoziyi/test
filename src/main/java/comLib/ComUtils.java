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

	//写日志
	public static boolean logWriter(final String log, final String filePathName, final Boolean isAppend)
	{
		boolean isSuccess = true;
		final String filePathConvert = filePathName.replaceAll("\\\\", "/");
		//过滤掉文件路径中的文件名
		final int index = filePathConvert.lastIndexOf("/");
		//获得文件路径字符串
		final String filePath = filePathConvert.substring(0, index);
		//创建文件夹
		final File fileFolder = new File(filePath);
		fileFolder.mkdir();
		//创建路径下的文件
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
		//将log写入文件
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

	//创建新文件
	public static boolean createNewFile(final String filePathName)
	{
		boolean isSuccess = true;
		//如有则将"\\"转为"/",没有则不产生任何变化
		final String filePathTurn = filePathName.replaceAll("\\\\", "/");
		//先过滤掉文件名
		final int index = filePathTurn.lastIndexOf("/");
		final String dir = filePathTurn.substring(0, index);
		//再创建文件夹
		final File fileForlder = new File(dir);
		isSuccess = fileForlder.mkdirs();
		//创建文件
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
	//瑕佺敤闈欐�佹柟娉曪紝杩愮敤鏃朵笉鐢ㄥ疄渚嬪寲
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
		//读取外部文件
		final InputStream inPutStream = new FileInputStream(filePath);
		//读取properties文件方法
		//		Properties  prop = new Properties();
		//		prop.load(inPutStream);
		//将文件流解析成poi文档
		//final POIFSFileSystem fileSystem = new POIFSFileSystem(inPutStream);
		//将POI文档解析成Excel工作簿
		final Workbook wb1 = OpenWorkook(inPutStream, filePath);

		Row row = null;
		//final Cell cell = null;
		//得到第一个工作簿
		final Sheet sheet = wb1.getSheet(sheetName);
		//得到第一行有多少列
		//final int totalCollumns = sheet.getRow(0).getPhysicalNumberOfCells();
		//得到最后一行数
		final int lasterRowNumber = sheet.getLastRowNum();

		//final String xPath = null;
		//把一个Sheet写入到Map
		//定一个Map, Key为Name，Value为ObjectList
		final Map<String, GUIObj> sheetMap = new HashMap<String, GUIObj>();
		//实例化一个对象， 存放一行obj的数据
		for (int i = 1; i <= lasterRowNumber; i++)
		{
			//从第一行开始遍历
			row = sheet.getRow(i);
			//获取每一行的type和expression
			//guiObj.setId(row.getCell(0).getStringCellValue().trim());
			guiObj.setName(row.getCell(1).getStringCellValue().trim());
			guiObj.setType(row.getCell(2).getStringCellValue().trim());
			guiObj.setActionType(row.getCell(3).getStringCellValue().trim());
			guiObj.setExpressText(row.getCell(4).getStringCellValue().trim());

			// 给Map复制， Key为对象Name ， value为数组
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
		//读取外部文件
		final InputStream inPutStream = new FileInputStream(filePath);
		//将文件流解析成poi文档
		final POIFSFileSystem fileSystem = new POIFSFileSystem(inPutStream);
		//将POI文档解析成Excel工作簿
		final Workbook wb1 = new HSSFWorkbook();
		Row row = null;
		//final Cell cell = null;
		//得到第一个工作簿
		final Sheet sheet = wb1.getSheet(sheetName);
		//得到第一行有多少列
		//final int totalCollumns = sheet.getRow(0).getPhysicalNumberOfCells();
		//得到最后一行数
		final int lasterRowNumber = sheet.getLastRowNum();

		//final String xPath = null;
		//把一个Sheet写入到Map
		//定一个Map, Key为Name，Value为ObjectList
		final Map<String, GUIObj> sheetMap = new HashMap<String, GUIObj>();
		//实例化一个对象， 存放一行obj的数据
		for (int i = 2; i <= lasterRowNumber; i++)
		{
			//从第一行开始遍历
			row = sheet.getRow(i);
			//获取每一行的type和expression
			guiObj.setId(row.getCell(1).getStringCellValue().trim());
			guiObj.setName(row.getCell(2).getStringCellValue().trim());
			guiObj.setActionType(row.getCell(3).getStringCellValue().trim());
			guiObj.setActionType(row.getCell(4).getStringCellValue().trim());
			guiObj.setExpressText(row.getCell(5).getStringCellValue().trim());

			// 给Map复制， Key为对象Name ， value为数组
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
			Connection con = null; //定义一个MYSQL链接对象
			Class.forName("com.mysql.jdbc.Driver"); //MYSQL驱动
			// conString= "jdbc:mysql://16.187.126.128:3306/hive", "hive", "hive"
			con = (Connection) DriverManager.getConnection(conString, user, pass); //链接本地MYSQL

			Statement stmt; //创建声明
			stmt = (Statement) con.createStatement();

			//查询数据并输出
			// String selectSql = "SELECT * FROM user";
			final ResultSet selectRes = stmt.executeQuery(selectSql);
			final Map<String, String> dbMap = new HashMap<String, String>();
			while (selectRes.next())
			{ //循环输出结果集
				final String username = selectRes.getString("name");
				final String password = selectRes.getString("email");
				System.out.print("\r\n\r\n");
				System.out.print("username:" + username + "password:" + password);
			}


			/*
			 *
			 * //新增一条数据 stmt.executeUpdate("INSERT INTO user (username, password) VALUES ('init', '123456')"); final
			 * ResultSet res = stmt.executeQuery("select LAST_INSERT_ID()"); int ret_id; if (res.next()) { ret_id =
			 * res.getInt(1); System.out.print(ret_id); }
			 *
			 * //删除一条数据 final String sql = "DELETE FROM user WHERE id = 1"; final long deleteRes = stmt.executeUpdate(sql);
			 * //如果为0则没有进行删除操作，如果大于0，则记录删除的条数 System.out.print("DELETE:" + deleteRes);
			 *
			 * //更新一条数据 final String updateSql = "UPDATE user SET username = 'xxxx' WHERE id = 2"; final long updateRes =
			 * stmt.executeUpdate(updateSql); System.out.print("UPDATE:" + updateRes);
			 *
			 */
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

	//截图方法
	public static void snapshot(final TakesScreenshot driver, final String filename)
	{
		final String currentpath = System.getProperty("user.dir"); // get current work folder
		//这个getScreenshotAs 方法是生成一个截图文件
		final File scrFile = driver.getScreenshotAs(OutputType.FILE);
		try
		{
			System.out.println("save snapshot path is:" + currentpath + "\\" + filename + "\n");
			//把生成的截图文件放本地保存，起名字
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
