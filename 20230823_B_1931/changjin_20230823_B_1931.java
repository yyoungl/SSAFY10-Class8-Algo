// 회의실 배정

package Baekjune;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// 회의실을 저장할 클래스 생성.
class room implements Comparable<room>{
	long st;
	long lt;
	public room(long st,long lt) {
		this.st=st;
		this.lt=lt;
	}
	// 끝나는시간이 늦은 순서로 정렬. 만약 끝나는 시간이 같다면 시작시간이 늦은 순서로 정렬.
	@Override
	public int compareTo(room o) {
		// TODO Auto-generated method stub
		if(this.lt>o.lt) return -1;
		else if(this.lt==o.lt) {
			if (this.st>o.st) return -1;
			else return 1;
		}
		else return 1;
	}
}
public class _1931 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int T=sc.nextInt();
		
		ArrayList<room> list=new ArrayList<>();
		// 리스트에 회의실 저장.
		for(int i=0;i<T;i++) {
			long a=sc.nextInt();
			long b=sc.nextInt();
			list.add(new room(a,b));
		}
		// 위에서 선언한 기준으로 회의실 정렬.
		Collections.sort(list);
		// 마지막에 선택된 회의실은 카운트가 안되므로 미리 1로 초기화
		int cnt=1;
		// 선택할 회의실 후보를 저장할 변수 p에 리스트의 첫번쨰 값을 넣어놓는다
		room p=list.get(0);
		// 현재 p에 저장되어 있는 회의실의 시작시간이 리스트의 현재 인덱스에 저장되어 있는 
		// 회의실의 끝나는 시간보다 같거나 크면 p를 새로운 회의실로 갱신하고 cnt를 증가시킨다
		// 그게 아니라면 시작시간을 비교하고 시작시간이 더 크면 p를 갱신한다.
		for(int i=1;i<T;i++) {
			if(p.st>=list.get(i).lt) {
				p=list.get(i);
				cnt++;
			}else {
				if(p.st<list.get(i).st) p=list.get(i);
			}
		}
		System.out.println(cnt);
	}
}
