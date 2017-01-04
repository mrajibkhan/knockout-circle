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

# Run Tests
./gradlew clean test

# Build
./gradlew build

# Run the application

java -jar build/libs/knockout-0.0.1-SNAPSHOT.jar --n=<integer> --k=<integer>

'n' is the number of children playing the game (optional)
'k' is the counter (optional)

If you don't provide these values then the program will ask you to provide these values at the start.  

