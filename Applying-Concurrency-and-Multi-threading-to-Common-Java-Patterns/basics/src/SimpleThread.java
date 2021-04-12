public class SimpleThread {

    public static void main(String[] args) throws InterruptedException {

        Runnable runnable = () -> {

            System.out.println( Thread.currentThread().getName() + ":Doing this as part of my concurrency course, I'm loving it!!!" );

        };

        System.out.println( Thread.currentThread().getName() + ":Just to prove main thread ran before this!!!" );
        Thread thread = new Thread( runnable );
        thread.setName("custom-thread");
        thread.start();

        // The scheduler gives the thread defined above cpu cycles to run, because main thread is paused, just for 1 ms
        // But without sleep, main thread always runs print before, as System.out.println has synchronized code block
        // and main thread already had the lock for that, thus is again allowed to run the print statement
        // Thread.sleep( 1L );
        // or use join

        thread.join();

        System.out.println( Thread.currentThread().getName() + ":Just to prove main thread ran after the thread run!!!" );
    }

}
