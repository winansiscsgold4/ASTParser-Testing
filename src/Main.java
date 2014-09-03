import org.eclipse.jdt.core.dom.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Main {

	static String lines[];
	static String source;
	static int methodCounter = 0;

	//use ASTParse to parse string
	public static void parse(String str) {

		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(str.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);

		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);

		cu.accept(new ASTVisitor() {

			Set names = new HashSet();


			@Override
			public boolean visit(VariableDeclarationFragment node) {
				SimpleName name = node.getName();
				String type = "";

				String lineNumber = "" + cu.getLineNumber(name.getStartPosition());
				this.names.add(name.getIdentifier());
				String line = lines[Integer.parseInt(lineNumber) - 1];

				/*  Other Object types
					NumberForamater
					Scanner
					ArrayList
					List

				 */
//				if (line.contains("byte")) type = "byte";
//				else if (line.contains("short")) type = "short";
//				else if (line.contains("int")) type = "int";
//				else if (line.contains("long")) type = "long";
//				else if (line.contains("float")) type = "float";
//				else if (line.contains("double")) type = "double";
//				else if (line.contains("char")) type = "char";
//				else if (line.contains("String")) type = "String";
//				else if (line.contains("boolean")) type = "boolean";
//				else if (line.contains("NumberFormat")) type = "NumberFormat";
//				else if (line.contains("Scanner")) type = "Scanner";
//				else if (line.contains("ArrayList")) type = "ArrayList";
//				else if (line.contains("HashMap")) type = "HashMap";
//				else if (line.contains("CSVReader")) type = "CSVReader";
//				else if (line.contains("Matcher")) type = "Matcher";
//				else if (line.contains("Pattern")) type = "Pattern";
//				else if (line.contains("FileOutputStream")) type = "FileOutputStream";
//				else if (line.contains("FileInputStream")) type = "FileInputStream";
//				else if (line.contains("ObjectOutputStream")) type = "ObjectOutputStream";
//				else if (line.contains("ObjectInputStream")) type = "ObjectInputStream";
//				else if (line.contains("Graphics2D")) type = "Graphics2D";
//				else if (line.contains("Color")) type = "Color";
//				else if (line.contains("File")) type = "File";
//				else if (line.contains("PrinterJob")) type = "PrinterJob";

				System.out.println("Declaration of \"" + name + "\", type " + type + " on line " + lineNumber);
				return false; // do not continue
			}

//			@Override
//			public boolean visit(SimpleName node) {
////				if (this.names.contains(node.getIdentifier())) {
////					System.out.println("Usage of '" + node + "' at line "
////							+ cu.getLineNumber(node.getStartPosition()));
////				}
//				return true;
//			}


//			@Override
//			public boolean visit(ReturnStatement node) {
//				System.out.println("Return statment found on line " + cu.getColumnNumber(node.getStartPosition()));
//
//				return true;
//			}

//			@Override
//			public boolean visit(MethodDeclaration node) {
//				if (methodCounter <= 5 && !node.isConstructor()) {
//					System.out.println();
//					int start = node.getStartPosition();
//					int end = start + node.getLength();
//					String comment = source.substring(start, end);
//					System.out.println("Original method\n" + comment);
//					System.out.println();
//					System.out.println();
//					String comDesc = " //end of " + node.getName() + " method";
//					System.out.println("Method should say\n" + "\t" + comment + comDesc);
//					System.out.println();
//					methodCounter++;
//				} else if (node.isConstructor()) {
//					if (node.parameters().size() == 0) {
//						int start = node.getStartPosition();
//						int end = start + node.getLength();
//						String comment = source.substring(start, end);
//						System.out.println("DEFAULT CONSTRUCTOR\n" + comment);
//						System.out.println();
//						System.out.println();
//					} else {
//						int start = node.getStartPosition();
//						int end = start + node.getLength();
//						String comment = source.substring(start, end);
//						System.out.println("NON-DEFAULT CONSTRUCTOR\n" + comment);
//						System.out.println();
//						System.out.println();
//					}
//				}
//				return true;
//			}
		});

	}

	//read file content into a string

	/**
	 * This method reads file content into a String.
	 *
	 * @param filePath The file path to be used.
	 * @return The file content
	 */
	public static String readFileToString(String filePath) throws IOException {
		StringBuilder fileData = new StringBuilder(1000);
		BufferedReader reader = new BufferedReader(new FileReader(filePath));

		char[] buf = new char[10];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
//			System.out.println(numRead);
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}

		reader.close();
		source = fileData.toString();
		lines = fileData.toString().split(System.getProperty("line.separator"));
		return fileData.toString();
	}

	//loop directory to get file list
	public static void ParseFilesInDir() throws IOException {
//		File dirs = new File(".");
//		String dirPath = dirs.getCanonicalPath() + File.separator + "src" + File.separator;
//
//		File root = new File(dirPath);
//		//System.out.println(rootDir.listFiles());
//		File[] files = root.listFiles();
//		String filePath = null;
//
//		for (File f : files) {
//			filePath = f.getAbsolutePath();
//			if (f.isFile()) {
//				parse(readFileToString(filePath));
//			}
//		}
	}

	public static void main(String[] args) throws IOException {
//		ParseFilesInDir();

		File[] files = new File[3];
		File f = new File("src/Address.java");
		String filePath = null;


		filePath = f.getAbsolutePath();
		parse(readFileToString(filePath));
	}
}