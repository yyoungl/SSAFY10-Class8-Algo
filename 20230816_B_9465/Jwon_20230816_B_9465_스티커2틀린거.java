package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Jwon_20230816_B_9465_스티커2틀린거 {
	static int[][] arr;
	static boolean[][] chk;
	static int num;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int testCase = 0 ; testCase < test ; testCase++) {
			num = Integer.parseInt(br.readLine());
			// 값들이 담길 배열
			arr = new int[2][num+2];

			for(int i = 0 ; i < 2 ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 1 ; j < num + 1 ; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			
			// 결과값들이 담길 배열
			int[][] result = new int[2][num+2];
			// 스티커 사용 유무 확인 배열
			chk = new boolean[2][num+2];
			
			// 첫번째부터 차례로 확인해보자.
			result[0][1] = arr[1][1];
			result[1][1] = arr[1][1];
			chk[1][1] = true;
			chk[1][2] = true;
			find(2,result);

			for(int[] tempArr : result) {
				System.out.println();
				for(int temp : tempArr) {
					System.out.print(temp + " ");
				}
			}
			System.out.println("\n"+result[0][num]);
			int max = result[0][num];
			
			// 두번째 확인.
			chk = new boolean[2][num+2];
			result = new int[2][num+2];
			result[0][1] = arr[0][1];
			result[1][1] = arr[0][1];
			chk[0][1] = true;
			chk[0][2] = true;
			find(2,result);

			for(int[] tempArr : result) {
				System.out.println();
				for(int temp : tempArr) {
					System.out.print(temp + " ");
				}
			}
			System.out.println("\n"+result[0][num]);
			
			max = Math.max(max, result[0][num]);
			System.out.println(max);
		}
	}
	
	private static void find(int n, int[][] result) {
		if(n == num+1) {
			return ;
		}
		for(int i = 0 ; i < 2 ; i++) {
			int anotherIdx = Math.abs(i-1);
			// 만약 현재 스티커를 사용할 수 없어도,
			if(chk[i][n]) {
				// 현재 스티커를 사용했을 때 더 큰 값이 나오는 지 확인한다.
				if(arr[i][n] - arr[i][n-1] > arr[anotherIdx][n]) {
					// 이용불가한 스티커를 사용했을 때 더 큰 값이 나온다면 이전에 사용했던 스티커를 제거한 후 
					// 현재 스티커를 사용할 수 있게 한다.
					result[i][n] = result[i][n-1] - arr[i][n-1] + arr[i][n];
					result[anotherIdx][n] = result[i][n];
					chk[i][n+1] = true;
					find(n+1,result);
				}
			// 현재 스티커를 사용할 수 있다면,
			}else {
				// 같은 열에 다른 스티커를 이용했을 때를 확인하여 스티커의 사용 유무를 결정한다.
				if(arr[anotherIdx][n] - arr[anotherIdx][n-1] <= arr[i][n]) {
					result[i][n] = result[anotherIdx][n-1] + arr[i][n];
					result[anotherIdx][n] = result[i][n];
					chk[i][n+1] = true;
					find(n+1,result);
				}
			}
		}
		
	}
}
