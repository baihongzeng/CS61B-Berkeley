public class Palindrome {
    /*transform a string word in deque form */
    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> charDeque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            charDeque.addLast(word.charAt(i));
        }
        return charDeque;
    }

    /*using deque and recursion*/
    public boolean isPalindrome(String word) {
        Deque<Character> deque = wordToDeque(word);
        return isPalindrome_re(deque); /* Don't use get */
    }

    private boolean isPalindrome_re(Deque<Character> deque) {
        if (deque.size() <= 1) {
            return true;
        }
        if (!(deque.removeFirst() == deque.removeLast())) {
            return false;
        }
        return isPalindrome_re(deque);
    }

    /*return true if the word is a palindrome according to the character comparison
    test provided by the CharacterComparator passed in as argument cc.*/
    public boolean isPalindrome(String word, CharacterComparator cc){
        if (word.length() <= 1) { //null and single character
            return true;
        }

        Deque<Character> deque = wordToDeque(word);
        return helper(deque, cc); /* Don't use get */
    }

    private boolean helper(Deque<Character> deque, CharacterComparator cc) {
        if(deque.size() <= 1) {
            return true;
        }

        if (!cc.equalChars(deque.removeFirst(), deque.removeLast())) {
            return false;
        }
        return helper(deque, cc);
    }
}
