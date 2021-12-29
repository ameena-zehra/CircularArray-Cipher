
public class CircularArrayQueue<T> implements QueueADT{
	
	private int front;
	private int rear;
	private int count;
	private T[] queue;
	private final int DEFAULT_CAPACITY = 20;
	
	
	/**
	* Constructor
	* For cases where the initialCapacity is not given
	* Initializes the front to 1, the rear to DEFAULT_CAPACITY and count to 0
	* Also creates an empty Queue array with the capacity set to DEFAULT_CAPACITY
	*/
	public CircularArrayQueue() {
		front = 1;
		rear = DEFAULT_CAPACITY;
		count = 0;
		queue =(T[])(new Object[DEFAULT_CAPACITY]);
	}
	
	/**
	* Constructor
	* For cases where the initialCapacity is given
	* Initializes the front to 1, the rear to initialCapacity and count to 0
	* Also creates an empty Queue array with the capacity set to initialCapacity
	* @param int initialCapacity 
	*/
	public CircularArrayQueue(int initialCapacity) {
		front = 1;
		rear = initialCapacity;
		count = 0;
		queue =(T[])(new Object[initialCapacity]);
	}
	
	/**
	* Public enqueue that adds a data element to the rear of the queue
	* If the queue storing the data item is full expandCapacity() is called 
	* @param Object element is the data item to be added to the rear of the queue
	*/
	@Override
	public void enqueue(Object element) {
		if (count== queue.length-1) expandCapacity();
		rear = (rear+1) % queue.length;
		queue[rear] = (T) element; 
		++count;
		
	}
	
	/**
	* Public dequeue method that removes and returns the data item at the front of the queue
	* EmptyStackException is thrown if the queue is empty
	* @return data item at the front of the queue
	*/
	@Override
	public T dequeue() {
		if (isEmpty()) {
			throw new EmptyCollectionException("Circular Array Queue");
		}
		T result = queue[front];
		queue[front] = null;
		count = count - 1;
		front = (front+1)% queue.length;
		return result;
	}
	
	/**
	* Public first method that returns the data item at the front of the queue
	* EmptyStackException is thrown if the queue is empty
	* @return data item at the front of the queue
	*/
	@Override
	public T first() {
		if (isEmpty()) {
			throw new EmptyCollectionException("Circular Array Queue");
		}
		T result = queue[front];
		return result;
	}
	
	/**
	* Public isEmpty method that returns true if the queue is empty and returns false otherwise
	* @return boolean whether or not the stack is empty
	*/
	@Override
	public boolean isEmpty() {
		return (count==0);
	}
	
	/**
	* Public size method that returns the number of data items in the queue
	* @return int number of data items in the queue being the count variable
	*/
	@Override
	public int size() {
		return count;
	}
	
	/**
	* Public getFront method that returns the front index value
	* @return int front index value
	*/
	public int getFront() {
		return front;
	}
	
	/**
	* Public getRear method that returns the rear index value
	* @return int rear index value
	*/
	public int getRear() {
		return rear;
	}
	
	/**
	* Public getLength method that returns the current length (capacity) of the array
	* @return int length of queue
	*/
	public int getLength() {
		return queue.length;
	}
	
	/**
	* Public toString method that returns a string representation of the queue
	* if the queue is empty then print "The queue is empty"
	* @return String representation of the queue
	*/
	public String toString() {
		String result = "";
		if (isEmpty()) {
			result = ("The queue is empty");
		}
		else {
			result = "QUEUE: ";
			for (int i =0; i< queue.length; i++) {
				if (queue[i]!= null){
					if (i == getRear()) {
						result += queue[i].toString()+".";
					}
					else {
						result += queue[i].toString()+", ";
					}
				}
			}
		}
		return result;
	}
	
	/**
	* Private helper method called by the enqueue method
	* If the queue has reached capacity this method is called
	* Creates a new array that has 20 more slots than than the current instance variable
	*/
	private void expandCapacity() {
		T[] largerqueue = (T[])(new Object[queue.length + 20]);   

	    for(int i=0; i < count; i++)
	    {
	      largerqueue[i] = queue[front-1];
	      front=(front+1) % queue.length; 
	    }

	    front = 1;
	    rear = count;
	    queue = largerqueue;
	}

}
