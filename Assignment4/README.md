# Airport Security State

-----------------------------------------------------------------------
## Description
This project uses State pattern for maintaining the security state of an airport.
The factors that affect the security implementation of an airport are known as SecurityFactors.
The operation of increasing/decreasing security is increaseOrDecreaseSecurity().
The following are the states that an airport can be in,
- LOW_RISK
- MODERATE_RISK
- HIGH_RISK
Two metrics are used to determine the state of the airport. The metrics are,
- Average Traffic Per Day (avgTrafficPerDay)
Computed as ⇒ Total number of travellers ÷ Total number of days
- Average Prohibited Items Per Day (avgProhibitedItemsPerDay)
Computed as ⇒ Total number of prohibited items ÷ Total number of days
The list of prohibited items,
- Grains
- NailCutters
- Plants
- EndangeredAnimals
For each state that the airport can be in, the following are the conditions that need to be satisfied,
- LOW_RISK
  - 0 ≤ avgTrafficPerDay < 4 OR
  - 0 ≤ avgProhibitedItemsPerDay < 2
- MODERATE_RISK
  - 4 ≤ avgTrafficPerDay < 8 OR
  - 2 ≤ avgProhibitedItemsPerDay < 4
- HIGH_RISK
  - avgTrafficPerDay ≥ 8 OR
  - avgProhibitedItemsPerDay ≥ 4

Let's assume that there are 10 operations that can be performed by the security agency, based on the current state of the aiport. Each operation is identified by an ID. Thus, we have 10 IDs in the range [1, 10]. For each state of the airport, the security performs a subset of the operations. These are mentioned below,

- LOW_RISK
  - OperationID = 1
  - OperationID = 3
  - OperationID = 5
  - OperationID = 7
  - OperationID = 9
- MODERATE_RISK
  - OperationID = 2
  - OperationID = 3
  - OperationID = 5
  - OperationID = 8
  - OperationID = 9
- HIGH_RISK
  - OperationID = 2
  - OperationID = 4
  - OperationID = 6
  - OperationID = 8
  - OperationID = 10

#### Note: Logger levels are as follows
DEBUG_VALUE=4 [Prints to file every time a constructor is called]<br/>
DEBUG_VALUE=3 [Prints to file the name of the current state]<br/>
DEBUG_VALUE=2 [Prints to file the Airport Metrics such as the number of travellers, number of prohibited items etc]<br/>
DEBUG_VALUE=1 [Prints to file the line currently read]<br/>
DEBUG_VALUE=0 [Prints the operation ids of the tasks needed to be performed for maintaining peace and harmony]<br/>

-----------------------------------------------------------------------
## Inputs
input_file.txt with the following format: <NUM_TO_BE_CHECKED><br/>
number_of_threads between: 1-5<br/>
debug_level between: 0-4

-----------------------------------------------------------------------
Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in primeNumberValidation/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile primeNumberValidation/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile primeNumberValidation/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

####Command: ant -buildfile primeNumberValidation/src/build.xml run -Darg0=<input_file.txt> -Darg1=<number_of_threads> -Darg2=<debug_level>

Note: Arguments accept the absolute path of the files.