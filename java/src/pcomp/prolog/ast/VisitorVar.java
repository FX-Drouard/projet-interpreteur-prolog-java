package pcomp.prolog.ast;

import java.util.ArrayList;
import java.util.List;

// Visiteur qui permet de lister toutes les variables dans un Term
public class VisitorVar implements TermVisitor<List<TermVariable>> {

	@Override
	public List<TermVariable> visit(TermVariable termVariable) {
		List<TermVariable> res = new ArrayList<>();
		res.add(termVariable);
		return res;
	}

	@Override
	public List<TermVariable> visit(TermPredicate termPredicate) {
		// TODO Auto-generated method stub
		return null;
	}

}
