public class Pair {

    private int first;
    private int second;

    Pair(int first,int second){
        this.first=first;
        this.second = second;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public void setFirst(int first) {
        if(first < this.first){
            this.first = first;
        }
    }

    public void setSecond(int second) {
        this.second = second;
    }
}
