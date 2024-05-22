package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos:
 * - Alocar o tamanho minimo possivel para o array de contadores (C)
 * - Ser capaz de ordenar arrays contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array != null && array.length > 0 && leftIndex >= 0 && leftIndex < rightIndex && rightIndex< array.length) {
			
			int maior = maiorElemento(array, leftIndex, rightIndex);
			int menor = menorElemento(array, leftIndex, rightIndex);
			
			int[] C = new int[maior - menor + 1];
			
			for (int i = leftIndex; i <= rightIndex; i++) {
				C[array[i] - menor] += 1;
			}
			
			for (int i = leftIndex + 1; i < C.length; i++) {
				C[i] += C[i-1];
			}
			
			int[] B = new int[array.length];
			
			for (int i = rightIndex; i >= leftIndex; i--) {
				B[C[array[i] - menor] - 1] = array[i];
				C[array[i] - menor] -= 1;
			}
			
			for (int i = leftIndex; i <= rightIndex; i++) {
				array[i] = B[i];
			}
		}
	}
	
	private int maiorElemento(Integer[] array, int leftIndex, int rightIndex) {
		int maior = array[leftIndex];
		
		for (int i = leftIndex + 1; i <= rightIndex; i++) {
			if (array[i] > maior) {
				maior = array[i];
			}
		}
		return maior;
	}
	
	private int menorElemento(Integer[] array, int leftIndex, int rightIndex) {
		int menor = array[leftIndex];
		
		for (int i = leftIndex + 1; i <= rightIndex; i++) {
			if (array[i] < menor) {
				menor = array[i];
			}
		}
		return menor;
	}
}
