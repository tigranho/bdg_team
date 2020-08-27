package lesson2.chapter3.collections.queue;

import java.util.ArrayDeque;
import java.util.Queue;

import static java.lang.System.out;

/**
 * @author Hrach
 */

public class QueueMethods {

    public static void main(String[] args) {
        Queue<Integer> queue = new ArrayDeque<>();
        out.println(queue.offer(10));
        out.println(queue.offer(4));
        out.println(queue.peek());
        out.println(queue.poll());
        out.println(queue.poll());
        out.println(queue.peek());
        out.println(queue.offer(4));
        out.println(queue.offer(12));
        ((ArrayDeque<Integer>)queue).push(6);
        out.println(queue);
        out.println(((ArrayDeque<Integer>) queue).offerFirst(7));
        out.println(((ArrayDeque<Integer>) queue).removeLast());
        out.println(queue.remove(11));
        out.println(queue.remove(7));
//        queue.clear();
        out.println(queue.poll());
        out.println(queue.element()); //throws NoSuchElementException

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(null); //throws NullPointerException
        stack.push(4);
        out.println(queue.peek());
        out.println(queue.poll());
        out.println(queue.poll());
        out.println(queue.peek());
    }
}
