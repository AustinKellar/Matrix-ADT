public class MatrixTest
{
	public static void main(String[] args)
	{
	    Matrix M = new Matrix(3);
	    Matrix N = new Matrix(3);

	    M.changeEntry(1,1,1); M.changeEntry(1,2,2); M.changeEntry(1,3,3);
	    M.changeEntry(2,1,4); M.changeEntry(2,2,5); M.changeEntry(2,3,6);
	    M.changeEntry(3,1,7); M.changeEntry(3,2,8); M.changeEntry(3,3,9);

        N.changeEntry(1,1,1); N.changeEntry(1,2,2); N.changeEntry(1,3,3);
	    N.changeEntry(2,1,4); N.changeEntry(2,2,5); N.changeEntry(2,3,6);
	    N.changeEntry(3,1,7); N.changeEntry(3,2,8); N.changeEntry(3,3,9);


	    System.out.println("M: \n" + M);
	    System.out.println("N: \n" + N);
	    System.out.println("M size: " + M.getSize());
	    System.out.println("N size: " + N.getSize());
	    System.out.println("M NNZ: " + M.getNNZ());
	    System.out.println("N NNZ: " + N.getNNZ());
	    System.out.println("M+N: \n" + M.add(N));
	    System.out.println("M-N: \n" + M.sub(N));
	    System.out.println("M*N: \n" + M.mult(N));
	    System.out.println("M transpose: \n" + M.transpose());
	    System.out.println("N transpose: \n" + N.transpose());
        System.out.println("-------------------------------------");

        System.out.println("Changing Entries...");
        M.changeEntry(1,2,0);
        N.changeEntry(3,1,0); N.changeEntry(3,3,0);

	    System.out.println("M: \n" + M);
	    System.out.println("N: \n" + N);
	    System.out.println("M size: " + M.getSize());
	    System.out.println("N size: " + N.getSize());
	    System.out.println("M NNZ: " + M.getNNZ());
	    System.out.println("N NNZ: " + N.getNNZ());
	    System.out.println("M+N: \n" + M.add(N));
	    System.out.println("M+M: \n" + M.add(M));
	    System.out.println("M-N: \n" + M.sub(N));
	    System.out.println("N-N: \n" + N.sub(N));
	    System.out.println("M*N: \n" + M.mult(N));
	    System.out.println("M transpose: \n" + M.transpose());
	    System.out.println("N transpose: \n" + N.transpose());
	    System.out.println("-------------------------------------");

	    System.out.println("Copying M into new Matrix L...");
	    Matrix L = M.copy();
	    System.out.println("M.equals(L)?: " + M.equals(L) + '\n');
	    System.out.println("Changing one Entry in M...");
	    M.changeEntry(2,3,1723469);
	    System.out.println("M.equals(L)?: " + M.equals(L) + '\n');
        System.out.println("-------------------------------------");

        System.out.println("Matrix M: \n" + M);
        System.out.println("M size: " + M.getSize());
	    System.out.println("M NNZ: " + M.getNNZ());
        System.out.println("Making M Zero...");
        M.makeZero();
        System.out.println("Matrix M: \n" + M);
        System.out.println("M size: " + M.getSize());
	    System.out.println("M NNZ: " + M.getNNZ());
    }
}