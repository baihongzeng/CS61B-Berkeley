public class OffByOne implements CharacterComparator{
    /** Returns true if characters are equal by the rules of the implementing class. */
    @Override
    public boolean equalChars(char x, char y){
        int x_int = x;
        int y_int = y;

        return Math.abs(x_int - y_int) == 1;
    }
}