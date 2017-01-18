Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words. You may assume the dictionary does not contain duplicate words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".


######BFS 
public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
    Queue<Integer> queue = new LinkedList<Integer>();
    queue.offer(0);
    // use a set to record checked index to avoid repeated work.
    // This is the key to reduce the running time to O(N^2).
    Set<Integer> visited = new HashSet<Integer>();
    visited.add(0);
    while (!queue.isEmpty()) {
        int curIdx = queue.poll();
        for (int i = curIdx+1; i <= s.length(); i++) {
            if (visited.contains(i)) continue;
            for ( int j = 0 ; j < wordDict.size(); j ++ ){
                  if (wordDict.get(j).equals (s.substring(curIdx, i))) {
                    if (i == s.length()) return true;
                    queue.offer(i);
                    visited.add(i);
                    }
            }
          
        }
    }
    return false;
    }
}


##########DFS 
public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if ( s== null || wordDict == null ){
            return false; 
        }
        Set<Integer> visited = new HashSet<Integer>(); 
        return help (s, wordDict, 0, visited ); 
    } 
        private boolean help(String s, List<String> wordDict, int curIdx,Set<Integer> visited){
            if ( curIdx == s.length()) {
                return true; 
            }
            
            if ( visited.contains(curIdx)){
                return false; 
            }
            
            //
            boolean stop = false; 
            for ( int i = curIdx+1; i <= s.length(); i ++ ){
                if (stop)  break;
                for ( int j = 0 ; j < wordDict.size(); j ++ ){
                    if (wordDict.get(j).equals (s.substring(curIdx, i))) {
                         stop  = help (s, wordDict, i,visited ); 
                        if (stop)  break;
                           
                        
                    }
                }
                
            }
            //visited save that start from this index can't find one in dic. 
            //eg: {le,et, cod, leet} then visited will store last e, c 
            if (!stop) visited.add(curIdx); 
            
            return stop; 
        }
}
