package pcomp.prolog.ast;

import java.util.ArrayList;
import java.util.List;

public class Log {
private List<Etat> l;
	public Log() {
		this.l=new ArrayList<Etat>();
	}
	public void addEtat(Etat x) {
		l.add(x);
	}
	public Etat getLast() {
		Etat res=l.get(l.size()-1);
		l.remove(l.size()-1);
		return res;
	}
	
	
}
