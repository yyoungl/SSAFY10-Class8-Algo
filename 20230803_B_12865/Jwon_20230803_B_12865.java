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
		
		// 물품의 갯수가 몇 개일 지 정확히 모르기 때문에 list에 넣는다.
		// 물품의 무게가 버틸 수 있는 무게보다 크다면 무시할 수 있기 때문.
		ArrayList<BackPack> list = new ArrayList<>();
		int[] maxValArr = new int[k + 1];
		for (int i = 0; i < n; i++) {
			int w = sc.nextInt();
			int v = sc.nextInt();
			// 물품의 무게가 버틸 수 있는 무게보다 크다면 무시한다.
			if (w > k || v == 0) {
				continue;
			}
			list.add(new BackPack(w, v));
		}

		// 물품들을 무게 순으로 오름차순 정렬한다.
		// 물품들을 무게 순으로 오름차순 정렬하는 이유 :
		// 무게가 무거운 물품을 넣을 경우 그 무거운 물품의 가치와 원래 들어가있는 가벼운 물품들의 합에 대한 가치를 비교해야하는데
		// 무거운 물품부터 넣는다면 나중에 가벼운 물품들을 넣을 때 더욱 계산이 복잡해지고 놓치는 부분이 있을 수 있다.
		// 따라서 우선 무거운 물품들은 무시한 후 가벼운 물품이 전부라고 계속 가정하며 진행한다.
		// 후에 무거운 물품을 넣을 때 가벼운 물품들로 채워진 가방의 최대 무게의 가치를 비교하여 더 큰 가치를 찾아낸다.
		Collections.sort(list, new Comparator<BackPack>() {

			@Override
			public int compare(BackPack o1, BackPack o2) {
				return o1.w - o2.w;
			}
		});

		// 모든 물품들을 확인한다.
		for (BackPack backPack : list) {
			int w = backPack.w;
			// 현 물품을 넣었을 때 생기는 무게에 대한 새로운 가치들을 담을 array.
			// 이렇게 하는 이유 : 가치의 값이 두 번 이상 들어갈 수 있기 때문.
			// 예를 들어 무게가 3이고 버틸 수 있는 가방의 무게가 9라면 4~9까지 3에 넣었던 가치의 값이 계속 더해지기 때문에 아예 다른 배열에 넣어준다.
			int[] tempArr = new int[k+1];
			// tempArr = maxValArr를 하지 않는 이유:
			// 주소값이 동일해지기 때문에 의미가 없어진다. 즉, maxValArr의 값이 변경되면 주소값이 같기 때문에 tempArr의 값도 변경이 된다.
			for(int i = 0 ; i < k+1 ; i++) {
				tempArr[i] = maxValArr[i];
			}
			
			for (int j = 0; w + j < k + 1; j++) {
				// 원래 들어있는 가치보다 새로운 가치가 더 크다면 변경해준다.
				if (tempArr[w + j] < backPack.v + maxValArr[j]) {
					tempArr[w + j] = backPack.v + maxValArr[j];
				} else {
					// 이건 지금 보니까 필요가 없네...
					tempArr[w + j] = maxValArr[w + j];
				}
				
			}
			
			// maxValArr을 새로 만든 배열로 변경한다.
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
