public class OffByN implements CharacterComparator {
    private int n;
    /*constructor: return an object whose equalChars
    method returns true for characters that are off by N*/
    public OffByN(int N) {
        n = N;
    }

    /** Returns true if characters are equal by the rules of the implementing class.*/
    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == n;
    }

}
