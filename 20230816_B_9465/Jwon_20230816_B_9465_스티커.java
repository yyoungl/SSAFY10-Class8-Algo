package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Jwon_20230816_B_9465_스티커 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		// 이 풀이 말고 스티커2에 풀었던 풀이를 봤을 때 시작점은 어쩔 수 없이 나눠야 된다는 것을 알게 되었다...
		// 나눠서 한다면 굳이 처음부터 내가 무엇을 이용했는 지에 대한 조건을 더 어렵게 짤 필요가 없었다.
		// 어떠한 스티커를 이용했다면 (이 경우가 최대를 구하는 경우인 지는 모르나 일단 그렇다고 생각하는 것이 마음 편한다.)
		// 대각선과 그 다음 대각선에 있는 수 중 더 큰 수를 더해주면 된다.
		// 만약 대각선을 선택했다면 그 다음 대각선은 사용할 수 없으므로 두 가지 경우를 생각해준다.
		// 세번째 대각선은 첫번째 대각선을 선택해도 가능한 경우이기 때문에 두번째 대각선까지만 생각을 해준다.
		for(int testCase = 0 ; testCase < test ; testCase++) {
			int num = Integer.parseInt(br.readLine());
			int[][] arr = new int[2][num+1];
			int[][] result = new int[2][num+1];

			for(int i = 0 ; i < 2 ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 1 ; j < num + 1 ; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			result[0][1] = arr[0][1];
			result[1][1] = arr[1][1];
			
			// 지금까지 더해왔던 것들을 저장해놓은 result 배열을 이용하여 대각선들을 더해준다.
			for(int i = 2 ; i < num + 1 ; i++) {
				result[0][i] = Math.max(result[1][i-1], result[1][i-2]) + arr[0][i];
				result[1][i] = Math.max(result[0][i-1], result[0][i-2]) + arr[1][i];
			}
			sb.append(Math.max(result[0][num], result[1][num])).append("\n");
		}
		System.out.println(sb.toString());
	}
}
