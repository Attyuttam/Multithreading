Lock Conditions
=
Locks are an important part in implementing concurrency in Java
But we also need to have some sort of interaction between the threads and locks,
so the mechanism which helps us in managing these interactions is called condition.

A lock can have more than one condition associated with it.

Similarity with Wait and Notify
-
Conditions are exactly similar to wait and notify with the only difference being
that conditions allow a more fine-grained control over the synchronization of the protected 
resource.
