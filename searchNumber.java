import java.io.EOFException;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

// Inputstreams are for reading from file, Outputstreams are for writing to file


public class searchNumber {
	public static void main(String[] args) throws Exception {
		File testFile = new File("lab08_input.dat");
		int largest;
		int smallest;
		if (!testFile.exists()) { // If file doesn't exist, then don't read into file.
			System.out.printf("File does not exist.");
		} else {
			int count = 0;
			// Declare readers for the input file
			FileInputStream fileInput = new FileInputStream("lab08_input.dat");
			ObjectInputStream inStream = new ObjectInputStream(fileInput);
			// Declare writers to write the input values into the output file
			FileOutputStream fileOutput = new FileOutputStream("lab08_output.bin");
			ObjectOutputStream outStream = new ObjectOutputStream(fileOutput);
			
			try {
				System.out.printf("Reading file...\n");
				for ( ;; ) {
					int number = inStream.readInt();
					count++;
					outStream.writeInt(number);
				}
			} catch (EOFException e) {
				System.out.printf("End of file!\n");
				inStream.close();
				outStream.close();
			}
			// Declare a new input stream (READ VALUES/DATA FROM THE FILE)
			FileInputStream readFileOutput = new FileInputStream("lab08_output.bin");
			ObjectInputStream readStream = new ObjectInputStream(readFileOutput);
			int[] values = new int[count];
			
			for (int x = 0; x < values.length; x++) {
				int number = readStream.readInt();
				values[x] = number;
			}
			
			largest = values[0];
			smallest = values[0];
			
			for (int x = 0; x < values.length; x++) {
				if (values[x] > largest) {
					largest = values[x];
				}
				if (values[x] < smallest) { 
					smallest = values[x];
				}
			}
			readStream.close();
			System.out.printf("\nLargest: %s\nSmallest: %s", largest, smallest);
		}
	}
}
