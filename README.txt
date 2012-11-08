Workshop testing
================

Welcome to the agile testing workshop. In this project you'll be invited
to work with, and gain experience with FitNesse.

Get this stuff up and running
-----------------------------

The workshop application can be started via Ant as well as with Maven.

The Ant version can be ran off-line, given the dependencies are cached (they
are cached in a directory local-cache/).

Ant
---

To start the web server (the monopoly game) run

	$ ant server

A webserver will be launched and listen to port 8080.

To start the FitNesse server run

	$ ant fitnesse

FitNesse can be accessed via http://localhost:9000.

Maven
-----

To start the server:

	$ mvn jetty:run

To start fitnesse:

	$ mvn -Pfitnesse test



