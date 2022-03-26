package pcomp.prolog.ast;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class CurrContext {
	// r�gle choisie
	private DeclAssertion choice;
	// liste des buts � r�soudre
	private List<Predicate> goals;
	// liste des r�gles � explorer
	private List<DeclAssertion> toExplore;
	// environnement
	private Environnement env;
	
	public CurrContext(DeclAssertion choice, List<Predicate> goals, List<DeclAssertion> rules, Environnement env) {
		this.choice = choice;
		// on enregistre que des copies des listes
		List<Predicate> g = new ArrayList<>();
		for (Predicate elem : goals) {
			g.add(elem);
		}
		this.goals = g;
		List<DeclAssertion> r = new ArrayList<>();
		for (DeclAssertion elem : rules) {
			r.add(elem);
		}
		toExplore = r;
		this.env = env.copy();
	}
	
	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner("\n");
		sj.add("Choix : "+choice.toString());
		sj.add("Buts � r�soudre :");
		for (Predicate goal : goals) {
			sj.add(goal.toString());
		}
		sj.add("Environnement :"+env.toString());
		return sj.toString();
	}
	
	public void afficheChoice() {
		System.out.println(toString());
	}
	
	public static void afficheListChoices(List<CurrContext> choices) {
		int i = 1;
		for (CurrContext c : choices) {
			System.out.println("Choix n�"+i);
			c.afficheChoice();
			i++;
		}
	}
}
