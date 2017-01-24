Implement an iterator to flatten a 2d vector.

According to the definition of the remove(),
Removes from the underlying collection the last element returned by this iterator (optional operation). This method can be called only once per call to next(). The behavior of an iterator is unspecified if the underlying collection is modified while the iteration is in progress in any way other than by calling this method.

So the remove() method actually removes the element returned from the next(). Whether remove the inner vector or not depends on the specific situation.

For example,
Given 2d vector = [ [1,2], [3], [4,5,6] ]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].

先把所有element存入iterator中， 再implement hasNext和next时调用。

Skip to content
 
 
Search…
All gists
GitHub
New gist @riaing
  Star 0
  Fork 0
  @cangoalcangoal/Flatten2DVector.java
Created 10 months ago
Embed  
<script src="https://gist.github.com/cangoal/e2cde70b18e71430749be4bb59cc7166.js"></script>
  Download ZIP
 Code  Revisions 1
LeetCode - Flatten 2D Vector
Raw
 Flatten2DVector.java
// Implement an iterator to flatten a 2d vector.

// For example,
// Given 2d vector =

// [
//   [1,2],
//   [3],
//   [4,5,6]
// ]
// By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].

public class Vector2D implements Iterator<Integer> {
    List<Iterator> iterators = new ArrayList<Iterator>();
    int index = 0;
    public Vector2D(List<List<Integer>> vec2d) {
        if(vec2d != null && vec2d.size() != 0){
            for(List<Integer> lst : vec2d){
                if(lst != null && lst.size() != 0) iterators.add(lst.iterator());
            }
        }
    }

    @Override
    public Integer next() {
        while(index < iterators.size()){
            if(iterators.get(index).hasNext()) return (Integer)iterators.get(index).next();
            index++;
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        if(index == iterators.size()) return false;
        else if(index < iterators.size() - 1) return true;
        else return iterators.get(index).hasNext();
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */
 
 // https://leetcode.com/discuss/50292/7-9-lines-added-java-and-c-o-1-space
 @riaing
  
         
Write  Preview

Leave a comment
Attach files by dragging & dropping,  Choose Files selecting them, or pasting from the clipboard.
 Styling with Markdown is supported
Comment
Contact GitHub API Training Shop Blog About
© 2017 GitHub, Inc. Terms Privacy Security Status Help

http://blog.csdn.net/pointbreak1/article/details/48823513
https://gist.github.com/cangoal/e2cde70b18e71430749be4bb59cc7166
