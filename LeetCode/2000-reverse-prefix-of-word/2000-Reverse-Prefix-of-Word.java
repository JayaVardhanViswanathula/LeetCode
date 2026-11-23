class Solution {
    public String reversePrefix(String word, char ch) {
        int i = word.indexOf(ch);

        if (i == -1)
            return word;

        StringBuilder sb = new StringBuilder(word.substring(0, i + 1));
        sb.reverse();

        return sb + word.substring(i + 1);
    }
}