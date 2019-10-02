package error.errorLogRead;

import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlFileRead {
	public static void main(String[] args) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date today = new Date();
			String todayDate = format.format(today);
			System.out.println("today--"+todayDate);
			// to get title from server
			///usr/local/EIPS2_LOGS/JAVA_LOGS/2019-10-01/16/ERROR
			File[] returnedfolderName = getFolderName(todayDate);
			for (File folderName : returnedfolderName) {
				System.out.println(folderName);
			    String intialPathName = folderName+"/ERROR/";
				System.out.println("intialPathName=="+intialPathName);
			    File[] returnedFileName = readingErrorLogFile(intialPathName);
				for(File filePath  : returnedFileName) {
					 System.out.println(filePath.getName());
					 String fullPathName = intialPathName+filePath.getName();
					 readFile(fullPathName);
				}
			}
			
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
	/**
	 * @param readingErrorLogFile
	 * 
	 */
	static File[] readingErrorLogFile(String intialPathName) {
		try {
			File f = new File(intialPathName);
			File[] matchingFiles = f.listFiles(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.startsWith("error") && name.endsWith("html");
				}
			});
			return matchingFiles;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	static File[] getFolderName(String todayDate) {
		try {
			File f = new File("/usr/local/EIPS2_LOGS/JAVA_LOGS/"+todayDate+"/");
			File[] matchingFiles = f.listFiles(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return true;
				}
			});
			return matchingFiles;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	 
	/**
	 * @param readFile
	 * - Element => get(0) if only once (i.e. for table) and Elements
	 * - text()
	 * - index of data rows start from one not zero
	 * - select("Element Name or Tag Name") 
	 * 
	 * 
	 * 
	 */
	static void readFile(String fullPathName){
		try {
			Document doc = Jsoup.parse(new File(fullPathName),"utf-8"); // encoding standard
			System.out.println("File Title -"+doc.title());
			Element table = doc.select("table").get(0);
			Elements rows = table.select("tr");
			for (int i = 1 ; i<rows.size() ; i ++) {
				System.out.println("Class Name -"+rows.get(i).select("td").get(3).text());
			}
		} catch (Exception e) {
			e.getLocalizedMessage();
		}
	}
	

}
