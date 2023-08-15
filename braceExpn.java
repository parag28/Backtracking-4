class Solution {
    public String[] expand(String s) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), s, 0);
        return result.toArray(new String[result.size()]);
    }
    
    private void backtrack(List<String> result, StringBuilder current, String s, int index) {
        // Base case: if the current string is complete, add it to the result
        if (index == s.length()) {
            result.add(current.toString());
            return;
        }
        
        if (s.charAt(index) == '{') {
            // Find the end of the brace-enclosed block
            int end = index + 1;
            while (end < s.length() && s.charAt(end) != '}') {
                end++;
            }
            
            // Extract the comma-separated options within the block
            String[] options = s.substring(index + 1, end).split(",");
            
            // Explore each option within the block
            for (String option : options) {
                current.append(option); // Add the current option
                backtrack(result, current, s, end + 1); // Recurse on the remaining part
                current.delete(current.length() - option.length(), current.length()); // Backtrack
            }
            
            // Move the index beyond the processed block
            index = end + 1;
        } else {
            // Process a single character (non-brace)
            current.append(s.charAt(index));
            backtrack(result, current, s, index + 1);
            current.deleteCharAt(current.length() - 1); // Backtrack
            index++;
        }
    }
}

