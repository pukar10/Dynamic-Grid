// TO DO: add your implementation and JavaDocs
/*
	GOT YAY 1 
	WORK ON YAY 2
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
		if(index < 0 || index > capacity)
		{
			throw new IndexOutOfBoundsException();
		}

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
	get item from the given index
	@parameter index of the item you want to get back
	@return item at the given index
	@throw IndexOutOfBoundsException if index lower than 0 or greater than capacity
	*/
	public T get(int index){
		// return the item at index
		// O(1)

		// throw IndexOutOfBoundsException for invalid index
		if(index < 0 || index > capacity)
		{
			throw new IndexOutOfBoundsException();
		}

		//return item at given index
		return ( storage[index] );
	}

	/*
	adds an item at the end of the list (append)
	increments size
	doubleCapacity if needs more space

	@parameter value item value you want to add
	@return true if success false if not success
	*/

	@SuppressWarnings("unchecked")
	public boolean add(T value){
		// add value to the end of the list (append)
		// return true	 
		
		// Note: Remember... code reuse is awesome...

		//if value is null return false.
		if(value == null)
		{
			return false;
		}

		System.out.println("value: " + value);
		System.out.println("size: " + size);
		System.out.println("capacity: " + capacity);
		System.out.println(storage[0]);

		//double size of array
		if(this.size >= this.capacity)
		{
			//makes storage twice the size keeps all items
			doubleCapacity();
			System.out.println("new capacity: " + capacity);
		}

		//add item at end of list.
		//this is broken but why?
		storage[size] = value;

		//increment size
		size++;

		//return true if item is in spot
		if(storage[size-1] == null)
		{
			System.out.println("DynamicArray:add:What?");
		}
		return true;

	}
	
	/*
	add item anywhere into dynamic array
	doubleCapacity if need be
	shifts items up if already item in slot

	@parameter index slot you want to add item into
	@parameter value item value you want to add into index
	@throw IndexOutOfBoundsException if index lower than 0 or greater than capacity
	*/
	@SuppressWarnings("unchecked")
	public void add(int index, T value){  
		// O(N) where N is the number of elements in the list
		
		// Note: this method may be used to append items as
		// well as insert items

		// throw IndexOutOfBoundsException for invalid index
		if(index < 0 || index > capacity)
		{
			throw new IndexOutOfBoundsException();
		}

		// double the capacity if no space is available or if last element is not null (need for shifting)
		if(this.size >= this.capacity || storage[capacity-1] != null)
		{
			doubleCapacity();
		}

		// insert value at index, shift elements if needed
		if(storage[index] != null)
		{
			//shift all items over 1
			System.out.println("2add() index is not null shift items up index: " + index);

			shiftItemsUp(index);

			System.out.println("index should be same as 2add() statement: " + index);
			System.out.println("value: " + value);

			storage[index] = value;
			size++;

		}else{
			storage[index] = value;
			size++;
		}

		System.out.println("Array after add()");
		for(int i = 0; i < capacity; i++)
		{
			System.out.println(storage[i]);
		}
	}
	
	
	/*
	remove item from dynamic array at position index.
	shift elements to cover up gap.
	halve capacity if number of elements falls below 1/3 of the capacity
	capacity does not go below INITCAP

	@parameter index of item you want to remove
	@return item you removed
	@throw IndexOutofBoudnsException if index lower than 0 or greater than capacity
	*/
	@SuppressWarnings("unchecked")
	public T remove(int index){
		// remove and return element at position index
		// shift elements to remove any gap in the list
		
		// halve capacity if the number of elements falls below 1/3 of the capacity
		// capacity should NOT go below INITCAP
		
		// O(N) where N is the number of elements in the list

		// throw IndexOutOfBoundsException for invalid index
		
		System.out.println("DynamicArray: remove: index-"+index+"- array display");
		for(int i = 0; i < capacity; i++)
		{
			System.out.println(storage[i]);
		}

		if(index < 0 || index > capacity)
		{
			throw new IndexOutOfBoundsException(); 
		}

		//if index is null return null
		if(storage[index] == null)
		{
			System.out.println("DynamicArray:remove:nothing in index");
			return null;
		}

		//get item at index
		T item = storage[index];

		if(index == capacity)
		{
			size--;
			if(size == capacity * (1/3))
			{
				halfCapacity();
			}
			return item;
		}

		//shift everything to cover gap.
		shiftItemsDown(index);

		//return item
		size--;
		if(size <= capacity * (1.0/3.0))
		{
			halfCapacity();
		}

		System.out.println("new storage: ");
		for(int i = 0; i < capacity; i++)
		{
			System.out.println(storage[i]);
		}

		return item;
	}

	/*
	halves the capacity of the dynamic array then moves all items into smaller array
	 */
	private void halfCapacity()
	{
		//half capacity
		this.capacity /= 2;

		//create temp array
		T[] temp = (T[]) new Object[capacity];
		
		//fill temp with values
		for(int i = 0; i < capacity; i++)
		{
			temp[i] = storage[i];
		}

		//make storage = temp
		storage = temp;
	}

	/*
	shifts all items down one spot
	@param index of the spot you want to start moving down from
	 */
	private void shiftItemsDown(int index)
	{
		//print original
		System.out.println("shiftItemsDown: orignial array:");
		for(int i = 0; i < capacity; i++)
		{
			System.out.println(storage[i]);
		}
		
		System.out.println("capacity: " + capacity);
		System.out.println("start: index: " + index);
		
		for(int i = index; i < capacity - 1; i++)
		{
			System.out.println("iteration: " + i);
			storage[i] = storage[i+1];
		}

		//print new Array
		System.out.println("shiftItemsDown: new array: from " + index + " should be changed");
		for(int i = 0; i < capacity; i++)
		{
			System.out.println(storage[i]);
		}

	
	}

	/**
	 * shift items up in the dynamic array
	 * @param index index you want to start shifting items up
	 */
	private void shiftItemsUp(int index)
	{
		//print original
		System.out.println("shiftItemsup: orignial array:");
		for(int i = 0; i < capacity; i++)
		{
			System.out.println(storage[i]);
		}

		System.out.println("forloopstart: " + capacity);
		System.out.println("forloopEnd: " + index);
		System.out.println("capacity: " + capacity);

		for(int i = capacity - 1; i >= index; i--)
		{
			storage[i] = storage[i - 1];
		}

		//print shifted array
		System.out.println("shiftItemsup:shifted Array:");
		for(int i = 0; i < capacity; i++)
		{
			System.out.println(storage[i]);
		}
	}

	/**
	 * double capacity of the dynamic array
	 */
	private void doubleCapacity()
	{
		//get new capacity
		int newCapacity = capacity * 2;

		//make new array with new capacity
		T[] temp = (T[]) new Object[newCapacity];

		//put data into new array
		//problem here
		System.out.println("Double capacity: size: " + size);
		System.out.println("Double capacity: capacity: " + capacity);
		
		for(int i = 0; i < size; i++)
		{
			temp[i] = storage[i];
		}

		//make storage equal to new array
		storage = temp;

		//update capacity
		this.capacity = newCapacity;
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
			System.out.println("Array should be 0 5 10");
		}

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
		System.out.println("Array should be: 0 -10 5 10 100");

		if (ida.set(1,-20)==-10 && ida.get(2) == 5 && ida.size() == 5 
			&& ida.capacity() == 8 ){
			System.out.println("Yay 3");
		}

//EVERYTHING ABOVE WORKS!
		
		// removing from the list?
		if (ida.remove(0) == 0){
			System.out.println("Yay 4");
		}

		if(ida.remove(0) == -20)
		{
			System.out.println("yay 4.1");
		}
		if(ida.remove(2) == 100)
		{
			System.out.println("yay 4.2");
		}
		if(ida.size() == 2)
		{
			System.out.println("yay 4.3");
		}

		if(ida.capacity() == 4)
		{
			System.out.println("yay 4.4!");
		} 
		
		// remember to tests more things...
	}

}