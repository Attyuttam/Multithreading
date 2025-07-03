### Daemon and User threads
* On the basis of surface of exeuction, threads can be of two tupes
  * Daemon threads
  * User threads

* When a Java program starts the main thread (main() method thread) starts running immediately. We can start child threads from the main thread. The main thread is the last thread to finish execution in normal circumstances, because it has to perform various shutdown opeartions.
* Daemon threads are intended to be helper threads which can run in the background and are of low priority. Eg. GC thread
* Daemon threads are terminated by the JVAm when all other user threads are terminated (don with their execution)
* So, under normal circumstances, user threads are allowed to terminate once they are done with their execution, however, the daemon threads are shutdown by JVM once all the other threads are done executing.