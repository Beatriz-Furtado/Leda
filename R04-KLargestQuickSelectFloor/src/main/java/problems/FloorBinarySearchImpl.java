package problems;

import util.Util;

public class FloorBinarySearchImpl implements Floor {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		if (array == null || array.length <= 0 || x == null) {
			return null;
		}
		
		quickSort(array, 0, array.length - 1);
		return floorBinarySearch(array, x, null, 0, array.length - 1);
	}
	
	private Integer floorBinarySearch(Integer[] array, Integer x, Integer floor, int leftIndex, int rightIndex) {
		if (array != null && array.length > 0 && leftIndex >= 0 && leftIndex <= rightIndex && rightIndex < array.length) {
			int middleIndex = (leftIndex + rightIndex) / 2;
			
			if (array[middleIndex].compareTo(x) == 0) {
				floor = array[middleIndex];
			}
			if (array[middleIndex].compareTo(x) > 0) {
				return floorBinarySearch(array, x, floor, leftIndex, middleIndex - 1);
			}
				
			if (array[middleIndex].compareTo(x) < 0) {
				floor = floorBinarySearch(array, x, array[middleIndex], middleIndex + 1, rightIndex);
			}
		}
		return floor;
	}
	
	private void pickPivotIndex(Integer[] array, int leftIndex, int rightIndex) {
		int middleIndex = (leftIndex + rightIndex) / 2;

		if (array[leftIndex].compareTo(array[rightIndex]) > 0) {
			Util.swap(array, rightIndex, leftIndex);
		}
		
		if (array[leftIndex].compareTo(array[middleIndex]) > 0) {
			Util.swap(array, middleIndex, leftIndex);
		}
		
		if (array[middleIndex].compareTo(array[rightIndex]) > 0) {
			Util.swap(array, rightIndex, middleIndex);
		}
		Util.swap(array, leftIndex, middleIndex);
	}
	
	private int partition(Integer[] array, int leftIndex, int rightIndex) {
		pickPivotIndex(array, leftIndex, rightIndex);
		
		int pivot = array[leftIndex];
		int index = leftIndex;
			
		for (int i = leftIndex + 1; i <= rightIndex; i++) {
			if (array[i].compareTo(pivot) <= 0) {
				index++;
				Util.swap(array, index, i);
			}
		}
		Util.swap(array, leftIndex, index);
		return index;
	}
	
	private void quickSort(Integer[] array, int leftIndex, int rightIndex) {
		if (array != null && array.length > 0 && leftIndex >= 0 && leftIndex < rightIndex && rightIndex < array.length) {
			int pivotIndex = partition(array, leftIndex, rightIndex);
			
			quickSort(array, leftIndex, pivotIndex - 1);
			quickSort(array, pivotIndex + 1, rightIndex);
		}
	}
}
