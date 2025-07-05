What is Copy On Write Array ?
-
Ensures a copy of the data is present for the readers
when multiple threads updates the data concurrently.

How it works ?
-
If a thread wants to read or write from/into the array, the CopyOnWriteArray creates a snapshot of the array and performs the respective operation
Once the write operation is done, that becomes the latest version of the array.
This array also consolidates the changes regularly.