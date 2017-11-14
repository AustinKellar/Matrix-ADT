//Austin Kellar
//akellar
//CMPS 101
//1/15/17
//Doubly linked list implementation of List ADT with cursor
public class List
{
	private class Node
	{
		//fields
		private Object value;
		private Node previous;
		private Node next;

		//constructor
		Node(Object x)
		{
			value = x;
			previous = null;
			next = null;
		}

		//toString()
		public String toString()
		{
			return String.valueOf(value);
		}

		//equals()
		public boolean equals(Object x)
		{
			boolean eq =  false;
			Node that;
			if(x instanceof Node)
			{
				that = (Node)x;
				eq = (this.value.equals(that.value));
			}
			return eq;
		}
	}

	//fields
	private Node front;
	private Node back;
	private Node cursor;
	private int numItems;
	private int index;

	//constructor
	List()
	{
		front = null;
		back = null;
		cursor = null;
		numItems = 0;
		index = -1;
	}

	//Access Functions ---------------------------------------

	//length()
	//Returns the number of elements in this list.
	int length()
	{
		return numItems;
	}

	//int index()
	//If cursor is defined, returns the index of the cursor element, otherwise returns -1
	int index()
	{
		return index;
	}

	//int front()
	//Returns front element. Pre: length()>0
	Object front()
	{
		if(numItems <= 0)
		{
			throw new RuntimeException("List Error: front() called on empty List");
		} 
		return front.value;
	}

	//int back()
	//Returns back element. Pre: length()>0
	Object back()
	{
		if(numItems <= 0)
		{
			throw new RuntimeException("List Error: back() called on empty List");
		} 

		return back.value;
	}

	//int get()
	//Returns cursor element. Pre: length()>0, index()>=0
	Object get()
	{
		if(numItems <= 0)
		{
			throw new RuntimeException("List Error: get() called on empty List");
		} 
		if(cursor == null)
		{
			throw new RuntimeException("List Error: get() called on invalid cursor index");
		}

		return cursor.value;
	}

	//boolean equals(List L)
	//Returns true if this List and L are the same integer sequence. The cursor is ignored in both lists
	boolean equals(List L)
	{
		boolean eq = false;
		Node N = null;
		Node M = null;

		M = L.front;
		N = this.front;
		eq = (this.numItems == L.numItems);

		while(eq && N!= null)
		{
			eq = (N.value.equals(M.value));
			N = N.next;
			M = M.next;
		}
	    return eq;
	}

	//Manipulation procedures ----------------------------------------

	//void clear()
	//Resets this List to its original empty state.
	void clear()
	{
		numItems = 0;
		index = -1;
		cursor = null;
		front = null;
		back = null;
	}

	//void moveFront()
	//If List is non-empty, places the cursor under the front element, otherwise does nothing.
	void moveFront()
	{
		if(numItems > 0)
		{
            cursor = front;
            index = 0;
		}
	}

	//void moveBack()
	//If List is non-empty, places the cursor under the back element, otherwise does nothing.
	void moveBack()
	{
		if(numItems >0)
		{
			cursor = back;
            index = numItems-1;
		}
	}

	//void movePrev()
	//If cursor is defined and not at the front, moves cursor one step toward the front of this list,
	//if cursor is defined and at the front, cursor becomes undefined, if cursor is undefined, does nothing
	void movePrev()
	{
		if(cursor == null)
		{
			cursor = null;
			index = -1;
		}
		else if(cursor == front)
		{
			cursor = null;
			index = -1;
		}
		else 
		{
			cursor = cursor.previous;
			index--;
		}
    }


	//void moveNext()
	//If cursor is defined and not at the back, moves cursor one step toward the back of this list,
	//if cursor is defined and at the back, cursor becomes undefined, if cursor is undefined, does nothing
	void moveNext()
	{
		if(cursor == null)
		{
			cursor = null;
			index = -1;
		}
		else if(cursor == back)
		{
			cursor = null;
			index = -1;
		}
		else
	    {
	    	cursor = cursor.next;
	    	index++;
	    }
	}

	//void prepend(int data)
	//Insert new element into this list. If the list is non-empty, insertion takes place before front element
	void prepend(Object data)
	{
		if(numItems==0)
		{
			Node N = new Node(data);
			front = N;
			back = N;
		    numItems++;
		}
		else
		{
			Node N = new Node(data);
			front.previous = N;
			N.next = front;
			front = N;
			if(index != -1)
			{
				index++;
			}
			numItems++;
		}
	}

