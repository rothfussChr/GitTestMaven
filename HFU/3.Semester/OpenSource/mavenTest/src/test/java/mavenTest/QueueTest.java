package mavenTest;

import org.junit.Test;
import junit.framework.TestCase;
import static org.junit.Assert.*; 
import mavenSource.Queue;

public class QueueTest extends TestCase{
	
	@Test(expected=IllegalArgumentException.class)
	public void testInitException(){
		try{
		Queue queue = new Queue(0);
		}catch(IllegalArgumentException e){
			assertEquals(e.getMessage(), "Queue Arraylength 0");
		}
	}
	public void testEmptyDequeue(){
		Queue queue = new Queue(3);
		try{
			queue.dequeue();
		}catch(IllegalStateException e){
			assertEquals(e.getMessage(), "dequeue on empty queue");
		}
	}
	public void testEnqueueDequeue(){
		Queue queue = new Queue(3);
		queue.enqueue(1);
		assertEquals(1, queue.dequeue());
	}
	public void testMoreElementsOnQueue(){
		Queue queue =  new Queue(3);
		for(int i = 0; i<5; ++i){
			queue.enqueue(i);
		}
		assertEquals(0, queue.dequeue());
		assertEquals(1, queue.dequeue());
		assertEquals(4, queue.dequeue());
	}

}
