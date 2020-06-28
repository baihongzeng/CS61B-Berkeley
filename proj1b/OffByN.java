public class OffByN implements CharacterComparator{
    private int n;
    /*constructor: return an object whose equalChars
    method returns true for characters that are off by N*/
    OffByN(int N) {
        n = N;
    }

    /**/
    public boolean equalChars(char x, char y) {
        int x_int = x;
        int y_int = y;

        return Math.abs(x_int - y_int) == n;
    }

}