Implement an iterator to flatten a 2d vector.

According to the definition of the remove(),
Removes from the underlying collection the last element returned by this iterator (optional operation). This method can be called only once per call to next(). The behavior of an iterator is unspecified if the underlying collection is modified while the iteration is in progress in any way other than by calling this method.

So the remove() method actually removes the element returned from the next(). Whether remove the inner vector or not depends on the specific situation.

For example,
Given 2d vector = [ [1,2], [3], [4,5,6] ]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].

先把所有element存入iterator中， 再implement hasNext和next时调用。

http://blog.csdn.net/pointbreak1/article/details/48823513
https://gist.github.com/cangoal/e2cde70b18e71430749be4bb59cc7166
