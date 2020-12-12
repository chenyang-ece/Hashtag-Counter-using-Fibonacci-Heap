
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;

public class hashtagcounter {

	public static void main(String[] args) throws IOException {
		// hashtable which contains hashtag and node pointer
		Hashtable<String, FibonacciNode> hashtags = new Hashtable<String, FibonacciNode>();
		MaxFibonacciHeap heap = new MaxFibonacciHeap(); // set up a dynamic table to store removed array
		ArrayList<FibonacciNode> removedNodes = new ArrayList<FibonacciNode>(); //update the ArrayList
		String fileName = args[0]; // read the input file, accept the input file by input the file's name here using boleann
		FileReader fileReader = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		// accept and read the input from the input file.
		String input = bufferedReader.readLine();
		PrintWriter writer = new PrintWriter("output_file.txt", "UTF-8");

		while (!input.equalsIgnoreCase("stop")) {
			String[] tempArr = input.split(" ");
			if (input.startsWith("#")) {
				if (hashtags.containsKey(tempArr[0])) {
					//if hashtag key is already exist then increase the value
					heap.increaseKey(hashtags.get(tempArr[0]), Integer.parseInt(tempArr[1]));
				} else {
					//if hashtag key is not present then get the node from hashtable and increase
					FibonacciNode node = new FibonacciNode(Integer.parseInt(tempArr[1]));
					node.setHashtag(tempArr[0]);
					hashtags.put(tempArr[0], node);
					heap.insertion(node);
				}
			} else {
				int n = Integer.parseInt(input);
				//initialize the string s as an empty string
				String s = "";
				while (n > 0) {
					//remove the max node from heap
					FibonacciNode node = heap.removeMax();
					if (n == 1) {
						//get the hashtag and append to s
						s = s.concat(node.getHashtag().substring(1));
					} else {
						//get the hashtag and append to s
						s = s.concat(node.getHashtag().substring(1) + ",");
					}
					//add removed max node to the dynamic array
					removedNodes.add(node);
					hashtags.put(node.getHashtag(), node);
					//repeat the loop for n queries
					n--;
				}
				//log the string s to output file
				writer.println(s);
				//reinsert the removed nodes to the fibonacci max heap
				for (FibonacciNode f : removedNodes) {
					f.setChildCut(false);
					f.setChildpointer(null);
					f.setDegree(0);
					f.setLeftSibling(null);
					f.setParentNode(null);
					f.setRightSibling(null);
					heap.insertion(f);
				}
				removedNodes.clear();

			}
			input = bufferedReader.readLine();
		}
		// close all the reader and logger
		writer.close();
		bufferedReader.close();
		fileReader.close();
	}

}
