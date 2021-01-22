package myExperimentsWithJavaPackage;

// after MFA

public class BinarySearchTreePractice {

	//OPTIMIZE HERE as this in GIT
	//node class for BST for integers
	private class NodeBST {
		int value;
		NodeBST left;
		NodeBST right;
		
		//constructor to build the node
		NodeBST(int data) {
			this.value = data;
			this.left = null;
			this.right = null;
		}
	}
	
	NodeBST root;
	
	BinarySearchTreePractice(){
		this.root = null;
	}
	
	// insert method
	void insert(BinarySearchTreePractice list, int data) {
		NodeBST newNode = new NodeBST(data);
		
		// if tree is empty, make this node as root node
		if(this.root == null)
		{
			this.root = newNode;
			return;
		}
		
		NodeBST lastNode = this.root;
		boolean traversalComplete = false;
		
		do
		{
			if(data < lastNode.value)
			{
				if(lastNode.left == null)
				{
					lastNode.left = newNode;
					//lastNode = newNode;
					traversalComplete = true;
				}
				else
				{
					lastNode = lastNode.left;
				}
			}
			else
			{
				if(lastNode.right == null)
				{
					lastNode.right = newNode;
					//lastNode = newNode;
					traversalComplete = true;
				}
				else
				{
					lastNode = lastNode.right;
				}
			}
			
		} while(traversalComplete == false);
		
	}
	
	boolean lookup(BinarySearchTreePractice list, int data) {
		boolean found = false;
		boolean traversalComplete = false;
		
		if(list.root == null)
			return found;
		
		NodeBST nextNode = list.root;
		do
		{
			if(data == nextNode.value)
			{
				found = true;
				traversalComplete = true;
			}
			else if(data < nextNode.value)
			{
				if(nextNode.left == null)
				{
					traversalComplete = true;
				}
				else
				{
					nextNode = nextNode.left;
				}
			}
			else
			{
				if(nextNode.right == null)
				{
					traversalComplete = true;
				}
				else
				{
					nextNode = nextNode.right;
				}
			}
			
		}while(traversalComplete == false);
		
		return found;
	}
	
	boolean remove(BinarySearchTreePractice list, int value) {
		boolean matchFound = false;
		// find node -> go right once -> go left till no more left -> take that as successor -> 
		// any right assign to parent of this successor on left
		if(list.root == null)
			return matchFound;
		
		// verify if the node to be removed is the root node
		boolean isThisRootNode = false;
		if(value == root.value)
			isThisRootNode = true;
		
		if(root.left == null && root.right == null)
		{
			//we have only root node
			if(isThisRootNode)
			{
				this.root = null;
				matchFound = true;
			}
			return matchFound;
		}
		
		NodeBST currentNode = this.root;
		NodeBST matchedNode = null;
		NodeBST leaderNode = null;
		NodeBST rightNodeOfToBeRemoved = null;
		NodeBST leftNodeOfToBeRemoved = null;
		NodeBST successorNode = null;
		boolean searchComplete = false;
		// begin traversing
		leaderNode = currentNode;
		successorNode = currentNode;
		rightNodeOfToBeRemoved = currentNode.right;
		leftNodeOfToBeRemoved = currentNode.left;
		String childNodeDirection = "tbd";
		
		do
		{
			if(value == currentNode.value)
			{
				//we found the node to be removed
				matchedNode = currentNode;
				rightNodeOfToBeRemoved = currentNode.right;
				leftNodeOfToBeRemoved = currentNode.left;
				matchFound = true;
				searchComplete = true;
			
			}
			else if(value < currentNode.value)
			{
				// check if this is leaf
				if(currentNode.left == null)
					searchComplete = true;
				
				// go left
				//update leaderNode
				leaderNode = currentNode;
				currentNode = currentNode.left;
				childNodeDirection = "left";
			}
			else
			{
				// check if this is leaf
				if(currentNode.right == null)
					searchComplete = true;

				// go right
				//update leaderNode
				leaderNode = currentNode;
				currentNode = currentNode.right;
				childNodeDirection = "right";
			}
		}while(searchComplete == false);
		
		if(matchFound == true)
		{
			// now we need to find the successor
			// go right once if right present
			if(currentNode.right == null)
			{
				// as no right, take first left as successor if it is present
				if(currentNode.left == null)
				{
					// no left also so current/match node is the leaf, so just remove it
					if(childNodeDirection == "left")
						leaderNode.left = null;
					else
						leaderNode.right = null;
				}
				else
				{
					// need to check if the node to be removed is the root node
					if(isThisRootNode == true)
					{
						this.root = currentNode.left;
					}
					else
					{
						//take first left as successor
						if(childNodeDirection == "left")
						{
							leaderNode.left = currentNode.left;
						}
						else
						{
							leaderNode.right = currentNode.left;
						}
					}
				}
			}
			else
			{
				// go right once
				currentNode = currentNode.right;
				
				// check left is present
				if(currentNode.left == null)
				{
					//need to check if the node to be removed is the root node
					if(isThisRootNode == true)
					{
						this.root = currentNode.right;
					}
					else
					{
						// make this node as successor
						currentNode.left = leftNodeOfToBeRemoved; // new left
						if(childNodeDirection == "left")
						{
							leaderNode.left = currentNode;
						}
						else
						{
							leaderNode.right = currentNode;
						}
					}
				}
				else
				{
					NodeBST rightWingLeader = currentNode;
					// find the left most node
					do
					{
						rightWingLeader = currentNode;
						currentNode = currentNode.left;
						
					}while(currentNode.left != null);

					rightWingLeader.left = null; // as this left is becoming the successor now
					currentNode.right = rightNodeOfToBeRemoved; // new right for the successor
					currentNode.left = leftNodeOfToBeRemoved; // new left for the successor
					if(childNodeDirection == "left")
					{
						leaderNode.left = currentNode;						
					}
					else
					{
						leaderNode.right = currentNode; // new successor
					}
					
				}
			}
		}
		
			
		return matchFound;
		
	}
	
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinarySearchTreePractice bst = new BinarySearchTreePractice();
		
		bst.insert(bst, 9);
		bst.insert(bst, 4);
		bst.insert(bst, 6);
		bst.insert(bst, 20);
		bst.insert(bst, 170);
		bst.insert(bst, 15);
		bst.insert(bst, 1);
		
		boolean found = bst.lookup(bst, 170);
		System.out.println(found);
		found = bst.remove(bst, 20);
		System.out.println(found);

	}

}
