package programmers;

import java.util.Collections;
import java.util.PriorityQueue;

public class Jwon_20230807_p_minerals {
	public static int solution(int[] picks, String[] materials) {
		int answer = 0;
		
		// 총 곡괭이의 갯수 나중에 어떤 돌맹이까지 캘 수 있는 지 확인하기 위함으로 들고 왔다.
		int pickCount = 0;
		
		for(int i = 0 ; i < 3 ; i++) {
			pickCount += picks[i];
		}
		
		
		// 돌을 5개로 묶었을 때 5개의 묶음들의 가치를 중심으로 생각하기로 했다.
		// 5개씩 묶은 돌 중 가장 큰 가치를 가지는 돌들의 묶음을 가장 비싼 곡괭이로 캐면 된다는 생각을 했다.
		// 따라서 각 돌의 가치를 넣어준다.
		// 애매하게 다이아 3 철 2 돌 1로 하면 가치가 낮은 돌의 갯수가 많아지면 가치가 큰 돌의 가치를 침범하기 때문에 각각 100 10 1로 지정해준다.
		// 이해를 쉽게 하기 위한 예시) 철 5개면 10이고 3으로 나눠져서 다이아로 생각할 수 있다. 그러나 100 10 1으로 하면 절대 침범할 수 없으므로 100 10 1로 생각하자.
		// 5개 돌 묶음의 가치를 가장 큰 것부터 뽑기위한 우선순위 큐, 제일 비싼 곡괭이부터 낮은 곡괭이까지 이용하자.
		PriorityQueue<Integer> pQue = new PriorityQueue<>(Collections.reverseOrder());
		
		// 5개씩 끊기 위해 i(시작점)을 5개씩 더해준다.
		for(int i = 0 ; i < materials.length; i += 5) {
			// 만약 더 이상 돌맹이들을 담아도 사용할 수 있는 곡괭이의 갯수가 없다면 더 이상 돌맹이를 담을 필요가 없으므로 break
			if(pickCount == 0) {
				break;
			}
			
			// 돌맹이 묶음의 가치.
			int value = 0;
			// 다섯개씩 그리고 돌맹이의 갯수를 넘기지 않을 때까지 가치를 더해준다.
			for(int j = i ; j < i + 5 && j < materials.length; j++) {
				if(materials[j].equals("diamond")) {
					value += 100;
				}else if(materials[j].equals("iron")) {
					value += 10;
				}else if(materials[j].equals("stone")) {
					value += 1;
				}
			}
			
			// 돌맹이의 묶음의 가치를 넣어준다.
			pQue.offer(value);
			// 이 돌맹이의 묶음을 캘 어떠한 곡괭이 갯수를 하나 없앤다.
			pickCount--;
		}
		
		// 만약 곡괭이가 돌맹이 묶음보다 많아서 남을 때를 대비하여 가치가 큰 돌맹이의 묶음을 제일 비싼 곡괭이부터 사용하여 캐자!
		for(int i = 0 ; i < 3 ; i++) {
			// 현재 곡괭이의 갯수가 없으면 캐면 안되기에 picks[i] > 0
			// 곡괭이의 갯수가 남아 있어도 더 이상 캘 돌맹이의 묶음이 없다면 피로도가 올라가면 안되니까 !pQue.isEmpty()
			while(picks[i] > 0 && !pQue.isEmpty()) {
				// 현재 돌맹이 묶음 중 가장 큰 가치의 묶음
				int value = pQue.poll();
				// 가장 비싼 곡괭이 하나 쓰기에 갯수 마이너스
				picks[i]--;
				
				if(i == 0) {
					// 만약 다이아 곡괭이라면
					answer += value / 100;
					value %= 100;
					
					answer += value / 10;
					value %= 10;
					
					answer += value ;
				}else if(i == 1) {
					// 만약 철 곡괭이라면
					answer += (value / 100) * 5;
					value %= 100;
					
					answer += value / 10;
					value %= 10;
					
					answer += value ;
					
				}else if(i == 2) {
					// 만약 돌맹이 곡괭이라면
					answer += (value / 100) * 25;
					value %= 100;
					
					answer += (value / 10) * 5;
					value %= 10;
					
					answer += value ;
				}
			}
		}
		
		// 끝!
		return answer;
	}
	
	
	
	
	
	public static void main(String[] args) {
		// 나를 괴롭히는 프로그래머스 문제 출제 방법...
		String[] minerals = {"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"};
		int[] picks ={1,3,2}; 
		System.out.println(solution(picks,minerals));

		String[] minerals2 = {"diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond"};
		int[] picks2 ={0,1,1}; 
		System.out.println(solution(picks2,minerals2));
		System.out.println(solution(new int[3], new String[3]));
		String[] minerals3 = {"diamond", "diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "diamond"};
		int[] picks3 ={1,1,1}; 
		System.out.println(solution(picks3,minerals3));
		String[] minerals4 = {"diamond", "diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "diamond"};
		int[] picks4 ={3,0,0}; 
		System.out.println(solution(picks4,minerals4));

	}
}
