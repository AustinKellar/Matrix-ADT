//Austin Kellar
//akellar
//CMPS 101
//1/15/17
//Doubly linked list implementation of List ADT with cursor
public class Matrix
{
	//private inner entry class
	private class Entry
	{
		//fields
		private int column;
		private double value;

		//contructor
	    Entry(int c, double x)
	    {
            column = c;
            value = x;
	    }

	    //toString()
	    public String toString()
	    {
            String returnValue = "(" + column + ", " + value + ")";
            return returnValue;
	    }

	    //equals()
        public boolean equals(Object x)
        {
    	    boolean eq = false;
    	    Entry that;
    	    if(x instanceof Entry)
    	    {
    		    that = (Entry)x;
    		    eq = (this.value == that.value && this.column == that.column);
    	    }
    	    return eq;
        }
	}

	//fields
	private List[] A;
	private int size;

	//constructor
	Matrix(int n)
	{
		size = n;
        A = new List[size];

        for(int i=0; i<size; i++)
            A[i] = new List();
	}

    //private inner function dot

	//Access Functions -----------------------------------------------------
    //dot()
    //returns the dot product of vectors P and Q
    private static double dot(List P, List Q)
    {
        P.moveFront();
        Q.moveFront();
        double dotProduct = 0;
        Entry pEntry,qEntry;
        while(P.index() != -1 && Q.index() != -1)
        {
            pEntry = (Entry) P.get();
            qEntry = (Entry) Q.get();
            if(pEntry.column == qEntry.column)
            {
                dotProduct += (pEntry.value * qEntry.value);
                P.moveNext();
                Q.moveNext();
            }
            else if(pEntry.column < qEntry.column)
            {
                P.moveNext();
            }
            else //(Q.get().column < P.get().column)
            {
                Q.moveNext();
            }
        }
        return dotProduct;
    }

	//getSize()
	//Returns the number of rows and columns of this Matrix
    int getSize()
    {
    	return size;
    }

    //getNNZ()
    //returns the number of Non-Zero entries in this Matrix
    int getNNZ()
    {
    	int count = 0;
    	for(int i=0; i<size; i++)
    	{
            count += A[i].length();
    	}
    	return count;
    }

    //equals()
    //Overrides Object's equals() method
    public boolean equals(Object x)
    {
        Matrix that;
        if(x instanceof Matrix)
        {
            that = (Matrix)x;
            if(this.size != that.size)
            {
                return false;
            }
            for(int i=0; i<this.size; i++)
            {
                if(this.A[i].length() != that.A[i].length())
                {
                    return false;
                }

                this.A[i].moveFront();
                that.A[i].moveFront();
                Entry thisEntry, thatEntry;
                while(this.A[i].index() >= 0)
                {
                    thisEntry = (Entry) this.A[i].get();
                    thatEntry = (Entry) that.A[i].get();

                    if(!thisEntry.equals(thatEntry))
                        return false;
            
                    this.A[i].moveNext();
                    that.A[i].moveNext();
                }
            }
        }
        return true;
    }

    //Mutator Functions ------------------------------------------------------

    //makeZero()
    //sets this Matrix to the zero state
    void makeZero()
    {
    	int j;
    	for(int i=0; i<size; i++)
    	{
    		A[i].clear();
    	}
    }

    //copy()
    //Returns a new Matrix having the same entries as this Matrix
    Matrix copy()
    {
    	Matrix M = new Matrix(this.size);
        Entry entry;

    	for(int i=0; i<this.size; i++)
    	{
    		this.A[i].moveFront();
    		while(this.A[i].index() >= 0)
    		{
                entry = (Entry) this.A[i].get();
                M.A[i].append(new Entry(entry.column, entry.value));
                this.A[i].moveNext();
    		}
    	}
    	return M;
    }

