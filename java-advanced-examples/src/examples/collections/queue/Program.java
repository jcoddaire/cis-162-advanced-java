package examples.collections.queue;

public class Program
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        ArrayListQueue queue = new ArrayListQueue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        while (queue.getSize() > 0)
            System.out.println(queue.dequeue());
    }
}
