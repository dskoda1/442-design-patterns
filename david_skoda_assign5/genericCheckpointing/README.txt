CS542 Design Patterns
Spring 2016
PROJECT 5 README FILE

Due Date: Tuesday, May 3rd 2016
Submission Date: 
Grace Period Used This Project: 0 Days
Grace Period Remaining: 3 Days
Author(s): David Skoda
e-mail(s): dskoda1@binghamton.edu


PURPOSE: 

DATA STRUCTURE JUSTIFICATION: 

PERCENT COMPLETE:
100%

PARTS THAT ARE NOT COMPLETE:
All parts are complete to the best of my knowledge.

BUGS:
There are no bugs to the best of my knowledge.

FILES:
 -david_skoda_assign5
 ---genericCheckpointing
    ----build.xml
    ----README.txt
    ----src
      ---genericCheckpointing
        ------driver
          ---------Driver.java
        ------server
          ---------StoreRestoreI.java [tag interface]
          ---------StoreI.java 
          ---------RestoreI.java 
        ------util
          ---------MyAllTypesFirst.java 
          ---------MyAllTypesSecond.java 
          ---------ProxyCreator.java 
          ---------SerializableObject [empty base class]
        ------xmlStoreRestore
          ---------StoreRestoreHandler.java  (implements InvocationHandler)

TO COMPILE:
From the same directory of this readme, run: 
ant -buildfile src/build.xml all

TO RUN:
ant -buildfile src/build.xml run -Darg0=InputFile -Darg1=OutputFile 

Sample running config:
ant -buildfile src/build.xml run -Darg0=Large.txt -Darg1=output.txt 

SAMPLE OUTPUT:


EXTRA CREDIT:
N/A

BIBLIOGRAPHY:

ACKNOWLEDGEMENT:
N/A
