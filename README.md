# 🌤️ demo-weather-api
경기도 의정부시 문충로74 지역의 기상청 단기예보 데이터를 공공데이터 포털 Open API로 연동하여  
DB에 저장하고, 이를 조회할 수 있는 멀티모듈 기반의 날씨 API 서비스입니다.

## 🔗 주요 기능

- 단기 예보 수집 (POST /weather/sync)
- 단기 예보 조회 (GET /weather) - 데이터 없을 시 204 반환

## 🛠 사용 기술 스택

- Java 17
- Spring Boot
- JPA
- MySQL
- Gradle (Kotlin DSL)
- RestTemplate
- 멀티모듈 구조

## 📂 프로젝트 구조

```bash
modules/
├── domain/
│   └── weather-domain/           # Entity, Repository
├── application/
│   ├── sync-application/         # POST /weather/sync (API 연동 → 저장)
│   └── inquire-application/      # GET /weather       (DB 조회)
├── internal/
│   └── common-utils/             # RestTemplate 설정 등 공통 유틸
└── boot/                         # Main Application (WeatherApplication)
