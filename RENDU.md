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
	occur_check utilise un TermVisitor, VisitorVar qui renvoie la liste des variables trouv�es dans un Term.
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
	Celui correspondant � ce Jalon est 'Jalon3'. Dans le main de cette classe, nous testons notre m�thode interprete3 avec les fichiers tests .pl que l'on avons mis avec les autres, dans le dossier 'tests_jalon_pl'.
```

```
	Pour la m�thode rename que l'on place dans la classe DeclAssertion, nous impl�mentons un autre TermVisitor qui nous renvoie un nouveau Term avec les variables renomm�es. La m�thode principale d�l�gue le renommage des variables au Predicate qui utilise le visiteur d�crit pr�c�demment.
	Pour la substitution des variables, nous pouvons faire un visiteur similaire. Il sera s�rement implant� au cours d'un Jalon ult�rieur.
```

```
	Nos m�thodes choose et solve, pour le moment public, sont plac�es dans notre classe statique Interprete. Ces m�thodes peuvent aussi �tre de visibilit� private car elles ne servent qu'� l'implantation de la m�thode interprete3. N�anmoins, les premiers tests effectu�s sur ces m�thodes se trouvant dans une autre classe MainTestJalon3, nous les conservons public jusqu'au grand nettoyage.
	Comme nous pensons que l'interprete3 doit pouvoir r�soudre les AST qui ne contiennent pas que des Assertion(head, body) avec body non vide, nous g�n�ralisons la r�solution � tous les DeclAssertion, c'est pour cela que nous passons une List<DeclAssertion> pour le param�tre rules, qui contiendra par la suite toutes les DeclAssertion de l'AST.
```

```
	Notre VisitorDecl a �t� modifi� pour pouvoir l'utiliser m�me dans le cas o� l'AST contiendrait des r�gles conditionnelles "Assertion (head,body)". Pour ne pas changer l'algorithme des interpr�tes impl�mant�s pr�c�demment, c'est-�-dire pour qu'ils lancent toujours IllegalArgumentException dans le cas o� l'AST pass� en param�tre ne contient pas seulement des faits et des buts, nous surchargeons le constructeur du Visiteur avec l'initialisation d'un boolean qui indiquera si on prend, ou non, les Assertion(head,body) avec body non vide.
```

```
	Nous remarquons que l'Environnement renvoy� contient beaucoup de variables � cause du renommage. Nous nous demandons donc s'il ne serait pas meilleur de "nettoyer" l'Environnement avant son affichage en faisant les substitution possible pour ne conserver que les variables qui seraient pertinentes.
```

#Jalon 4

```
	Pour repr�senter un choix, nous impl�mentons la classe CurrContext. Dans une instance, nous conservons l'Environnement r�sultant du choix, les r�gles � explorer et les buts qu'il reste � r�soudre. Dans l'attribut choix, nous pr�cisons la r�gle choisie.
	Une suite de choix sera donc repr�sent�e par une List<CurrContext> se comportant comme une pile. Le choix le plus r�cent sera � la position size()-1 de la liste et le plus ancien sera � la position 0.
	Pour afficher une suite de choix, nous impl�mentons la m�thode statique afficheListChoices dans la classe CurrContext qui utilise la m�thode afficheChoice qui affiche le choix effectu�.
```

```
	La m�thode solve demand�e dans ce Jalon est implant�e dans la classe Interprete. L'algorithme d�crit est impl�ment� par la m�thode priv�e choose qui est une m�thode r�cursive. La m�thode public solve se contente donc de rattraper les exceptions lanc�es par choose.
	On peut se passer de cette division de l'algorithme si on modifie choose pour qu'elle soit r�cursive terminale.
```

#Jalon 5

```
	Dans le Jalon 3, nous avons �voqu� le nombre important de variables non-pertinentes contenues dans l'Environnement � cause du renommage.
	Nous avons donc impl�ment� une m�thode nettoieEnv dans la classe Environnement qui prend en param�tre une liste de variables qui sont celles des buts initiaux. Ainsi, l'Environnement renvoy� dans les interpr�tes des deux Jalons pr�c�dents ne contient strictement que les variables qui servent � r�soudre les buts.
```

```
	Pour l'algorithme nous servant � donner plusieurs solutions, nous alons surcharger la m�thode choose impl�ment�e dans le Jalon pr�c�dent.
	Notre algorithme se repose sur la sauvegarde des contextes dans un journal de choix. Nous remontons chaque choix, en commen�ant par le choix le plus r�cent, pour voir si on ne pouvait pas choisir autrement � ce moment-l�.
	Nous ajoutons donc un attribut List � CurrContext qui nous sert � conserver les choix effectu�s juste apr�s le choix courant. Il sera ajout� lors de la cr�ation du contexte du choix suivant. La liste est initialis�e � vide.
	Dans notre nouvelle m�thode choose, nous allons v�rifier, lors du choix, qu'il n'a pas �t� effectu� avant
	On remonte ainsi � chaque demande de solutions diff�rentes.
```