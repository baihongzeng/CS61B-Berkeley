// public class DrawTriangle{

//      public static void main(String []args){
//          int star_num = 1;
//          while (star_num <= 5) {
//              for (int i = 1; i <= star_num; i++) {
//                  System.out.print("*"); 
//              }
//             System.out.println();
//             star_num++;
//          }
        
//      }
// }

public class DrawTriangle {
   public static void drawTriangle (int N) {
      int star_num = 1;
      while (star_num <= N) {
         for (int i = 1; i <= star_num; i++) {
            System.out.print("*"); 
         }
         System.out.println();
         star_num++;
      }
   }
   public static void main(String[] args) {
      drawTriangle(10);
   }
}