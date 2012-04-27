package com.jwetherell.algorithms.sorts;


/**
 * Heapsort is a comparison-based sorting algorithm to create a sorted array (or list), and is part of the 
 * selection sort family. Although somewhat slower in practice on most machines than a well-implemented 
 * quicksort, it has the advantage of a more favorable worst-case O(n log n) runtime.
 * Family: Selection.
 * Space: In-place.
 * Stable: False.
 * 
 * Average case = O(n*log n)
 * Worst case = O(n*log n)
 * Best case = O(n*log n)
 * 
 * http://en.wikipedia.org/wiki/Heap_sort
 * 
 * @author Justin Wetherell <phishman3579@gmail.com>
 */
public class HeapSort<T extends Comparable<T>> {

    private HeapSort() { }

    public static <T extends Comparable<T>> T[] sort(T[] unsorted) {

        createHeap(unsorted);
        sortHeap(unsorted);
        
        return unsorted;
    }

    private static <T extends Comparable<T>> void sortHeap(T[] unsorted) {
        for (int index=(unsorted.length-1); index>0; index--) {
            swap(0, index, unsorted); // swap root with the last heap element
            int i = 0; // index of the element being moved down the tree
            while (true) {
                int left = (i*2)+1;
                if (left >= index) // node has no left child
                    break;
                int right = left+1;
                if (right >= index) { // node has a left child, but no right child
                    if (unsorted[left].compareTo(unsorted[i])==1) swap(left, i, unsorted);  // if left child is greater than node
                    break;
                }
                T ithElement = unsorted[i];
                T leftElement = unsorted[left];
                T rightElement = unsorted[right];
                if (ithElement.compareTo(leftElement)==-1) { // (left > i)
                    if (unsorted[left].compareTo(rightElement)==1) { // (left > right)
                        swap(left, i, unsorted);
                        i = left; 
                        continue;
                    } else { // (left > i)
                        swap(right, i, unsorted);
                        i = right; 
                        continue;
                    }
                } else { // (i > left)
                    if (rightElement.compareTo(ithElement)==1) {
                        swap(right, i, unsorted);
                        i = right; 
                        continue;
                    } else { // (n > left) & (n > right)
                        break;
                    }
                }
            }
        }
    }
    
    private static <T extends Comparable<T>> void createHeap(T[] unsorted) {
        //Creates a max heap
        int size = 0;
        for (int i=0; i<unsorted.length; i++) {
            T e = unsorted[i];
            size = add(size, e, unsorted);
        }
    }
    
    private static <T extends Comparable<T>> int add(int length, T element, T[] unsorted) {
        int i = length;
        unsorted[length++] = element;

        T e = unsorted[i];
        int parentIndex = ((i-1)/2);
        T parent = unsorted[parentIndex];
        while (e.compareTo(parent)==1) {
            swap(parentIndex, i, unsorted);
            i = parentIndex;
            e = unsorted[i];
            parentIndex = ((i-1)/2);
            parent = unsorted[parentIndex];
        }
        return length;
    }
    
    private static <T extends Comparable<T>> void swap(int parentIndex, int childIndex, T[] unsorted) {
        T parent = unsorted[parentIndex];
        unsorted[parentIndex] = unsorted[childIndex];
        unsorted[childIndex] = parent;
    }
}
