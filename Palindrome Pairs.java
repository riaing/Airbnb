Given a list of unique words. Find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:
Given words = ["bat", "tab", "cat"]
Return [[0, 1], [1, 0]]
The palindromes are ["battab", "tabbat"]

public List<List<Integer>> palindromePairs(String[] words) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
 
    HashMap<String, Integer> map = new HashMap<String, Integer>();
    for(int i=0; i<words.length; i++){
        map.put(words[i], i);
    }
 
    for(int i=0; i<words.length; i++){
        String s = words[i];
 
        //if the word is a palindrome, get index of ""
        if(isPalindrome(s)){
            if(map.containsKey("")){
                if(map.get("")!=i){
                    ArrayList<Integer> l = new ArrayList<Integer>();
                    l.add(i);
                    l.add(map.get(""));
                    result.add(l);
 
                    l = new ArrayList<Integer>();
 
                    l.add(map.get(""));
                    l.add(i);
                    result.add(l);
                }
 
            }
        }
 
        //if the reversed word exists, it is a palindrome
        String reversed = new StringBuilder(s).reverse().toString();
        if(map.containsKey(reversed)){
            if(map.get(reversed)!=i){
                ArrayList<Integer> l = new ArrayList<Integer>();
                l.add(i);
                l.add(map.get(reversed));
                result.add(l);
            }
        }
 
        for(int k=1; k<s.length(); k++){
            String left = s.substring(0, k);
            String right= s.substring(k);
 
            //if left part is palindrome, find reversed right part
            if(isPalindrome(left)){
                String reversedRight = new StringBuilder(right).reverse().toString();
                if(map.containsKey(reversedRight)){
                     if(map.get(reversedRight)!=i){
                        ArrayList<Integer> l = new ArrayList<Integer>();
                        l.add(map.get(reversedRight));
                        l.add(i);
                        result.add(l);
                     }
                }
            }
 
            //if right part is a palindrome, find reversed left part
            if(isPalindrome(right)){
                String reversedLeft = new StringBuilder(left).reverse().toString();
                if(map.containsKey(reversedLeft)){
                    if(map.get(reversedLeft)!=i){
 
                        ArrayList<Integer> l = new ArrayList<Integer>();
                        l.add(i);
                        l.add(map.get(reversedLeft));
                        result.add(l);
                    }
                }
            }
        }
    }
 
    return result;
}
 
public boolean isPalindrome(String s){
 
 
    int i=0;
    int j=s.length()-1;
 
    while(i<j){
        if(s.charAt(i)!=s.charAt(j)){
            return false;
        }
 
        i++;
        j--;
    }
    return true;
}
