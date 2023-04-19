import java.util.ArrayList;

public class OtherProblem extends Problem {
    private int target;
    public OtherProblem(int target){
        this.target = target;
    }

    public State getStartState(){
        return new TripleSubtractTwo(1, this.target);
    }

    public boolean isGoal(State st){
        return ((TripleSubtractTwo)st).currentValue == this.target;
    }

}

class TripleSubtractTwo extends State{
    private int target;
    public int currentValue;
    public TripleSubtractTwo(int currentVal, int targetVal){
        this.currentValue = currentVal;
        this.target = targetVal;
    }

    public ArrayList<State> getSuccessors(){
        ArrayList<State> sList = new ArrayList<State>();
        int tripVal = this.currentValue * 3;
        int subVal = this.currentValue - 2;
        if(this.currentValue > 1)
            {sList.add(new TripleSubtractTwo(subVal, this.target));}
        if(this.currentValue < this.target)
            {sList.add(new TripleSubtractTwo(tripVal, this.target));}
        return sList;
    }

    public String toString(){
        return "[Val: " + this.currentValue + " (" + this.target + ")]";
    }

    public boolean equals(State st){
        return ((TripleSubtractTwo)st).currentValue == this.currentValue && ((TripleSubtractTwo)st).target == this.target;
    }

    public int compareTo(State st){
        return  this.currentValue - ((TripleSubtractTwo)st).currentValue;
    }
}
