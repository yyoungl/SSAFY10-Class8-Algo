package Programmers;

public class ���̽�ƽ {
	static String name="JAN";
	public static void main(String[] args) {
		// name�� ���� �� A�� �ƴ� ������ ������ ������ cnt���� ����. name�� 0�� �ε����� A�� �µ� �ƴϵ� ���������̹Ƿ� cnt=1�� �ʱ�ȭ
		int cnt=1;
		int answer2=0;
		// name�� 1�� �ε������� ������ �� ���Ĺ��� ����� ���� ���̽�ƽ�� �������� �ϴ� Ƚ�� ���
		for(int i=1;i<name.length();i++) {
			if(name.charAt(i)!='A') {
				// A���� ������ ���������� �����̴� ���� ���������� �����̴� ��츦 ����� �������� �����ش�.
				answer2+=Math.min(name.charAt(i)-'A', 'Z'+1-name.charAt(i));
				cnt++;
			}
		}
		// name�� 0�� �ε����� ��쵵 �����ش�. 
		answer2+=Math.min(name.charAt(0)-'A', 'Z'+1-name.charAt(0));
		// 0�� �ε������� ���������� �� �� �� A�� �ƴ� ���ڱ��� ���µ� �ɸ��� �Ÿ��� ������ arr�迭�� ���������� ���� �� A�� �ƴ� ���ڱ��� ���µ� �ɸ��� �Ÿ��� ������ rarr�迭 ����.
		int[] arr=new int[cnt];
		int[] rarr=new int[cnt];
		// �̹� 0������ �����ϹǷ� idx�� 1���� ����.
		int idx=1;
		// for ���� ���� arr�迭���� A�� �ƴ� ���ڰ� ���ö����� ���������� �ε����� �����ϰ�
		// rarr���� A�� �ƴ� ���ڰ� ���� �� ���������� �ε����� �����Ѵ�. �̶� ���������� ������ ���� -�� �ٿ� �����Ѵ�.
		for(int i=1;i<name.length();i++) {
			if(name.charAt(i)!='A') {
				arr[idx]=i;
				rarr[arr.length-idx]=-name.length()+i;
				idx++;
			}
		}
		// A���ƴ� �� ���ڸ� �ִ� ��Ʈ�� �湮�� �� ���̽�ƽ�� �������� �ϴ� Ƚ���� ������ answer�迭 ����. 
		int answer=Integer.MAX_VALUE;
		// ���� ���������� ��� �Ҷ� �ּҰ� Ž��
		for(int i=0;i<arr.length;i++) {
			if(i==arr.length-1) answer=Math.min(answer, arr[i]);
			else answer=Math.min(answer,arr[i]*2-rarr[arr.length-1-i]);
		}
		// ���������� ����Ҷ� �ּҰ� Ž��.
		for(int i=0;i<arr.length;i++) {
			if(i==arr.length-1) answer=Math.min(answer, -rarr[i]);
			else answer=Math.min(answer,-(rarr[i]*2-arr[arr.length-1-i]));
		}
		// �ռ� ���� �� A���� �����ڷ� ���� ���� �̵��ϴ� ���� �ּ�Ž�� �Ÿ��� �����ش�.
		System.out.println(answer+answer2);
		
	}
}
