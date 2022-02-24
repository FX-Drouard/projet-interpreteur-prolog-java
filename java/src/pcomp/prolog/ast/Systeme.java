package pcomp.prolog.ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pcomp.prolog.ast.excep.FoundRException;
import pcomp.prolog.ast.excep.NoSolutionException;

public class Systeme {
	
	private List<Equation> eqs = new ArrayList<>();
	private Map<TermVariable, Term> env = new HashMap<>();
	
	// Methodes pour gerer la liste d'Equation
	/////////////////////////////////////////////
	
	public void addEquation(Equation e) {
		eqs.add(e);
	}
	
	public void removeEquation(Equation e) {
		eqs.remove(e);
	}
	
	public Equation getEq(int index) {
		return eqs.get(index);
	}
	
	//Renvoie une copie en surface de la liste eqs
	private List<Equation> copie() {
		List<Equation> res = new ArrayList<>();
		for (Equation e : eqs) {
			res.add(e);
		}
		return res;
	}
	
	public int size() {
		return eqs.size();
	}
	
	// Affichage
	public void afficherSysteme() {
		System.out.println("Affichage du systeme :");
		for (Equation e : eqs) {
			System.out.println(e);
		}
	}
	
	// Methodes pour gerer l'environnement
	/////////////////////////////////////////////
	
	private void addEnv(Equation e) {
		TermVariable key = (TermVariable)e.getGauche();
		env.put(key, e.getDroite());
	}
	
	public Map<TermVariable,Term> getEnv() {
		return env;
	}
	
	public void afficherEnv() {
		System.out.println("Environnement :");
		for (TermVariable key : env.keySet()) {
			System.out.println(key + " -> "+ env.get(key));
		}
	}
	
	// Regles d'unification
	///////////////////////////
	
	private boolean decomposer() {
		boolean replaced = false;
		// liste contiendra l'ancien systeme d'equations, on fera la boucle dessus
		List<Equation> oldSys = copie();
		for (Equation e : oldSys) {
			replaced = replaced || e.decomposer(this);
		}
		afficherSysteme();
		return replaced;
	}
	
	private boolean effacer() {
		boolean replaced = false;
		// liste contiendra l'ancien systeme d'equations, on fera la boucle dessus
		List<Equation> oldSys = copie();
		for (Equation e : oldSys) {
			replaced = replaced || e.effacer(this);
		}
		afficherSysteme();
		return replaced;
	}
	
	private boolean orienter() {
		boolean replaced = false;
		// liste contiendra l'ancien systeme d'equations, on fera la boucle dessus
		List<Equation> oldSys = copie();
		for (Equation e : oldSys) {
			replaced = replaced || e.orienter(this);
		}
		afficherSysteme();
		return replaced;
	}
	
	private boolean remplacer() throws NoSolutionException {
		boolean replaced = false;
		// liste contiendra l'ancien systeme d'equations, on fera la boucle dessus
		List<Equation> oldSys = copie();
		for (Equation e : oldSys) {
		// si e est de la forme TermVariable = Term, on peut l'ajouter à l'environnement
			if (e.formatROK()) {
				// ajout dans l'environnement
				addEnv(e);
			}
		}
		afficherSysteme();
		return replaced;
	}
	
	private void subst() {
		// regleapp sert de condition d'arret de notre boucle d'unification
		boolean replaced = true;
		List<Equation> oldSys;
		while (replaced) {
			replaced = false;
			oldSys = copie();
			for (TermVariable key : env.keySet()) {
				for (Equation e : oldSys) {
					e.subst(this, key, env.get(key));
				}
			}
		}
	}
	
	public void unify() {
		// regleapp sert de condition d'arret de notre boucle d'unification
		boolean regleapp = true;
		
		while (regleapp) {
			regleapp = false; //aucune regle n'a ete appliquee sur le systeme pendant ce tour
			// Application des regles
			subst();
			regleapp = regleapp || effacer();
			regleapp = regleapp || decomposer();
			regleapp = regleapp || orienter();
			try {
				regleapp = regleapp || remplacer();
			} catch (NoSolutionException excep) {
				System.out.println("Pas de solutions pour ce système");
				env.clear(); //pas de solution, pas d'environnement
			}
		}
		afficherEnv();
	}

}
