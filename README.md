👵🏻 BugiCareServer_SpringBoot 🤖
=============
> 2023 HSU Capstone AI를 활용한 어르신 돌봄 시스템 - Bugicare의 SpringBoot Server입니다.

<br>

## ✔️ GUIDES

AWS의 ec2 환경에서 실행하였습니다.

```shell
$git clone https://github.com/BugiCare/BugiCareServer_SpringBoot.git
$cd BugiCareServer_SpringBoot
$./gradlew clean
$./gradlew build
$cd build/lib
$java -jar BugiCareServer-0.0.1-SNAPSHOT.jar
```

</br>

## ✏️ API

#### 👨🏻‍💼 매니저

http://15.164.7.163:8080/allManager

http://15.164.7.163:8080/manager/{id}

http://15.164.7.163:8080/myUser

</br>

#### 👵🏻 사용자

http://15.164.7.163:8080/allUser

http://15.164.7.163:8080/user/{id}

http://15.164.7.163:8080/userImage/{id}

</br>

#### 🥶 냉장고 문 열림 횟수

http://15.164.7.163:8080/count/day/refrigerator

http://15.164.7.163:8080/count/week/refrigerator

http://15.164.7.163:8080/count/month/refrigerator

</br>

#### 🚪 문 열림 횟수

http://15.164.7.163:8080/count/day/door

http://15.164.7.163:8080/count/week/door

http://15.164.7.163:8080/count/month/door

</br>

#### 🛏️ 취침 시간

http://15.164.7.163:8080/sleepTime/day

http://15.164.7.163:8080/sleepTime/week

http://15.164.7.163:8080/sleepTime/month

<br>

## 🕗 VERSION

- SpringBoot 3.0.2

</br>

## 📂 Database

<img width="1059" alt="스크린샷 2023-03-13 오후 9 53 23" src="https://user-images.githubusercontent.com/92321183/224710045-94bfa8e2-58e2-4b20-8e11-8e869ab79949.png">
