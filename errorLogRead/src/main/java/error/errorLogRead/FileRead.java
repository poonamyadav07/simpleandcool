package error.errorLogRead;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class FileRead {
	
    public void readFile()  {
		try {
		    System.out.println("Please Provide The file path where you mapped all your error classs name");
			Scanner sc = new Scanner(System.in);
			String filePath = sc.nextLine();
		    File myFile = new File(filePath);
			System.out.println("Attempting to read  file from Location "+myFile.getCanonicalPath());
			BufferedReader reader =  new BufferedReader(new FileReader(myFile.getCanonicalPath()));
			String line  = reader.readLine();
			while(line!=null) {
				System.out.println(line);
				ErrorClassName.errorClass.add(line);
				line  = reader.readLine();
			}
			System.out.println("WholeList"+ErrorClassName.errorClass);
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
}
