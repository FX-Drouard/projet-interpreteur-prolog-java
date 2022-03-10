_Documentation du rendu de projet,  remplir._

# Jalon 1

## Question 1
```
    Comme nous avons choisi le langage Java, nous allons repr�senter les �quations par une classe Equation qui aura pour attributs deux Terme.
Ainsi, on pourra repr�senter un syst�me d'�quations par une Collection de l'API Java. Ici, on utilisera les List, plus pr�cis�ment les ArrayList afin de dynamiser le remplissage et am�liorer l'efficacit�.
	Nous cr�ons donc une classe Systeme qui impl�mentera les r�gles d'unification. Chaque m�thode correspondant � une r�gle est de visibilit� private car elles sont utilis�es uniquement par la m�thode unify.
	Pour automatiser l'unification du syst�me d'�quations, nous d�cidons d'essayer d'appliquer chaque r�gle au syst�me entier dans un ordre arbitraire.
```

## Question 2
```
	Notre fonction occur_check est impl�ment�e dans la classe Equation. Elle est de visibilit� private car elle n'est appel�e que localement dans la fonction formatROK. Cette fonction renvoie true si l'instance d'Equation est une bonne candidate pour appliquer la r�gle remplacer. C'est-�-dire que son Term gauche est une instance de TermVariable.
	occur_check utiise un TermVisitor, VisitorVar qui renvoie la liste des variables trouv�es dans un Term.
Ainsi, elle l�ve l'exception NoSolutionException dans le cas o� une variable qui se trouve dans le Term gauche est dans le Term droit d'une Equation. Cette Exception est rattrapp�e le plus tard possible, c'est-�-dire dans la m�thode unify de la classe Systeme, ce qui nous permet de mettre fin � la boucle d'unify.
```

## Question 3
```
	Pour l'environnement, nous l'impl�mentons � l'aide d'une Map Java. Cela nous permet d'associer un TermVariable unique a un Term. Nous ajoutons cet environnement comme attribut � notre classe Systeme afin d'all�ger l'�criture des algorithmes suivants.
```

## Question 4
```
	La substitution d'une variable dans une Equation est d�l�gu� � la classe Equation qui prend un TermVariable � remplacer et le Term correspondant. Cette m�thode secondaire est appel�e pour toutes les variables contenues dans l'environnement li� au syst�me pass� en param�tre.
```

## Question 5
```
	Notre algorithme unify est une m�thode d'instance propre � un objet Systeme contenant des �quations et un environnement. Il prend donc implicitement en param�tre ces deux �l�ments.
Pour appliquer l'algorithme d'unification sur un syst�me d'�quation, il faut donc dans un premier temps instancier un Systeme et remplir son environnement et ses �quations.
```


## Conclusion :

```
	Dans ce premier jalon, nous avons implant� l'unification d'un syst�me d'�quations et le debut d'un affichage graphique.
Dans un premier temps, nous avons choisi d'impl�menter la r�gle subst (remplacer) sans utiliser le Design Pattern Visiteur car nous voulions changer directement les TermVariable sans dans les objets concern�s : Equation et TermPredicat en tant qu'argument. Mais son comportement quasi similaire nous fait douter de l'impl�mentation de cette r�gle. Nous la modifierons s�rement plus tard dans le projet.
Dans un second temps nous avons cr�� une interface graphique simple avec une zone d'�dition de texte et un systeme de chargement/sauvegarde de la zone d'�dition qui utilise un lecteur de fichier simple et utilise le parser donn� dans le projet. Nous am�liorerons cette interface avec le temps.

	Pour ex�cuter ce Jalon, il suffit d'ex�cuter la classe MainUnification qui contient tous les exemples de l'exercice 7 du TD4 concernant l'unification. La v�rification du bon fonctionnement des fonctions se base sur les affichages du syst�me d'�quations et de l'environnement � chaque r�gle appliqu�e.
	Nous avons rencontr� des difficult�s concernant l'implantation de la r�gle remplacer. Comme nous l'avons dit pr�c�demment, nous avons des doutes sur l'efficacit� de nos m�thodes.
```

#Jalon 2

```
	Nous avons renomm� notre classe MainUnification qui testait les unifications des �quations du TD par `Jalon1` pour correspondre � l'�nonc�.
	Nous avons �galement modifi� la classe MainTestRegles qui avait pour but de tester les r�gles d'unification une � une et de contr�ler leurs effets sur le syst�me par l'affichage. Ces m�mes affichages, pour ce Jalon 2, ont �t� comment�es afin de ne pas alourdir l'affichage des interpr�tes.
	Une classe Environnement a �t� cr��e afin de ne pas surcharger la classe Systeme et al�ger la signature des m�thodes cr��es pour ce Jalon.
```

```
	Nous pla�ons les m�thodes des diff�rents interpr�tes une classe "statique" Interprete.
	Dans nos m�thodes, nous v�rifions que les programmes v�rifient bien les conditions de l'�nonc�. Si ce n'est pas le cas, nous levons l'exception IllegalArgumentException.
	On l�ve cette exception dans :
	-interprete0 quand :
		- une DeclAssertion n'est pas un fait
		- il n'y a pas qu'un seul fait
		- il n'y a pas qu'un seul but
	-interprete1 et interprete2 quand :
		- les DeclAssertion ne sont pas que des faits
		- il y a deux faits avec le m�me symbole de pr�dicat
	interprete1 lance en plus l'exception quand il y a trop de buts.
```

```
	Nos fichiers tests .pl se trouvent dans le dossier nomm� `tests_jalon_pl`. Dans la classe Jalon1 (du package pcomp.prolog.ast.test), nous avons ajout� les tests avec tous les fichiers .pl que nous fournissons.
	Nous testons aussi les cas o� l'interprete rejetterait le Program pass� en param�tres.
```

```
	Dans les m�thodes d'interpr�tes, la mani�re que nous avons de s�parer les faits et les buts n'�tait pas tr�s optimale et �tait plut�t redondante. Nous avons donc d�cid� d'implanter un DeclVisitor que l'on appelle VisitorDecl. Il s�pare les faits et buts et construit les listes. La valeur de retour ne nous sert pas encore donc nous avons mis des List<Predicate> par d�faut.
	Ce visiteur pourra �tre �toff� s'il faut � l'avenir traiter les DeclAssertion qui ne seraient pas des faits.
```

#Jalon 3
```
	Nous avons d�plac� les classes ex�cutables correspondant aux Jalons dans le package principal pcomp.prolog.ast afin de les mettre davantage en �vidence.
```

```
	Pour la m�thode rename que l'on place dans la classe DeclAssertion, nous impl�mentons un autre TermVisitor qui nous renvoie un nouveau Term avec les variables renomm�es. La m�thode principale d�l�gue le renommage des variables au Predicate qui utilise le visiteur d�crit pr�c�demment.
```

```
	Notre m�thode choose, pour le moment private, est plac�e dans notre classe statique Interprete car elle sert � l'implantation de la m�thode interprete3.
```