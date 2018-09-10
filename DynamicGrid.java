// TO DO: add your implementation and JavaDoc

public class DynamicGrid<T>{
	private DynamicArray<DynamicArray<T>> storage;	// underlying storage
	// HINT: Read the big-O requirements of the methods below to determine
	// how the columns/rows should be stored in storage.

	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!
	private int rows;
	private int cols;

	public DynamicGrid(){
		// constructor
		// create an empty table of 0 rows and 0 cols
		storage = new DynamicArray<DynamicArray<T>>();
	}

	//number of items not capacity
	public int getNumRow() {
		// return number of rows in the grid
		// O(1) 
		return storage.size();
	}

	//number of items not capacity
	public int getNumCol() {
		// return number of columns in the grid
		// O(1) 
		return (storage.size() == 0) ? 0 : storage.get(0).size();
	}

	public T get(int indexRow, int indexCol){
		// return the value at the specified row and column
		// throw IndexOutOfBoundsException for invalid index
		// O(1)
		if(storage.size() == 0)
		{
			return null;
		}else{
			checkRowBound(indexRow);
			checkColBound(indexCol);

			return storage.get(indexRow).get(indexCol);	
		}
	}

	public T set(int indexRow, int indexCol, T value){
		// Note: this can not be used to add new items, only replace

		// change value at the specified row and column to be value
		// return the old value
		// throw IndexOutOfBoundsException for invalid index
		// O(1)

		// existing items.
		checkRowBound(indexRow);
		checkColBound(indexCol);

		//get item then change it
		T item = storage.get(indexRow).get(indexCol);
		storage.get(indexRow).set(indexCol, value);

		return item;
	}

	public boolean addRow(int index, DynamicArray<T> newRow){
		// copy values from newRow to add a row at the row index specified
		// cannot add if the length of newRow does not match existing rows
		// 
		// Note: make a deep copy of the incoming row for insertion
		//
		// return true if newRow can be added correctly
		// return false otherwise
		// 
		// O(C+R) where R is the number of rows and C is the number of columns of the grid
		// Hint: Remember the big-O of the underlying DynamicArray of DynamicArrays

		// Note: this can be used to append rows as well as insert rows

		//GOOD
		checkRowBound(index);
		if(checkRowLength(newRow))
		{
			if(storage.get(index) == null)
			{
				storage.add(index, newRow);
				this.rows++;
				return true;

			}else{
				//deep oopy
				for(int i = 0; i < newRow.capacity(); i++)
				{
					storage.get(index).set(i, newRow.get(i));
				}
				this.rows++;
				return true;
			}
		}
		return false;
	}

	public boolean addCol(int index, DynamicArray<T> newCol){
		// copy values from newCol to add a column at the column index specified
		// cannot add if the length of newCol does not match existing columns
		//
		// return true if newCol can be added correctly
		// return false otherwise
		//
		// O(RC) where R is the number of rows and C is the number of columns of the grid
		// Hint: Remember the big-O of the underlying DynamicArray of DynamicArrays

		// Note: this can be used to append columns as well as insert columns
		checkColBound(index);
		if(checkColLength(newCol))
		{
			if(storage.get(0).get(index) == null)
			{
				for(int i = 0; i < newCol.size(); i++)
				{
					storage.get(i).add(index, newCol.get(i));
				}
				this.cols++;
				return true;
			}else{
				//if editing an existing column
				for(int i = 0; i < newCol.size(); i++)
				{
					storage.get(i).set(index, newCol.get(i));
				}
				return true;
			}
		}else{
			return false;
		}
	}

	public DynamicArray<T> removeRow(int index){
		// remove and return a row at index x
		// shift rows to remove the gap
		// throw IndexOutOfBoundsException for invalid index
		// Hint: Use the underlying storage to do 90% of this...
		//
		// O(R) where R is the number of rows of the grid

		checkRowBound(index);
		if(storage.get(index) == null)
		{
			return null;
		}else{
			this.rows--;
			return storage.remove(index);
		}
	}

