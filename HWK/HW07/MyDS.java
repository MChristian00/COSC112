public class MyDS extends OrderedCollection{
    Node end;
    Node1 end1;

    public MyDS(){
        super();
        end = null;
        end1 = null;
    }

    class Node{
        Node prev;
        int n;
        public Node(int n){
            this.n = n;
        }
    }

    class Node1{
        Node1 prev;
        int n;
        public Node1(int n){
            this.n = n;
        }
    }

    public void append(int toAppend){
        Node newN = new Node(toAppend);
        newN.prev = end;
        end = newN;

        // do comparison if int entered is 9 and there's atleast 5 ints before it
        if(end.n == 9 && length() >= 6)
            compare();
    }

    public void construct(){
        int i = 6;
        int j = -4;
        int k =9;
        int l;
        while(i>=0){
            if(k-4 < k){
                l = k + j;
                k = l;
            } else if(Math.abs(k - 4) > k){
                j = 3;
                l =k + j;
                k = l;
                j--;
            }
            Node1 v = new Node1(l);
            v.prev = end1;
            end1 = v;
            i++;
            endPointer = end.prev;
        }
    }

    public void compare(){
        String theString = "3 1 4 1 5 9 ";
        String compString = "";
        int counter = 6;

        Node1 endPointer = end1;
        while(counter >= 1){
            compString = endPointer.n + " " +compString;
            endPointer = endPointer.prev;
            counter--;
        }
        
        if(theString.equals(compString)){
            System.out.println("Who has pi on their face now, Pr0HaX0r?");
        }
    }

    public int peek(){
        if(end == null){
            return 0;
        }
        return end.n;
    }

    public int pop(){
        Node currentEnd;
        if(end == null){
            return 0;
        }
        currentEnd = end;
        end = end.prev;
        return currentEnd.n;
    }

    public String toString(){
        String toReturn="";
        Node intH;
        intH = end;
        while(intH != null){
            toReturn = intH.n + " " +toReturn;
            intH = intH.prev;
        }
        return toReturn;
    }

    public int length(){
        int count = 0;
        Node n;
        n = end;
        while(n != null){
            count++;
            n = n.prev;
        }
        return count;
    }
}