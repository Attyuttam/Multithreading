CONCURRENT COLLECTIONS
=========================

Java has a rich set of collections classes. However, msot of them are not thread safe. Thus, we need to ensure that in a multithreading context, we are using the collections in a proper manner.
There are two approaches of doing so:
1. Use Collections.synchronize() method
2. Use the concurrent collections which are synchronized

Downsides of using the Collections.synchronize() approach:
1. Coarse grained locking
2. Limited functionality
3. No fail fast iterators
4. performance overhead

COUNTDOWN LATCH
=========================


Introduction 
- 
Countdown latch is a synchronization utility that allows one or more threads to wait until a set of operations which is being performed in another threads completes.
  It is used to control the flow of execution of the concurrent programs
  The key concept of countdown latch is that it maintains a count of a specific number and increases each time the countdown method is called
  Threads which needs to wait for the countdown latch to reach zero can call the await method which will block until the count becomes zero

When to use countdown latch ?
- 
Let us understand this with a scenario. Imagine you are a tech lead of a team, and you have been assigned with a huge task.
So you divide the task amongst your team members. But now you are not aware on how long you need to wait for the tasks to complete.
So, you use a countdown latch and every team member after finishing a task reduces the value of the countdown latch.
Once the countdown latch reaches 0, you understand that all tasks have been completed and then you can start with your task which was dependent on the completion of the tasks being done by the team members.

You will wait using the await() method.

Thus, countdown latch helps in synchronizing the execution of multiple threads working together.

Is it functionally similar to join() ?
- 
Countdown latch is designed to allow one or more threads to wait until a set of operations in other threads completes. It is typically used for co-ordination when there are multiple threads working.

Join on the other hand is used for a thread to complete execution before proceeding with the rest of the code and its specifically used for thread synchronization within a single threaded context

In terms of the user's context:
* The countdown latch is useful when you have multiple threads performing independent tasks, and you want to coordinate them before moving forward
* Join is useful when you have a main thread and it spawns worker threads and needs to wait for them to finish before continuing its execution
* Also, when you have a dynamic number of threads and you want to resume execution after the threads have completed their task, then you dont know the number of times you would have to write join() on each thread since the number of threads is dynamic in nature. So here the countdown latch shines !

Can we reset the count in CountdownLatch ?
- 
No, we can't. For that we need to use the Cyclic barrier.

