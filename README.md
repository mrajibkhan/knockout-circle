# **knockout-circle**
simulates kids' game where kids stands in a circle and gets knocked out in order based on a counter

# **Rules:**
    * n children stand around a circle. 
    * Starting with a given child and working clockwise, each child gets a 
      sequential number, which we will refer to as it’s id. 
    * Then starting with the first child, they count out from 1 until k. The 
      k’th child is now out and leaves the circle. The count starts again 
      with the child immediately next to the eliminated one.
    * Children are so removed from the circle one by one. The winner is the 
      child left standing last.

# Solution:
This game is a classic example of circular linked list (singly / doubly linked list) where the first node / element 
and last node / element of the list have pointers to each other. We'll loop through this circular list in order based 
on the value of the counter (K) and remove the element at k. This depends on linking the elements at (k -1) and (k + 1)
during the remove operation. Then we start counting again from this position (i.e k + 1) and follow the same process until 
there is only 1 element left in the list. This remaining element is the winner of the game. 
  
Google's Guava library is used to solve the problem which contains rich collection APIs. This solution uses "FluentIterable" which
is backed by (implements) Iterable. Its "cycle" method returns an FluentIterable which is cyclic i.e. fulfills our 
requirements.
  
SpringBoot framework is used for this solution which provides easy way to build highly configurable, deployable and maintainable 
production quality application. This application is a command line application which take user inputs and completes the
game, shows the result, logs information and exits. But this application can easily be extended to run as a webb application 
or run as a API, used from other applications, add security etc. as spring provides all these features. The flip side is the 
size of the deliverable codebase / package which is quite large considering the requirements of the application.  

This design uses OOD instead of pure mathematical functions using numbers which will consume lot more memory. But trade off
was made intentionally for better maintainability and flexibility of the application. So, performance will decrease with
the increased input values (see 'limitations' section below) 
         

# Environment
 * JAVA - 1.8.x (tested with 1.8.0_102)
 * Gradle - 3.0
 * Tested on MacOS (OS X EI Capitan - 10.11.6) and Windows 8
  
# **How to run the application**  
 
# Run Tests
./gradlew clean test

# Build
./gradlew build

# Run the application

java -jar build/libs/knockout-0.0.1-SNAPSHOT.jar --n=5 --k=3

 - 'n' is the number of children playing the game (optional)
 - 'k' is the counter (optional)

If you don't provide these values then the program will ask you to provide these values at the start.  

`Example:`
```
    $ java -jar build/libs/knockout-0.0.1-SNAPSHOT.jar --n=5 --k=3
    Number of players = 5, value of k = 3
    Iteration 1 - OUT: Player{id=3}
    Iteration 2 - OUT: Player{id=1}
    Iteration 3 - OUT: Player{id=5}
    Iteration 4 - OUT: Player{id=2}
    Winner: Player{id=4}
    Total loop = 4
    Game Result: 
    1 - player#4
    2 - player#2
    3 - player#5
    4 - player#1
    5 - player#3
```
**LOG:** log file will be created at logs/application.log 
 - Make sure user has write permission

**Configuration:** src/main/resources/application.properties

# Limitations
 * **input values:** values for number of players and counter is assumed to be integer which is limited 
to a maximum value of 2,147,483,647. But this demo application will struggle with
big values. Tested (4 core macbook and 16GB ram) with 1 million players with counter
10 which took approximately 150 seconds to complete with results printed in console and log file.
 * **performance:** again for the large input values performance will degrade due to
object creation (Player).
 * **error handling:** error handling is very basic which is used for demonstration purpose only.
In some cases exception stack will be displayed in console e.g. for non integer values of 
command line  values of 'n' and 'k' (--n=a --k=b)
  
  
