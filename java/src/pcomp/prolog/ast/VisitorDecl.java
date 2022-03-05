package pcomp.prolog.ast;

import java.util.ArrayList;
import java.util.List;

// visiteur utilisé pour séparer les faits des buts et les récupérer
public class VisitorDecl implements DeclVisitor<List<Predicate>> {
	
	private List<Predicate> buts = new ArrayList<>();
	private List<Predicate> faits = new ArrayList<>();

	@Override
	public List<Predicate> visit(DeclAssertion a) {
		//on ne prend que les faits
		if (a.getPredicates().isEmpty()) {
			faits.add(a.getHead());
		} else {
			throw new IllegalArgumentException("Il n'a pas que des faits.");
		}
		return faits;
	}

	@Override
	public List<Predicate> visit(DeclGoal a) {
		buts.addAll(a.getPredicates());
		return buts;
	}
	
	public List<Predicate> getFaits() {
		return faits;
	}
	
	public List<Predicate> getButs() {
		return buts;
	}

}
