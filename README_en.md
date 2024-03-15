**Mastermind**
================

![](mastermind.jpg)

The objective of this project is to design an application allowing a player to play Mastermind against the computer.

General Instructions
---------------------

#### To start with the Git repository

1. Create a group `lastname1-lastname2` where lastname1 and lastname2 are the last names of the two members of the team.
1. Fork the repository https://git.unistra.fr/a313/a31-mastermind into the created group above.
1. Add the module supervisor and your TD/TP instructor as Reporters to your repository.

#### Throughout the project

- This project is to be completed **in pairs**.
- You must design in UML and implement in Java the game of Mastermind, respecting a **MVC architecture** and providing a **graphical interface in Swing**.
- You must adhere to **design principles** and implement **design patterns** seen in class, **when relevant**. Note that the goal is not to try to use all design patterns seen in class!
- You will simultaneously write **report(s)** to explain your design choices. It's also possible that you may have to forgo a part of the development due to time constraints. In that case, the report is where to mention it ("I would have needed to do *this* but I didn't have enough time, and I preferred to focus on *that* because ...").
- Your repository should be **updated at least after each TP session** so that we can evaluate your approach throughout the project.

Game Rules
----------

For the classic rules, you can refer to:

- a simple version [https://www.regles-de-jeux.com/regle-du-mastermind/](https://www.regles-de-jeux.com/regle-du-mastermind/).
- a more detailed explanation: [https://fr.wikihow.com/jouer-au-Mastermind](https://fr.wikihow.com/jouer-au-Mastermind).

#### Vocabulary

In terms of vocabulary, we'll use the following terms:

- a **combination**: it's a line of pegs
  - there's the **secret combination** and the combinations attempted by the player, called **attempts**.
- an **index**: information about a peg in a combination, given by the computer, indicating whether the peg is correctly placed, misplaced, or absent
- a **line of indices**: it's the set of indices associated with a combination
- a **round**: it's the set of attempts whether they led to finding the secret combination or not
- a **game**: it's a set of rounds

Features
--------

You must model and implement:

- the generation of the secret combination,
- the generation of indices associated with a combination proposed by the player,
- detection of the player's victory and defeat,
- management of rounds and games,
- score management

taking into account these specifics:

- your application must have a maximum of **8 different pegs**
- before starting a game, the player sets these parameters that will be the same for all rounds:
  - the **number of rounds**: minimum 1, default 3, maximum 5
  - the **number of available pegs**: minimum 4, default 8, maximum 8
  - the **number of pegs in a combination**: minimum 2, default 4, maximum 6
  - the **maximum number of attempts** to find the secret combination: minimum 2, default 10, maximum 12
  - the **display mode of the indices**: easy, classic, or numeric
- the **score of a round** is calculated from the player's last attempt as the sum of the number of pegs misplaced, three times the number of pegs correctly placed, and 4 bonus points if in classic mode.

Graphical Interface
--------------------

Your application must offer 3 screens:

- a **startup screen** to choose the parameters: player's name, game type, etc.
- a **game screen** with the board displaying the attempted combinations, associated indices, etc.
- an **end-of-game screen** that displays the player's score and whether they won or lost.

#### User Interactions

A player must be able to perform the following actions **at any time during a game**:

- choose the colors for their next combination
- validate their combination to receive the computer's index
- reset their combination
- abandon the current round to move on to the next one

### Display of Indices

The display of indices depends on the mode chosen in the parameters:

- **"easy" mode**: black and white pegs are displayed corresponding to the combination proposed by the player (i.e., in the same position)
- **"classic" mode** (default mode): black pegs are displayed first, then white pegs
- **numeric mode**: display the number of pegs correctly placed and the number of pegs misplaced.

Bonus
-----

Ability to restart a game without relaunching the application.

Deliverables
------------

### 1st Delivery

Deadline: Sunday **10/12 at 23:59**

Documents:

- the UML **class diagram**
  - the `model` package should be described by anticipating all development
  - the `controller` and `view` packages should offer a basic version but may be incomplete
- the **source code**
- a **report** to explain your design choices

The submission is to be made on your Git repository **on a branch named `rendu1`**.

### 2nd Delivery

Deadline: Sunday **14/01 at 23:59**

Documents:

- the UML **class diagram**
	- it must be entirely consistent with the code
	- it must represent the entirety of your application
- the **source code**
- an **executable in JAR format**
- an **instruction manual** named `INSTALL.md` at the root of your project, explaining how to install and launch your application
- a **report** to present your new design choices since the 1st delivery and explain the reasons for the evolution of design choices that were announced at the 1st delivery.

The submission is to be made on your Git repository **on a branch named `rendu2`**.

### Clarifications

- Your UML diagrams should be in PlantUML and svg format,
- At the end of the project, your repository must contain at least one `rendu1` branch and one `rendu2` branch; these will be the only branches evaluated. You are free to manage the rest of your repository as you wish.
- Make sure to verify that your executable works on Linux, Windows, and Mac.
