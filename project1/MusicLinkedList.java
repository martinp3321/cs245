import java.util.Iterator;


//Martin Murphy
//2/22/2016
//CS245
//Project 1
//https://www.cs.usfca.edu/svn/mpmurphy3/cs245/project1/





public class MusicLinkedList implements MusicList {
	
	float sampleRate;
	int numChannels;
	Link head;
	Link tail;
	int size;
	//numSamples
	//duration;
	
	
	public MusicLinkedList(float sampleRate, int numChannels){
		
		
		//size=0;
		this.sampleRate = sampleRate;
		this.numChannels = numChannels;
	}
	
	

	public int getNumChannels() {
		
	  return numChannels;
	}

	
	public float getSampleRate() {
		
		return sampleRate;
	}

	
	public int getNumSamples() {
		
		return size;
	}

	
	public float getDuration() {
		
		return sampleRate/size;
	}

	@Override
	public void addEcho(float delay, float percent) {
		
		
	}

	@Override
	public void reverse() {
		
		Link current = head;
		Link anchor = head;
		
		Link prev = null;
        
        Link next = null;
        Boolean first=true;
        
      		
		while (anchor!= null) {
			current = anchor;
			prev = null;
			next = null;

			while (current != null) {
	            next = current.next;
	            current.next = prev;
	            prev = current;
	            current = next;
	        }
			if(first){
				tail = head;
				head = prev;
	        	first = false;
			}
	        anchor = anchor.up;   
        }	
	}

	
	@Override
	public void changeSpeed(float percentChange) {
		
		
	}

	@Override
	public void changeSampleRate(float newRate) {
		
		
	}

	
	public void addSample(float sample) {
		
		
		
		if ( numChannels != 1 ) {
	        throw new IllegalArgumentException("Greater than one channel");
	    }
		else{
			if (head == null){
				Link bottom = new Link();
				bottom.setValue(sample);
				head = bottom;
				tail = bottom;
					
				
			}
			else{
				Link bottom = new Link();
				bottom.setValue(sample);
				tail.setNextSample(bottom);
				tail=bottom;
				
			}
			
			size++;
			
		}		
	}



		public void addSample(float[] sample)  {
			
			if ( numChannels != sample.length ) {
		        throw new IllegalArgumentException("The index [" + numChannels + "] is greater than the current size [" + size + "].");
		    }

			if (head==null){
				Link bottom = new Link();
				Link newNode, curr;
				curr=bottom;
				for(int i =0; i < numChannels; i++){
//				//for (float num : sample){
					newNode = new Link();
					curr.setNextChan(newNode);
					curr = newNode;
					curr.setValue(sample[i]);
				}
				tail = head = bottom.up;
				size++;
				//System.out.println(tail.sampleValue);
				//System.out.println(head.sampleValue);
			}		
			else{			
				Link bottom = new Link();
				Link curr,newNode;
				curr = bottom;
				Link preTmp = tail;
				
				for(int i = 0; i < numChannels; i++){
				//for (float num : sample){					
					//connect the column
					newNode= new Link();
					curr.setNextChan(newNode);
					curr = newNode;
					curr.setValue(sample[i]);
					//connect the row
					preTmp.setNextSample(curr);
					preTmp = preTmp.up;
				}
				tail = bottom.up;
				
				size++;


			}
			}


	public void clip(float startTime, float duration) {
		
		
		Link current = head;
		Link anchor = head;
		
		float start = startTime*sampleRate;
		float end= sampleRate + duration * sampleRate;
		
		//start		
		//startTime*sampleRate
				
		//end
		//sampleRate + duration * sampleRate
		

		while(startTime*sampleRate==current.sampleValue){
			
			while(sampleRate + duration * sampleRate==current.sampleValue){				
			}			
		}		
	}

	
	public void spliceIn(float startSpliceTime, MusicList clipToSplice) {
		
		
		Link current = head;
		Link anchor = head;
		MusicList list = clipToSplice;
		
		float start = startSpliceTime*sampleRate;
		
		for(int i = 0; i <= start; i++){
			Iterator<float[]> channel = clipToSplice.iterator();
			current = current.next;
		
		}
		
		current=head;
		
		//clipToSplice.addSample(list);
		
		
		
		
	}

	
	public void makeMono(boolean allowClipping) {
		Link current = head;
		Link anchor = head;
		float max = 0;
		float total = 0;

		while (anchor != null){
			current=anchor;
			
			while(current.up != null){
				current = current.up;
				total= current.sampleValue + anchor.sampleValue;
				
				anchor.setValue(total);
				
				if(anchor.sampleValue>max){
					max=anchor.sampleValue;
					//System.out.println("max " +max);
					}

			}
			
			if(allowClipping){
				if(total >= 1){
					
					anchor.setValue(1);
					
					
					
				} else if(total < 1 && total>-1 ){
					
					//anchor.setValue();			
				} else{
					
					anchor.setValue(-1);					
				}
			}
			
	
			anchor.up = null;
			anchor = anchor.next;

		}
		
		if(allowClipping != true){
			current = head;
			float clip = 0;
			
			while (current != null) {
				clip = current.sampleValue/max; 
				current.setValue(clip);
				current = current.next;
			}
		}
	}
				
//				anchor = anchor.next;
//				current = anchor;

	

