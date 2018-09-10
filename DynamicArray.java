/**
 * Pukar Subedi
 * CS330
 * generic DynamicArray
 */
public class DynamicArray<T>
{
	
	private static final int INITCAP = 2;	// default initial capacity / minimum capacity
	private T[] storage;					// underlying array

	private int capacity; 					//capacity = length of array
	private int size;	  					//size = items in array

	/**
	 * Constructor
	 * initial capacity of array is INITCAP
	 * generic array
	 */
	@SuppressWarnings("unchecked")
	public DynamicArray()
	{
		storage = (T[]) new Object[INITCAP];

		this.capacity = INITCAP;
		this.size = 0;
	}

	/**
	 * constructor
	 * builds generic storage array
	 * 
	 * @throw IllegalArgumentException when initCapacity is lower than 1
	 * @param  initCapacity capacity of storage
	 * @return              
	 */
	@SuppressWarnings("unchecked")
	public DynamicArray(int initCapacity)
	{
		if(initCapacity < 1)
		{
			throw new IllegalArgumentException();
		}

		storage = (T[]) new Object[initCapacity];

		this.capacity = initCapacity;
		this.size = 0;
	}

	
	/**
	 * size of storage, number of elements in storage
	 * O(1)
	 * 
	 * @return int size of storage
	 */
	public int size() 
	{	  
		return this.size;
	}
	
	/**
	 * capacity of storage, length of storage array
	 * O(1)
	 * 	
	 * @return int capacity length of storage array
	 */
	public int capacity() 
	{ 
		return this.capacity;
	}
		

	/**
	 * updates item at given index with given value
	 * does not add new items
	 * returns old item at index
	 * O(1)
	 * 
	 * @param  index of the item in storage array you want to change
	 * @param  value value you want to update the item with
	 * @return T     generic item before you updated it.
	 * @throw IndexOutOfBoundsException for invalid index
	 */
	public T set(int index, T value)
	{
		checkIndexBounds(index);

		if(storage[index] == null)
		{
			System.out.println("DynamicArray:set:index is null");
			return null;
		}

		T item = storage[index];
		storage[index] = (T) value;
		return item;
	}

	/**
	 * get item from storage. does not delete update or add item
	 * 
	 * @param  index of the item you want to get 
	 * @return T     generic item you want from storage
	 * @throw IndexOutOfBoundsException for invalid index
	 */
	public T get(int index)
	{
		checkIndexBounds(index);

		return ( storage[index] );
	}

	/**
	 * adds an item to the end of storage (append)
	 * increments size
	 * doubleCapacity if more space is needed
	 * if value is null return false
	 * O(1)
	 * 
	 * @param  value you want to add to storage
	 * @return boolean true if success false if cannot add
	 */
	@SuppressWarnings("unchecked")
	public boolean add(T value)
	{
		if(value == null)
		{
			return false;
		}

		if(checkSizetoCapacity())
		{
			doubleCapacity();
		}
		storage[size] = value;
		size++;
		return true;
	}
	
	/*
	add item anywhere into dynamic array
	doubleCapacity if need be
	shifts items up if already item in slot
	O(N)
	used to insert items, shift up if needed.


	@parameter index slot you want to add item into
	@parameter value item value you want to add into index
	@throw IndexOutOfBoundsException if index lower than 0 or greater than capacity
	*/
	/**
	 * add item into dynamic array at given index
	 * double capacity if needed
	 * shifts items up if spot is already taken
	 * used to insert items, shift up if needed
	 * O(N)
	 * 
	 * @param index you want to add value at
	 * @param value added to storage
	 * @throw IndexOutOfBoundsException for invalid index
	 */
	@SuppressWarnings("unchecked")
	public void add(int index, T value){
		checkIndexBounds(index);

		if(checkSizetoCapacity())
		{
			doubleCapacity();
		}

		if(storage[index] == null)
		{
			storage[index] = value;
			size++;
		}else{
			shiftItemsUp(index);
			storage[index] = value;
			size++;
		}
	}

	
	/**
	 * remove value at index and return element that was there
	 *shift elements to cover gap
	 *halve capacity if number of elements falls below 1/3 of capacity
	 *capacity does not go below INITCAP
	 *O(N)
	 * 
	 * @param  index being removed
	 * @return T     generic item you removed
	 * @throw IndexOutOfBoundsException for invalid index
	 */
	@SuppressWarnings("unchecked")
	public T remove(int index)
	{
		checkIndexBounds(index);
		if(storage[index] == null)
		{
			return null;
		}

		T item = storage[index];
		storage[index] = null;
		shiftItemsDown(index);

		size--;

		if(size < capacity * (1.0/3.0) && capacity > INITCAP)
		{
			halfCapacity();
		}
		return item;
	}

