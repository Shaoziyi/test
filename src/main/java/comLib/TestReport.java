package comLib;//com.hpe.gdc.

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;


public class TestReport extends TestListenerAdapter
{

	private String reportPath;

	@Override
	public void onStart(final ITestContext context)
	{
		final File htmlReportDir = new File("test-output/customizeHtml-report");
		if (!htmlReportDir.exists())
		{
			htmlReportDir.mkdirs();
		}
		final String reportName = formateDate() + ".html";
		reportPath = htmlReportDir + "/" + reportName;
		final File report = new File(htmlReportDir, reportName);
		if (report.exists())
		{
			try
			{
				report.createNewFile();
			}
			catch (final IOException e)
			{
				e.printStackTrace();
			}
		}
		final StringBuilder sb = new StringBuilder(
				"<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />"
						+ "<title >UI automation report </title></head><body style=\"background-color:#99FFCC;\">"
						+ "<div id=\"top\" align=\"center\"><p style=\"font-weight:bold;\">execution result list</p>"
						+ "<table width=\"90%\" height=\"80\" border=\"1\" align=\"center\" cellspacing=\"0\" rules=\"all\" style=\"table-layout:relative;\">"
						+ "<thead>" + "<tr>" + "<th>test case name </th>" + "<th>execution result</th>" + "</tr>" + "</thead>"
						+ "<tbody style=\"word-wrap:break-word;font-weight:bold;\" align=\"center\">");
		final String res = sb.toString();
		try
		{
			Files.write((Paths.get(reportPath)), res.getBytes("utf-8"));
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSuccess(final ITestResult result)
	{
		final StringBuilder sb = new StringBuilder("<tr><td>");
		sb.append(result.getMethod().getRealClass() + "." + result.getMethod().getMethodName());
		sb.append("</td><td><font color=\"green\">Passed</font></td></tr>");
		final String res = sb.toString();
		try
		{
			Files.write((Paths.get(reportPath)), res.getBytes("utf-8"), StandardOpenOption.APPEND);
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(final ITestResult result)
	{
		final StringBuilder sb = new StringBuilder("<tr><td>");
		sb.append(result.getMethod().getRealClass() + "." + result.getMethod().getMethodName());
		sb.append("</td><td><font color=\"yellow\">Skipped</font>");
		sb.append("<p align=\"left\">测试用例<font color=\"red\">跳过</font>，原因：<br>");
		sb.append("<br><a style=\"background-color:#CCCCCC;\">");
		final Throwable throwable = result.getThrowable();
		sb.append(throwable.getMessage());
		sb.append("</a></p></td></tr>");
		final String res = sb.toString();
		try
		{
			Files.write((Paths.get(reportPath)), res.getBytes("utf-8"), StandardOpenOption.APPEND);
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(final ITestResult result)
	{
		final StringBuilder sb = new StringBuilder("<tr><td>");
		sb.append(result.getMethod().getRealClass() + "." + result.getMethod().getMethodName());
		sb.append("</td><td><font color=\"red\">Failed</font><br>");
		sb.append("<p align=\"left\">测试用例执行<font color=\"red\">失败</font>，原因：<br>");
		sb.append("<br><a style=\"background-color:#CCCCCC;\">");
		final Throwable throwable = result.getThrowable();
		sb.append(throwable.getMessage());
		sb.append("</a></p></td></tr>");
		final String res = sb.toString();
		try
		{
			Files.write((Paths.get(reportPath)), res.getBytes("utf-8"), StandardOpenOption.APPEND);
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void onFinish(final ITestContext testContext)
	{
		final StringBuilder sb = new StringBuilder("</tbody></table><a href=http://blog.csdn.net/#top\">返回顶部</a></div></body>");
		sb.append("</html>");
		final String msg = sb.toString();
		try
		{
			Files.write((Paths.get(reportPath)), msg.getBytes("utf-8"), StandardOpenOption.APPEND);
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}

	}

	public static String formateDate()
	{
		final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		final Calendar cal = Calendar.getInstance();
		final Date date = cal.getTime();
		return sf.format(date);
	}
}