	@Override
	public void combine(MusicList clipToCombine, boolean allowClipping) {
		
		Link current = head;
		Link anchor = head;
		float max=0;
//		System.out.println(current.sampleValue);
		
		for(int i = 0; i < numChannels; i ++){
			Iterator<Float> channel = clipToCombine.iterator(i);
			float value= 0, total = 0;
			//float max=0;
			while(channel.hasNext()){
				value= channel.next();
				total = value + current.sampleValue; 
				current.setValue(total);
				
				if(total>max){
				max=total;
				//System.out.println("max " +max);
				}
				
				
				
				
				if(allowClipping){
					if(total>1){
						
						current.setValue(1) ;
										
					} else if(total<-1)
					{
						current.setValue(-1);						
				} 					
				}

				//current.setValue(value + current.sampleValue) ;	
				current=current.next;
				
			}
			anchor = anchor.up;
			current=anchor;
	
		}
		
		if(allowClipping != true){
			
			current = head;
			anchor = head;
			float clip = 0;
			
			
			
			while(anchor!=null){
				
			
				while(current!=null){
					
					clip = current.sampleValue/max; 
					current.setValue(clip);
					
					current = current.up;
					
					
				}
				
				anchor = anchor.next;
				current = anchor;		
			}	
		}
	}

	@Override
	public MusicList clone() {
		
		MusicLinkedList  newList = new MusicLinkedList(sampleRate, numChannels);
		Iterator<float[]> martin = this.iterator();
		float[] samples = new float[numChannels];
				
		while(martin.hasNext()){				
					samples = martin.next();
					newList.addSample(samples);
		}

		return newList;
}

	@Override
	public Iterator<float[]> iterator() {
		// TODO Auto-generated method stub
		return new SampleIterator();
	}

	@Override
	public Iterator<Float> iterator(int channel) {
		// TODO Auto-generated method stub
		return new ChannelIterator(channel);
	}
	
	
	
	
	public void printList() {
        Link next = head;
        Link up;
        while (next != null) {
            //System.out.print("mmjh ghygi ");    
        	up=next;
            //System.out.print("|||");
            while (up != null) {         	
            	System.out.print(up.sampleValue + "->");
            	up=up.up;     
            }
            System.out.print("\n");
            next=next.next;
        }
 }

	
	private class ChannelIterator implements Iterator<Float>{
		
	//	int i ;
	//	int channel;
		private Link current;
		private boolean first = true;
		//private Link previous;
		
		
		public ChannelIterator(int channel){
			
			current=head;
			
			
			for (int i = 0; i < numChannels; i++)
							
			{
				if(i!=channel){					
					current=current.up;	
			
				}	
				else{
					break;
				}
			}	
		}

		
		public boolean hasNext() {
			
			if(current==null){
				return false;
				
			}
			else{
				if(current.next==null){
					return false;
				}
				else{
					return true;
				}
				
			}
			//return current != null && current.next == null;
		}

		
		public  Float next() {
			
			//previous = current;
			if (first){
				first = false;
				return current.sampleValue;
				
			}
			
			current = current.next;
			return current.sampleValue;
		}
		
	}
	
	
	private class SampleIterator implements Iterator<float[]>{
		
		private int i;		
		private float[] list = new float[numChannels];
		private Link current = head;
		private Link bottom = head;
		//Link next = head;
		
		SampleIterator(){
					
		}

		@Override
		public boolean hasNext() {
			if(current==null){
				return false;
				
			}
			else{
				if(current.next == null){
					return false;
					
				}
				
				else{
					return true;
					
				}
					
					
				
			}
		}

		@Override
		public float[] next() {
			
			
			
			int i = 0;
			
			while(current!=null){
				list[i]=current.sampleValue;
				current = current.up;
				i ++; 
				
			}
			bottom=bottom.next;
			current = bottom;
			
			return list;

			
			
		}	
	}
	
	
	
	public class Link{
		
		
		float sampleValue;
		public Link next; //sample
		public Link up; //up
		
//		Link nextChan;
//		Link nextSamp;
		
		
		
		Link(){
			
			//next=null;
			//up=null;
		
		}
		
		public Link sampleValue() {
			// TODO Auto-generated method stub
			return null;
		}

		public float samp() {
	    	return sampleValue;
	    }
	    

	    public void setValue(float samp) {
	    	sampleValue = samp;
	    }
		
		public void setNextChan(Link newNode) {
			
			up=newNode;		
		}
		

		public void setNextSample(Link newNode) {
	
			next=newNode;
	
		}
	
	}
}

	






