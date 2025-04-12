# 실시간 주식 정보 조회 웹 서비스
![urecastock-bg](https://github.com/user-attachments/assets/fab9b9b6-4673-48c2-a7d1-ae30cd621e66)

실시간 주식 정보 조회 및 입출금 기능을 개발하며 보얀 취약점을 제고하고 실시간 통신을 구현해봤습니다.

- 시연 영상: https://youtu.be/JFfyY8l5ogk?si=dhuO34P-ooW7wICu

 
## 목차

- [전체 구조](#전체-구조)
- [개발 과정](#개발-과정)
- [기술 스택](#기술-스택)
- [팀원 소개](#팀원-소개)


## 전체 구조
1. WebSocket API를 사용하여 주식 정보를 실시간으로 받아오는 [Polygon.io](https://polygon.io/) 서버와
2. 회원을 관리하고 입출금 기능을 제공하는 자체 서버로 구성되어 있습니다.
- 참고: [Frontend Github Repository](https://github.com/yyeonkim/ureca-stock-frontend)

![Image](https://github.com/user-attachments/assets/6fdb36b4-0f68-460c-aa30-c00d4187d985)


## 개발 과정

### 1️⃣ WebSocket 설정 및 실시간 주식 정보 가져오기
Polygon에 구현된 WebSocket으로 토큰을 보내 권한을 받고, 별도의 요청(Request) 없이 실시간으로 주식 데이터를 받았습니다.
- **배운 점**: 이 과정에서 WebSocket이 HTTP와 비교하여 실시간 통신에서 어떤 장점이 있는지 알게 되었습니다.
- 참고: [Frontend Github Repository](https://github.com/yyeonkim/ureca-stock-frontend)

![Image](https://github.com/user-attachments/assets/8b31fac3-1371-42b5-b213-40850bb553ac)


### 2️⃣ 회원가입/로그인 서비스
- **비밀번호 암호화**: 회원가입 시 Rainbow Table을 만들 위험이 있는 SHA 대신 **BCrypt 해싱 기술**을 사용하여 비밀번호를 암호화하였습니다.
- **토큰 발급**: 토큰은 연산이 빠르고 복호화가 불가능한 SHA256 해싱 기술을 사용했습니다.

<div align="center">
 <img width="600px" src="https://github.com/user-attachments/assets/05bfeaa9-89d2-4940-97bc-a4646471ff87" />
</div>

#### 💀 무작위 대입 공격 문제
무작위 대입 공격에 취약한 SHA의 단점을 보완하기 위해 아래와 같은 작업을 하였습니다.
1. **토큰 유효기간**을 설정하여 설정한 시간이 지나면 토큰이 자동으로 만료되도록 했습니다.
2. 로그인 할 때마다 **무작위로 salt를 설정**하여 이전 salt가 탈취되어도 공격을 방어할 수 있도록 했습니다.
3. API 호출 횟수를 제한하는 Interceptor를 설정하여 Controller로 요청이 들어올 때 자동으로 요청 횟수를 카운트하고 설정한 횟수를 초과하면 429 에러를 반환하였습니다.


### 3️⃣ 계좌 발급 및 입출금 서비스
회원가입 시 계좌를 자동으로 발급하며, **안전한 입출금**을 위해 사용자의 1)토큰으로 가져온 이메일과 2)계좌 번호로 가져온 이메일을 한 번 더 대조하여 입출금을 수행했습니다.

![image](https://github.com/user-attachments/assets/09c649d9-c979-4224-9583-74ef7803d412)


## 기술 스택

### Language
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> 

### Environment
<img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/visual studio code-2F80ED?style=for-the-badge&logo=visual-studio-code&logoColor=white">
  
### Development
<img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"> <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> 
  
### DataBase
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> 

### Communication
<img src="https://img.shields.io/badge/slack-4A154B?style=for-the-badge&logo=slack&logoColor=white"> <img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white"> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"> <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"> <img src="https://img.shields.io/badge/googlemeet-00897B?style=for-the-badge&logo=googlemeet&logoColor=white">


## 팀원 소개

<table>
  <tbody>
    <tr>
        <td align="center">
        <a href="https://github.com/yyeonkim">
            <img src="/img/coder-profile.png" width="100px;" alt=""/>
            <br />
            <sub><b>yyeonkim</b></sub>
        </a>
        <br />
        </td>
        <td align="center">
        <a href="https://github.com/Ahn-jeongeun">
            <img src="/img/profile.jpg" width="100px;" alt=""/>
            <br />
            <sub><b>Ahn-jeongeun</b></sub>
        </a>
        <br />
        </td>
    </tr>
    <tr>
        <td>
            웹소켓 통신 구현
            <br />
            입출금 구현
        </td>
        <td>
            로그인/회원가입
        </td>
    </tr>
  </tbody>
</table>
