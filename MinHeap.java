import java.util.Arrays;

public class MinHeap {
	private int size = 0;
	private int capacity = 10;
	public int[] items;

	public MinHeap() {
		items = new int[capacity];
	}

	public MinHeap(int capacity) {
		items = new int[capacity];
		this.capacity = capacity;
	}

	private int getLeftChildIndex(int parentIndex) { return (2 * parentIndex) + 1; }
	private int getRightChildIndex(int parentIndex) { return (2 * parentIndex) + 2; }
	private int getParentIndex(int childIndex) { return (childIndex - 1) / 2; }
	private boolean hasLeftChild(int index) { return getLeftChildIndex(index) < size; }
	private boolean hasRightChild(int index) { return getRightChildIndex(index) < size; }
	private boolean hasParent(int index) { return getParentIndex(index) >= 0; }
	private int parent(int index) { return items[getParentIndex(index)]; }
	private int left(int index) { return items[getLeftChildIndex(index)]; }
	private int right(int index) { return items[getRightChildIndex(index)]; }

	private void swap(int indexOne, int indexTwo) {
		int temp = items[indexOne];
		items[indexOne] = items[indexTwo];
		items[indexTwo] = temp;
	}

	private void ensureExtraCapacity() {
		if(capacity == size) {
			items = Arrays.copyOf(items, capacity * 2);
			capacity *= 2;
		}
	}

	private void heapifyUp() {
		int index = size - 1;
		while(hasParent(index) &&  parent(index) > items[index]) {
			swap(getParentIndex(index), index);
			index = getParentIndex(index);
		}
	}

	private void heapifyDown() {
		int index = 0;

		while(hasLeftChild(index)) {
			int smallerIndex = getLeftChildIndex(index);
			if(hasRightChild(index) && right(index) < left(index)) {
				smallerIndex = getRightChildIndex(index);
			}

			if(items[index] < items[smallerIndex]) {
				break;
			} else {
				swap(index, smallerIndex);
			}

			index = smallerIndex;
		}
	}

	public int peek() {
		if(size == 0) throw new IllegalStateException();

		return items[0];
	}

	public int poll() {
		if(size == 0) throw new IllegalStateException();

		int item = items[0];
		items[0] = items[size - 1];
		size--;
		heapifyDown();
		return item;
	}

	public void add(int item) {
		ensureExtraCapacity();
		items[size] = item;
		size++;
		heapifyUp();
	}

	public static void main(String[] args) {
		MinHeap min = new MinHeap();
		min.add(10);
		min.add(9);
		min.add(78);
		min.add(2);
		min.add(99);
		min.add(1);
		min.add(100);

		Arrays.stream(min.items).forEach(i -> System.out.print(i + " "));
	}
}