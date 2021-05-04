# Commit-mon
- 커밋을 먹고 자라는 Commit-mon 입니다.

## Commit-mon은..
- 하루하루 빠지지않고 열심히 **Commit**을 하면 좋아합니다 ❤️

## Commit-mon Score Rule
- Commit을 하게되면 **기본 1점**입니다.
- 연속으로 커밋을 할 경우 **Combo**가 발생합니다.
- **Combo Rule**은 연속 Commit을 한 날짜를 **Score**에 합산하게 됩니다.
- Level Up Score에 도달하면 Commit-mon이 진화를 하고, **Combo**가 초기화됩니다.
- 아무리 많은 Score를 한번에 획득한다해도 최대 Level Up은 1 입니다.

## Level Rule

-|Level 0|Level 1|Level 2| Level3 ...
-|-|-|-|-
Next Level|40|60|100|140

## Score 계산 예제
---
-|1일차|2일차|3일차 ...
-|-|-|-
Combo|1|2|3
Commit 유무|O|O|O ...
책정되는 Score|1|1 + 2 Combo|1 + 3 Combo ...
---
-|1일차|2일차|3일차|4일차 ...
-|-|-|-|-
Combo|1|0|1|2 ...
Commit 유무|O|X|O|O ...
책정되는 Score|1|0|1|1 + 2 Combo ...

## How To
- 준비중
