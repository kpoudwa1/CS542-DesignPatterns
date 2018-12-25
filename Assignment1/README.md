# Student Courses Allocation

-----------------------------------------------------------------------
## Description
The project is for allocating courses to students based on their priority (academic year) and preferences. The allocations are done in the decreasing order of priority with the highest priority given to third year students and lowest to first year students. While allocating courses the class time and capacity is also taken into consideration. The student is not allocated to different courses with same timing. The class time is represented by an integer value. The student is allocated to a course if the course has seats available.

-----------------------------------------------------------------------
## Inputs
Courses_Info.txt with the following format: <COURSE_NAME>-CAPACITY:<COURSE_CAPACITY>; CLASS_TIMING: <CLASS_TIMING>
Student_Preference.txt with the following format: <STUDENT_ID> <6_PREFERENCES>; STUDENT_LEVEL: <STUDENT_LEVEL>
Expected values for <STUDENT_LEVEL>: FIRST_YEAR, SECOND_YEAR, THIRD_YEAR 

-----------------------------------------------------------------------
Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in coursesRegistration/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile coursesRegistration/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile coursesRegistration/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

####Command: ant -buildfile coursesRegistration/src/build.xml run -Darg0=<student_preference_file.txt> -Darg1=<course_info_file.txt>

Note: Arguments accept the absolute path of the files.