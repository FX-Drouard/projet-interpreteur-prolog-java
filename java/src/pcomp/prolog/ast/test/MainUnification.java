package pcomp.prolog.ast.test;

import java.util.ArrayList;
import java.util.List;

import pcomp.prolog.ast.*;

public class MainUnification {

	public static void main(String[] args) {
		// p (g (X), X)
		TermVariable x = new TermVariable("X",null);
		List<Term> argPred1 = new ArrayList<>();
		argPred1.add(x);
		Predicate pred1 = new Predicate("g",argPred1,null);
		TermPredicate term1 = new TermPredicate(pred1,null);
		List<Term> argPred2 = new ArrayList<>();
		argPred2.add(term1);
		argPred2.add(x);
		Predicate pred2 = new Predicate("p",argPred2,null);
		TermPredicate term2 = new TermPredicate(pred2,null);
		
		// p (Y, h(Y))
		TermVariable y = new TermVariable("Y",null);
		List<Term> argPred3 = new ArrayList<>();
		argPred3.add(x);
		Predicate pred3 = new Predicate("h",argPred3,null);
		TermPredicate term3 = new TermPredicate(pred3,null);
		List<Term> argPred4 = new ArrayList<>();
		argPred4.add(y);
		argPred4.add(term3);
		Predicate pred4 = new Predicate("p",argPred4,null);
		TermPredicate term4 = new TermPredicate(pred4,null);
		
		//p (g (X), X) = p (Y, h(Y))
		Equation eq1 = new Equation(term2,term4);
		Systeme s = new Systeme();
		s.addEquation(eq1);
		s.afficher();
		s.unification();
		s.afficher();
	}

}