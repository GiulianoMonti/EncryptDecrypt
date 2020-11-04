# EncryptDecrypt
program for encrypt and decrypt data, you can read from a file, and write to a new one with the data encrypted. Can choose Unicode, or Shift


this is my number 5 project, in here im trying to apply some strategy patterns(very very noob) , i had to do everything in one file because i cant make my terminal read multiple files.



Example 1
java Main -mode enc -in road_to_treasure.txt -out protected.txt -key 5 -alg unicode

This command must get data from the file road_to_treasure.txt, encrypt the data with the key 5, create a file called protected.txt and write ciphertext to it.

Example 2

Input:
java Main -mode enc -key 5 -data "Welcome to hyperskill!" -alg unicode
Output:
\jqhtrj%yt%m~ujwxpnqq&

Example 3

Input:
java Main -key 5 -alg unicode -data "\jqhtrj%yt%m~ujwxpnqq&" -mode dec
Output:
Welcome to hyperskill!

Example 4:

Input:
java Main -key 5 -alg shift -data "Welcome to hyperskill!" -mode enc
Output:
Bjqhtrj yt mdujwxpnqq!

Example 5:

Input:
java Main -key 5 -alg shift -data "Bjqhtrj yt mdujwxpnqq!" -mode dec
Output:
Welcome to hyperskill!
