// ȸ�ǽ� ����

package Baekjune;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// ȸ�ǽ��� ������ Ŭ���� ����.
class room implements Comparable<room>{
	long st;
	long lt;
	public room(long st,long lt) {
		this.st=st;
		this.lt=lt;
	}
	// �����½ð��� ���� ������ ����. ���� ������ �ð��� ���ٸ� ���۽ð��� ���� ������ ����.
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
		// ����Ʈ�� ȸ�ǽ� ����.
		for(int i=0;i<T;i++) {
			long a=sc.nextInt();
			long b=sc.nextInt();
			list.add(new room(a,b));
		}
		// ������ ������ �������� ȸ�ǽ� ����.
		Collections.sort(list);
		// �������� ���õ� ȸ�ǽ��� ī��Ʈ�� �ȵǹǷ� �̸� 1�� �ʱ�ȭ
		int cnt=1;
		// ������ ȸ�ǽ� �ĺ��� ������ ���� p�� ����Ʈ�� ù���� ���� �־���´�
		room p=list.get(0);
		// ���� p�� ����Ǿ� �ִ� ȸ�ǽ��� ���۽ð��� ����Ʈ�� ���� �ε����� ����Ǿ� �ִ� 
		// ȸ�ǽ��� ������ �ð����� ���ų� ũ�� p�� ���ο� ȸ�ǽǷ� �����ϰ� cnt�� ������Ų��
		// �װ� �ƴ϶�� ���۽ð��� ���ϰ� ���۽ð��� �� ũ�� p�� �����Ѵ�.
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
