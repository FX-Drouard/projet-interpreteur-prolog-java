_Documentation du rendu de projet, à remplir._

# Jalon 1

## Question 1
```
    Comme nous avons choisi le langage Java, nous allons repr�senter les �quations par une classe Equation qui aura pour attributs deux Terme.
Ainsi, on pourra repr�senter un syst�me d'�quations par une Collection de l'API Java. Ici, on utilisera les List, plus pr�cis�ment les ArrayList afin de dynamiser le remplissage et am�liorer l'efficacit�.
	Nous cr�ons donc une classe Systeme qui impl�mentera les r�gles d'unification. Chaque m�thode correspondant � une r�gle est de visibilit� private car elles sont utilis�es uniquement par la m�thode unification.
	Pour automatiser l'unification du syst�me d'�quations, nous d�cidons d'essayer d'appliquer chaque r�gle au syst�me entier dans un ordre arbitraire.
```

## Question 2
```
	Notre fonction occur_check est impl�ment�e dans la classe Equation. Elle est de visibilit� private car elle n'est appel�e que localement dans la fonction formatROK qui renvoie true si l'instance d'Equation est une bonne candidate pour appliquer la r�gle remplacer. Elle l�ve une NoSolutionException qui est rattrapp�e le plus tard possible, c'est-�-dire dans la m�thode unification de la classe Systeme, ce qui nous permet de mettre fin � la boucle d'unification.
```

## Question 3
```
	Pour l'environnement, nous l'impl�mentons � l'aide d'une Map Java, nous permettant d'associer un TermVariable unique a un Term. Nous ajoutons cet environnement comme attribut � notre classe Systeme afin d'all�ger l'�criture des algorithmes suivants.
```

## Question 4
```
```

## Question 5
```
Notre algorithme unify est une m�thode d'instance propre � un objet Systeme contenant des �quations et un environnement. Il prend donc implicitement en param�tre ces deux �l�ments.
Pour appliquer l'algorithme d'unification sur un syst�me d'�quation, il faut donc dans un premier temps instancier un Systeme et remplir son environnement et ses �quations.
```


## Conclusion :

```
	Dans ce premier jalon, nous avons implant� l'unification d'un syst�me d'�quations _ajouter les autres choses_.
Dans un premier temps, nous avons choisi d'impl�menter la r�gle remplacer sans utiliser le Design Pattern Visiteur car nous voulions changer directement les TermVariable sans dans les objets concern�s : Equation et TermPredicat en tant qu'argument. Mais son comportement quasi similaire nous fait douter de l'impl�mentation de cette r�gle. Nous la modifierons peut-�tre plus tard dans le projet.
```
