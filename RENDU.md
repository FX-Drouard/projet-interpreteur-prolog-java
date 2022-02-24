_Documentation du rendu de projet, Ã  remplir._

# Jalon 1

## Question 1
```
    Comme nous avons choisi le langage Java, nous allons représenter les équations par une classe Equation qui aura pour attributs deux Terme.
Ainsi, on pourra représenter un systême d'équations par une Collection de l'API Java. Ici, on utilisera les List, plus précisément les ArrayList afin de dynamiser le remplissage et améliorer l'efficacité.
	Nous créons donc une classe Systeme qui implémentera les rêgles d'unification. Chaque méthode correspondant à une rêgle est de visibilité private car elles sont utilisées uniquement par la méthode unification.
	Pour automatiser l'unification du systême d'équations, nous décidons d'essayer d'appliquer chaque rêgle au systême entier dans un ordre arbitraire.
```

## Question 2
```
	Notre fonction occur_check est implémentée dans la classe Equation. Elle est de visibilité private car elle n'est appelée que localement dans la fonction formatROK qui renvoie true si l'instance d'Equation est une bonne candidate pour appliquer la règle remplacer. Elle lève une NoSolutionException qui est rattrappée le plus tard possible, c'est-à-dire dans la méthode unification de la classe Systeme, ce qui nous permet de mettre fin à la boucle d'unification.
```

## Question 3
```
	Pour l'environnement, nous l'implémentons à l'aide d'une Map Java, nous permettant d'associer un TermVariable unique a un Term. Nous ajoutons cet environnement comme attribut à notre classe Systeme afin d'alléger l'écriture des algorithmes suivants.
```

## Question 4
```
```

## Question 5
```
Notre algorithme unify est une méthode d'instance propre à un objet Systeme contenant des équations et un environnement. Il prend donc implicitement en paramètre ces deux éléments.
Pour appliquer l'algorithme d'unification sur un système d'équation, il faut donc dans un premier temps instancier un Systeme et remplir son environnement et ses équations.
```


## Conclusion :

```
	Dans ce premier jalon, nous avons implanté l'unification d'un système d'équations _ajouter les autres choses_.
Dans un premier temps, nous avons choisi d'implémenter la règle remplacer sans utiliser le Design Pattern Visiteur car nous voulions changer directement les TermVariable sans dans les objets concernés : Equation et TermPredicat en tant qu'argument. Mais son comportement quasi similaire nous fait douter de l'implémentation de cette règle. Nous la modifierons peut-être plus tard dans le projet.
```
