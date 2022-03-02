package pcomp.prolog.ast;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pcomp.prolog.ast.excep.FormatASTNotOK;
import pcomp.prolog.parser.PrologParser;

public class Interprete {
	
	public static Map<TermVariable,Term> interprete0(Program ast) throws FormatASTNotOK {
		// verification que ast contient un DeclAssertion
		List<Decl> decls = ast.getDeclarations();
		if (decls.size() != 2) {
			throw new FormatASTNotOK("Program passé à interprete0 non correct, réessayez avec un autre interprète");
		}
		if (!(decls.get(0) instanceof DeclAssertion && decls.get(1) instanceof DeclGoal)) {
			throw new FormatASTNotOK("Program passé à interprete0 non correct, réessayez en mettant le fait avant le but");
		}
		DeclAssertion fait = (DeclAssertion)decls.get(0);
		DeclGoal but = (DeclGoal)decls.get(1);
		if (but.getPredicates().size() != 1) {
			throw new FormatASTNotOK("Program passé à interprete0 non correct, il y a plusieurs buts");
		}
		if (!fait.getPredicates().isEmpty()) {
			throw new FormatASTNotOK("Program passé à interprete0 non correct, le fait doit être indépendant");
		}
		Equation eq = new Equation(new TermPredicate(fait.getHead(),fait.getHead().getPosition()),new TermPredicate(but.getPredicates().get(0),but.getPredicates().get(0).getPosition()));
		Systeme s = new Systeme();
		s.addEquation(eq);
		s.unify();
		Map<TermVariable,Term> res = s.getEnv();
		return res;
	}
	
	public static Map<TermVariable,Term> interprete1(Program ast) {
		Map<TermVariable,Term> res = new HashMap<TermVariable,Term>();
		return res;
	}
	
	public static Map<TermVariable,Term> interprete2(Program ast) {
		Map<TermVariable,Term> res = new HashMap<TermVariable,Term>();
		return res;
	}
	
	public static void afficher(Map<TermVariable,Term> env) {
		System.out.println("Environnement :");
		for (TermVariable key : env.keySet()) {
			System.out.println(key + " -> " + env.get(key));
		}
	}
	
	public static void main(String[] args) throws IOException {
		Program prog1 = PrologParser.parseFile("tests_jalon_pl/test0.pl");
		try {
			Map<TermVariable,Term> env = interprete0(prog1);
			afficher(env);
		} catch (FormatASTNotOK e) {
			System.out.println(e);
		}
		
	}

}
