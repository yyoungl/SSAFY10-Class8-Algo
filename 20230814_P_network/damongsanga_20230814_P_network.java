import java.util.ArrayList;

public class damongsanga_20230814_P_network {

    public int solution(int n, int[][] wires) {
        int answer = 987654321;

        ArrayList<Integer>[] graph = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < wires.length; i++) {
            int v1 = wires[i][0];
            int v2 = wires[i][1];
            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        for (int i = 0; i < n-1; i++) {
            int v1 = wires[i][0];
            int v2 = wires[i][1];
            boolean[] visited = new boolean[n+1];
            graph[v1].remove(Integer.valueOf(v2));
            graph[v2].remove(Integer.valueOf(v1));

            int count = dfs(graph, visited, 1);

            answer = Math.min(answer, Math.abs(count - (n - count)));
            graph[v1].add(Integer.valueOf(v2));
            graph[v2].add(Integer.valueOf(v1));
        }
        return answer;
    }


    public static int dfs(ArrayList<Integer>[] graph, boolean[] visited, int current){
        visited[current] = true;
        int count = 1;

        for (int next: graph[current]) {
            if (!visited[next]){
                count += dfs(graph, visited, next);
            }
        }
        return count;

    }

}

