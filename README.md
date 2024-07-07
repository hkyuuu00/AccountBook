# 나만의 가계부

### 📖 프로젝트 개요
안드로이드 스튜디오를 이용하여 간단한 어플을 제작하기 위해 주제를 생각하던 중, 지출을 기록하고 월별, 카테고리별로 통계를 보여주는 가계부 어플을 만들어 사용해보고 싶어서 계획
<br/>
### 🚀 프로젝트 목표
SQLite를 사용하여 데이터베이스에 지출내역을 기록하고 일별, 월별, 카테고리별로 데이터베이스를 불러와 보여주도록 목표
<br/><br/><br/>

## 📝 프로젝트 설명

### 💼 기능
나만의 가계부는 달력을 통해 일별 지출내역을 확인할 수 있으며 월별 통계와, 카테고리별 지출내역을 확인할 수 있도록 서비스<br/>


### 🗺 기술 설계도
<img width=100% src="https://github.com/hkyuuu00/AccountBook/assets/155419559/2cc5894d-c60d-41c7-927b-6a58918fefae"><br/><br/>
- Layout에서 요청을 전달
- Service에서 동작을 처리 및 데이터는 DB로 전달
- DB에서 값을 불러올 경우 Model로 전달
- Model에 저장된 값을 Adapter를 통해 Layout에 구현

<br/><br/>


### 💻 기술 스택
- **Language** ![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
- **Build** ![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
- **DataBase** ![SQLite](https://img.shields.io/badge/sqlite-%2307405e.svg?style=for-the-badge&logo=sqlite&logoColor=white)
- **Tools** ![Android Studio](https://img.shields.io/badge/android%20studio-346ac1?style=for-the-badge&logo=android%20studio&logoColor=white)
<br/>

### ✨ 주요 기능 및 이미지

#### 📌 일별 지출 확인
<table>
  <tr>
    <td>
      <img width=100% src="https://github.com/hkyuuu00/AccountBook/assets/155419559/bc549ede-ec0b-4d29-a7d3-9ec33b45950a">
    </td>
    <td>
      <strong>일별 조회:</strong> 달력의 날짜를 클릭하여 일별 지출 조회<br/>
      <strong>추가 버튼:</strong> 추가 버튼을 통해 지출 내역을 추가하는 화면으로 이동
    </td>
  </tr>
</table>
<br/><br/>

#### 📌 지출 내역 작성
<table>
  <tr>
    <td>
      <img width=100% src="https://github.com/hkyuuu00/AccountBook/assets/155419559/c971f317-e850-4949-9d66-b03f6c13c93b">
    </td>
    <td>
      <strong>지출 작성:</strong> 날짜, 카테고리, 사용내역, 금액을 작성<br/>
      <strong>추가 버튼:</strong> 추가 버튼을 통해 데이터베이스에 저장 후 메인 화면으로 이동
    </td>
  </tr>
</table>
<br/><br/>

#### 📌 월별 지출 확인
<table>
  <tr>
    <td>
      <img width=100% src="https://github.com/hkyuuu00/AccountBook/assets/155419559/fa720473-f693-4acf-8559-e563d4be84d8">
    </td>
    <td>
      <strong>날짜 선택:</strong> 스피너를 돌려 월별 지출을 조회가능<br/>
      <strong>월별 지출:</strong> 해당 월의 지출 내역과 정보, 한달 총 사용금액을 보여줌
    </td>
  </tr>
</table>
<br/><br/>

#### 📌 월 카테고리별 지출 확인
<table>
  <tr>
    <td>
      <img width=100% src="https://github.com/hkyuuu00/AccountBook/assets/155419559/83abdd8d-9a95-4324-8e21-f5a0a1c302e1">
    </td>
    <td>
      <strong>날짜 선택:</strong> 스피너를 돌려 월별 지출을 조회가능<br/>
      <strong>카테고리별 지출:</strong> 해당 월의 카테고리별 지출 비율과 그래프를 통해<br/> 한눈에 보기 쉽게 나타냄
    </td>
  </tr>
</table>
<br/><br/>

#### 📌 지출 내역 삭제
<table>
  <tr>
    <td>
      <img width=100% src="https://github.com/hkyuuu00/AccountBook/assets/155419559/ed87e63b-85d2-4675-8df1-6647492eb425">
    </td>
    <td>
      <strong>항목 삭제:</strong> 지출내역 리스트에서 삭제하고 싶은 내역을 클릭하여 삭제
    </td>
  </tr>
</table>
<br/><br/><br/>


## ⚙️ 프로젝트 설치 및 실행 방법

### 📦 설치 방법
- Android Studio에서 Build > Build Bundle(s) / APK(s) > Build APK(s) 클릭
- Android Studio/AccountBook/app/build/outputs/apk/debug 폴더에 있는 app-debug.apk파일을 안드로이드 폰에서 실행 or NOX 프로그램으로 작동가능
