ATOMIC VARIABLES
=
What is read-modify-write cycle ?
-
Variables when needed to be updated are first read,
then brought into the local register where its updated
and then flushed to the shared space.
So, this process of modification essentially involves 3 steps:
1. read the variable
2. Modify the variable
3. Write the modified value of the variable back

What are atomic variables ?
-
Every modification is done as single non-divisible step