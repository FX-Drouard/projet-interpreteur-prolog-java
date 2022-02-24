package pcomp.prolog.ast;

import java.util.ArrayList;
import java.util.List;

import pcomp.prolog.ast.excep.FoundRException;

public class Systeme {
	
	private List<Equation> eqs = new ArrayList<>();
	
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
	
	// Regles d'unification
	///////////////////////////
	
	private boolean decomposer() {
		boolean replaced = false;
		// liste contiendra l'ancien systeme d'equations, on fera la boucle dessus
		List<Equation> oldSys = copie();
		for (Equation e : oldSys) {
			replaced = replaced || e.decomposer(this);
		}
		afficher();
		return replaced;
	}
	
	private boolean effacer() {
		boolean replaced = false;
		// liste contiendra l'ancien systeme d'equations, on fera la boucle dessus
		List<Equation> oldSys = copie();
		for (Equation e : oldSys) {
			replaced = replaced || e.effacer(this);
		}
		afficher();
		return replaced;
	}
	
	private boolean orienter() {
		boolean replaced = false;
		// liste contiendra l'ancien systeme d'equations, on fera la boucle dessus
		List<Equation> oldSys = copie();
		for (Equation e : oldSys) {
			replaced = replaced || e.orienter(this);
		}
		afficher();
		return replaced;
	}
	
	private boolean remplacer() {
		boolean replaced = false;
		// liste contiendra l'ancien systeme d'equations, on fera la boucle dessus
		List<Equation> oldSys = copie();
		try {
			for (Equation e : oldSys) {
				// si e est de la forme TermVariable = Term, on peut essayer de remplacer
				if (e.formatROK()) {
					throw new FoundRException(e);
				}
			}
		} catch (FoundRException excep) {
			System.out.println(excep);
			// on replace!
			Equation nouv = excep.getEquation();
			oldSys.remove(nouv); // pour conserver cette Equation
			for (Equation e : oldSys) {
				e.remplacer(this, nouv);
			}
		}

		afficher();
		return replaced;
	}
	
	public void unification() {
		// regleapp sert de condition d'arret de notre boucle d'unification
		boolean regleapp = true;
		
		while (regleapp) {
			regleapp = false; //aucune regle n'a ete appliquee sur le systeme pendant ce tour
			// Application des regles
			regleapp = regleapp || effacer();
			regleapp = regleapp || decomposer();
			regleapp = regleapp || orienter();
			regleapp = regleapp || remplacer();
		}
	}
	
	// Affichage
	public void afficher() {
		System.out.println("Affichage du systeme :");
		for (Equation e : eqs) {
			System.out.println(e);
		}
	}

}
