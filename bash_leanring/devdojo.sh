#! /usr/bin/env bash
# variable name
name="DevDojo"
# normal print
echo "Hello World!"
# print variable name
echo $name
# another way to print variable name
echo ${name}
# print varible in a sentence
echo "Hello World $name ${name}"
# start with params
# print sentence with param 1
echo "hello world" $1
# print sentence with param 2
echo "hello world" $2
# print sentence with all params
echo "hello world" $@

# read and output
echo "what is your name?"
read name

echo "Hi there is $name"
echo "Welcome"

# use read -p to replace of echo and read
read -p "what is your name? " name
echo "Hi there is $name"
echo "welcome"
