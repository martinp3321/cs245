//Martin Murohy
//CS245
//Project3
public class PersistentStack
{
	private NodeS[] timeArray;
	private int currentTime;
	private NodeS head;
	private int [] timeSize;
	
    
    int top;
    private int size;
    public static final int DEFAULT_INITIAL_SIZE = 100;
    
   
    public PersistentStack() 
    {
    	this(DEFAULT_INITIAL_SIZE);
    }

    public PersistentStack(int initsize) 
    {
    	top = 0;
    	size = initsize;
    	head = null;
    	timeArray = new NodeS[initsize];
    	timeSize = new int[initsize];
    	timeArray[top] = head;
    	timeSize[top] = 0;
    }
    
    public boolean empty() 
    {
    	return top == 0;
    }

    public void push(String value)  
    {
		if (top == size) {
			GrowStack();
		}
		timeArray[++top] = new NodeS(value);
		timeSize[top] = timeSize[top-1]+1;
		timeArray[top].next = head;
		head = timeArray[top];
//	data[top++] = elem;
    }

    //TODO
    public NodeS pop() 
    {
    	NodeS tmp = head;
    	head = head.next;
    	timeArray[++top] = head;
    	timeSize[top] = timeSize[top-1] - 1;
    	return tmp;
    }
    


    protected void GrowStack() 
    {
       int i;
       NodeS newdata[];
       int newTime[];

       newdata = new NodeS[size * 2];
       newTime = new int[size * 2];
       for (i=0; i<size; i++) {
    	   newdata[i] = timeArray[i];
    	   newTime[i] = timeSize[i];
       }
       		
       timeArray = newdata;
       timeSize = newTime;
       size = size * 2;
    }


	public int currentTime() {
		return top;
	}

	public String[] getAllElements(int i, boolean b) {
		String[] result = new String[timeSize[i]];
		if (i <= top) {
			int j=0;
			NodeS curr = timeArray[i];
			while (curr != null) {
				result[j++] = curr.data;
				curr = curr.next;
			}
		}
		
		return result;
	}
}