package pcomp.prolog.ast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pcomp.prolog.ast.excep.FormatASTNotOK;
import pcomp.prolog.parser.PrologParser;

public class Interprete {
	
	public static Map<TermVariable,Term> interprete0(Program ast) throws FormatASTNotOK {
		// verification que ast contient un seul fait et un seul but
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
		Equation eq = new Equation(new TermPredicate(fait.getHead(),fait.getHead().getPosition()),
				new TermPredicate(but.getPredicates().get(0),but.getPredicates().get(0).getPosition()));
		Systeme s = new Systeme();
		s.addEquation(eq);
		s.unify();
		return s.getEnv();
	}
	
	public static Map<TermVariable,Term> interprete1(Program ast) throws FormatASTNotOK {
		List<Decl> decls = ast.getDeclarations();
		List<Predicate> faits = new ArrayList<>();
		List<String> symbols = new ArrayList<>();
		Predicate but = null;
		// initialisation des faits et du but
		for (Decl d : decls) {
			if (d instanceof DeclAssertion) {
				// Vérification qu'on a qu'on a que des faits
				DeclAssertion head = (DeclAssertion)d;
				if (head.getPredicates().isEmpty()) {
					// Vérification qu'on a des faits avec des symboles de prédicats différents
					if (symbols.contains(head.getHead().getSymbol())) {
						throw new FormatASTNotOK("Program passé à interprete1 non correct : il n'y a pas un fait par symbole de prédicat");
					} else {
						faits.add(head.getHead());
					}
				} else {
					throw new FormatASTNotOK("Program passé à interprete1 non correct : il n'y a pas que des faits");
				}
			} else {
				// Vérification qu'on a qu'un seul but
				if (but == null) {
					if (((DeclGoal)d).getPredicates().size() == 1) {
						but = ((DeclGoal)d).getPredicates().get(0);
					} else {
						throw new FormatASTNotOK("Program passé à interprete1 non correct : il n'y a pas un seul but");
					}
				} else {
					//on a déjà un but
					throw new FormatASTNotOK("Program passé à interprete1 non correct : il n'y a pas un seul but");
				}
			}
		}
		System.out.println("Faits : "+faits);
		System.out.println("But : "+but);
		// recherche du fait avec le bon prédicat
		for (Predicate p : faits) {
			if (p.getSymbol().equals(but.getSymbol())) {
				Equation eq = new Equation(new TermPredicate(p,p.getPosition()),
						new TermPredicate(but,but.getPosition()));
				Systeme s = new Systeme();
				s.addEquation(eq);
				s.unify();
				return s.getEnv();
			}
		}
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

}
