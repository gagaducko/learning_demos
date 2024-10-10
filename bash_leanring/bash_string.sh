#!/bin/bash

# string:start:length
text="ABCDE"

# Extract from index 0, maximum 2 characters
echo "${text:0:2}"  # Output: AB

# Extract from index 3 to the end
echo "${text:3}"    # Output: DE

# Extract 3 characters starting from index 1
echo "${text:1:3}"  # Output: BCD

# If length exceeds remaining characters, it stops at the end
echo "${text:3:3}"  # Output: DE (only 2 characters available)
