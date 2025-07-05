CONCURRENT MAP
====================

Introduction
-
Map in java that can be operated on concurrently
Handles situations where multiple threads need to work on a Map concurrently

Internal implementation and working of Concurrent Map
-
* Adding an element to concurrent hash map
    * Hashing and determining segment
    * Acquiring lock
    * Insertion in segment
    * Releasing lock

* Fetching an element from concurrent hashmap
    * Hashing and determining segment
    * Acquiring lock
    * Searching in segment
    * Releasing lock
