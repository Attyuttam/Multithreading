Read Write Lock
=
Another implementation of the Lock interface which allows
multiple threads to read the shared resource concurrently
but only one thread can write to the resource at a time.

This lock is useful when the resource is predominantly 
read heavy.

Example a bus seat booking system

Wait Queue behavior in ReadWriteLock 
-
Let's visualize how the queues are arranged and managed
in case of a read write lock. In the case of readwrite lock there a
are two types of threads: read and write

When there is a read lock put on the shared resource
and there are reader threads waiting to acquire the lock ?
In that case, the reader threads will be allowed to take the lock
since that is not a critical behavior. Although if a writer 
thread is waiting on a read lock, it will be made to wait

But what happens id there is a writer thread waiting in 
the queue and the reader thread keeps on coming to the queue ?

The writer thread should not be made to wait in that case
as it will lead to an infinite wait. Such low level implementations
are taken care by the kind of implementation of the 
read write lock we chose to use.

NOTE: 
-
Waiting readers do NOT block writers. But active readers do.



