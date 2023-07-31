from itertools import permutations
# 파이썬 permutation 라이브러리 import 비겁하게 라이브러리를 사용했습니다.

def solution(numbers):
    permu = []
    numbers = list(numbers)
    answer = []
    for i in range(len(numbers)):
        perms = permutations(numbers, i+1)
        # numbers에 있는 숫자를 1개부터 len(numbers) 개까지 사용해서 만들 수 있는 순열을 반환
        # perms = [(1, 2), (2, 1), (1, 2, 3), ...] 이런 형태로 반환됨
        permu += [''.join(p) for p in perms]
        # 숫자를 조합해서 만들어야 하기 때문에 string 자료형을 유지하고, join method로 숫자의 형태로 만들어 준다.
    # print(permu)
    
    # permu 에 담긴 숫자들이 소수인지 판별하는 반복문
    for perm in permu:
        x = int(perm)
        if x < 2:
            continue
        is_prime = True
        # 이렇게 계산하는 게 국룰이라 들었는데... 왜인진 몰름......
        for i in range(2, int(x**0.5)+1):
            if x % i == 0:
                is_prime = False
        if is_prime:
            answer.append(x)
    
    return len(set(answer)) 
    # 여기 set으로 중복을 없애기 과정을 첫 번째 반복문 끝나고 해도 될 것 같다.
    # 중복되는 게 많으면 시간적으로 이득일 듯함!!