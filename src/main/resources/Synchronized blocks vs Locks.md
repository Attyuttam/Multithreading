Synchronized blocks vs Locks
=

Synchronized blocks
-
Synchronized blocks uses the synchronized keyword to ensure that only one
thread can execute a particular block of code at any given point of time.
They provide intrinsic locking, that means that the lock associated with them is acquired and released
automatically by the JVM.

Synchronized blocks are much easier to use because they require less boilerplate code
compared to the locks. However, they have limitations such as lack of flexibility in lock acquisition
and inability to handle the interrupts.

On the other hand, locks provide more flexibility and control over locking mechanisms.
Java lock interface and its implementations allow to manually acquire and release the locks.
Moreover, you can acquire and release the locks in any sequence and in any scope which is not possible
if you use synchronized approach.

When to use Synchronized locks vs locks ?
-
Use synchronized blocks for simple synchronization needs where 
flexibility and performance are not that critical.

use locks for complex synchronization scenarios where fine-grained control and flexibility are essential.