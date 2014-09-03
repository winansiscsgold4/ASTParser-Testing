import org.eclipse.jdt.core.dom.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Ben Scholer on 9/3/14.
 */
public class NewMain {

	static String lines[];
	static String source;

	public static void main(String args[]) throws IOException {
		File f = new File("src/Address.java");
		String filePath = null;

		filePath = f.getAbsolutePath();

		readFileToString(filePath);

		// Initialize ASTParser
		ASTParser parser = ASTParser.newParser(AST.JLS3); //initialize
		parser.setKind(ASTParser.K_COMPILATION_UNIT);     //to parse compilation unit
		parser.setSource(source.toCharArray());          //content is a string which stores the java source
		parser.setResolveBindings(true);
		CompilationUnit result = (CompilationUnit) parser.createAST(null);

		//show import declarations in order
		List importList = result.imports();
		System.out.println("import:");
		for (Object obj : importList) {
			ImportDeclaration importDec = (ImportDeclaration) obj;
			System.out.println(importDec.getName());
		}

		//show class name
		List types = result.types();
		TypeDeclaration typeDec = (TypeDeclaration) types.get(0);
		System.out.println("className:" + typeDec.getName());

		//show fields
		FieldDeclaration fieldDec[] = typeDec.getFields();
		System.out.println("Fields:");
		for (FieldDeclaration field : fieldDec) {
			System.out.println("Field fragment:" + field.fragments());
			System.out.println("Field type:" + field.getType());
		}

		//show methods
		MethodDeclaration methodDec[] = typeDec.getMethods();
		System.out.println("Method:");
		for (MethodDeclaration method : methodDec) {
			//get method name
			SimpleName methodName = method.getName();
			System.out.println("method name:" + methodName);

			//get method parameters
			List param = method.parameters();
			System.out.println("method parameters:" + param);

			//get method return type
			Type returnType = method.getReturnType2();
			System.out.println("method return type:" + returnType);

			//get method body
			Block body=method.getBody();
			List statements=body.statements();   //get the statements of the method body
			Iterator iter=statements.iterator();
			while(iter.hasNext())
			{
				//get each statement
				Statement stmt=(Statement)iter.next();

				if(stmt instanceof ExpressionStatement)
			{
				ExpressionStatement expressStmt=(ExpressionStatement) stmt;
				Expression express=expressStmt.getExpression();

				if(express instanceof Assignment)
				{
					Assignment assign=(Assignment)express;
					System.out.println("LHS:"+assign.getLeftHandSide()+"; ");
					System.out.println("Op:"+assign.getOperator()+"; ");
					System.out.println("RHS:"+assign.getRightHandSide());

				}
				else if(express instanceof MethodInvocation)
				{
					MethodInvocation mi=(MethodInvocation) express;
					System.out.println("invocation name:"+mi.getName());
					System.out.println("invocation exp:"+mi.getExpression());
					System.out.println("invocation arg:"+mi.arguments());

				}
				System.out.println();

			}
				if(stmt instanceof ExpressionStatement)
				{
					ExpressionStatement expressStmt=(ExpressionStatement) stmt;
					Expression express=expressStmt.getExpression();

					if(express instanceof Assignment)
					{
						Assignment assign=(Assignment)express;
						System.out.println("LHS:"+assign.getLeftHandSide()+"; ");
						System.out.println("Op:"+assign.getOperator()+"; ");
						System.out.println("RHS:"+assign.getRightHandSide());

					}
					else if(express instanceof MethodInvocation)
					{
						MethodInvocation mi=(MethodInvocation) express;
						System.out.println("invocation name:"+mi.getName());
						System.out.println("invocation exp:"+mi.getExpression());
						System.out.println("invocation arg:"+mi.arguments());

					}
					System.out.println();

				}
			}


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

} //end of NewMain class