    //void changeEntry()
    //changes ith row, jth column of this Matrix to x
    //Pre: 1<=i<=getSize(), 1<=j<=getSize()
    void changeEntry(int i, int j, double x)
    {
	    if(i<1 || i>size)
	    	throw new RuntimeException("Matrix Error: changeEntry() called on invalid row index");
	    if(j<1 || j>size)
	    	throw new RuntimeException("Matrix Error: changeEntry() called on invalid column index");

        boolean added = false;
        int index = i-1;
        Entry entry;
        boolean run = true;

        if(x == 0)
        {
            run = false;
            if(A[index].length() > 0)
            {
                A[index].moveFront();
                while(A[index].index() >= 0)
                {
                    entry = (Entry) A[index].get();
                    if(entry.column == j)
                        A[index].delete();
                    A[index].moveNext();
                }
            }
        }
        	
        if(run)
        {
           
            if(A[index].length() == 0)
            {
             	A[index].append(new Entry(j,x));
        	    added = true;
            }
            else
            {
        	    A[index].moveFront();
	            while(A[index].index() >= 0)
	            {
                    entry = (Entry) A[index].get();
	    	        if(entry.column == j)
	    	        {
	    		        A[index].insertBefore(new Entry(j,x));
	    		        A[index].delete();
	    		        added = true;
	    		        break;
	    	        }
	    	        if(entry.column > j)
	    	        {
	    		        A[index].insertBefore(new Entry(j,x));
	    		        added = true;
	    		        break;
	        	    }
	        	    A[index].moveNext();
	            }
	            if(!added)
	            {
	        	    A[index].append(new Entry(j,x));
	            }   
            }
        }

	  
    }

    //scalarMult
    //Returns a new Matrix that is the scalar product of this Matrix with x
    Matrix scalarMult(double x)
    {
    	Matrix M = new Matrix(this.size);
    	if(x == 0.0)
    	{
    		return M;
    	}

    	int j,k,index;
    	double data,newData;
        Entry entry;
    	for(int i=1; i<=this.size; i++)
    	{
    		index = i-1;
    		this.A[index].moveFront();
            while(this.A[index].index() >= 0)
            {
                entry = (Entry) this.A[index].get();
                data = entry.value;
                newData = data*x;
                M.changeEntry(i,entry.column,newData);
                A[index].moveNext();
            }
    	}
    	return M;
    }

    //add()
    //Returns a new Matrix that is the sum of this Matrix with M
    //Pre: getSize() == M.getSize()
    Matrix add(Matrix M)
    {
    	if(this.size != M.size)
    		throw new RuntimeException("Matrix Error: add() called on incompatible matrices");

    	Matrix N = new Matrix(this.size);
    	int index;
    	double thisData, thatData, newData;
        Entry thisEntry, thatEntry;

        if(this.equals(M))
        {
            return this.scalarMult(2);
        }

    	for(int i=1;i<=this.size;i++)
    	{
    		index = i-1;
    		this.A[index].moveFront();
    		M.A[index].moveFront();
    		while(this.A[index].index() != -1 && M.A[index].index() != -1)
    		{  
                thisEntry = (Entry) this.A[index].get();
                thatEntry = (Entry) M.A[index].get();
                if(thisEntry.column == thatEntry.column)
                {
                	thisData = thisEntry.value;
                	thatData = thatEntry.value;
                	newData = thisData + thatData;
                	N.changeEntry(i, thisEntry.column, newData);
                	this.A[index].moveNext();
                	M.A[index].moveNext();
                }
                else if(thisEntry.column < thatEntry.column)
                {
                	N.changeEntry(i, thisEntry.column, thisEntry.value);
                	this.A[index].moveNext();
                }
                else //(M.A[index].get().column < this.A[index].get().column)
                {
                	N.changeEntry(i, thatEntry.column, thatEntry.value);
                	M.A[index].moveNext();
                }
	        }    
                
            while(M.A[index].index() == -1 && this.A[index].index() != -1)
            {
                thisEntry = (Entry) this.A[index].get();
                N.changeEntry(i, thisEntry.column, thisEntry.value);
                this.A[index].moveNext();
            }
            while(this.A[index].index() == -1 && M.A[index].index() != -1)
            {
                thatEntry = (Entry) M.A[index].get();
                N.changeEntry(i, thatEntry.column, thatEntry.value);
                M.A[index].moveNext();
            }                  
    	}
    	return N;
    }

