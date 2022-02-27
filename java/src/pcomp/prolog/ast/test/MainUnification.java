package pcomp.prolog.ast.test;

import java.util.ArrayList;
import java.util.List;

import pcomp.prolog.ast.*;
import pcomp.prolog.parser.PrologParser;

public class MainUnification {

	public static void main(String[] args) {
		// Tests avec les équations du TD
		
		System.out.println("\n\n\n Eq 1\n\n");
		// 1 :
		Program p1 = PrologParser.parseString("?- p(Z,h(Z,W),f(W)).");
		System.out.println(p1);
		Program p2 = PrologParser.parseString("?- p(f(X),h(Y,f(a)),Y).");
		System.out.println(p2);
		Predicate pred1 = p1.getDeclarations().get(0).getPredicates().get(0);
		System.out.println(pred1);
		Predicate pred2 = p2.getDeclarations().get(0).getPredicates().get(0);
		System.out.println(pred2);
		Equation eq1 = new Equation(new TermPredicate(pred1,pred1.getPosition()), new TermPredicate(pred2,pred2.getPosition()));
		Systeme s1 = new Systeme();
		s1.addEquation(eq1);
		s1.afficherEnv();
		s1.afficherSysteme();
		s1.unify();
		
		System.out.println("\n\n\n Eq 2\n\n");
		// 2 :
		Program p21 = PrologParser.parseString("?- p(a,X,f(g(Y))).");
		System.out.println(p21);
		Program p22 = PrologParser.parseString("?- p(Z,f(Z),f(U)).");
		System.out.println(p22);
		Predicate pred21 = p21.getDeclarations().get(0).getPredicates().get(0);
		System.out.println(pred21);
		Predicate pred22 = p22.getDeclarations().get(0).getPredicates().get(0);
		System.out.println(pred22);
		Equation eq2 = new Equation(new TermPredicate(pred21,pred21.getPosition()), new TermPredicate(pred22,pred22.getPosition()));
		Systeme s2 = new Systeme();
		s2.addEquation(eq2);
		s2.afficherEnv();
		s2.afficherSysteme();
		s2.unify();
		
		System.out.println("\n\n\n Eq 3\n\n");
		// 3 :
		Program p31 = PrologParser.parseString("?- q(f(a),g(X)).");
		System.out.println(p31);
		Program p32 = PrologParser.parseString("?- q(Y,Y).");
		System.out.println(p32);
		Predicate pred31 = p31.getDeclarations().get(0).getPredicates().get(0);
		System.out.println(pred31);
		Predicate pred32 = p32.getDeclarations().get(0).getPredicates().get(0);
		System.out.println(pred32);
		Equation eq3 = new Equation(new TermPredicate(pred31,pred31.getPosition()), new TermPredicate(pred32,pred32.getPosition()));
		Systeme s3 = new Systeme();
		s3.addEquation(eq3);
		s3.afficherEnv();
		s3.afficherSysteme();
		s3.unify();
		
		System.out.println("\n\n\n Eq 4\n\n");
		// 4 : 
		Program p41 = PrologParser.parseString("?- p(X,f(X),f(f(X))).");
		System.out.println(p41);
		Program p42 = PrologParser.parseString("?- p(f(f(Y)),Y,f(Y)).");
		System.out.println(p42);
		Predicate pred41 = p41.getDeclarations().get(0).getPredicates().get(0);
		System.out.println(pred41);
		Predicate pred42 = p42.getDeclarations().get(0).getPredicates().get(0);
		System.out.println(pred42);
		Equation eq4 = new Equation(new TermPredicate(pred41,pred41.getPosition()), new TermPredicate(pred42,pred42.getPosition()));
		Systeme s4 = new Systeme();
		s4.addEquation(eq4);
		s4.afficherEnv();
		s4.afficherSysteme();
		s4.unify();	
		
		
		System.out.println("\n\n\n Eq 5\n\n");
		// 5 : 
		Program p51 = PrologParser.parseString("?- q(x, q(y, z)).");
		System.out.println(p51);
		Program p52 = PrologParser.parseString("?- q(x, g(h(k(x)))).");
		System.out.println(p52);
		Predicate pred51 = p51.getDeclarations().get(0).getPredicates().get(0);
		System.out.println(pred51);
		Predicate pred52 = p52.getDeclarations().get(0).getPredicates().get(0);
		System.out.println(pred52);
		Equation eq5 = new Equation(new TermPredicate(pred51,pred51.getPosition()), new TermPredicate(pred52,pred52.getPosition()));
		Systeme s5 = new Systeme();
		s5.addEquation(eq5);
		s5.afficherEnv();
		s5.afficherSysteme();
		s5.unify();	
		
		System.out.println("\n\n\n Eq 6\n\n");
		// 6 : 
		Program p61 = PrologParser.parseString("?-  q(X, r(U, X)).");
		System.out.println(p61);
		Program p62 = PrologParser.parseString("?-  q(r(Y, a), r(Z, r(b, Z))).");
		System.out.println(p62);
		Predicate pred61 = p61.getDeclarations().get(0).getPredicates().get(0);
		System.out.println(pred61);
		Predicate pred62 = p62.getDeclarations().get(0).getPredicates().get(0);
		System.out.println(pred62);
		Equation eq6 = new Equation(new TermPredicate(pred61,pred61.getPosition()), new TermPredicate(pred62,pred62.getPosition()));
		Systeme s6 = new Systeme();
		s6.addEquation(eq6);
		s6.afficherEnv();
		s6.afficherSysteme();
		s6.unify();	
				
		System.out.println("\n\n\n Eq 7\n\n");
		// 7 : 
		Program p71 = PrologParser.parseString("?-  p(X, f(X), r(f(X), X)).");
		System.out.println(p71);
		Program p72 = PrologParser.parseString("?-  p(z, f(f(a)), r(f(r(a, Z)), v)).");
		System.out.println(p72);
		Predicate pred71 = p71.getDeclarations().get(0).getPredicates().get(0);
		System.out.println(pred71);
		Predicate pred72 = p72.getDeclarations().get(0).getPredicates().get(0);
		System.out.println(pred72);
		Equation eq7 = new Equation(new TermPredicate(pred71,pred71.getPosition()), new TermPredicate(pred72,pred72.getPosition()));
		Systeme s7 = new Systeme();
		s7.addEquation(eq7);
		s7.afficherEnv();
		s7.afficherSysteme();
		s7.unify();	
				
			
		System.out.println("\n\n\n Eq 8\n\n");
		// 8 : 
		Program p81 = PrologParser.parseString("?-  p(f(r(X, Y)), r(V, W), Y).");
		System.out.println(p81);
		Program p82 = PrologParser.parseString("?-  p(f(Z), X, f(X)).");
		System.out.println(p82);
		Predicate pred81 = p81.getDeclarations().get(0).getPredicates().get(0);
		System.out.println(pred81);
		Predicate pred82 = p82.getDeclarations().get(0).getPredicates().get(0);
		System.out.println(pred82);
		Equation eq8 = new Equation(new TermPredicate(pred81,pred81.getPosition()), new TermPredicate(pred82,pred82.getPosition()));
		Systeme s8 = new Systeme();
		s8.addEquation(eq8);
		s8.afficherEnv();
		s8.afficherSysteme();
		s8.unify();	
		
		System.out.println("\n\n\n Eq 9\n\n");
		// 9 : 
		Program p91 = PrologParser.parseString("?-  p(f(Y), f(Z), f(T), f(X)).");
		System.out.println(p91);
		Program p92 = PrologParser.parseString("?-  p(g(Z), g(X), g(Y), g(Z)).");
		System.out.println(p92);
		Predicate pred91 = p91.getDeclarations().get(0).getPredicates().get(0);
		System.out.println(pred91);
		Predicate pred92 = p92.getDeclarations().get(0).getPredicates().get(0);
		System.out.println(pred92);
		Equation eq9 = new Equation(new TermPredicate(pred91,pred91.getPosition()), new TermPredicate(pred92,pred92.getPosition()));
		Systeme s9 = new Systeme();
		s9.addEquation(eq9);
		s9.afficherEnv();
		s9.afficherSysteme();
		s9.unify();	
	}

}
