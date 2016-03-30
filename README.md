# CSCI-UA-480: Algorithmic Problem Solving
By Jason Yao, [github](https://github.com/JasonYao/Algorithmic-Problem-Solving)

## Description
An repository to store all completed homework and recitation assignments for the
"Algorithmic Problem Solving (CSCI-UA-480)" class.

Date: Spring, 2016

Location: New York University

Professor: Dr. John Zhang

Language(s): Java (1.7.x due to older java compilers on the online judges)

## Course outcomes
- Developed more dev-ops ability from scratch (the ability to automatically start with boilerplate),
and to design a very efficient workflow for programming contests

- Developed greater understanding of algorithmic approaches, and more importantly their implementations.

- Learned the basics of competitive programming

- Competed in Google's 2016 [Code Jam](https://code.google.com/codejam)

- Implemented memoisation, recursion, graph traversal algorithms, and many more

- Learned max-flow & min-cut <-- cool stuff actually that surprisingly relates to machine image processing

- Became much more familiar than I already was with the Java API

## Setup instructions
In order to make your life easier, I've thrown together a setup script that should help with anybody in APS.

```sh
./setup
```
That's basically it. Now, when you're doing homework, say for example for the homework assigned in week 6,
simply copy over the directory with the following commands:

```sh
cd homeworks
cp -r JavaBoilerplate ex6
```
You can really call it whatever you like, but this way allows you to keep good records for your code.

## What's in this repo

### [Homework](homeworks/)
The homework sets that we are given each week, mostly using UVA's established 
questions, in which we need to send our solutions to be graded in a binary 
fashion by an online automated judge.

### [Recitations](recitations/)
The recitation sets that we are given each friday, in which we are given a time 
limit of 2 hours to implement **correct** solutions to an online automated judge.

### [Java Boilerplate](JavaBoilerplate)
This folder contains everything that I believe to be necessary at the start of 
each recitation/homework. This folder will be copied over to each new week as 
the course continues, and because it is symlinked, a single point of boilerplate 
and reference code may be kept.

### [Google's Code Jam Boilerplate](GoogleCodeJamBoilerplate)
As our midterms and finals are graded by the same system as Google's Code Jam
competitive programming contest, it is different from the normal workflow for 
submitting problems in recitations or homework sets.

### [Midterm solutions](midterm/)
These are my terrible solutions to the midterm. TODO upsolve remaining questions.

## License
This repo is licensed under the GNU GPL v3, a copy of which may be found [here](LICENSE)
