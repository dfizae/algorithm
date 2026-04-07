# 💻 Algorithm & Data Structure Study

> 알고리즘 및 자료구조 학습과 코딩 테스트 대비를 위한 저장소입니다. 
> 꾸준한 문제 풀이를 통해 컴퓨팅 사고력을 기르고, 더 나은 코드를 작성하기 위해 고민합니다.

## 🧑‍💻 Author
* **김재영**
* 알고리즘 학습 및 개인 코딩 테스트 대비 코드 기록

## 🛠️ Tech Stack
<p>
  <img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=Java&logoColor=white"/>
  <img src="https://img.shields.io/badge/Eclipse%20IDE-2C2255?style=for-the-badge&logo=Eclipse&logoColor=white"/>
  <img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=Git&logoColor=white"/>
</p>

## 📁 Repository Structure
알고리즘 개념 및 플랫폼별로 패키지를 분리하여 코드를 관리합니다.

```text
src/
├── baekjoon/          # 백준 온라인 저지 문제
├── bfs/               # 너비 우선 탐색
├── dfs/               # 깊이 우선 탐색
├── divide_conquer/    # 분할 정복
├── dp/                # 동적 계획법
├── greedy/            # 탐욕법
└── etc/               # 기타, 각종 정렬 등등
```

## 📌 Commit Message Convention
깃허브 히스토리를 직관적으로 파악하기 위해 아래의 커밋 규칙을 준수합니다.

* **[플랫폼/주제] 문제이름 / 소요시간**
* 예시: `feat: [BOJ] 1260 DFS와 BFS / 40분`

| 태그 | 설명 |
| --- | --- |
| `feat` | 새로운 문제 풀이 및 코드 추가 |
| `refactor` | 기존 코드 수정 및 최적화 (시간복잡도 개선 등) |
| `docs` | README 등 문서 수정 |

## 📝다른 컴퓨터에 pull 하는 방법

**1단계: 워크스페이스와 겹치지 않는 안전 지대 만들기**
* 바탕화면이나 `C드라이브` 최상단에 **`GitProjects`** 라는 새 폴더를 하나 만들어 줍니다. (추천 경로: `C:\GitProjects`)
* 그 폴더 안으로 들어가서 빈 공간에 마우스 우클릭 -> **Open Git Bash here**를 클릭합니다.

**2단계: 깃허브에서 내 코드 통째로 복사해 오기 (Clone)**
열린 Git Bash 창에 아래 명령어를 치고 엔터를 누릅니다.
```bash
git clone https://github.com/dfizae/algorithm.git
```

**3단계: 🚨 잔디 보호용 이메일 세팅 (필수!)**
새로운 PC에 앉았으니, 내 잔디가 날아가지 않도록 컴퓨터에게 내 정체를 다시 알려줘야 합니다.
```bash
git config --global user.email "gaza1268@naver.com"
git config --global user.name "dfizae"
```

**4단계: 이클립스(STS)에 깔끔하게 연결하기**
1. 이클립스를 켤 때 나오는 워크스페이스 경로는 기본값(`C:\Users\SSAFY\eclipse-workspace`)으로 두고 실행합니다.
2. 상단 메뉴에서 **File -> New -> Java Project**를 클릭합니다.
3. Project name에 `algorithm` 이라고 적습니다.
4. **`[ ] Use default location` 체크박스를 해제**합니다.
5. 우측의 **Browse...** 버튼을 누릅니다.
6. 아까 2단계에서 다운받았던 안전 지대 경로 **`C:\GitProjects\algorithm`** 폴더를 찾아서 선택하고 열기를 누릅니다.
7. 아래쪽의 **Finish**를 누릅니다 (module-info 창이 뜨면 Don't Create 클릭)

**한국어가 안나오는 경우**
🛠️ 이클립스 UTF-8 인코딩 설정법
1. 워크스페이스 전체 설정 변경
이클립스 맨 위쪽 메뉴에서 Window -> Preferences를 클릭합니다.
왼쪽 메뉴 트리에서 General -> Workspace를 클릭합니다.
화면 왼쪽 아래에 Text file encoding 이라는 구역이 있습니다.
아마 Default (MS949)로 되어 있을 텐데, 그 아래 Other 라디오 버튼을 클릭합니다.
드롭다운 목록에서 **UTF-8**을 선택합니다.
맨 아래 Apply and Close 버튼을 누릅니다.

2. 프로젝트 개별 설정 확인 (혹시 모르니 확실하게!)
이클립스 왼쪽 패널(Project Explorer)에서 algorithm 프로젝트 폴더를 마우스 우클릭합니다.
맨 아래에 있는 Properties를 누릅니다.
왼쪽 메뉴에서 Resource를 누릅니다.
여기서도 Text file encoding이 **Other: UTF-8**로 잘 맞춰져 있는지 확인하고, 아니라면 바꿔준 뒤 Apply and Close를 누릅니다.
---

## 🔗 Profiles
* [Baekjoon Profile](https://solved.ac/profile/gaza1268@naver.com)
