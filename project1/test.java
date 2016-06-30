
public class test {
	public static void main(String[] args) {
		float[] nums = {1,2,3};
		float[] nums2 = {4,0.005f,6};
		float[] nums3 = {7,8,9};
		MusicLinkedList list = new MusicLinkedList(3, 3);
		
		list.addSample(nums);
		list.addSample(nums2);
		list.addSample(nums3);
		list.printList();
		
		float[] nums4 = {10,12,43};
		float[] nums5 = {54,0.05f,16};
		float[] nums6 = {17,58,89};
		MusicLinkedList list2 = new MusicLinkedList(3, 3);
		
		list2.addSample(nums4);
		list2.addSample(nums5);
		list2.addSample(nums6);
		System.out.println();
		list2.printList();

		//list.combine(list2, false);
		//list.makeMono(false);
		list.reverse();
		list.reverse();
		list.reverse();
		System.out.println(list.getNumSamples());
		list.printList();
	}
}
