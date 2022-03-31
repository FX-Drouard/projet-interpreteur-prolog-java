package pcomp.prolog.ast;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class CurrContext {
	// règle choisie
	private DeclAssertion choice;
	// liste des buts à résoudre
	private List<Predicate> goals;
	// liste des règles à explorer
	private List<DeclAssertion> toExplore;
	// environnement
	private Environnement env;
	// liste des choix suivants
	private List<CurrContext> nextChoices;
	// choix précédent
	private CurrContext pere;
	
	public CurrContext(DeclAssertion choice, List<Predicate> goals, List<DeclAssertion> rules, Environnement env, CurrContext p) {
		this.choice = choice;
		//pas besoin de copier parce que par construction, le goals passé en paramètre est une nouvelle liste (dans solve du Jalon4)
		this.goals = goals;
		toExplore = rules;
		this.env = env.copy();
		nextChoices = new ArrayList<>();
		pere = p;
	}
	
	// Constructeur pour la racine
	public CurrContext(List<Predicate> goals, List<DeclAssertion> rules, Environnement env) {
		this.choice = null;
		//pas besoin de copier parce que par construction, le goals passé en paramètre est une nouvelle liste (dans solve du Jalon4)
		this.goals = goals;
		toExplore = rules;
		this.env = env.copy();
		nextChoices = new ArrayList<>();
		pere = null;
	}
	
	// Getters
	
	public DeclAssertion getChoice() {
		return choice;
	}
	
	public List<Predicate> getGoals() {
		return goals;
	}
	
	public List<DeclAssertion> getRules() {
		return toExplore;
	}
	
	public Environnement getEnv() {
		return env;
	}
	
	public List<CurrContext> getNextChoices() {
		return nextChoices;
	}
	
	public void addNextChoice(CurrContext choice) {
		nextChoices.add(choice);
	}
	
	// Fonctions d'affichages
	
	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner("\n");
		if (pere != null) {
			sj.add(pere.toString());
		}
		if (choice == null) {
			sj.add("Racine :");
		} else {
			sj.add("Choix : "+choice.toString());
		}
		sj.add("Buts à résoudre :");
		for (Predicate goal : goals) {
			sj.add(goal.toString());
		}
		sj.add("Environnement :"+env.toString());
		return sj.toString();
	}
	
	public static void afficheChoice(CurrContext choix) {
		System.out.println(choix.toString());
	}
}
