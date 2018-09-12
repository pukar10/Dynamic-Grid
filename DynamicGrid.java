// Pukar Subedi

public class DynamicGrid<T>{
	private DynamicArray<DynamicArray<T>> storage;	// underlying storage

	private int rows;
	private int cols;

	/**
	 * constructor
	 * create an empty table of 0 rows and 0 cols
	 * @return nothing
	 */
	public DynamicGrid(){
		storage = new DynamicArray<DynamicArray<T>>();
	}

	/**
	 * return number of rows in grid
	 * O(1)
	 * 
	 * @return number of rows
	 */
	public int getNumRow() {
		return storage.size();
	}

	/**
	 * return number of columns in the grid
	 * O(1)
	 * 
	 * @return number of columns
	 */
	public int getNumCol() {
		return (storage.size() == 0) ? 0 : this.cols;
	}

	/**
	 * return the value at the specified row and column
	 * ther class throws IndexOutOfBoundsException for invalid index
	 * O(1)
	 * 
	 * @param  indexRow index of row
	 * @param  indexCol index of col
	 * @return          value at given row and col
	 */
	public T get(int indexRow, int indexCol){
		if(storage.size() == 0)
		{
			return null;
		}else{
			checkRowBound(indexRow);
			checkColBound(indexCol);

			return storage.get(indexRow).get(indexCol);	
		}
	}

	/**
	 * conly used to replace existing items NOT ADD NEW ONES
	 * change value at the specified row and column to be value
	 * return the old value
	 * other class takes care of IndexOutOfBoundsException for invalid index
	 * O(1)
	 * 
	 * @param  indexRow row Index
	 * @param  indexCol col index
	 * @param  value    new value
	 * @return          old value
	 */
	public T set(int indexRow, int indexCol, T value){
		checkRowBound(indexRow);
		checkColBound(indexCol);

		T item = storage.get(indexRow).get(indexCol);
		storage.get(indexRow).set(indexCol, value);

		return item;
	}

	/**
	 * copy values from newRow to add a row at the row index specified
	 * cannot add if the length of newRow does not match existing rows.
	 * make a deep copy
	 * return true if newRow can be added
	 * return false otherwise
	 * used to append rows as well as insert rows!
	 * O(C+R) where R is the number of rows and C isthe number of Columns of the grid
	 * 
	 * @param  index  index of row position to add at
	 * @param  newRow new row you want to add
	 * @return        true of success false otherwise
	 */
	public boolean addRow(int index, DynamicArray<T> newRow){
		if(checkRowBound(index))
		{
			if(checkRowLength(newRow))
			{
				DynamicArray<T> tmp = new DynamicArray<>();
				for(int i = 0; i < newRow.size(); i++)
				{
					tmp.add(newRow.get(i));
				}

				storage.add(index, tmp);
				updateRowsCols();
				return true;

			}else{
				return false;
			}	
		}else{
			return false;
		}
	}

	/**
	 * copy values from newCol to add a column at the column index specified 
	 * cannot add if the length of newCol does not match existing columns
	 * return true if newCol can be added correctly
	 * return false otherwise
	 * used to append columns as well as insert columns
	 * O(RC) where R is the number of rows and C is the number of columns of the grid
	 * 
	 * @param  index  position you want to add colmn
	 * @param  newCol new values you want added
	 * @return        old values - replaced values
	 */
	public boolean addCol(int index, DynamicArray<T> newCol){
		checkColBound(index);
		if(checkColLength(newCol))
		{
			for(int i = 0; i < newCol.size(); i++)
			{
				storage.get(i).add(index, newCol.get(i));
			}
			updateRowsCols();
			return true;
		}else{
			return false;
		}
	}

	/**
	 * remove and return a row at index x
	 * shift rows to remove the gap
	 * other class handles IndexOutOfBoundsException for invalid index
	 * O(R) where R is the number of rows 
	 * 
	 * @param  index Row index you want removed
	 * @return       values of row that was removed
	 */
	public DynamicArray<T> removeRow(int index){
		checkRowBound(index);
		this.rows--;
		return storage.remove(index);
	}

