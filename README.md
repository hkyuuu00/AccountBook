# 나만의 가계부

### 📖 프로젝트 개요
안드로이드 스튜디오를 이용하여 간단한 어플을 제작하기위해 주제를 생각하던 중, 지출을 기록하고 월별, 카테고리별로 통계를 보여주는 가계부 어플을 만들어 사용해보고 싶어서 계획

### 🚀 프로젝트 목표
SQLite를 사용하여 데이터베이스에 지출내역을 기록하고 일별, 월별, 카테고리별로 데이터베이스를 불러와 보여주도록 목표


## 📝 프로젝트 설명

### 💼 기능
나만의 가계부는 달력을 통해 일별 지출내역을 확인할 수 있으며 월별 통계와, 카테고리별 지출내역을 확인할 수 있도록 서비스

### 🗺 기술 설계도
<img width=100% src="https://github.com/hkyuuu00/AccountBook/assets/155419559/832245ed-a90e-42b9-8020-d6604b38ea9e"><br/><br/>
- Layout에서 요청을 전달
- Service에서 동작을 처리 및 데이터는 DB로 전달
- DB에서 값을 불러올 경우 Model로 전달
- Model에 저장된 값을 Adapter를 통해 Layout에 구현
- <br/><br/>


### 💻 기술 스택
- **Front-End:** `XML``
- **Back-End:** `Java`
- **DataBase:** `SQLite`
- **Tools:** `Android Studio`
<br/>

### ✨ 주요 기능 및 이미지

#### 📌 일별 지출 확인
<table>
  <tr>
    <td>
      <img width=100% src="https://github.com/hkyuuu00/AccountBook/assets/155419559/bc549ede-ec0b-4d29-a7d3-9ec33b45950a">
    </td>
    <td>
    <strong></strong>
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
    <strong></strong>
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
    <strong></strong>
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
    <strong></strong>
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
    <strong></strong>
    </td>
  </tr>
</table>
<br/><br/>

<img width=100% src="https://github.com/hkyuuu00/SeoulParkingInfo/assets/155419559/3ca54a54-9cb3-459d-8bf6-d93c9a3e081b"><br/><br/>
- **위치정보:** 해당 주차장의 위치정보를 지도를 통해 확인<br/>
- **상세정보:** 요일별 운영 시간과 시간별 금액을 확인할 수 있으며, 실시간 주차 공간을 확인 가능<br/><br/><br/>


### 🛠 문제 해결 과정
<table>
  <tr>
    <td>
    <strong>문제</strong>
    </td>
    <td>
    <strong></strong>
    </td>
  </tr>
  <tr>
    <td>원인</td>
    <td></td>
  </tr>
  <tr>
    <td>해결</td>
    <td></td>
  </tr>
</table>

---

## ⚙️ 프로젝트 설치 및 실행 방법

### 📝 Prerequisites
- Node.js 14.x 이상
- Docker 20.x 이상

### 📦 설치 방법
프로젝트를 로컬에 설치하는 방법을 단계별로 설명합니다.

```sh
git clone https://github.com/your-username/your-project.git
cd your-project
npm install
