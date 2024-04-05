# helidon-playground

📚 Learning and exploring the Helidon web server.

> Lightweight. Fast. Crafted for Microservices.
> 
> Helidon is a cloud-native, open‑source set of Java libraries for writing microservices that run on a fast web core
> powered by Java virtual threads.
> 
> -- <cite> https://helidon.io/ </cite>

Sample Helidon SE project that includes multiple REST operations.


## Overview

This repository is me learning Helidon. Specifically, I'm focusing on just the Helidon web server because this is what
I'm interested in, especially the support for virtual threads starting with Helidon 4.0 which is based on the finalization
of [*virtual threads*](https://openjdk.org/jeps/444) released in Java 21.

The overall Helidon project contains many other libraries like those for database access, configuration, security, etc.
There is also [Helidon MP](https://helidon.io/docs/v4/#/mp/introduction) which implements Eclipse MicroProfile and is in the area of Jakarta EE. Helidon MP is
described as a Spring Boot-like development experience.

I originally scaffolded this project with the Maven archetype in the [Helidon SE Quickstart](https://helidon.io/docs/v4/#/se/guides/quickstart),
but I've adapted it to my own preferences.


## Instructions

Follow these instructions to build and run the program:

1. Pre-requisite: Java 21
2. Build the program distribution
   * ```shell
     ./gradlew installDist
     ```
3. Run the program
   * ```shell
     build/install/helidon-playground/bin/helidon-playground
     ```
4. Exercise the program via an HTTP requests
   * Try the following request.
   * ```shell
     curl http://localhost:8080/always-lucky
     ```
   * It should respond with a lucky result.
   * ```text
     You rolled a 7!
     ```
   * Try some other requests.
   * ```shell
     curl http://localhost:8080/dice-roll
     ```
   * ```shell
     curl http://localhost:8080/dice-roll/5
     ```
5. Study the program distribution
   * The set of libraries (`.jar` files) needed is nice and low when using just the Helidon web server. This is nice
     because in the Java ecosystem we don't have many lean options. Specifically, look in the `build/install/helidon-playground/lib`
     directory. Use the following command.
   * ```shell
     ls -lh build/install/helidon-playground/lib
     ```
   * Or use the following command to sum up the sizes across all the `.jar` files.
   * ```shell
     du -sh build/install/helidon-playground/lib
     ```


## Wish List

General clean-ups, changes and things I wish to implement for this project:

* [x] DONE Scaffold.
* [x] DONE Convert to Gradle.
* [x] DONE Prune down to just the web server.
* [x] DONE Change the package name.
* [x] DONE Replace logging with SLF4J.
* [x] DONE Replace GreetService with something of my own creation.