	public DynamicArray<T> removeCol(int index){
		// remove and return a col at index x
		// shift columns to remove the gap
		// throw IndexOutOfBoundsException for invalid index
		// Hint: Use the underlying storage to do 90% of this...
		//
		// O(RC) where R is the number of rows and C is the number of columns

		checkColBound(index);
		if(storage.get(index) == null)
		{
			return null;
		}else{
			DynamicArray<T> holder = new DynamicArray<>();
			for(int i = 0; i < this.rows; i++)
			{
				holder.add(storage.get(i).remove(index));
			}
			this.cols--;
			return holder;
		}
	}

	private boolean checkColLength(DynamicArray<T> newCol)
	{
		if(storage.size() == 0)
		{
			this.rows = newCol.size();
			return true;
		}else{
			return (newCol.size() == this.cols) ? true : false;
		}
	}

	private boolean checkRowLength(DynamicArray<T> newRow)
	{
		if(storage.size() == 0)
		{
			this.cols = newRow.size();
			return true;
		}else{
			return (newRow.size() == this.cols) ? true : false;
		}
	}



	private void checkRowBound(int index)
	{
		if(index < 0 || index > this.rows)
		{
			throw new IndexOutOfBoundsException();
		}
	}

	private void checkColBound(int index)
	{
		if(index < 0 || index > this.cols)
		{
			throw new IndexOutOfBoundsException();
		}
	}

	// --------------------------------------------------------
	// example testing code... edit this as much as you want!
	// --------------------------------------------------------


	// Not required, update for your own testing
	@Override
	public String toString(){
		return "dynamic grid";
	}


	public static void main(String[] args){
		// make some simple grids
		System.out.println("start DynamicGrid");

		DynamicGrid<String> sgrid = new DynamicGrid<>();
		DynamicArray<String> srow = new DynamicArray<>();

		srow.add("English");
		srow.add("Spanish");
		srow.add("German");

		System.out.println("Start Debugging\n");

		if (sgrid.getNumRow() == 0)
		{
			System.out.println("Yay 1");
		}
		if(sgrid.getNumCol() == 0)
		{
			System.out.println("Yay 1.1");
		}
		if(sgrid.addRow(0, srow))
		{
			System.out.println("Yay 1.2");
		}
		if(sgrid.getNumRow() == 1)
		{
			System.out.println("Yay 1.3");
		}
		if(sgrid.getNumCol() == 3)
		{
			System.out.println("Yay 1.4");
		}


		if (sgrid.get(0,0).equals("English") && sgrid.set(0,1,"Espano").equals("Spanish")
				&& sgrid.get(0,1).equals("Espano")) {
			System.out.println("Yay 2");
		}




		// more complicated grids
		DynamicGrid<Integer> igrid = new DynamicGrid<Integer>();
		boolean ok = true;

		for (int i=0; i<3; i++){
			DynamicArray<Integer> irow = new DynamicArray<>();
			//irow.add : 10, 20, 30
			irow.add( (i+1) *10);

			ok = ok && igrid.addRow(igrid.getNumRow(),irow);
		}
		if (ok && igrid.getNumRow() == 3 && igrid.getNumCol() == 1 && igrid.get(2,0)==30) {
			System.out.println("Yay 3");
		}
		

//DEBUG THIS

		DynamicArray<Integer> icol = new DynamicArray<>();
		icol.add(-10);
		icol.add(-20);
		ok = igrid.addCol(1,icol);
		icol.add(-30);
		if (!ok && igrid.addCol(1,icol) && igrid.getNumRow() == 3 && igrid.getNumCol() == 2){
			System.out.println("Yay 4");		
		}
		
/*		
		DynamicArray<Integer> irow = new DynamicArray<>();
		irow.add(5); irow.add(10);
		if (!igrid.addRow(5,irow) && igrid.addRow(0,irow) && igrid.getNumRow() == 4 &&
			igrid.get(0,0) == 5 && igrid.get(3,1) == -30){
			System.out.println("Yay 5");		
		}
		//System.out.println(igrid.toString());
		
		irow = igrid.removeRow(2);
		if (igrid.getNumRow() == 3 && igrid.getNumCol() == 2 && irow.get(0) == 20 &&
			igrid.get(0,1) == 10 && igrid.get(2,0) == 30){
			System.out.println("Yay 6");		
		}		
*/
	}

}