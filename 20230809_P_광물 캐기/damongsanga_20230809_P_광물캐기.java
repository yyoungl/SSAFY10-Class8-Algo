import java.util.Arrays;
import java.util.Comparator;

class damongsanga_20230809_P_광물캐기  {
    public static int solution(int[] picks, String[] minerals) {
        int[][] arr;
        // 곡괭이 갯수
        int p_sum = 0;
        for (int p : picks) {
            p_sum += p;
        }

        /*
        곡괭이는 최대한 높은 광물에 높은 곡괭이를 소모하는 것이 좋다.
        때문에 5개 단위로 구간을 나누어 광물이 얼마나 많은 피로도를 소모하는지 구하고
        가장 높은 피로도를 소모하게 하는 구간을 좋은 곡괭이로 광물을 캐야 한다.

        구현 idea.
        1. 광물을 5개 단위로 구간을 나누어 피로도가 높은 순서대로 내림차순 정렬한다

        *** 함정 케이스 (stone 5개면 iron 곡괭이로 1이 나오면 안됨) ***
        2. 여기서 돌 곡괭이 기준으로 필요한 피로도는 같으나 광물의 갯수가 다른 경우가 있다.
        => [돌, 돌, 돌, 돌, 돌] 과 [철], [철, 철, 철, 철, 철] 과 [다이아몬드]는
        돌 곡괭이 기준으로 각각 5, 25의 피로도를 소모하나 철 곡괭이로 기준으로는 1, 5, 다이아몬드 곡괭이 기준으로는 1, 1의 피로도를 소모한다
        따라서 같은 피로도라면 광물이 적을수록 좋은 곡괭이를 사용해야 함으로 이를 반영하여 정렬한다.

        3. 다이아몬드 곡괭이 -> 철 곡괭이 -> 돌 곡괭이 순서로 소모하여 피로도를 구한다.

        1.
        광물을 모두 캐는데 필요한 곡괭이 갯수만큼 배열을 만들고 곡괭이의 피로도 & 그 곡괭이로 실제로 캔 광물의 갯수를 저장한다.
        arr[i][0] : i-1 번째 곡괭이의 피로도
        arr[i][1] : i-1 번째 곡괭이가 실제로 캔 광물의 갯수
        우선 모두 돌 곡괭이로 캔다고 가정하고 피로도를 저장.
        예를들어 광물이 13개가 있다면 곡괭이 3개가 필요하며, 첫 번째, 두 번째 곡괭이는 5개씩 캤을 것이며 마지막 곡괭이는 3개 캤을 것이다.

         */
        arr = new int[p_sum][2];
        // 광물을 5개 단위로 끊어서 검사한다.
        for (int i = 0; i < p_sum * 5; i++) { // 0 ~ 곡괭이가 캘 수 있는 만큼
            if (i > minerals.length - 1) continue; // 곡괭이가 캘 수 있는 양이 주어진 광물보다 많으면 스킵한다.
            if (minerals[i].equals("diamond")) arr[i / 5][0] += 25; // 돌 곡괭이 기준임으로 다이아몬드면 25의 피로도를 저장한다
            else if (minerals[i].equals("iron")) arr[i / 5][0] += 5; // 마찬가지로 쇠는 5
            else arr[i / 5][0] += 1; // 돌은 1이다
            arr[i / 5][1] += 1; // 실제로 캔 광물의 갯수이다.
        }

        // 2.
        // 피로도가 많이 소모되는 순서로 내림차순 정렬한다.
        // 피로도가 같이 소모된다면 실제로 캔 광물이 적을 수록 앞으로 정렬한다
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) return o1[1] - o2[1];
                else return o2[0] - o1[0];
            }
        });

        // 3.
        // 곡괭이 배열 count 변수
        int pick_count = 0;
        // arr count 변수
        int arr_count = 0;
        // 최소 피로도 변수
        int answer = 0;

        // 곡괭이
        // 우선 다이아몬드부터 곡괭이가 남아있는지 확인하고 있으면 그 곡괭이로 필요한 피로도를 계산하여 답에 더한다.
        // 다이아 > 철 > 돌 곡괭이 순서로 진행하고 광물을 모두 캤거나 곡괭이가 남아있지 않으면 종료한다.
        while (pick_count <= 2 && arr_count < arr.length) {
            switch (pick_count) {
                case (0):
                    if (picks[pick_count] > 0) {
                        answer += arr[arr_count++][1];
                        picks[pick_count]--;
                    }
                    break;
                case (1):
                    if (picks[pick_count] > 0) {
                        answer += Math.max(arr[arr_count][0] % 5 + arr[arr_count][0] / 5, arr[arr_count][1]); // rock + (iron + diamond)
                        arr_count++;
                        picks[pick_count]--;
                    }
                    break;
                case (2):
                    if (picks[pick_count] > 0) {
                        answer += arr[arr_count++][0];
                        picks[pick_count]--;
                    }
                    break;
            }
            if (picks[pick_count] == 0) pick_count++;
        }
        return answer;
    }


    public static void main(String[] args) {
        System.out.println(solution(new int[]{0, 1, 0}, new String[]{"stone", "stone", "stone", "stone", "stone"}));
    }
}