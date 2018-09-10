// TO DO: add your implementation and JavaDocs
/*
	WORKING
*/

public class DynamicArray<T>{
	
	private static final int INITCAP = 2;	// default initial capacity / minimum capacity
	private T[] storage;	// underlying array

	private int capacity; //length of array
	private int size;	  //items in array
	
	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!

	/*
	constructor
	creates generic array that holds INITCAP (which is initially set to 2)
	*/	
	@SuppressWarnings("unchecked")
	public DynamicArray(){
		// constructor
		// initial capacity of the array should be INITCAP
		
		//creates generic array, initial capacity 2
		storage = (T[]) new Object[INITCAP];

		//set capacity and size
		this.capacity = INITCAP;
		this.size = 0;
	}

	/*
	constructor 
	creates generic array that holds initCapacity
	throws error if initCapacity < 1
	@parameter initCapacity size for dynamic aray
	@throw IllegalArgumentException if initCapacity lower than 1
	*/
	@SuppressWarnings("unchecked")
	public DynamicArray(int initCapacity){
		// constructor

		// throw IllegalArgumentException if initCapacity < 1
		if(initCapacity < 1)
		{
			throw new IllegalArgumentException();
		}

		// set the initial capacity of the array as initCapacity
		storage = (T[]) new Object[initCapacity];

		//set capacity and size
		this.capacity = initCapacity;
		this.size = 0;
	}

	/*
	size keeps track of the dynamicArray size.
	size is number of elements inside the dynamic array
	returns size of dynamicArray
	@return size, elements in dyanimc array
	*/
	public int size() {	  
		//report the number of elements in the list
		// O(1)

		return this.size;
	}
	
	/*
	capacity keeps track of dynanimcArray length.
	capacity is amount of slots in dyamicArray.
	returns capacity of dynamicArray
	@return capacity, length of dynamic array
	*/
	public int capacity() { 
		//report the max number of elements before the next expansion
		// O(1)

		return this.capacity;
	}
		
	/*
	updates item at given index with given value
	does not add new items

	@parameter index of the item you want to change
	@parameter value you want to update the item with
	@return old item at given index
	@throw IndexOutOfBoundsException if index is lower than 0 or greater than capacity
	*/
	public T set(int index, T value){
		// change item x at index i to be value	
		// return old item at index
		// O(1)
		
		// Note: you cannot add new items with this method

		// throw IndexOutOfBoundsException for invalid index
		checkIndexBounds(index);

		//don't add new items
		if(storage[index] == null)
		{
			System.out.println("DynamicArray:set:index is null");
			return null;
		}

		//save old item
		T item = storage[index];

		//update value
		storage[index] = (T) value;

		//return old item
		return item;
	}

	/*
	item from the given index
	@parameter index of the item you want to get back
	@return item at the given index
	@throw IndexOutOfBoundsException if index lower than 0 or greater than capacity
	*/
	public T get(int index){
		// return the item at index
		// O(1)
		// throw IndexOutOfBoundsException for invalid index

		checkIndexBounds(index);

		//return item at given index
		return ( storage[index] );
	}

	/*
	adds an item at the end of the list (append)
	increments size
	doubleCapacity if needs more space
	if value is null return false
	double size of array is qual to or over capacity size

	@parameter value item value you want to add
	@return true if success false if not success
	*/

	@SuppressWarnings("unchecked")
	public boolean add(T value){
		if(value == null)
		{
			return false;
		}

		if(checkSizetoCapacity())
		{
			doubleCapacity();
		}

		//add item at end of list.
		storage[size] = value;

		//increment size
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

	
	/*
	remove and return element at position index
	remove item from dynamic array at position index.
	shift elements to cover up gap.
	halve capacity if number of elements falls below 1/3 of the capacity
	capacity does not go below INITCAP
	throw IndexOutOfBoundsException for invalid index
	O(N)
	Capacity should not go below INITCAP

	@parameter index of item you want to remove
	@return item you removed
	@throw IndexOutofBoudnsException if index lower than 0 or greater than capacity
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


	private void shiftItemsDown(int index)
	{
		for(int i = index; i < storage.length - 1; i++)
		{
			storage[i] = storage[i+1];
		}
	}

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
	
	private boolean checkSizetoCapacity()
	{
		return (this.size >= this.capacity-1) ? true : false;
	}

	private void checkIndexBounds(int index)
	{
		if(index < 0 || index > this.capacity)
		{
			throw new IndexOutOfBoundsException();
		}
	}

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