	/**
	 * halves the capacity of storage
	 */
	private void halfCapacity()
	{
		this.capacity /=2;
		T[] temp = (T[]) new Object[capacity];
		for(int i = 0; i < capacity; i++)
		{
			temp[i] = storage[i];
		}
		storage = temp;
	}

	/**
	 * shifts array items down at index
	 * 
	 * @param index where the shift down starts
	 */
	private void shiftItemsDown(int index)
	{
		for(int i = index; i < storage.length - 1; i++)
		{
			storage[i] = storage[i+1];
		}
	}

	/**
	 * shifts array items up at index
	 * 
	 * @param index where the shift up starts
	 */
	private void shiftItemsUp(int index)
	{
		if(checkSizetoCapacity())
		{
			doubleCapacity();
	}	
		
		for(int i = storage.length - 1; i > index; i--)
		{
			storage[i] = storage[i-1];
		}
	}

	/**
	 * doubles the capacity of storage
	 */
	private void doubleCapacity()
	{
		capacity *= 2;
		T[] temp = (T[]) new Object[capacity];
		for(int i = 0; i < size; i++)
		{
			temp[i] = storage[i];
		}
		storage = temp;
	}

	/**
	 * checks that storage has not reached capacity
	 * @return true if reached capacity false if not
	 */
	private boolean checkSizetoCapacity()
	{
		return (this.size >= this.capacity-1) ? true : false;
	}

	/**
	 * checks if given index is legal
	 * 
	 * @param index you want to check if legal
	 */
	private void checkIndexBounds(int index)
	{
		if(index < 0 || index > this.capacity)
		{
			throw new IndexOutOfBoundsException();
		}
	}

	/**
	 * prints items in storage
	 */
	private void print()
	{
		System.out.println("print array: --------	");
		for(int i = 0; i < storage.length; i++)
		{
			System.out.println(storage[i]);
		}
	}


	// --------------------------------------------------------
	// example testing code... edit this as much as you want!
	// --------------------------------------------------------


	// Not required, update for your own testing
	@Override
	public String toString(){
		// return string representation of DynamicArray
		// update if you want to include more information 
		return "DynamicArray with size "+size+", capacity "+capacity;
		
  	}
  	
	public static void main (String args[]){
		// new list?
		DynamicArray<Integer> ida = new DynamicArray<>();

		if ((ida.size() == 0) && (ida.capacity() == 2)){
			System.out.println("Yay 1");
		}




		// adding to the list?
		boolean ok = true;

		for (int i = 0; i < 3; i++)
		{
			ok = ok && ida.add(i*5);
			//System.out.println("Array should be 0 5 10");
		}

		//ida.print();
		//printed: 0 5 10 null

		if (ok && ida.size() == 3){ 
			System.out.println("Yay 2");
		}

		if(ida.get(2) == 10)
		{
			System.out.println("yay2.1");
		}
		if(ida.capacity() == 4)
		{
			System.out.println("yay2.2!");
		}



		ida.add(1,-10);
		ida.add(4,100);
		System.out.println("Array should be: 0 -10 5 10 100 null null null with size: 5");
		ida.print();
		System.out.println(ida.size());

		if (ida.set(1,-20) == -10){
			System.out.println("Yay 3");
		}
		System.out.println("set index 1 to -20");
		ida.print();

		if(ida.get(2) == 5){
			System.out.println("Yay3.1");
		}

		ida.print();
		if(ida.size() == 5)
		{
			System.out.println("Yay3.2");
		}

		if(ida.capacity() == 8)
		{
			System.out.println("Yay3.3");
		}

//EVERYTHING ABOVE WORKS!
		
		// removing from the list?
		if (ida.remove(0) == 0){
			System.out.println("Yay 4");
		}
		ida.print();
		System.out.println("size: "+ida.size());

		if(ida.remove(0) == -20)
		{
			System.out.println("yay 4.1");
		}
		ida.print();

		if(ida.remove(2) == 100)
		{
			System.out.println("yay 4.2");
		}
		ida.print();
		
		System.out.println("ida size: "+ida.size() +" should be 4");
		if(ida.size() == 2)
		{
			System.out.println("yay 4.3");
		}

		System.out.println("ida cap: "+ida.capacity()+" should be 4");
		if(ida.capacity() == 4)
		{
			System.out.println("yay 4.4!");
		} 
		ida.print();

		// remember to tests more things...
	
	}

}