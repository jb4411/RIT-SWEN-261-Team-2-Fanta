---
geometry: margin=1in
---
# PROJECT Design Documentation

> _The following template provides the headings for your Design
> Documentation.  As you edit each section make sure you remove these
> commentary 'blockquotes'; the lines that start with a > character
> and appear in the generated PDF in italics._ DELETE THESE LAST

## Team Information
* Team name: 2208-SWEN-261-01-2-Fanta
* Team members
  * Dominic Kavanagh
  * Jesse Burdick-Pless - jb4411@rit.edu
  * Jess Zhao
  * Eric Landers

## Executive Summary

WebCheckers is a web-app that simulates checkers played with American rules.

The goal for the project is to allow players to sign in and challenge others players to a game of checkers. 
In addition, we plan to add a spectator mode and a method to save replays of past games to further enhance the player 
experience.

The application uses Spark and Freemarker for webpage rendering.


### Purpose
The WebCheckers application allows users to play checkers anytime, anywhere. It provides a simple way for players to 
challenge each other to a classic game of checkers right from the convenience of their web browser.

### Glossary and Acronyms
> _Provide a table of terms and acronyms._

| Term | Definition |
|------|------------|
| VO | Value Object |


## Requirements

This section describes the features of the application.

> _In this section you do not need to be exhaustive and list every
> story.  Focus on top-level features from the Vision document and
> maybe Epics and critical Stories._

### Definition of MVP
> _Provide a simple description of the Minimum Viable Product._

### MVP Features
> _Provide a list of top-level Epics and/or Stories of the MVP._

### Roadmap of Enhancements
> _Provide a list of top-level features in the order you plan to consider them._


## Application Domain

A game of WebCheckers is played by two players on an 8 by 8 checkerboard. Each player starts with 12 pieces (either
red or black), which they control. On their turn, a player moves one piece of their color. The user interface
consists of the home page, the signin page, and the game page. Players start on the home page, from which they can get
to the signin page. Once a player signs in with a unique username, they will be redirected back to the home page where
they will now be able to see other active players. At this point they can either wait to be challenged by another
player, or they can challenge a player. Once they have challenged a player, or have been challenged by another player,
both players will be redirected to the game page. Once on the game page, starting with the red player, players take
turns making moves. After the current player makes a move, their turn ends, and the other player's turn begins. This
cycle continues until a player wins the game or a player resigns.

![The WebCheckers Domain Model](WebCheckers_Team_2_Domain_Model.png)

## Architecture and Design

This section describes the application architecture.

### Summary

The following Tiers/Layers model shows a high-level view of the webapp's architecture.

![The Tiers & Layers of the Architecture](architecture-tiers-and-layers.png)

As a web application, the user interacts with the system through their browser. The client-side of the UI is comprised 
of HTML pages with some minimal CSS for styling. There is also some JavaScript that has been provided to the team by the
architect.

The server-side tiers include the UI tier that is composed of UI controllers and views. Controllers are built using the 
Spark framework and the views are built using the FreeMarker engine. The application and model tiers are built using 
Java objects.

### Overview of User Interface

The user interface consists of the home page, the signin page, and the game page.

The user starts off at the homepage, from which they can get to the signin page. On the signin page they can sign in
with a valid, unique username.  Once a player signs in, they are redirected back to the home page where they can see the 
names of other active players. At this point they can either wait to be challenged by another player, or they can 
challenge an unoccupied player.

Once on the game page, starting with the red player, players take turns making moves. After the current player finishes 
making a move, their turn ends, and the other player's turn begins. This cycle continues until a player wins the game, 
or a player resigns.

![The WebCheckers Web Interface Statechart](StateChart_WebCheckers.png)



### UI Tier
> _Provide a summary of the Server-side UI tier of your architecture.
> Describe the types of components in the tier and describe their
> responsibilities.  This should be a narrative description, i.e. it has
> a flow or "story line" that the reader can follow._

> _At appropriate places as part of this narrative provide one or more
> static models (UML class structure or object diagrams) with some
> details such as critical attributes and methods._

> _You must also provide any dynamic models, such as statechart and
> sequence diagrams, as is relevant to a particular aspect of the design
> that you are describing.  For example, in WebCheckers you might create
> a sequence diagram of the `POST /validateMove` HTTP request processing
> or you might show a statechart diagram if the Game component uses a
> state machine to manage the game._

> _If a dynamic model, such as a statechart describes a feature that is
> not mostly in this tier and cuts across multiple tiers, you can
> consider placing the narrative description of that feature in a
> separate section for describing significant features. Place this after
> you describe the design of the three tiers._


### Application Tier
> _Provide a summary of the Application tier of your architecture. This
> section will follow the same instructions that are given for the UI
> Tier above._


### Model Tier
> _Provide a summary of the Application tier of your architecture. This
> section will follow the same instructions that are given for the UI
> Tier above._

### Design Improvements
> _Discuss design improvements that you would make if the project were
> to continue. These improvement should be based on your direct
> analysis of where there are problems in the code base which could be
> addressed with design changes, and describe those suggested design
> improvements. After completion of the Code metrics exercise, you
> will also discuss the resutling metric measurements.  Indicate the
> hot spots the metrics identified in your code base, and your
> suggested design improvements to address those hot spots._

## Testing
> _This section will provide information about the testing performed
> and the results of the testing._

### Acceptance Testing
> _Report on the number of user stories that have passed all their
> acceptance criteria tests, the number that have some acceptance
> criteria tests failing, and the number of user stories that
> have not had any testing yet. Highlight the issues found during
> acceptance testing and if there are any concerns._

### Unit Testing and Code Coverage
> _Discuss your unit testing strategy. Report on the code coverage
> achieved from unit testing of the code base. Discuss the team's
> coverage targets, why you selected those values, and how well your
> code coverage met your targets. If there are any anomalies, discuss
> those._
