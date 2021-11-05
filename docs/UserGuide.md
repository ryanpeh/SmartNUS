---
layout: page
title: User Guide
---

<div class="no-bullet-points">
  * Table of Contents
  {:toc}
</div>

--------------------------------------------------------------------------------------------------------------------

## 1. Introduction

SmartNUS is a **desktop app for students to revise for their exams, optimized for use via a Command Line Interface** (CLI)
while still having the benefits of a Graphical User Interface (GUI). SmartNUS allows you to create a question bank and
quiz yourself, as well as add notes for revision. If you can type fast, SmartNUS can make your review
sessions more effective and faster than traditional GUI apps.

Interested in using our app? Check out the [Quick Start section](#quick-start) to get started!

## 2. About

This section will provide you with the details on how to navigate and interpret the user guide. This guide includes all the features the app provides, with in-depth explanations and examples on how to use them. 

### 2.1. Using the User Guide

The user guide has been structured in a way that makes it easy for you to navigate and find what you need.

Here's a quick summary of the available sections in the user guide:

<!-- TODO: Ensure that the numberings below are correct at the end -->
* [Section 1: Introduction](#1-introduction) - Introduction of SmartNUS
* [Section 2: About](#2-about) - Introduction of SmartNUS
* [Section 3: Quick Start](#3-quick-start) - A quick start guide to get you started with using SmartNUS.
* [Section 4: Features](#4-features) - Documentation of all available features SmartNus has to offer.
* [Section 5: FAQ](#5-faq) - Frequently asked questions about SmartNUS.
* [Section 6: Command Summary](#6-main-window-command-summary) - Summary of available SmartNUS commands and formats.

### 2.2. Commands

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `mcq qn/QUESTION ans/ANSWER opt/OPTION1 i/IMPORTANCE`, `QUESTION`, `ANSWER`, `OPTION1`, `IMPORTANCE` is a parameter which can be used as `mcq qn/what is 1 + 1? opt/3 opt/1 opt/0 ans/2 i/1`.

* Items in square brackets are optional.<br>
  e.g `qn/QUESTION [t/TAG]` can be used as `qn/What is 1+1? t/math` or as `qn/What is 1+1?`.

* Items with `…`​ after them can be used multiple times.<br>
  e.g. `opt/…​` can be used as `opt/ OPTION1`, `opt/ OPTION1 opt/ OPTION2` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `ans/ ANSWER opt/ OPTION1`, `opt/ OPTION1 ans/ ANSWER` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `ans/t ans/abc`, only `ans/abc` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* All commands are case-sensitive unless otherwise stated.<br>
 e.g. `help` or `/ans` is acceptable, but `Help` or `Ans/` is not acceptable.<br>

</div>

## 3. Quick Start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `smartnus.jar` from [here](https://github.com/AY2122S1-CS2103T-F12-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your SmartNUS app.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/user-guide/UiMainWindow.png)

<!-- 1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app. -->

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## 4. Features

This section lists all the commands SmartNUS supports, with information about the commands as well as relevant examples.

### 4.1. Main Window Commands

#### 4.1.1. Viewing Help : `help`

Shows a message explaning how to access the help page.

![help message](images/user-guide/helpMessage.png)

Format: `help`


#### 4.1.2. Add a Multiple Choice Question: `mcq`

Adds a multiple choice question to the question bank.

Format: `mcq qn/QUESTION opt/OPTION1 opt/OPTION2 opt/OPTION3 ans/ANSWER i/IMPORTANCE [t/TAG]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A multiple choice question must have exactly three incorrect options and one correct answer
</div>

Examples:
* `mcq qn/what is 1 + 1? opt/3 opt/1 opt/0 ans/2 i/1`

#### 4.1.3. Add a True False Question: `tfq`

Adds a true false question to the question bank.

Format: `tfq qn/QUESTION ans/ANSWER i/IMPORTANCE [t/TAG]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A true false question can only have one answer, either "T" or "F"
</div>

Examples:
* `tfq qn/Is 1+1 = 2 ? ans/T i/1`

#### 4.1.4. Add a Short Answer Question: `saq`

Adds a short answer question to the question bank.

Format: `saq qn/QUESTION ans/ANSWER INCLUDING KEYWORDS k/KEYWORD... i/IMPORTANCE [t/TAG]...`

* Each short answer question must have exactly one answer.
* The answer must include at least one keyword which is specified using `k/` (e.g. `ans/k/powerhouse of the k/cell`).
* Note that keywords MUST be specified within the answer to be recognised as keywords 
  * `qn/my question? k/KEYWORDOUTSIDEANSWER ans/my answer i/2` is an invalid command.
  * See examples given below for valid commands to try out.
* Keywords are stored in lowercase. Specifying `k/ABC` and `k/abc` both result in "abc" being stored as the keyword. However,
  the answer will be displayed with the correct case ("ABC" and "abc" respectively).
* During a [quiz](#start-a-quiz-quiz), any answer that contains all the keywords (case-insensitive)
in any order is considered correct (e.g. "DatAstrUcturesandalgorithms" will be a correct answer
to a question whose keywords are "structure" and "data").
* Keywords are made up of alphanumeric characters.
  When specifying a keyword that includes non-alphanumeric characters
  (e.g. `k/"harry, !'abc@#^e,y?`), only the first valid part of the word
  (continuous string of alphanumeric characters) will be taken as the keyword i.e. "harry".

Examples:
* `saq qn/What does mRNA stand for? ans/k/messenger k/ribonucleic k/acid i/1`
* `saq qn/Who wrote The Merchant of Venice? ans/William k/Shakespeare i/2 t/literature t/classics`
* `saq qn/You're a wizard, Harry. Which book is this quote from? ans/k/Harry k/Potter and the Philosopher's Stone i/2`
  * Answer is displayed as "Harry Potter and the Philosopher's Stone".
  * During a [quiz](#start-a-quiz-quiz):
    * Correct answers include "haRRy PoTtEr", "Harry Potter and the Philosopher's Stone",
      "potter harry", "harrypotter" and "wordthatincludesharryandpotter".
    * Incorrect answers include "Potter" and "harr pottery".

#### 4.1.5. List All Items : `list`

Shows a list of all questions, notes, or tags stored in SmartNus.

Format: `list question` OR `list note` OR `list tag`

#### 4.1.6. Delete a Question or Note: `delete`

Deletes an existing question or note from the question bank or note list.

Format: `delete question QUESTION_INDEX` OR `delete note NOTE_INDEX`

* Deletes the question or note with the specified `QUESTION_INDEX` or `NOTE_INDEX`.
* The `QUESTION_INDEX` or `NOTE_INDEX` refers to the index number shown in the displayed list. The index **must be a positive integer** between 1 and 2147483647 (both inclusive) 

#### 4.1.7. Edit a Question and Answers: `edit`

Edits an existing question in the question bank with the specified question number.

Format: `edit QUESTION_ID [qn/QUESTION] [t/TAG]... [ans/CORRECT_ANSWER] [opt/INCORRECT_OPTION]... [i/IMPORTANCE]`

* Existing values will be updated to the input values. 
* You cannot edit question type (e.g. cannot edit a Multiple Choice Question to a True-False Question)
* At least one of the optional fields must be provided.


* Edit Tags 
  * When editing tags, the existing tags of the question will be removed i.e adding of tags is not cumulative. 
  * You can remove all the question’s tags by typing `t/` without specifying any tags after it.


* Edit Answers/Options 
  * If editing the answers of a question, all option(s) and answer(s) must be valid for the type of question being edited. 
  * Multiple Choice Question: Specify all three incorrect options (`opt/`) and one correct answer (`ans/`).
  * True/False Question: Only specify the correct answer (`ans/`), which must be “T” or “F”.
  * Short Answer Question: Only specify the correct answer (`ans/`) which must include at least one keyword (`k/`).

Examples:
* Multiple Choice Question (MCQ): `edit 1 opt/1 opt/2 opt/3 ans/4 t/` sets the incorrect options to 1, 2, and 3,
and the correct answer to 4, and removes all the tags from Question 1 if it is an MCQ
* True/False Question (TFQ): `edit 2 ans/T` sets the answer of Question 2 to True if it is a TFQ.
* Short Answer Question (SAQ): `edit 3 ans/k/powerhouse of the k/cell t/CS2100`
sets the answer of Question 3 to powerhouse of the cell, with keywords "powerhouse" and "cell",
and replaces all tags with CS2100 if Question 3 is an SAQ.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**

If you were previously in a filtered view (e.g. after running the `find CS2100` command),
and your edited question no longer matches this filter (e.g. question title no longer contains "CS2100"),
your edited question will not be shown on-screen. To see a list of all questions,
run the `list question` command.

</div>

#### 4.1.8. Find/Search Questions: `find`

Shows a list of all questions in SmartNUS that have all the specified keywords,
at least one of the specified tags, and the importance value (if specified).

Format: `find [KEYWORDS]... [t/TAG]... [i/IMPORTANCE]`

* At least one of the optional fields to find by must be specified.
* The search is case-insensitive for both keywords and tags (e.g. `math` will match `MaTH`).
* Only full words will be matched for both keywords and tags (e.g. `CS2100` will not match `CS210`).
* The following characters `,.?!:;*"()[]{}` which are commonly used to separate words are not considered part of a word or keyword. 
Instead, they are considered as word separators similar to a space.
  * `find *("literature":,;?!)]}` returns the same result as `find literature`.
  * `find ,:;?(]` is an invalid command as it is the same as finding a blank keyword or only inputting spaces as keywords.
  * `find first? second (third...)!` is the same as `find first second third`.
* Hyphenated words are considered as one word (e.g. `find grey-box` will not return a question titled `grey box`).
* Any question that has at least one of the tags **AND** all the keywords in its title (in any order)
**AND** the importance specified will be listed.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You can search for more than one tag or keyword.
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**

To return to the list of all questions, use the `list question` command.

</div>

Examples:
* `find load word t/CS2100 t/MIPS i/2` returns questions tagged with at least one of the tags, with importance value 2 and whose title
includes "load" and "word" in any order.
  * e.g. A question titled "What is the load word instruction used for?" tagged with only CS2100 and with an importance value of 2 will be listed.
* `find java` returns a question titled "How do you output text to the console in Java?" but not a question titled "Javascript is commonly used in web development. True or false?" (since `java` is not a full word match for `javascript`).

<!-- TODO: standardise format, remove params from header, add brief description-->
#### 4.1.9. Find/Search Stats: `stat [t/TAG]...`

* Shows the list of statistics by Tag for the questions attempted.
* The search is case-insentitive for tags
* Only full words will be matched (e.g. `CS2100` will not match `CS210`)
* Statistics for any of the tags passed in will be shown
* If no parameters are passed in, it will show all statistics

Examples:
* `stat t/CS2100 t/MIPS` returns the overall statistics for the questions tagged with `CS2100` or `MIPS` or both.

<!-- TODO: add brief description before format to standardise format-->
#### 4.1.10. Start a Quiz: `quiz`
Format: `quiz [lim/ LIMIT] [t/TAG]... [n/INDEX]`

* If no parameters are passed, a quiz session will be created using all the questions in the question list.
* LIMIT is a positive, non-zero integer that will limit the number of questions in the quiz.
  * If the total number of questions is less than the limit, it will just give all the questions.
* TAG can be used to filter the quiz to only give questions with the tags specified, works with limit.

Examples:
* `quiz lim/5 t/CS2100 t/MIPS` quiz will select questions tagged with at least one of the tags, limited to 5 questions.

Format: `quiz`

* Opens a new window for the quiz.

Examples:
*  `quiz` opens a new quiz window and only [quiz commands](#quiz_window_commands) can be used.

#### 4.1.11. Clear All Entries : `clear`

Clears all entries (questions, notes and tags) from the question bank.

Format: `clear`

#### 4.1.12. Exit the Program : `exit`

Exits the program.

Format: `exit`

#### 4.1.13. Change the Theme: `theme`

Changes the theme of the app.

Format: `theme THEME`

Parameters:
- `THEME`: can only be light or dark

Examples:
- `theme light`: Sets the theme to light
- `theme dark`: Sets the theme to dark

### 4.2. Quiz Window Commands

#### 4.2.1. Answer a Multiple Choice Question: `A` `B` `C` `D`
Answers the multiple choice question with the options.

Format: `A` `B` `C` or `D` (case-insensitive)

#### 4.2.2. Answer a True False Question: `T` `F`
Answers the multiple choice question with the options.

Format: `T` `F` `True` `False` (case-insensitive)

#### 4.2.3. Answer a Short Answer Question: `ans/`
Answers the short answer question with the provided answer.

Format: `ans/ANSWER`

Examples:
* `ans/Harry Potter` answers the short answer question with "Harry Potter".

#### 4.2.4. Go to the Next Question: `next`
Navigate to the next question.

Format: `next`

#### 4.2.5. Go to the Previous Question: `prev`
Navigate to the previous question.

Format `prev`


#### 4.2.6. Exit the Quiz: `exit`
Exits the quiz.

Format: `exit`

--------------------------------------------------------------------------------------------------------------------

## 5. FAQ

**Q**: How do I save my data?<br>
**A**: SmartNUS data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous SmartNUS home folder.

**Q**: Am I allowed to edit the data file directly?<br>
**A**: SmartNUS data are saved as a JSON file `[JAR file location]/data/smartnus.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, SmartNUS will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## 6. Command Summary
<!-- TODO: Ensure that all commands are added below -->
### 6.1. Main Window Command Summary

Action | Format, Examples
--------|------------------
**MCQ** | `mcq qn/QUESTION opt/OPTION1 opt/OPTION2 opt/OPTION3 ans/ANSWER i/IMPORTANCE` <br> e.g., `mcq qn/what is 1 + 1? opt/3 opt/1 opt/0 ans/2 i/1`
**SAQ** | `saq qn/QUESTION ans/ANSWER INCLUDING KEYWORDS k/KEYWORD... i/IMPORTANCE [t/TAG]...` <br> e.g., `saq qn/what is Shakespeare's first name? ans/k/William i/1`
**Delete** | `delete question QUESTION_INDEX` OR `delete note NOTE_INDEX`<br> e.g., `delete question 1`, `delete note 2`
**Quiz** | `quiz [lim/ LIMIT] [t/TAG]... [n/INDEX]` <br>
**Exit** | `exit` <br>
**Help** | `help` <br>

### 6.2. Quiz Window Command Summary

Action | Format, Examples
--------|------------------
**Option A** | `A` <br>
**Option B** | `B` <br>
**Option C** | `C` <br>
**Option D** | `D` <br>
**Option True** | `T`, `True` (Case-insensitive) <br> 
**Option False** | `F`, `False` (Case-insensitive) <br>
**SAQ Answer** | `ans/ANSWER`<br> e.g., `ans/Harry Potter` <br>
**Next** | `next` <br>
**Prev** | `prev` <br>
**Exit** | `exit` <br>
