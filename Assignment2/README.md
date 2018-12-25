# Backup System for Student Courses Allocation

-----------------------------------------------------------------------
## Description
This project implements a backup system for the courses allocated to the students. The data structure used for storing the student information is Binary Search Tree (BST), since the BST supports the following operations insert, search and delete nodes. Each node consists of bNumber (student id) and list of allocated courses. The bNumber is used as key for the BST. Since a student can be allocated to multiple courses, the allocated courses are stored using a list. The BST itself is backed up using the observer pattern. There are two backup trees, which are copies of the original tree and are maintained using the Observer pattern. Each node of the tree implements the SubjectI interface and ObserverI interface.

-----------------------------------------------------------------------
## Inputs
input_file.txt with the following format: <B_NUMBER>:<ALLOCATED_COURSE><br/>
delete_file.txt with the following format: <B_NUMBER>:<ALLOCATED_COURSE>

-----------------------------------------------------------------------
Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in studentCoursesBackup/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile studentCoursesBackup/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile studentCoursesBackup/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

####Command: ant -buildfile studentCoursesBackup/src/build.xml run -Darg0=<input_file.txt> -Darg1=<delete_file.txt> -Darg2=<output1_file.txt> -Darg3=<output2_file.txt> -Darg4=<output3_file.txt>

Note: Arguments accept the absolute path of the files.