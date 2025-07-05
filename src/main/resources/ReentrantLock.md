Reentrant Lock
=
One of the many implementations of the Lock interface in Java.
it allows the thread to acquire the lock multiple times without causing any deadlocks.

What this means is that a thread which is holding a lock can acquire it again without blocking itself if the lock is Reentrant.

In contrast, without a reentrant lock, an attempt to acquire the lock again within the same thread without releasing it first
would typically result in blocking it and also cause potential deadlocks if not handled properly.

How does this work ?
-
The way it works is that the locking mechanism keeps track of the lock 
being held by the thread. In the case of non-reentrant locks, attempting to acquire the lock again within the same thread
typically results in blocking the thread. However, in the case of Reentrant lock, the lock mechanism allows the same thread
to acquire the lock multiple times, without blocking itself. Effectively the held count of the lock acquired by the given thread is incremented
since the lock is already acquired, there is no sense of acquiring it again and this is achieved by maintaining the count of how many times
the lock has been acquired by the same thread. The lock is only released when the count reaches zero, which indicates that the thread has released 
the lock the same number of times as it had acquired it.

Lock fairness
-
There is a boolean parameter that we can pass in Reentrant locks that determines whether the lock is fair or not
To understand the concept of fairness, let's look at this example:

Let's say there are n threads waiting for a lock and one of the thread x acquires the lock.
When it is done with the lock, it releases it so the lock should go to one of the waiting threads.
Fairness comes into picture when we need to determine to which thread the lock should now go to.

if the fairness parameter was set to true then the thread that had been waiting for the longest will be given the lock.
Whereas, if the fairness parameter was false, there is no deterministic way to find out the thread to which the lock will go to and the selection of that thread is arbitrary in nature.

By default Reentrant locks are unfair.