package pcomp.prolog.ast.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pcomp.prolog.ast.Decl;
import pcomp.prolog.ast.DeclAssertion;
import pcomp.prolog.ast.Program;
import pcomp.prolog.parser.PrologParser;

public class Jalon3 {
	
	public static void main(String[] args) throws IOException {
		Program prog1 = PrologParser.parseFile("tests_jalon_pl/interprete3_test1.pl");
		List<Decl> decls = prog1.getDeclarations();
		List<Decl> renamed = new ArrayList<>();
		for (Decl d : decls) {
			if (d instanceof DeclAssertion) {
				renamed.add(((DeclAssertion) d).rename(1));
			}
		}
		System.out.println(renamed);
	}
	
	

}
