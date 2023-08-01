package algorithm.baekjoon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Jwon_20230803_B_12865 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		ArrayList<BackPack> list = new ArrayList<>();
		int[] maxValArr = new int[k + 1];
		for (int i = 0; i < n; i++) {
			int w = sc.nextInt();
			int v = sc.nextInt();
			if (w > k || v == 0) {
				continue;
			}
			list.add(new BackPack(w, v));
		}

		Collections.sort(list, new Comparator<BackPack>() {

			@Override
			public int compare(BackPack o1, BackPack o2) {
				return o1.w - o2.w;
			}
		});

		for (BackPack backPack : list) {
			int w = backPack.w;
			int[] tempArr = new int[k+1];
			for(int i = 0 ; i < k+1 ; i++) {
				tempArr[i] = maxValArr[i];
			}
			
			for (int j = 0; w + j < k + 1; j++) {
				if (tempArr[w + j] < backPack.v + maxValArr[j]) {
					tempArr[w + j] = backPack.v + maxValArr[j];
				} else {
					tempArr[w + j] = maxValArr[w + j];
				}
				
			}
			
			for(int i = 0 ; i < k+1 ; i++) {
				maxValArr[i] = tempArr[i];
			}
		}
		System.out.println(maxValArr[k]);
	}

	static class BackPack {
		int w;
		int v;

		public BackPack(int w, int v) {
			this.w = w;
			this.v = v;
		}

	}
}
