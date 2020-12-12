
import java.util.HashMap;

public class MaxFibonacciHeap {

	FibonacciNode maxpointer; // max node indicator

	/**insert a new element into the fibonacci max heap, if the new inserted data is greater than the max key, then replace the original max value*/
	public void insertion(FibonacciNode node) {
		if (maxpointer != null) {
			if (node.getData() > maxpointer.getData()) {
				node.setRightSibling(maxpointer);
				maxpointer.setLeftSibling(node);
				maxpointer = node;
			} else {
				node.setLeftSibling(maxpointer);
				if (maxpointer.getRightSibling() != null) {
					node.setRightSibling(maxpointer.getRightSibling());
					maxpointer.getRightSibling().setLeftSibling(node);
				}
				maxpointer.setRightSibling(node);
			}

		}
		else {
			maxpointer = node;
		}
	}

	/**removes the max node in the fibonacci heap them according to the situation decide if there is need to  do pairwise combining*/
	public FibonacciNode removeMax() {
		FibonacciNode temp = maxpointer;
		FibonacciNode count;
		if (maxpointer.getChildpointer() != null) {
			count = maxpointer.getChildpointer();
			while (count != null) {
				FibonacciNode tempNode=count;
				count = count.getRightSibling();
				tempNode.setParentNode(null);
				tempNode.setRightSibling(null);
				tempNode.setLeftSibling(null);
				insertion(tempNode); // inserting all children in top level doubly

				
			}
		}
		if (maxpointer.getRightSibling() != null) {
			maxpointer = maxpointer.getRightSibling(); // setting up temporary max pointer
			maxpointer.setLeftSibling(null);
		} else {
			maxpointer = null;
		}

		// pairwise and recombine the heap to rearragne the heap
		if (maxpointer != null) {
			HashMap<Integer, FibonacciNode> degreeTable = new HashMap<Integer, FibonacciNode>();
			count = maxpointer;
			while (count != null) {
				int degree = count.getDegree();
				FibonacciNode tmp = count;
				count = count.getRightSibling();
				while (degreeTable.containsKey(degree)) {
					FibonacciNode node = degreeTable.get(degree);
					tmp = compareandCombine(node, tmp);
					degreeTable.remove(degree);
					degree++;
				}
				degreeTable.put(degree, tmp);
				
			}
			// recreating heap by inserting combined nodes
			maxpointer = null;
			for (int d : degreeTable.keySet()) {
				FibonacciNode tmp=degreeTable.get(d);
				tmp.setLeftSibling(null);
				tmp.setRightSibling(null);
				insertion(degreeTable.get(d));
			}
		}
		return temp;
	}

	/**
	 * combines same degree nodes by comparing data values and returns combined**
	 * output node**
	 * param node1**
	 * param node2**
	 * return**
	 **/
	private FibonacciNode compareandCombine(FibonacciNode node1, FibonacciNode node2) {
		if (node1.getData() > node2.getData()) {
			FibonacciNode child = node1.getChildpointer();
			if (node2.getLeftSibling() != null)
				node2.getLeftSibling().setRightSibling(node2.getRightSibling());
			if (node2.getRightSibling() != null)
				node2.getRightSibling().setLeftSibling(node2.getLeftSibling());
			if (child != null) {
				child.setLeftSibling(node2);
				node2.setRightSibling(child);
				node2.setLeftSibling(null);
				node2.setParentNode(node1);
				node1.setChildpointer(node2);
			} else {
				node1.setChildpointer(node2);
				node2.setParentNode(node1);
				node2.setLeftSibling(null);
				node2.setRightSibling(null);
			}
			node1.setDegree(node1.getDegree() + 1);
			return node1;
		} else {
			FibonacciNode child = node2.getChildpointer();
			if (node1.getLeftSibling() != null)
				node1.getLeftSibling().setRightSibling(node1.getRightSibling());
			if (node1.getRightSibling() != null)
				node1.getRightSibling().setLeftSibling(node1.getLeftSibling());
			if (child != null) {
				child.setLeftSibling(node1);
				node1.setRightSibling(child);
				node1.setLeftSibling(null);
				node2.setChildpointer(node1);
				node1.setParentNode(node2);
				// count
			} else {
				node2.setChildpointer(node1);
				node1.setParentNode(node2);
				node1.setLeftSibling(null);
				node1.setRightSibling(null);
			}
			node2.setDegree(node2.getDegree() + 1);
			return node2;
		}

	}

	/**Increase the node data value **/
	public void increaseKey(FibonacciNode node, int d) {
		node.setData(node.getData() + d);
		if (node.getParentNode() != null && node.getParentNode().getData() < node.getData()) {
			cascadingCut(node);
		}

		else {
			if(maxpointer.getData()<node.getData()){
				if (node.getLeftSibling() != null)
					node.getLeftSibling().setRightSibling(node.getRightSibling());
				if (node.getRightSibling() != null)
					node.getRightSibling().setLeftSibling(node.getLeftSibling());
				maxpointer.setLeftSibling(node);
				node.setRightSibling(maxpointer);
				node.setLeftSibling(null);
				maxpointer =node;
			}
		}

	}

	/** inserts the node into toplevel doubly linked list if parent's chidCut**
	 * flag is false sets it to true else performs cascading cut on parent**/
	public void cascadingCut(FibonacciNode node) {

		FibonacciNode parent = node.getParentNode();

		if (node.getLeftSibling() != null)
			node.getLeftSibling().setRightSibling(node.getRightSibling());
		if (node.getRightSibling() != null)
			node.getRightSibling().setLeftSibling(node.getLeftSibling());

		if (parent != null && parent.getChildpointer() == node) {
			parent.setChildpointer(node.getRightSibling());
			
		}
		node.setParentNode(null);
		node.setLeftSibling(null);
		node.setRightSibling(null);
		insertion(node);
		if (parent != null) {
			parent.setDegree(parent.getDegree()-1);
			boolean childCut = parent.getChildCut();
			if (!childCut)
				parent.setChildCut(true);
			else
				cascadingCut(parent);
		}
	}


}
