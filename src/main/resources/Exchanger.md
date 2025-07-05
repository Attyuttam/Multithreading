EXCHANGER
====================
Introduction
-
In Java, an Exchanger refers to a synchronization point at which threads can pair within
a concurrent environment.
The java.util.concurrent.Exchanger class facilitates this functionality.
The way this works is that, two threads can call exchange method on the exchanger
object by passing the object.

Why use exchangers ?
-
Exchangers are useful in scenarios where two threads need to synchronize and 
exchange data before proceeding with their respective tasks.

For example, imagine you have a pipeline of processing steps and each step is executed by a different thread.
In such a case you might want to use a exchanger to exchange data between adjacent steps in the pipeline.

How is exchanger implemented in Java ?
-
Exchanger is a class in itself and does not implement any interface or extend any class in Java.
It is a standalone class that has been designed specifically to allow the exchange of data between 2 threads.

The class has two important method:
* exchange(obj)
  * This method is used to perform a blocking exchange operation.
  * it waits until another thread arrives at the exchange point and then exchanges the object X provided by the current thread with the object provided by the other thread.
  * it blocks until the other thread has arrived
* exchange(obj, timeout)
  * variation of exchange
  * Along with object, also expects us to provide a timeout.
  