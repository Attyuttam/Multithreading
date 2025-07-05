CYCLIC BARRIER
====================
What is the Cyclic barrier ?
-
Imagine you are with a group of friends gathered at a point.
When a friend reaches that checkpoint they call the await() method.
Once all the friends reach that checkpoint, the Cyclic barrier is lifted

P.S: I still did not understand this


How does Cyclic barrier work under the hood ?
- 
Under the hood, the Cyclic barrier uses a combination of a counter and a
condition to manage the waiting threads. When you create a cyclic barrier object,
you must specify the number of threads that must call the await() method before
the barrier is broken. Each thread that calls the await(), decrements the internal counter
of the cyclic barrier.

If the counter has not reached zero yet, the calling thread enters a waiting state.
Once all the threads, has called the await() method, the barrier is broken and the counter resets back
to the original value.

This reusability distinguishes Cyclic barrier from the countdown latch which cannot reset itself.


