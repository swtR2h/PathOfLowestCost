Path Of Lowest Cost
===================

To run, open this up in android studio and run.

Algorithm
---------

Dynamic programming was used to solve this problem.


~~~
lowestCost = min{C(1,1), ..., C(numRows,1)}
    where
        C(i,j) = gridValue(i,j) + min{C(i-1,j+1), C(i,j+1), C(i+1,j+1)}
        C(i,lastColumn) = gridValue(i, lastColumn)
~~~
