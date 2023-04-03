public class Tester{
    public static void main(String args[]){
        MyDS ds = new MyDS();
        testDS(ds);
    }
    public static void testDS(OrderedCollection ds){
      //append some ints to ds, pop from ds, peek, and print it

      ds.append(7);
      ds.append(2);
      ds.append(712);
      ds.append(12);
      System.out.println(ds.toString());
      System.out.println(ds.peek());
      System.out.println(ds.pop());
      System.out.println(ds.length());
    }
}