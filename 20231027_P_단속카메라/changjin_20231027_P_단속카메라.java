import java.util.ArrayList;
import java.util.Collections;
class Solution {
    static class Car implements Comparable<Car> {
		int st, ed;

		public Car(int st, int ed) {
			super();
			this.st = st;
			this.ed = ed;
		}

		@Override
		public int compareTo(Car o) {
			// TODO Auto-generated method stub
			return this.st - o.st;
		}

	}
    public int solution(int[][] routes) {
        ArrayList<Car> list = new ArrayList<>();

		for (int i = 0; i < routes.length; i++) {
			list.add(new Car(routes[i][0], routes[i][1]));
		}

		Collections.sort(list);
		int answer = 1;
		int temp = list.get(0).ed;
		for (int i = 1; i < routes.length; i++) {
			if (temp < list.get(i).st) {
				answer++;
				temp = list.get(i).ed;
			} else {
				if (temp > list.get(i).ed)
					temp = list.get(i).ed;
			}
		}
		return answer;

    }
}