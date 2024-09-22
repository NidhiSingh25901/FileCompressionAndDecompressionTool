File Compression and Decompression Tool:- 

This tool implements a file compression and decompression utility using Huffman Coding. It allows
users to compress files to a more space-efficient format and later decompress them to retrieve 
the original data. 

Features :- 
Compression:- Compress files into a Huffman-encoded format. 
Decompression - Restores compressed files back to their original format. 
Command Line Interface - Simple Command - Line Commands for both compressing and decompressing files. 

Pre-requisites :- 
Java Development Kit (JDK) 23 or higher. 

Installation:-
Clone this repository or download the source code files. 
Navigate to the project directory containing java files. 
Compile the Java files , run the following command:- 
javac *.java
java FileCompressor compress "C:\\Users\\v-nidhsingh\\Music\\nidhi.txt"
java FileCompressor decompress "C:\\Users\\v-nidhsingh\\Music\\nidhi.txt.huff"

Important Notes :- 
Ensures trhat the original file exists and is not empty. 
The output file will be saved with a .huff extension for compressed files. 
Decompressed files will be restored to their original format without any extension changes. 

Troubleshooting:-
If you encounter issues such as "No encoded data found in the file", ensures that the compression
step was successful and that the output files was correctly written. 
If the decompressed file does not open preoperly, verify that the original file was not corrupted
and that the Huffman coding logic is correctly implemented. 


The Project took almost 3 month to be implemented, The project basic structure is completed but ,
will be adding more features to the code.

Thanks for reading the Project Overview !!
Hope you find it useful, any suggestion for the project . 
Always welcome , give your feedback in Linkedin :- 
[username :- nidhisingh25901] - (https://www.linkedin.com/in/nidhi-singh-67293a18b/)
