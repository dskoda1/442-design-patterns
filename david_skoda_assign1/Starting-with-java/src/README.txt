CS542 Design Patterns
Spring 2016
Assignment 1 README FILE

Due Date: Monday, February 1, 2016
Submission Date: Sunday, January 31, 2016
Grace Period Used This Project: 0 Days
Grace Period Remaining: 3 Days
Author(s): David Skoda
e-mail(s): dskoda1@binghamton.edu


PURPOSE:

The purpose of this project is to take an xml file as input, and output
the name of the xs:element that occurs the most.

DATA STRUCTURES:

In StringOperations, I chose to use a HashMap for storing each element
name and the number of occurrences. This works well because the time
complexity for lookup is O(1), and I needed to do a lookup every time
an element name was found. I would rather have the O(1) lookup time and
be forced to iterate at the end to find the highest number of
occurrences, as opposed to getting a slower lookup/insert and getting
the highest number of occurrences in O(1), since lookup and insert are
performed many more times.
The array list I use in StringOperations is only used at the end for
storing element names if there are multiple that share the same # of
occurrences, so it suits a small number of items well.

PERCENT COMPLETE:

I believe I have completed 100% of this project.

PARTS THAT ARE NOT COMPLETE:

No part of this project is not complete I believe.

BUGS:

None.

FILES:

Included in the david_skoda_assign1/Working-with-java/src directory are
Driver.java
FileProcessor.java
StringOperations.java
README.txt

SAMPLE OUTPUT:

The most frequently occurring element is ASIN. It appears 15 times.
This is the output from a copy of amazon.wsdl THAT I MODIFIED.

TO COMPILE:

After extracting the files and cd'ing into the src directory, run:

javac *.java

TO RUN:

After compiling, simply run java Driver <path to xml file>

EXTRA CREDIT:

N/A


BIBLIOGRAPHY:

This serves as evidence that we are in no way intending Academic Dishonesty.
David Skoda

Some Java input code was borrowed from this url:
http://www.programcreek.com/2011/03/java-read-a-file-line-by-line-code-example/

ACKNOWLEDGEMENT:

N/A
