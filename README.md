## 다함께 자격증 공부하자!
# 다자공

# 아맞다 Git 설치!!!!! 인터넷에 git 검색해서 가장 최신 버전을 받으세요


**PAT (Personal Access Token) 발급**:
   - GitHub 프로필 → `Settings` → `Developer settings` → `Personal access tokens` → `Tokens (classic)`
   - `Generate new token (classic)` → Note에 이름, Expiration 90일, Scopes에서 **`repo`** 체크
   - `Generate token` → **토큰 복사해서 메모장 저장** ⚠️ 한 번 닫으면 다시 못 봄 


### STS 설정 (최초 1회)

`Window → Preferences → Team → Git → Configuration → User Settings`에서 `Add Entry`:

| Key | Value |
|---|---|
| `user.name` | 본인 GitHub 사용자명 |
| `user.email` | 본인 GitHub 이메일 |
| `init.defaultBranch` | `main` |

### 프로젝트 Clone

1. `Window → Show View → Other → Git → Git Repositories`
2. Git Repositories 뷰에서 **Clone a Git repository** 클릭
3. URI: `https://github.com/acorn1066/dajagong.git`
4. User: 본인 GitHub 사용자명
5. Password: **PAT** (비밀번호 아님)
6. `Store in Secure Store` 체크
7. Next → Branch 기본값 → Next → Local destination 확인 → Finish

### STS에 프로젝트 Import

Clone만으로는 STS가 프로젝트로 인식 못 함. 별도 import 필요:

1. `File → Import...`
2. `Gradle → Existing Gradle Project` 선택 → Next
3. Project root directory: Clone한 폴더 선택 (예: `C:\Users\사용자명\git\dajagong`)
4. Next → Finish

Package Explorer에 `dajagong` 프로젝트가 보이면 성공.

> ⚠️ 처음 import 후 Gradle이 라이브러리 다운로드하느라 몇 분 걸립니다. 기다리세요.
> ⚠️ 빨간 X 뜨면 프로젝트 우클릭 → `Gradle → Refresh Gradle Project`
