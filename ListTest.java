//Austin Kellar
//akellar
//CMPS 101
//2/4/17
//Test file for List.java
public class ListTest
{
	public static void main(String[] args)
	{
		List L = new List();
		System.out.println("Length: " + L.length());
		System.out.println("index: " + L.index());

		L.append(1);
		System.out.println("appending 1...");
		L.moveFront();
		System.out.println("Length: " + L.length());
        System.out.println("index: " + L.index());
        L.clear();
        for(int i=1;i<=10;i++)
        {
        	L.append(i);
        }
        System.out.println("\n" + "----------------------");
        System.out.println("appending...");
        System.out.println("List: " + L);
        System.out.println("List using get method: ");
        L.moveFront();
        for(int i=1;i<=10;i++)
        {
            System.out.println("number " + L.get() + " index: " + L.index());
            L.moveNext();
        }
        System.out.println("End of list. index is now: " + L.index());
        System.out.println("front number: " + L.front());
        System.out.println("back number: " + L.back());

        System.out.println("\nNow testing prepend() and movePrev()--------------");
        L.clear();
        for(int i=10;i>=1;i--)
        {
        	L.prepend(i);
        }
        L.moveBack();
        for(int i=1;i<=10;i++)
        {
        	System.out.println("number: " + L.get() + " index: " + L.index());
        	L.movePrev();
        }
        System.out.println("End of list. index is now: " + L.index());
        System.out.println("front number: " + L.front());
        System.out.println("back number: " + L.back());

        System.out.println("\nTesting delete methods: ------------------------");
        L.deleteFront();
        L.deleteBack();
        System.out.println("List after deleteFront() and deleteBack(): " + L);

        L.moveBack();
        System.out.println("Back number: " + L.back());

        L.moveFront();
        for(int i=0;i<3;i++)
        	L.moveNext();
        L.delete();
        System.out.println("List after deleting 5: " + L);
        System.out.println("index: " + L.index());
        L.moveFront();
        L.delete();
        L.moveBack();
        L.delete();
        System.out.println("List after deleting front and back manually: " + L);

        System.out.println("\nTesting manual insert methods ----------------");
        L.moveFront();
        L.insertBefore(2);
        L.moveBack();
        L.insertAfter(9);
        System.out.println("List after manually inserting a 2 before front and a 9 after back: " + L);
        L.moveFront();
        for(int i=0;i<2;i++)
        {
        	L.moveNext();
        }
        L.insertAfter(5);
        System.out.println("List after inserting a 5 AFTER the 4: " + L);
        L.moveNext();
        L.delete();
        System.out.println("List after deleting 5: " + L);
        L.moveFront();
        for(int i=0;i<3;i++)
        {
        	L.moveNext();
        }
        L.insertBefore(5);
        System.out.println("List after inserting a 5 BEFORE the 6: " + L);
        System.out.println("Length of List: " + L.length());

     
        System.out.println("\nTesting a list of strings now-----------------------------");
        List S = new List();
        S.append("one"); S.append("two"); S.append("three"); S.append("Four");
        System.out.println(S);
        System.out.println("List of strings.equals(list of ints) ?: " + S.equals(L));
        List T = new List();
        System.out.println("\nCreating identical list---------------------------------");
        T.append("one"); T.append("two"); T.append("three"); T.append("Four");
        System.out.println("List of strings.equals(identical list) ?: " + S.equals(T));
        T.deleteFront();
        System.out.println("Deleting front from one list and testing equals: " + S.equals(T));
        System.out.println("Making a list of different types of objects------------------");
        L.clear();
        L.append(1); L.append("two"); L.append(3.0);
        System.out.println("New List:\n" + L);
        

	}
}