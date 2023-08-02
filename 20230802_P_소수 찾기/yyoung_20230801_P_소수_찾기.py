from itertools import permutations


def solution(numbers):
    check = [False] * 7
    numbers = list(numbers)
    answer = []
    arr = []

    def dfs(string, temp, m):
        if len(temp) == m:
            num = int(temp)
            if num not in arr:
                arr.append(num)
        for i in range(len(string)):
            if not check[i]:
                check[i] = True
                temp += string[i]
                dfs(string, temp, m)
                check[i] = False
                temp = temp[0:len(temp)-1]

    for i in range(len(numbers)):
        dfs(numbers, "", i + 1)


    # permu 에 담긴 숫자들이 소수인지 판별하는 반복문
    for perm in arr:
        x = int(perm)
        if x < 2:
            continue
        is_prime = True
        # 이렇게 계산하는 게 국룰이라 들었는데... 왜인진 몰름......
        for i in range(2, int(x ** 0.5) + 1):
            if x % i == 0:
                is_prime = False
        if is_prime:
            answer.append(x)

    return len(set(answer))
    # 여기 set으로 중복을 없애기 과정을 첫 번째 반복문 끝나고 해도 될 것 같다.
    # 중복되는 게 많으면 시간적으로 이득일 듯함!!
