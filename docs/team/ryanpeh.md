---
layout: page
title: Ryan Peh's Project Portfolio Page
---

### Project: AddressBook Level 3

**SmartNUS** is a desktop app specifically designed for students to revise for their exams. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 15 kLoC.

Given below are my contributions to the project.

* **Implemented Quiz Functionality:**
  * Implemented quiz functionality to allow the user to quiz themselves on various question types from the question bank. This includes answering questions, navigation between questions and tracking of quiz progress.
  * Justification: This is a core feature of SmartNUS, as the application is designed to promote active recall through quizzing yourself.
  * Highlights: This enhancement required an analysis on various design implementations. The eventual design included the implementation of an alternative parser during to quiz to parse user inputs without affecting the main window. TODO
  

* **Designed and Implemented Quiz User Interface:**
  * Designed and implemented a graphical user interface (UI) for the quiz feature of SmartNUS, taking into account user (UX) to ensure an intuitive and easy-to-use user experience of the quiz functionality.
  * Justification: As one of the core features of SmartNUS, it is important for the quiz to have a well-developed UI/UX to keep the user interested in using the application.
  * Highlights: The interface includes the support for displaying various question types during the quiz (Multiple Choice Questions, True False Questions, Short Answer Questions, etc.), and is easily extendable to include more question types in the future.
  * Credits: The code for the user interface was adapted from the AddressBook-Level3 project by [SE-EDU initiative](https://se-education.org).


* **Other Features and Enhancements to Existing Features**:
  * Quiz
    * Implemented forward and navigation between quiz questions (Pull request [\#TODO]()).
    * Added quiz progress tracking to display quiz progress.
  * Wrote additional tests for existing features to increase coverage (Pull requests [\#abc](), [\#def]()).


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/#breakdown=true&search=ryanpeh)


* **Documentation**:
  * User Guide:
    * Added documentation for quiz related features [\#TODO]()
    * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
  * Developer Guide:
    * Added implementation details of the `delete` feature.

* **Project management**:
  * Managed deadlines and deliverables for the group, including updating the issue tracker and milestones.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]().
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]()).
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]()).

* **Team Based Tasks**:
  * Set up team GitHub organisation, repository and project website.
  * Integrated a new Github plugin (CodeCov) to the team repo.
  
