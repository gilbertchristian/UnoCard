public class HijiException extends Exception { 
    public HijiException() {
        super();
    }

    public void declarehiji1() {
        nextPlayer(1);
    }

    public void declareHiji2() {
        drawTwo(1);
    }

    public void undeclareHiji1() {
        drawTwo(1);
    }

    public void undeclareHiji2() {
        nextPlayer(1);
    }
    
}