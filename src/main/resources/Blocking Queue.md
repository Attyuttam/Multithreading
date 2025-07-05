BLOCKING QUEUE
====================

Blocking queue is a data structure which allows multiple threads to put items onto the queue and take off items from the queue in a blocking manner.

There are 2 aspects to the BQ :
1. Blocking
    - If a thread tries to take an item from an empty queue then that thread will be paused/blocked until the item becomes available in the queue.
    - Similarly, if a thread tries to add an item to a queue which is full then it will paused/blocked until the queue has some space
2. Uses of the queue
    - Blocking queue follows the FIFO principle

Blocking queue interfaces
- 
BlockingQueue acts as the parent interface to a few other interfaces and the concrete implementation classes
The interfaces are:
- BlockingDeque
    - As the name suggests, insertion & consumption can be done from both ends.
    - Producer can enqueue even if no consumer is waiting
- TransferQueue
    - This is specialized for direct handoff between the producer and consumer
    - The producer is blocked until the consumer has received the item

Major implementations
-
* ArrayBlockingQueue
* LinkedBlockingQueue
* PriorityBlockingQueue
* DelayQueue
* SynchronousQueue

BlockingQueue operations
-
* put(E e)
* take()
* offer(E e)
* poll()
* peek()
