package pcomp.prolog.ast.test;

import java.util.ArrayList;
import java.util.List;

import pcomp.prolog.ast.*;
import pcomp.prolog.parser.PrologParser;

public class MainUnification {

	public static void main(String[] args) {
		// Tests avec les équations du TD
		
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
		
		// 2 :
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
	}

}
