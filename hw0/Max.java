public class Max {
    /** Returns the maximum value from m. */
    public static int max(int[] m) {
    	int maximum = m[0];
        for (int i = 0; i < m.length; i++) {
        	if (m[i] > maximum) {
        		maximum = m[i];
        	}
        }
        return maximum;
    }
    public static void main(String[] args) {
       int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
       int maximum = max(numbers);     
       System.out.print(maximum); 
    }
}