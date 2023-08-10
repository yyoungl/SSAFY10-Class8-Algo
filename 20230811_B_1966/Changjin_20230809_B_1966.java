package _Baekjune;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 문서의 순서와 중요도를 저장하기 위한 클래스
class A{
	int num;
	int value;
	public A(int num,int value) {
		this.num=num;
		this.value=value;
	}
}

public class _1966 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int T=sc.nextInt(); //테스트케이스 수
		int[] answer=new int[T]; // 정답 저장
		for(int i=0;i<T;i++) {
			int n=sc.nextInt();
			int m=sc.nextInt();
			ArrayList<Integer> list=new ArrayList<>(); // 중요도를 저장할 List
			Queue<A> Q=new LinkedList<A>();  
			
			for(int j=0;j<n;j++) {
				int value=sc.nextInt();
				Q.add(new A(j,value)); //순서와 중요도를 클래스에 담아 큐에 저장
				list.add(value); // 중요도만 리스트에 저장
			}
			Collections.sort(list,Collections.reverseOrder()); //중요도가 높은순서로 정렬
			int idx=0; //인쇄 횟수를 저장할 변수
			while(!Q.isEmpty()) {
				if(Q.peek().value==list.get(idx)) { //현재 나올 Q의 문서가 남은 문서중 가장 중요도가 높을때 
					idx++;  // 인쇄 횟수 증가 및 리스트에서 다음으로 중요도가 높은 값을 나타냄
					if(Q.poll().num==m) {  // 인쇄를 위해 Q에서  poll 해주고 poll한 클래스 A의 num이 처음 입력받은 m과 같으면 브레이크
						break;
					}
				}else Q.offer(Q.poll()); // 현재 poll될 문서의 중요도가 남은 것중 가장 크지 않으면 큐의 가장 후미로 보냄
			}
			answer[i]=idx;
		}

		for(int x:answer) {
			System.out.println(x);
		}
	}

}
