package main;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import lexer.MiniJavaParser;
import lexer.ParseException;
import syntaxtree.Program;
import typechecker.MiniJavaSymbolVisitor;
import typechecker.TypeCheckVisitor;

public class Main {
	
	public static void main(String[] args) throws ParseException {
		if (null instanceof syntaxtree.Type) {
			System.out.println("noo");
		}
		InputStream file;
		if (args.length == 0) {
			try {
				file = new FileInputStream("src/programs/fac.mjava");
			} catch (FileNotFoundException e) {
				System.err.println("Can't find file: '" + args[0] + "'");
				System.exit(1);
				return;
			}
		} else {
			file = new BufferedInputStream(System.in);
		}
		MiniJavaParser parser = new MiniJavaParser(file);
		Program p = parser.Program();
		MiniJavaSymbolVisitor symbolVisitor = new MiniJavaSymbolVisitor();
		symbolVisitor.visit(p);
		if (!symbolVisitor.isError()) {
			(new TypeCheckVisitor(symbolVisitor.getGlobals())).visit(p);
		}
	}

}
