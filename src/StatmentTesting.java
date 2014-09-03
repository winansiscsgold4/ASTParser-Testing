import org.eclipse.jdt.core.dom.*;

/**
 * Created by Ben Scholer on 8/30/14.
 */
public class StatmentTesting {

	static String source = "int i = 5;\nint j = i + 4;";

	public static void main(String[] args) {

		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(source.toCharArray());

		parser.setKind(ASTParser.K_STATEMENTS);

		Block block = (Block) parser.createAST(null);

		//here can access the first element of the returned statement list
		String str = block.statements().get(0).toString();

		System.out.println(str);

		block.accept(new ASTVisitor() {

			public boolean visit(SimpleName node) {

				System.out.println("Name: " + node.getFullyQualifiedName());

				return true;
			}

		});
	}

} //end of StatmentTesting class
