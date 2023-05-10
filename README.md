👵🏻🤖 BugiCareServer_SpringBoot
=============
<br>

### 📖 API

------
* 🍉 냉장고 현재 ~ 5시간 전/일/주 별 열림 횟수 API
>`/count/day/refrigerator` ➡️ **String 6개 반환(현재 ~ 5시간전)**<br>
`/count/week/refrigerator` ➡️ **String 7개 반환(월, 화, 수, 목, 금, 토, 일)**<br>
`/count/month/refrigerator` ➡️ **String 4개 반환(각 하나의 주 * 4)**


* 🚪 문 현재 ~ 5시간 전/일/주 별 열림 횟수 API
>`/count/day/door` ➡️ **String 6개 반환(현재 ~ 5시간전)**<br>
`/count/week/door` ➡️ **String 7개 반환(월, 화, 수, 목, 금, 토, 일)**<br>
`/count/month/door` ➡️ **String 4개 반환(각 하나의 주 * 4)**
> 
* 🛏️💤 어르신 취침시간 현재/일/주 별 API
>`/sleepTime/day` ➡️ **String 1개 반환 ("1" 👉🏻 취침, "0" 👉🏻 활동 중)**<br>
`/sleepTime/week` ➡️ **String 7개 반환(월, 화, 수, 목, 금, 토, 일)**<br>
`/sleepTime/month` ➡️ **String 4개 반환(각 하나의 주 * 4)**

<br>

### 📂 Database

------

<img width="1059" alt="스크린샷 2023-03-13 오후 9 53 23" src="https://user-images.githubusercontent.com/92321183/224710045-94bfa8e2-58e2-4b20-8e11-8e869ab79949.png">
<br><br>

### 🌱 Swagger

------

##### 1. 사용법

(1) SpringBootApplication 실행

(2) http://localhost:8080/swagger-ui/index.html 접속

(3) API를 확인할 수 있다.
