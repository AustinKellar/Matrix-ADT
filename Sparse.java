import java.io.*;
import java.util.Scanner;
public class Sparse
{
	public static void main(String[] args) throws IOException
	{
		Scanner in = null;
        PrintWriter out = null;
        int n,aNNZ,bNNZ,row,col;
        double value;
        Matrix A,B;

		if(args.length < 2)
		{
            System.err.println("Usage: Sparese infile outfile");
            System.exit(1);
        }
        
        in = new Scanner(new File(args[0]));
        out = new PrintWriter(new FileWriter(args[1]));

        //load n, aNNZ and bNNZ
        n = in.nextInt();
        aNNZ = in.nextInt();
        bNNZ = in.nextInt();

        //Create the matrices
        A = new Matrix(n);
        B = new Matrix(n);

        //move to the rest of the text
        in.nextLine();

        //load Matrix A
        for(int i=0; i<aNNZ; i++)
        {
            row = in.nextInt();
            col = in.nextInt();
            value = in.nextDouble();
            A.changeEntry(row,col,value);
        }

        //move to Matrix B test
        in.nextLine();

        //load Matrix B
        for(int i=0; i<bNNZ; i++)
        {
        	row = in.nextInt();
        	col = in.nextInt();
        	value = in.nextDouble();
        	B.changeEntry(row,col,value);
        }

        //write A NNZ and print A
        out.println("A has " + aNNZ + " non-zero entries:");
        out.println(A);

        //write B NNZ and print B
        out.println("B has " + bNNZ + " non-zero entries:");
        out.println(B);

        //write (1.5) * A
        out.println("(1.5)*A =");
        out.println(A.scalarMult(1.5));

        //write A+B
        out.println("A+B =");
        out.println(A.add(B));

        //write A+A
        out.println("A+A =");
        out.println(A.add(A));

        //write B-A
        out.println("B-A =");
        out.println(B.sub(A));

        //write A-A
        out.println("A-A =");
        out.println(A.sub(A));

        //write transpose of A
        out.println("Transpose(A) =");
        out.println(A.transpose());

        //write A*B
        out.println("A*B =");
        out.println(A.mult(B));

        //write B*B
        out.println("B*B =");
        out.println(B.mult(B));

        //close infile and outfile
        in.close();
        out.close();
	}
}