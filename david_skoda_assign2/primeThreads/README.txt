CS542 Design Patterns
Spring 2016
PROJECT 2 README FILE

Due Date: Monday, February 29, 2016
Submission Date: Monday, February 29, 2016
Grace Period Used This Project: 0 Days
Grace Period Remaining: 3 Days
Author(s): David Skoda
e-mail(s): dskoda1@binghamton.edu


PURPOSE:

The purpose of this project is to read an input file, and accomplish a few things with that input. All of the following is to be done in a multithreaded fashion: Read a line, check if the number is prime, and place it in a data structure to be read later. Once all of the input has been processed, the threads will have completed their tasks, and the program will output the sum of all of the prime numbers it processed.

DATA STRUCTURE JUSTIFICATION:
For this project, I chose a vector due to a number of reasons. First, vector provides built in support for synchronization between threads. Also, due to the larger rate of increase (2x), less system calls will need to be made, as opposed to choosing a data structure which grows in size more slowly. We are only ever pushing items onto the back, which is O(1), and upon reading the vector at the end, that is done in O(n) time as it is just a sequential read. 

PERCENT COMPLETE:

100%

PARTS THAT ARE NOT COMPLETE:

All parts are complete to the best of my knowledge.

BUGS:

None. 

FILES:
All files are inside the primeThreads directory after unpacking. The code structure is exactly as in the code template that was supplied for this project. Only a single test file is supplied to minimize the size of the package.

SAMPLE OUTPUT:

remote03:~/Documents/442/david_skoda_assign2/primeThreads> ant -buildfile src/build.xml run -Darg0=input.txt -Darg1=3 -Darg2=0
Buildfile: /import/linux/home/dskoda1/Documents/442/david_skoda_assign2/primeThreads/src/build.xml
jar:
run:
     [java] The sum of all the prime numbers is: 1470310839
BUILD SUCCESSFUL
Total time: 1 second

TO COMPILE:

Change into the primeThreads directory, and run the following command
ant -buildfile src/build.xml all

TO RUN:


From the primeThreads directory run the following command:
ant -buildfile src/build.xml run -Darg0=input.txt -Darg1=3 -Darg2=0


EXTRA CREDIT:

N/A


BIBLIOGRAPHY:

This serves as evidence that we are in no way intending Academic Dishonesty.

http://www.programcreek.com/2011/03/java-read-a-file-line-by-line-code-example/


ACKNOWLEDGEMENT:


N/A


