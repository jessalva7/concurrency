public class ProducerConsumer {

    static final Object lock = new Object();
    static int[] dataBuffer;
    static int count;

    static class Producer{


        void produce() throws InterruptedException {

            synchronized ( lock ){

                if(isFull()){
                    lock.wait();
                }

                dataBuffer[count++] = 1;
                lock.notify();
            }

        }


    }

    static class Consumer{

        public void consume() throws InterruptedException {

            synchronized ( lock ){

                if(isEmpty()){
                    lock.wait();
                }

                dataBuffer[--count] = 0;
                lock.notify();

            }

        }


    }

    private static boolean isFull() {
        return count == dataBuffer.length;
    }

    private static boolean isEmpty() {
        return count == 0;
    }

    public static void main(String[] args) throws InterruptedException {

        Producer producer = new Producer();
        Consumer consumer = new Consumer();

        dataBuffer = new int[50];
        count = 0;

        Runnable producerTask = () -> {

            for (int i = 0; i < 50; i++) {
                try {
                    producer.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };

        Runnable consumerTask = () -> {

            for (int i = 0; i < 50; i++) {
                try {
                    consumer.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };

        Thread producerThread = new Thread( producerTask );
        Thread consumerThread = new Thread( consumerTask );

        consumerThread.start();
        producerThread.start();

        producerThread.join();
        consumerThread.join();

        System.out.println( "Value of count is: " + count );

    }

}
