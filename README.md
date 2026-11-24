# 게시판 프로젝트

프론트엔드와 Spring Boot 백엔드를 별도 컨테이너로 실행하는 게시판입니다. 게시글 목록 조회, 작성, 수정, 삭제를 지원합니다.

## 실행

Ubuntu에서 Docker와 Compose 플러그인을 설치한 뒤 이 디렉터리에서 실행합니다.

```bash
docker compose up --build
```

브라우저에서 `http://localhost:8080`에 접속합니다. VirtualBox NAT를 사용하고 호스트 PC에서 접속한다면 호스트의 8080 포트를 Ubuntu 게스트의 8080 포트로 전달하세요. API 문서는 `http://localhost:8080/swagger-ui/index.html`에서 확인할 수 있습니다.

데이터는 Docker 볼륨 `board-data`의 H2 파일에 저장됩니다. `docker compose down`으로 컨테이너를 내려도 유지되지만, 볼륨을 삭제하면 게시글도 삭제됩니다. 단일 VM 시연용 구성입니다.

## API

| 메서드 | 경로 | 동작 |
| --- | --- | --- |
| GET | `/v1/posts` | 목록 조회 |
| GET | `/v1/posts/{id}` | 단건 조회 |
| POST | `/v1/posts` | 작성 |
| PUT | `/v1/posts/{id}` | 수정 |
| DELETE | `/v1/posts/{id}` | 삭제 |

작성·수정 본문은 `{"title":"제목","content":"내용"}`입니다. 모든 응답은 `success`, `message`, `data`를 포함합니다.

Kubernetes, CI/CD 및 인증 기능은 아직 구성하지 않았습니다. 인증이 없어 접속 가능한 사용자는 모든 글을 수정하거나 삭제할 수 있습니다.
