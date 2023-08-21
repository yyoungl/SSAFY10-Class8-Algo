package Algorithm.algorithm.baekjoon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Jwon_20230823_B_1931_회의실배정 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		// 시작과 끝 시간을 가지고 있는 Meeting을 제네릭으로 하는 리스트를 만들어준다.
		ArrayList<Meeting> list = new ArrayList<>();
		for(int i = 0 ; i < n ; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			list.add(new Meeting(start, end));
		}
		
		Collections.sort(list, new Comparator<Meeting>() {

			@Override
			public int compare(Meeting o1, Meeting o2) {
				// TODO Auto-generated method stub
				// 끝 시간이 같다면 시작 시간을 기준으로 오름차순 해준다.
				if(o1.end == o2.end) {
					return o1.start - o2.start;
				}
				// 끝 시간을 기준으로 오름차순 해준다.
				return o1.end - o2.end;
			}
		});
		
		// 마지막 회의의 끝난 시간.
		int lastEnd = 0;
		// 가능한 회의의 갯수.
		int count = 0;
		for(int i = 0 ; i < n ; i++) {
			Meeting temp = list.get(i);
			// 새로운 강의의 시작 시간이 마지막 회의의 끝난 시간보다 작으면 회의를 할 수 없으니
			// 같거나 크다면 회의를 한다.
			if(temp.start >= lastEnd) {
				count++;
				lastEnd = temp.end;
			}
		}
		System.out.println(count);
	}
	
	static class Meeting{
		int start;
		int end;
		public Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
}
