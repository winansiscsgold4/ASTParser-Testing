import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Ben Scholer on 8/31/14.
 */
public class LineSplittingTesting {

	public static void main(String args[]) throws IOException {
		Scanner scanner = new Scanner(System.in);

		File[] files = new File[3];
		File f = new File("src/Address.java");
		String filePath = null;

		filePath = f.getAbsolutePath();

		StringBuilder fileData = new StringBuilder(1000);
		BufferedReader reader = new BufferedReader(new FileReader(filePath));

		char[] buf = new char[10];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}

		reader.close();

		String[] lines = fileData.toString().split(System.getProperty("line.separator"));
		System.out.print("Line Number: ");
		int num = Integer.parseInt(scanner.nextLine());
		System.out.println("Line " + num + " is -- " + lines[num - 1]);
	}

} //end of LineSplittingTesting class
