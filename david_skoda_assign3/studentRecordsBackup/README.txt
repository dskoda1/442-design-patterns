CS542 Design Patterns
Spring 2016
PROJECT 3 README FILE

Due Date: Tuesday, March 22 2016
Submission Date: Tuesday, March 22 2016
Grace Period Used This Project: 0 Days
Grace Period Remaining: 3 Days
Author(s): David Skoda
e-mail(s): dskoda1@binghamton.edu


This is the second version of the project, using lambda functions instead of
filters.

PURPOSE:
The purpose of this project is to understand and implement the Observer pattern
using Binary Search Trees. The BST's can be imagined as file systems, with one
as the subject and two as observers, and so whenever the subject is updated, the 
corresponding observers are updated as well (based on a filter check).

DATA STRUCTURE JUSTIFICATION:
I used a HashSet inside the node class, to store ObserverI instances when a node
is a subject. This allows for easy iteration over the observers, along with constant
time lookup if needed. The Observers themselves have a test method which calls the 
saved lambda function itself. This means the predicate is essentially attached to the 
observer, and in the future could allow for the observer to easily modify its filters
without the subject ever needing to worry itself about it. The observer could then
also combine various predicates through logical and's/or's and the testing behavior
would be identical to the subject checking the filter.

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
     [java] Before update
     [java] Tree 1
     [java] The sum of all B-Numbers is: 525847.
     [java] Tree 2
     [java] The sum of all B-Numbers is: 525847.
     [java] Tree 3
     [java] The sum of all B-Numbers is: 525847.
     [java] After Update
     [java] Tree 1
     [java] The sum of all B-Numbers is: 525863.
     [java] Tree 2
     [java] The sum of all B-Numbers is: 525863.
     [java] Tree 3
     [java] The sum of all B-Numbers is: 525847.

BUILD SUCCESSFUL
Total time: 1 second


TO COMPILE:
From the same directory of this readme, run: 
ant -buildfile src/build.xml all

TO RUN:

ant -buildfile src/build.xml run -Darg0=FileName -Darg1=UpdateValue -Darg2=DebugLevel
Sample running config:
ant -buildfile src/build.xml run -Darg0=525847.txt -Darg1=2 -Darg2=1 

Currently the debug levels work in the following manner:
0: Prints the sums of the B-Numbers.
1: Prints whenever a Node is inserted into a tree.
2: Prints when a nodes value is updated, along with the old and new values.
3: Prints the In-order traversal of each tree.
4: Prints when an object is constructed.

As specified in the requirements, printing is offered before and after the update.
This debug scheme lets you look at one particular area functionality at a time.


EXTRA CREDIT:
N/A


BIBLIOGRAPHY:

ACKNOWLEDGEMENT:
A lot
N/A
