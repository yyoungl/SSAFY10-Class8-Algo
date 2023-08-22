import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Gubeomlee_20230825_B_11723 {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int len = Integer.parseInt(br.readLine());
		boolean[] arr = new boolean[21];

		for (int i = 0; i < len; i++) {
			String input = br.readLine();
			String[] tokens = input.split(" ");
			String str = tokens[0];
			int idx = 0;

			if (tokens.length > 1) {
				idx = Integer.parseInt(tokens[1]);
			}

			if (str.equals("add")) {
				arr[idx] = true;
			} else if (str.equals("remove")) {
				arr[idx] = false;
			} else if (str.equals("check")) {
				if (arr[idx]) {
					bw.write("1\n");
				} else {
					bw.write("0\n");
				}
			} else if (str.equals("toggle")) {
				arr[idx] = !arr[idx];
			} else if (str.equals("all")) {
				for (int j = 1; j < 21; j++) {
					arr[j] = true;
				}
			} else if (str.equals("empty")) {
				for (int j = 1; j < 21; j++) {
					arr[j] = false;
				}
			}
		}
		br.close();
		bw.flush();
		bw.close();
	}
}