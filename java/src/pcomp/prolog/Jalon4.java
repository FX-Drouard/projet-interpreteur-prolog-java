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
import pcomp.prolog.parser.PrologParser;

public class Jalon4 {
	
	public static void main(String[] args) throws IOException {
		Program prog1OK = PrologParser.parseFile("tests_jalon_pl/interprete4_test1.pl");
		// séparation des buts et regles
		VisitorDecl v = new VisitorDecl(false);
		List<Decl> decls = prog1OK.getDeclarations();
		for (Decl d : decls) {
			d.accept(v);
		}
		List<Predicate> goals = v.getButs();
		List<DeclAssertion> rules = v.getRegles();
		Environnement e = Interprete.solve(new ArrayList<>(), goals, rules, new Environnement());
		System.out.println(e);
	}
}