	/**
	 * remove and return a col at index x
	 * shift columns to remove the gap
	 * other class handles IndexOutOfBoundsException for invalid index
	 * O(RC) where R is the number of rows and C is the number of columns
	 * 
	 * @param  index column index to be removed
	 * @return       old values that were removed
	 */
	public DynamicArray<T> removeCol(int index){
		checkColBound(index);
		DynamicArray<T> tmp = new DynamicArray<>();
		for(int i = 0; i < this.rows; i++)
		{
			tmp.add(storage.get(i).remove(index));
		}
		this.cols--;
		return tmp;
	}

	/**
	 * helper method to update number rows and cols
	 */
	private void updateRowsCols()
	{
		this.rows = storage.size();
		this.cols = storage.get(0).size();
	}

	/**
	 * helper method to check if Col can be added to grid
	 * @param  newCol col to check
	 * @return        true if can be added false if not
	 */
	private boolean checkColLength(DynamicArray<T> newCol)
	{
		if(storage.size() == 0)
		{
			return true;
		}else{
			if(this.rows == newCol.size())
			{
				return true;
			}else{
				return false;
			}
		}
	}

	/**
	 * helper method to check if row can be added to the grid
	 * 
	 * @param  newRow row to check
	 * @return        true if able false if not
	 */
	private boolean checkRowLength(DynamicArray<T> newRow)
	{
		if(storage.size() == 0)
		{
			return true;
		}else{
			if(this.cols == newRow.size())
			{
				return true;
			}else{
				return false;
			}
		}
	}

	/**
	 * helper method to check if index is inside row bounds
	 * 
	 * @param  index index to check
	 * @return       true if inside bounds false if outside
	 */
	private boolean checkRowBound(int index)
	{
		return (index < 0 || index > this.rows) ? false : true;
	}

	/**
	 * helper method to check if index is inside col bounds
	 *
	 * @param  index index to check
	 * @return       true if inside bounds false if outside bounds
	 */
	private void checkColBound(int index)
	{
		if(index < 0 || index > this.cols)
		{
			throw new IndexOutOfBoundsException();
		}
	}

	/**
	 * helper method for debugging
	 */
	private void print()
	{
		for(int i = 0; i < storage.capacity(); i++)
		{
			for(int x = 0; i < storage.get(i).capacity(); i++)
			{
				System.out.println(storage.get(i).get(i));
			}
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

//persnal test		


//DEBUG THIS

		DynamicArray<Integer> icol = new DynamicArray<>();
		icol.add(-10);
		icol.add(-20);
		ok = igrid.addCol(1,icol);
		icol.add(-30);
		if (!ok && igrid.addCol(1,icol))
		{
			System.out.println("Yay 4");		
		}
		if(igrid.getNumRow() == 3)
		{
			System.out.println("Yay4.1");
		}

		//work on getNumCol();
		if(igrid.getNumCol() == 2)
		{
			System.out.println("Yay4.2");
		}
		

		DynamicArray<Integer> irow = new DynamicArray<>();
		System.out.println("debug:");

		System.out.println(igrid.getNumRow()); //3 rows

		System.out.println("irow.add(): "+irow.add(5));
		System.out.println("irow.add(): "+irow.add(10));

		if (!igrid.addRow(5,irow))
		{
			System.out.println("Yay 5");		
		}
		if(igrid.addRow(0,irow))
		{
			System.out.println("Yay 5 first");
		}
		if(igrid.getNumRow() == 4)
		{
			System.out.println("Yay 5.1");
		}
		if(igrid.get(0,0) == 5)
		{
			System.out.println("Yay 5.2");
		}
		if(igrid.get(3,1) == -30)
		{
			System.out.println("yay 5.3");
		}
		System.out.println(igrid.toString());
		irow = igrid.removeRow(2);
		if (igrid.getNumRow() == 3 && igrid.getNumCol() == 2 && irow.get(0) == 20 &&
			igrid.get(0,1) == 10 && igrid.get(2,0) == 30){
			System.out.println("Yay 6");		
		}		
	}

}