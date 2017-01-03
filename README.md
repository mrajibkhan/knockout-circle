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
