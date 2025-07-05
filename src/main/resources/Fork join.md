FORK JOIN
=
Introduction to Fork Join framework
-
Fork Join framework is a concurrency framework introduced in Java 7
to simplify the process of parallel programming. It is designed to take
advantage of multicore processors by dividing tasks into
smaller subtasks executing them in parallel and then combining the 
results together.

The Fork join is very similar to the executor service because
even in Executor service, a task is assigned to multiple threads
which work on it. But in FJ framework, we can divide a task
into subtasks which isn't possible in executor service, we can
provide multiple tasks to the executor service.

Differences with ExecutorService
-
FJ framework differs with ExecutorService in 2 aspects:
1. Task producing subtask
2. Per thread queuing and work stealing

in case of the Fj framework, a task is capable of creating subtasks
to simplify the problem being solved. Consider this as the standard divide
and conquer approach which is not the case in the standard
ExecutorService. In the ExecutorService, there is a shared task
queue but in FJ framework, the threads have their dedicated task queue.

There is also a mechanism of load balancing where a thread can pick
a task from the other queue. This approach is called as work stealing.

Why do we need Fork Join framework ?
-
* Utilization of multicore processors
  * FJ framework uses all the cores efficiently
* Simplified parallelism
* Efficient work stealing algorithms

Key concepts in FJ framework
-
* Forking
* Joining
* Recursive task
* Recursive action

Fork Join pool
-
* Work stealing algorithm
* Parallelism
* For & Join operations
* Managing Fork Join tasks

