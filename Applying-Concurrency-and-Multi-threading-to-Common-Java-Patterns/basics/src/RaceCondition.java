
public class RaceCondition {

    public static void main(String[] args) throws InterruptedException {

        IntW intW = new IntW( 0 );
        Runnable runnable = () -> {
            for ( int i = 0; i < 100; i++){

                intW.IncrementVal();

            }
        };

        Thread[] threads = new Thread[ 100 ];

        for (int i = 0; i < threads.length; i++) {

            threads[i] = new Thread( runnable );
            threads[i].start();

        }

        for (Thread thread : threads) {

            thread.join();

        }
        System.out.println( "Value is:" + intW.getVal() );

        //from course
        IntWithKey intWithKey = new IntWithKey( 0 );
        runnable = () -> {
            for ( int i = 0; i < 100; i++){

                intWithKey.IncrementVal();

            }
        };

        threads = new Thread[ 100 ];

        for (int i = 0; i < threads.length; i++) {

            threads[i] = new Thread( runnable );
            threads[i].start();

        }

        for (Thread thread : threads) {

            thread.join();

        }
        System.out.println( "Value is:" + intWithKey.getVal() );



    }

}

class IntW {

    private int val;

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public IntW( int val ){
        this.val = val;
    }

    public synchronized void IncrementVal(){

        this.val++;

    }

}

class IntWithKey {

    private Object key = new Object();
    private int val;

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public IntWithKey( int val ){
        this.val = val;
    }

    public void IncrementVal() {

        synchronized (key){
            this.val++;
        }

    }

}