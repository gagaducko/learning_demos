#!/bin/bash

array=("A" "B" "C" "D" "E")

# Print entire array
echo "${array[@]}"  # Output: A B C D E

# Access a single element
echo "${array[1]}"  # Output: B

# Print a range of elements (requires Bash 4.0+)
echo "${array[@]:1:3}"  # Output: B C D

# Print from an index to the end
echo "${array[@]:3}"  # Output: D E