    //void append(int data)
    //Insert new element into this List. If List is non-empty, insertion takes place after the back element.
    void append(Object data)
    {
    	if(numItems == 0)
    	{
    		Node N = new Node(data);
    		front = N;
    		back = N;
    		numItems++;
    	}
    	else
    	{
    		Node N = new Node(data);
    		back.next = N;
    		N.previous = back;
    		back = N;
    		numItems++;
    	}
    }

    //void insertBefore(int data)
    //Insert new element before cursor
    //Pre: length()>0 index()>=0
    void insertBefore(Object data)
    {
    	if(numItems <= 0)
		{
			throw new RuntimeException("List Error: insertBefore() called on empty List");
		} 
		if(cursor == null)
		{
			throw new RuntimeException("List Error: insertBefore() called on invalid cursor index");
		}

		if(cursor == front)
		{
			Node N = new Node(data);
			N.next = front;
			front.previous = N;
			front = N;
			numItems++;
			index++;
		}
		else
		{
			Node N = new Node(data);
			N.previous = cursor.previous;
			N.next = cursor;
			cursor.previous.next = N;
			cursor.previous = N;
			numItems++;
			index++;
		}
    }

    //void insertAfter(int data)
    //Insert new element after cursor
    //Pre: length()>0, index()>=0
    void insertAfter(Object data)
    {
    	if(numItems<=0)
    	{
			throw new RuntimeException("List Error: insertAfter() called on empty List");
		} 
		if(cursor == null)
		{
			throw new RuntimeException("List Error: insertAfter() called on invalid cursor index");
		}
        if(cursor == back)
        {
        	Node N = new Node(data);
        	N.previous = back;
        	back.next = N;
        	back = N;
        	numItems++;
        }
		else
		{
			Node N = new Node(data);
			N.previous = cursor;
			N.next = cursor.next;
			cursor.next.previous = N;
			cursor.next = N;
			numItems++;
		}
    }

    //void deleteFront()
    //Deletes front element. Pre: Length()>0
    void deleteFront()
    {
    	if(numItems<=0)
    	{
			throw new RuntimeException("List Error: deleteFront() called on empty List");
		} 

        if(numItems == 1)
        {
        	front = null;
        	back = null;
        	cursor = null;
        	numItems=0;
        	index = -1;
        }
		else
		{
			if(cursor == front)
			{
				cursor = null;
				index = 0;
			}
				
			front = front.next;
			front.previous.next = null;
			front.previous = null;
			index--;
			numItems--;
		}

    }

    //void deleteBack()
    //Deletes back element. Pre: length()>0
    void deleteBack()
    {
        if(numItems<=0)
    	{
			throw new RuntimeException("List Error: deleteFront() called on empty List");
		} 

        if(numItems == 1)
        {
        	front = null;
        	back = null;
        	cursor = null;
        	numItems=0;
        }
		else
		{
			if(cursor == back)
			{
				cursor = null;
				index = -1;
			}
				
			back = back.previous;
			back.next.previous = null;
			back.next = null;
			numItems--;
		}
    }

    //void delete()
    //Deletes cursor element, making cursor undefined. Pre: length()>0, index()>=0
    void delete()
    {
        if(numItems<=0)
    	{
			throw new RuntimeException("List Error: insertAfter() called on empty List");
		} 
		if(cursor == null)
		{
			throw new RuntimeException("List Error: insertAfter() called on invalid cursor index");
		}

		if(cursor == front && numItems == 1)
		{
			front = null;
			back = null;
			cursor = null;
			index = -1;
			numItems = 0;
		}
		else if(numItems > 1 && cursor == front)
		{
			front = front.next;
			front.previous.next = null;
			front.previous = null;
			cursor = null;
			index = -1;
			numItems--;
		}
		else if(numItems > 1 && cursor == back)
		{
			back = back.previous;
			back.next.previous = null;
			back.next = null;
			cursor = null;
			index = -1;
			numItems--;
		}
		else 
		{
			cursor.previous.next = cursor.next;
			cursor.next.previous = cursor.previous;
			cursor.previous = null;
			cursor.next = null;
			cursor = null;
			index = -1;
			numItems--;
		}
    }

    //Other methods ---------------------------------

    //String toString() 
    //Overrides Object's toString method. Returns a String represenation of this List 
    //consisting of a space separated sequence of integers, with front on the left
    public String toString()
    {
    	StringBuffer sb = new StringBuffer();
        Node N = front;
        if(numItems > 0)
        {
        	while( N!=null )
            {
                sb.append(N.toString());
                if(N.next != null)
            	sb.append(" ");
                N = N.next;
            }
        }
         return new String(sb);
    }
}