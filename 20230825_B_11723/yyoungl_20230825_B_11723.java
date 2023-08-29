import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class yyoungl_20230825_B_11723 {

	public static void main(String[] args) throws IOException {
		// 인풋이 1~20이라고 했으므로 21칸짜리 boolean 배열로 값이 있는지 없는지 확인한다
		boolean[] check = new boolean[21];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.valueOf(br.readLine());
		for (int i=0; i<N; i++) {
			String s = br.readLine();
			String[] str = s.split(" ");
			String com = str[0];
			// 값이 없는 command도 있어서... 그런데 처음부터 여기서 조건문으로 num을 할당하면 뭔가 잘못됨 !! ㅠㅠ 이유 아시는 분
			if (com.equals("add")) {
				int num = Integer.valueOf(str[1]);
				if (check[num]) continue;
				else check[num] = true;
			} else if (com.equals("remove")) {
				int num = Integer.valueOf(str[1]);
				check[num] = false;
			}
			else if (com.equals("toggle")) {
				int num = Integer.valueOf(str[1]);
				check[num] = !check[num];
			}
			else if (com.equals("all")) {
				for (int idx=1; idx<=20; idx++) check[idx] = true;
			} else if (com.equals("check")) {
				int num = Integer.valueOf(str[1]);
				if (check[num]) bw.write("1\n");
				else bw.write("0\n");
			} else if (com.equals("empty")) {
				for (int idx=1; idx<=20; idx++) check[idx] = false;
			}
		}
		bw.flush();
		
	}

}
