package main;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import lexer.MiniJavaParser;
import lexer.ParseException;
import syntaxtree.Program;

public class Main {
	
	public static void main(String[] args) throws ParseException {
		InputStream file;
		if (args.length > 0) {
			try {
				file = new FileInputStream(args[0]);
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
	}

}
