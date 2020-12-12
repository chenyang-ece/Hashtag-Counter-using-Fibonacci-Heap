
public class FibonacciNode{

	private int degree; //degree of the node
	private String hashtag; //hashtag name
    private int data; //data of node
    private FibonacciNode childpointer; //the child node pointer
    private FibonacciNode leftSibling; //left sibling pointer
    private FibonacciNode parentNode; //parentnode pointer
    private FibonacciNode rightSibling; //right sibling pointer
    private boolean childCut; //child cut indicator

// the following function is of no need to explain, just the operation like its name**//

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public void setChildpointer(FibonacciNode childpointer) {
		this.childpointer = childpointer;
	}

	public FibonacciNode getLeftSibling() {
		return leftSibling;
	}

	public void setLeftSibling(FibonacciNode leftSibling) {
		this.leftSibling = leftSibling;
	}

	public void setRightSibling(FibonacciNode rightSibling) {
		this.rightSibling = rightSibling;
	}

	public void setChildCut(boolean childCut) {
		this.childCut = childCut;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	public FibonacciNode getChildpointer() {
		return childpointer;
	}

	public FibonacciNode getParentNode() {
		return parentNode;
	}

	public boolean getChildCut() {
		return childCut;
	}

	public void setParentNode(FibonacciNode parentNode) {
		this.parentNode = parentNode;
	}

	public FibonacciNode getRightSibling() {
		return rightSibling;
	}

	public FibonacciNode(int data) {
		super();
		this.data = data;
		this.childCut=false;
		this.degree=0;
	}


    
}
