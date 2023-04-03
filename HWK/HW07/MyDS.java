public class MyDS extends OrderedCollection{
    Node end;

    public MyDS(){
        super();
        end = null;
    }

    class Node{
        Node prev;
        int n;
        public Node(int n){
            this.n = n;
        }
    }

    public void append(int toAppend){
        Node newN = new Node(toAppend);
        newN.prev = end;
        end = newN;
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
            toReturn += intH.n;
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