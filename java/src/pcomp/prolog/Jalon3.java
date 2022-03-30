package pcomp.prolog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pcomp.prolog.ast.Decl;
import pcomp.prolog.ast.DeclAssertion;
import pcomp.prolog.ast.Environnement;
import pcomp.prolog.ast.Interprete;
import pcomp.prolog.ast.Predicate;
import pcomp.prolog.ast.Program;
import pcomp.prolog.ast.VisitorDecl;
import pcomp.prolog.ast.excep.NoSolutionException;
import pcomp.prolog.parser.PrologParser;

public class Jalon3 {
	
	public static void main(String[] args) throws IOException {
		Program prog1OK = PrologParser.parseFile("tests_jalon_pl/interprete3_test1.pl");
		Program prog1NOK = PrologParser.parseFile("tests_jalon_pl/interprete3_test2.pl");
		Program prog2NOK = PrologParser.parseFile("tests_jalon_pl/interprete3_test3.pl");
		Program prog2OK = PrologParser.parseFile("tests_jalon_pl/interprete3_test5.pl");
		Program prog3OK = PrologParser.parseFile("tests_jalon_pl/interprete3_test4.pl");
		
		try {
			System.out.println(prog1OK);
			Environnement env1OK = Interprete.interprete3(prog1OK);
			System.out.println("Environnement obtenu : "+env1OK);
		} catch (NoSolutionException excep) {
			System.out.println(excep);
		}
		try {
			System.out.println(prog1NOK);
			Environnement env1NOK = Interprete.interprete3(prog1NOK);
			System.out.println("Environnement obtenu : "+env1NOK);
		} catch (NoSolutionException excep) {
			System.out.println(excep);
		}
		try {
			System.out.println(prog2NOK);
			Environnement env2NOK = Interprete.interprete3(prog2NOK);
			System.out.println("Environnement obtenu : "+env2NOK);
		} catch (NoSolutionException excep) {
			System.out.println(excep);
		}
		try {
			System.out.println(prog2OK);
			Environnement env2OK = Interprete.interprete3(prog2OK);
			System.out.println("Environnement obtenu : "+env2OK);
		} catch (NoSolutionException excep) {
			System.out.println(excep);
		}
		try {
			System.out.println("\n\nHere\n"+prog3OK);
			Environnement env3OK = Interprete.interprete3(prog3OK);
			System.out.println("Environnement obtenu : "+env3OK);
		} catch (NoSolutionException excep) {
			System.out.println(excep);
		}
	}
	
	

}
