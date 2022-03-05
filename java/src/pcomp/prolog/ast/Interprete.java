package pcomp.prolog.ast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pcomp.prolog.ast.excep.FormatASTNotOK;
import pcomp.prolog.parser.PrologParser;

public class Interprete {
	
	public static Environnement interprete0(Program ast) {
		// récupération des faits et buts
		List<Decl> decls = ast.getDeclarations();
		VisitorDecl v = new VisitorDecl();
		for (Decl d : decls) {
			d.accept(v);
		}
		List<Predicate> faits = v.getFaits();
		List<Predicate> buts = v.getButs();
		
		// verification que ast contient un seul fait et un seul but
		if (faits.size() != 1) {
			throw new IllegalArgumentException("Nombre de faits incorrect. Il n'en faut qu'un.");
		}
		if (buts.size() != 1) {
			throw new IllegalArgumentException("Nombre de buts incorrect. Il n'en faut qu'un");
		}
		
		// unification
		Equation eq = new Equation(new TermPredicate(faits.get(0),faits.get(0).getPosition()),
				new TermPredicate(buts.get(0),buts.get(0).getPosition()));
		Systeme s = new Systeme();
		s.addEquation(eq);
		s.unify();
		return s.getEnv();
	}
	
	public static Environnement interprete1(Program ast) {
		List<Decl> decls = ast.getDeclarations();
		
		// séparation des buts et faits
		VisitorDecl v = new VisitorDecl();
		for (Decl d : decls) {
			d.accept(v);
		}
		List<Predicate> faits = v.getFaits();
		List<Predicate> buts = v.getButs();
		if (buts.size() != 1) {
			throw new IllegalArgumentException("Il y a trop de buts. Il n'en faut qu'un.");
		}
		// vérification des symboles de faits.
		List<String> symbols = new ArrayList<>();
		for (Predicate p : faits) {
			if (symbols.contains(p.getSymbol())) {
				throw new IllegalArgumentException("Il faut un fait par symbole de prédicat.");
			}
			symbols.add(p.getSymbol());
		}
		
		// recherche du fait avec le bon prédicat
		for (Predicate p : faits) {
			if (p.getSymbol().equals(buts.get(0).getSymbol())) {
				
				// unification
				Equation eq = new Equation(new TermPredicate(p,p.getPosition()),
						new TermPredicate(buts.get(0),buts.get(0).getPosition()));
				Systeme s = new Systeme();
				s.addEquation(eq);
				s.unify();
				return s.getEnv();
			}
		}
		Environnement res = new Environnement();
		return res;
	}
	
	public static Environnement interprete2(Program ast) {
		List<Decl> decls = ast.getDeclarations();
		
		// séparation des buts et faits
		VisitorDecl v = new VisitorDecl();
		for (Decl d : decls) {
			d.accept(v);
		}
		List<Predicate> faits = v.getFaits();
		List<Predicate> buts = v.getButs();
		// vérification des symboles de faits.
		List<String> symbols = new ArrayList<>();
		for (Predicate p : faits) {
			if (symbols.contains(p.getSymbol())) {
				throw new IllegalArgumentException("Il faut un fait par symbole de prédicat.");
			}
			symbols.add(p.getSymbol());
		}
		
		//pour tous les buts, on cherche un fait de même symbole
		Systeme s = new Systeme();
		for (Predicate b : buts) {
			for (Predicate p : faits) {
				if (p.getSymbol().equals(b.getSymbol())) {
					Equation eq = new Equation(new TermPredicate(p,p.getPosition()),
							new TermPredicate(b,b.getPosition()));
					s.addEquation(eq);
				}
			}
		}
		s.afficherSysteme();
		s.unify();
		return s.getEnv();
	}

}
