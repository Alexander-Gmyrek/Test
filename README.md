# TestReader
This is a program that takes text files from an input folder runs the program on a .java file and compares the text output to the output in the output folder.
The inteded use is for checking the accuracy of an assignment.
ensure that the inputs in the input folder match the outputs in the output folder in alphabetical order. input1.txt -> output1.txt and so on. 
(if you have more than ten move on to a-z)

I cant promise this program will work on anything other than windows

to run the program boot up comand prompt and navigate to the folder containing the files
this can be done by typing: cd then copying the adress of the folder in file manager

once you have reached the folder type:
java TestRunnerV1 input output [assignment name].java

if the program output is blank this likely means there is a compiling error
to test this type:
javac d . [assignment name].java
