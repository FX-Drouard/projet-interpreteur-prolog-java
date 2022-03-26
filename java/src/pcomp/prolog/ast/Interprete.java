package pcomp.prolog.ast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pcomp.prolog.ast.excep.FormatASTNotOK;
import pcomp.prolog.ast.excep.NoSolutionException;
import pcomp.prolog.parser.PrologParser;

public class Interprete {
	
	// Interpretes
	//////////////////
	
	public static Environnement interprete0(Program ast) {
		// r�cup�ration des faits et buts
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
		
		// s�paration des buts et faits
		VisitorDecl v = new VisitorDecl();
		for (Decl d : decls) {
			d.accept(v);
		}
		List<Predicate> faits = v.getFaits();
		List<Predicate> buts = v.getButs();
		if (buts.size() != 1) {
			throw new IllegalArgumentException("Il y a trop de buts. Il n'en faut qu'un.");
		}
		// v�rification des symboles de faits.
		List<String> symbols = new ArrayList<>();
		for (Predicate p : faits) {
			if (symbols.contains(p.getSymbol())) {
				throw new IllegalArgumentException("Il faut un fait par symbole de pr�dicat.");
			}
			symbols.add(p.getSymbol());
		}
		
		// recherche du fait avec le bon pr�dicat
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
		
		// s�paration des buts et faits
		VisitorDecl v = new VisitorDecl();
		for (Decl d : decls) {
			d.accept(v);
		}
		List<Predicate> faits = v.getFaits();
		List<Predicate> buts = v.getButs();
		// v�rification des symboles de faits.
		List<String> symbols = new ArrayList<>();
		for (Predicate p : faits) {
			if (symbols.contains(p.getSymbol())) {
				throw new IllegalArgumentException("Il faut un fait par symbole de pr�dicat.");
			}
			symbols.add(p.getSymbol());
		}
		
		//pour tous les buts, on cherche un fait de m�me symbole
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
	
	public static Environnement interprete3(Program ast) {
		// s�paration des buts et regles
		VisitorDecl v = new VisitorDecl(false);
		List<Decl> decls = ast.getDeclarations();
		for (Decl d : decls) {
			d.accept(v);
		}
		List<Predicate> goals = v.getButs();
		List<DeclAssertion> rules = v.getRegles();
		
		// v�rification qu'il y a qu'une regle par symbole de pr�dicat
		List<String> symbols = new ArrayList<>();
		for (DeclAssertion r : rules) {
			if (symbols.contains(r.getHead().getSymbol())) {
				throw new IllegalArgumentException("Il faut un fait par symbole de pr�dicat.");
			}
			symbols.add(r.getHead().getSymbol());
		}
		
		// r�solution :
		return solve(goals,rules);
	}
	
	// Algorithmes
	////////////////
	
	public static Environnement choose(int n, Environnement v, Predicate but, List<DeclAssertion> rules, List<Predicate> nouvGoals) {
		//choose fait aussi l'unification pour les faits
		for (DeclAssertion d : rules) {
			if (d.getHead().getSymbol().equals(but.getSymbol())) {
				// on match, donc on renomme
				DeclAssertion renamed = d.rename(n);
				Systeme s = new Systeme();
				s.setEnv(v.copy()); // copie de l'environnement pour ne pas modifier celui pass� en param�tre
				s.addEquation(new Equation(
						new TermPredicate(renamed.getHead(),renamed.getPosition()),
						new TermPredicate(but,but.getPosition())));
				s.unify();
				if (!s.getEnv().isEmpty()) {
					nouvGoals.addAll(renamed.getPredicates());
					return s.getEnv();
				}
			}
		}
		throw new NoSolutionException("pas d'environnement correspondant pour le but "+but);
	}
	
	public static Environnement solve(List<Predicate> goals, List<DeclAssertion> rules) {
		Environnement res = new Environnement();
		int cpt = 1;
		while (!goals.isEmpty()) {
			System.out.println("Buts � v�rifier : "+goals);
			res = choose(cpt,res,goals.get(0),rules,goals);
			goals.remove(0);
			cpt++;
		}
		return res;
	}
	
	public static Environnement solve(List<CurrContext> ch, List<Predicate> goals, List<DeclAssertion> rules, Environnement env) {
		Environnement res = new Environnement();
		//si goals non vide :
			//on prend le premier but de la liste goals
			//parcourt de rules
				//si on peut unifier :
					//on renomme
					//on unifie le head !!!!! ne pas oublier de mettre une copie de l'environnement !!!!
					//si il y a une solution :
						//cr�ation d'un CurrContext avec :
						//la regle avec laquelle on a unifi�
						//les nouveaux buts (s'il y en a) ajout�s � goals
						//la m�me liste des r�gles
						//on empile dans ch
						//on fait un appel r�cursif avec ch, les donn�es du CurrContext cr�� !!!! encadr� par try catch !!!!
				//si on n'a pas pu trouver un bon match -> on revient au CurrContext pr�c�dent
		//on retourne l'environnement du dernier CurrContext s'il y en a
		//sinon, on lance une exception
		return res;
	}
}
