CS542 Design Patterns
Spring 2016
PROJECT 3 README FILE

Due Date: Tuesday, March 22 2016
Submission Date: Sunday, March 20 2016 
Grace Period Used This Project: 0 Days
Grace Period Remaining: 3 Days
Author(s): David Skoda
e-mail(s): dskoda1@binghamton.edu

NOTE:
This is the first version of the project, with the original design.

PURPOSE:
The purpose of this project is to understand and implement the Observer pattern
using Binary Search Trees. The BST's can be imagined as file systems, with one
as the subject and two as observers, and so whenever the subject is updated, the 
corresponding observers are updated as well (based on a filter check).

DATA STRUCTURE JUSTIFICATION:
I used a HashMap inside the node class, with a FilterI as key and an Observer as
the value. I decided on this data structure because I can iterate through the
map, and check each filter individually, without needing to call the observer 
method if the predicate does not pass. Also, if it does pass, the hash map provides
constant time lookup to find the matching observer.

PERCENT COMPLETE:
100%

PARTS THAT ARE NOT COMPLETE:
All parts are complete to the best of my knowledge.

BUGS:
There are no bugs to the best of my knowledge.

FILES:
---studentRecordsBackup
     ----- README.txt
     ----- src
          ----- build.xml
          ---studentRecordsBackup
           ----------driver
           -----------------Driver.java
           ----------util
           -----------------BSTBuilder.java
           -----------------OddEvenFilterI.java
           -----------------EvenFilter.java
           -----------------OddFilter.java
           -----------------FileProcessor.java
           -----------------Logger.java
           ----------bst	   
           -----------------Node.java
           -----------------SubjectI.java
           -----------------ObserverI.java
           -----------------BST.java


SAMPLE OUTPUT:
Ran with a file that has a sum of 525847, an update value of 2, and debug value of 0 (print the sum):

remote02:~/Documents/442/david_skoda_assign3/studentRecordsBackup> ant -buildfile src/build.xml run -Darg0=525847.txt -Darg1=2 -Darg2=0
Buildfile: /import/linux/home/dskoda1/Documents/442/david_skoda_assign3/studentRecordsBackup/src/build.xml

jar:

run:
     [java] Tree
     [java] The sum of all B-Numbers is: 525879.
     [java] Tree
     [java] The sum of all B-Numbers is: 525847.
     [java] Tree
     [java] The sum of all B-Numbers is: 525879.

BUILD SUCCESSFUL
Total time: 1 second


TO COMPILE:
From the same directory of this readme, run: 
ant -buildfile src/build.xml all

TO RUN:

ant -buildfile src/build.xml run -Darg0=FileName -Darg1=UpdateValue -Darg2=DebugLevel
Sample running config:
ant -buildfile src/build.xml run -Darg0=525847.txt -Darg1=2 -Darg2=1 


EXTRA CREDIT:
N/A

BIBLIOGRAPHY:
https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
http://www.oracle.com/technetwork/articles/java/ma14-java-se-8-streams-2177646.html

ACKNOWLEDGEMENT:
N/A