    //sub()
    //Returns a new Matrix that is the difference of this Matrix with M
    //Pre: getSize() == M.getSize()
    Matrix sub(Matrix M)
    {
        if(this.size != M.size)
            throw new RuntimeException("Matrix Error: sub() called on incompatible matrices");

        Matrix N = new Matrix(this.size);
        int index;
        double thisData, thatData, newData;
        Entry thisEntry, thatEntry;

        if(this.equals(M))
        {
            return this.scalarMult(0);
        }

        for(int i=1;i<=this.size;i++)
        {
            index = i-1;
            this.A[index].moveFront();
            M.A[index].moveFront();
            while(this.A[index].index() != -1 && M.A[index].index() != -1)
            {  
                thisEntry = (Entry) this.A[index].get();
                thatEntry = (Entry) M.A[index].get();
                if(thisEntry.column == thatEntry.column)
                {
                    thisData = thisEntry.value;
                    thatData = thatEntry.value;
                    newData = thisData - thatData;
                    N.changeEntry(i, thisEntry.column, newData);
                    this.A[index].moveNext();
                    M.A[index].moveNext();
                }
                else if(thisEntry.column < thatEntry.column)
                {
                    N.changeEntry(i, thisEntry.column, thisEntry.value);
                    this.A[index].moveNext();
                }
                else //(M.A[index].get().column < this.A[index].get().column)
                {
                    N.changeEntry(i, thatEntry.column, thatEntry.value * -1);
                    M.A[index].moveNext();
                }
            }    
                
            while(M.A[index].index() == -1 && this.A[index].index() != -1)
            {
                thisEntry = (Entry) this.A[index].get();
                N.changeEntry(i, thisEntry.column, thisEntry.value);
                this.A[index].moveNext();
            }
            while(this.A[index].index() == -1 && M.A[index].index() != -1)
            {
                thatEntry = (Entry) M.A[index].get();
                N.changeEntry(i, thatEntry.column, thatEntry.value * -1);
                M.A[index].moveNext();
            }                  
        }
        return N;
    }

    //transpose()
    //Returns a new Matrix that is the transpose of this Matrix
    Matrix transpose()
    {
    	Matrix M = new Matrix(this.size);
    	int i,j,index;
    	double data;
        Entry entry;

    	for(int k=1; k<=this.size; k++)
    	{
    		index = k-1;
    		this.A[index].moveFront();
    		while(this.A[index].index() >= 0)
    		{
               entry = (Entry) this.A[index].get();
               j = entry.column;
               i = k;
               data = entry.value;
               M.changeEntry(j,i,data);
               this.A[index].moveNext();
    		}
    	}
        return M;
    }

    //mult()
    //Returns a new Matrix that is the product with this matrix and M
    //Pre: getSize() == M.getSize()
    Matrix mult(Matrix M)
    {
        if(this.getSize() != M.getSize())
            throw new RuntimeException("Matrix Error: sub() called on incompatible matrices");

        double dotProduct;
        Matrix N = new Matrix(this.size);
        Matrix transpose = M.transpose();
        for(int i=0; i<this.size; i++)
        {
            if(this.A[i].length() > 0)
            {
                for(int j=0; j<this.size; j++)
                {
                    if(transpose.A[j].length() > 0)
                    {
                        dotProduct = dot(this.A[i], transpose.A[j]);
                        N.changeEntry(i+1,j+1,dotProduct);
                    } 
                }
            }   
        }
        return N;
    }

    //other methods -----------------------------------------------------------
    //toString()
    //overrides Object's toString() method
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<size;i++)
        {
            if(A[i].length() > 0)
                sb.append(i+1 + ": ");

            sb.append(A[i].toString());

            if(A[i].length() > 0)
                sb.append('\n');
        }
        return new String(sb);
    }
}



