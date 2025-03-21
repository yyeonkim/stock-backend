# 실시간 주식 정보 조회 웹 서비스

![Image](https://github.com/user-attachments/assets/a0fc6a0e-23ca-4796-91d1-d292dbffef02)

실시간 주식 정보 조회 및 입출금 기능을 개발하며 보얀 취약점을 제고하고 실시간 통신을 구현해봤습니다.

 https://youtu.be/JFfyY8l5ogk?si=129oT_Uj6anBolKC



 
## 목차

- [전체 구조](#전체-구조)
- [주요 기능](#주요-기능)
- [트러블 슈팅](#트러블-슈팅)
- [기술 스택](#기술-스택)
- [팀원 소개](#팀원-소개)




## 전체 구조
![Image](https://github.com/user-attachments/assets/6fdb36b4-0f68-460c-aa30-c00d4187d985)
크게 두 가지의 백엔드로 이루어져있습니다. 
첫 번쨰는 실시간 웹소켓을 통신하는 polygon web server, 두 번 째는 회원을 관리하고 입출금을 관리하는 자체 백엔드 서버입니다.




## 주요 기능

### 웹소켓을 통한 실시간 주식 정보 열람
![Image](https://github.com/user-attachments/assets/8b31fac3-1371-42b5-b213-40850bb553ac)
polygon api를 이용해 실시간으로 바뀌는 주식정보가 새로고침 없이 사용자에게 보여집니다

### 회원가입/로그인 서비스
![Image](https://github.com/user-attachments/assets/4a480cf1-7c15-4a6c-a6a0-05e9ca2d4949)
입출금 서비스를 이용하기 위해 간단한 회원가입/로그인 절차가 필요합니다.
토큰 유효기간은 5분으로 5분이 지나면 세션이 만료되어 자동 로그아웃됩니다.

### 입출금 서비스
![Image](https://github.com/user-attachments/assets/c2e58785-e792-468b-9bf3-51bc037a5562)
자신이 원하는 금액을 인출하고, 예금할 수 있습니다. 
인출할 금액이 예금 금액보다 많으면 잔액이 부족해 인출이 불가능합니다. 




## 트러블 슈팅

### 초가 아닌 분 단위 데이터 전송
![Image](https://github.com/user-attachments/assets/e2ed7f4e-9780-448b-88ad-73b2ff9fe0f5)
promise.all을 사용해 동시에 요청한 후 응답을 한 번에 받습니다.

### 로컬 날짜에 따른 시차문제
![Image](https://github.com/user-attachments/assets/26e6cd12-8a61-4622-a9a5-2edd19d8d740)

try-catch 를 이용해 하루 전 날짜의 주식정보를 불러옵니다.




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

1. 김용연 https://github.com/yyeonkim
2. 안정은 https://github.com/Ahn-jeongeun
