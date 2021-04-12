
public class Deadlock {

    public static void main(String[] args) throws InterruptedException {
        TwoKeyClass twoKeyClass = new TwoKeyClass();

        Runnable runFuncOne = twoKeyClass::funcOne;
        Runnable runFuncTwo = twoKeyClass::funcTwo;
        Runnable runFuncThree = twoKeyClass::funcThree;

        Thread threadStartAtFuncOne = new Thread( runFuncOne );
        Thread threadStartAtFuncTwo = new Thread( runFuncTwo );
        Thread threadStartAtFuncThree = new Thread( runFuncThree );

        threadStartAtFuncOne.start();
        threadStartAtFuncTwo.start();
        threadStartAtFuncThree.start();

        threadStartAtFuncOne.join();
        threadStartAtFuncTwo.join();
        threadStartAtFuncThree.join();

    }



}

class TwoKeyClass {

    private final Object keyA = new Object();
    private final Object keyB = new Object();

    public void funcOne(){

        synchronized ( keyA ){

            System.out.println( Thread.currentThread().getName() + ": has keyA in funcOne" );
            funcTwo();

        }

    }

    public void funcTwo(){

        synchronized ( keyB ){

            System.out.println( Thread.currentThread().getName() + ": has keyB in funcTwo" );
            funcThree();

        }

    }

    public void funcThree(){

        synchronized ( keyA ){

            System.out.println( Thread.currentThread().getName() + ": has keyA in funcThree" );


        }

    }
}