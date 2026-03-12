# Un template d'Application en Ligne de Commande (CLI) avec Java 25 et les JEP 458, 511 et 512

[![Classic Build](https://github.com/java-cli-apps/java-25-quickstart/actions/workflows/build.yml/badge.svg)](https://github.com/java-cli-apps/java-25-quickstart/actions/workflows/build.yml)

Le template **java-25-quickstart** permet de démarrer une nouvelle application en commande ligne avec Java et Gradle.

Ce template nécessite d'utiliser Java 25 ou une version supérieure car il utilise les JEPs suivantes :

* La [JEP 458: Launch Multi-File Source-Code Programs](https://openjdk.org/jeps/458) qui permet de lancer un programme
  Java dont le code est réparti dans plusieurs fichiers sources sans avoir besoin de les compiler préalablement
* La [JEP 511: Module Import Declarations](https://openjdk.org/jeps/511) qui permet d'importer tous les packages
  exportés par un module java
* La [JEP 512: Compact Source Files and Instance Main Methods](https://openjdk.org/jeps/512) qui permet de :
    * Se passer de déclaration de classe
    * Simplifier la déclaration de la méthode `main`
    * Utiliser les méthodes `println` et `readln` de la classe `java.lang.IO`

Le template utilise la librairie [record-args](https://github.com/nipafx/record-args), développée par
[Nicolai Parlog](https://nipafx.dev), et qui s'appuie sur les `record` et les interfaces `sealed` pour définir les
arguments de la ligne de commande et en effectuer le parsing.

* [Créer une application depuis ce template](#créer-une-application-depuis-ce-template)
* [Construire votre application](#construire-votre-application)
    * [Changer le nom de l'application](#changer-le-nom-de-lapplication)
    * [Lancer l'application localement](#lancer-lapplication-localement)
    * [Afficher l'aide de l'application](#afficher-laide-de-lapplication)
    * [Construire le livrable de l'application](#construire-le-livrable-de-lapplication)
* [Installer l'application](#installer-lapplication)
    * [Shell de lancement](#shell-de-lancement)
    * [Lancer l'application installée](#lancer-lapplication-installée)
* [Exécuter les tests](#exécuter-les-tests)
* [Autres templates](#autres-templates)

## Créer une application depuis ce template

Pour créer une application depuis ce template, on procédera comme suit :

<a href="https://asciinema.org/a/740271" target="_blank"><img src="https://asciinema.org/a/740271.svg" /></a>

Vous trouverez sur [GitHub](https://github.com/grumpyf0x48/java-25-cli-16168)
l'application générée dans cet asciicast.

## Construire votre application

Après avoir [créé le dépôt GitHub](https://github.com/new?template_name=java-25-quickstart&template_owner=java-cli-apps)
de votre nouvelle application à partir de ce template, vous pouvez suivre les étapes suivantes pour construire votre application.

### Changer le nom de l'application

Pour changer le nom de l'application, initialement nommée _Quickstart_, il faut positionner la variable `APP_NAME`
dans le terminal comme suit :

```bash
$ export APP_NAME=MyCmdLine
```

### Lancer l'application localement

```bash
$ make run-app
```

```console
./gradlew run --args="--language French"
Bonjour 🇫🇷
```

### Construire le livrable de l'application

Le livrable de l'application comprend les sources ainsi que ses dépendances.

```bash
$ make package
```

```console
./gradlew scriptsDistZip
```

#### Shell de lancement

Le script de lancement [Application.sh](bin/Application.sh), dont le rôle est de lancer le fichier
[Application.java](src/main/java/Application.java), est renommé lors de la construction du livrable en `MyCmdLine.sh`.

Cela permet d'ajouter plusieurs applications dans le `PATH` et donc d'invoquer directement `MyCmdLine.sh`.

### Installer l'application

```bash
$ DEST_DIR=/home/user make install
```

```console
unzip -q -d /home/user build/distributions/QuickStart.zip
```

### Lancer l'application installée

```bash
$ DEST_DIR=/home/user make run-installed-app
```

```console
PATH=/home/user/QuickStart/bin:/usr/lib/jvm/jdk-25/bin:/home/fopy/.local/bin:... QuickStart.sh --language French
Bonjour 🇫🇷
```

Il ne nous reste plus qu'à :

- Implémenter notre métier dans [Application.java](src/main/java/Application.java)
- Ajouter dans le répertoire [src/main/java](src/main/java) les fichiers qui déclarent les classes utilisées par `Application.java`
- Ajouter les caractéristiques de nos dépendances dans [gradle/libs.versions.toml](gradle/libs.versions.toml) et [build.gradle.kts](build.gradle.kts)
- Implémenter des tests


## Exécuter les tests

Dans ce template utilisant Gradle, les tests utilisent JUnit, et contrairement
au template [basic-java-25-quickstart](https://github.com/java-cli-apps/basic-java-25-quickstart)
ils n'utilisent pas la JEP 458: les sources sont compilées avant les tests.

```bash
$ make test
```

## Autres templates

### Sans utiliser Java 25

Si vous ne disposez pas de Java 25, vous avez trois possibilités :

- Utiliser les templates [java-23-quickstart](https://github.com/java-cli-apps/java-23-quickstart) ou
  [java-22-quickstart](https://github.com/java-cli-apps/java-22-quickstart) qui ne nécessitent
  respectivement que la version 23 ou 22 de Java.
- Utiliser le template [basic-java-11-quickstart](https://github.com/java-cli-apps/basic-java-11-quickstart)
  qui ne requiert que la version 11 de Java. Il est alors nécessaire que tout le code Java réside dans le même fichier
  comme décrit dans la [JEP 330](https://openjdk.org/jeps/330).

### Sans utiliser Gradle

Si vous ne disposez pas de Gradle ou que vous ne souhaitez pas l'utiliser, vous
pouvez utiliser les templates [basic-java-25-quickstart](https://github.com/java-cli-apps/basic-java-25-quickstart)
ou [basic-java-23-quickstart](https://github.com/java-cli-apps/basic-java-23-quickstart)
qui ne requierent que `make` pour construire l'application.
