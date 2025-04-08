## Keycloak 환경설정 (계속)
## 5. 쇼셜 로그인 (OIDC) 적용하기
* IdP : GitHub / GitLab / Google 등
  * <Google 계정으로 로그인> 과 같은 로그인 및 회원가입 방식을 제공

### Google Cloud Platform 가입
> 1. Google Cloud Platform 로그인
> 2. 새 프로젝트 생성 : keycloak-google-idp-pjt
> 3. 사용자 인증정보 click
> 4. app 이름 등록
> 5. 사용지 지원 이메일 등록
> 6. User Type : 외부 선택
> 7. 애플리케이션 유형 : 웹 애플리케이션 선택
> 8. 이름 : keycloak
> 9. 승인된 Redirect URI : keycloak oidc 추가 하면에서 생성된 URL 등록
> 10. OAuth Client가 생성됨
> 11. client-id / client-secret 정보를 keycloak 추가 설정쪽에 등록한다.

### Keycloak 설정
#### Identity Provider 추가
* IdP를 추가하면 Login 시 "Google 계정으로 로그인"과 같은 링크가 로그인 페이지에 생긴다.
* 이 경우 구글 사이트 로그인으로 인증을 수행한다.
```sh
# Realm 선택 : ex) SSO_TEST
# Identity Providers > Add provider 선택
# OpenID Connect v1.0 선택
  - Redirect URI : IdP 인중 후 다시 돌아 Keycloak endpoint
    (Alias를 등록하면 자동으로 endpoint가 구성 됨)
    (ex: https://keycloak.test.com/realms/SSO_TEST/broker/google/endpoint
  - Alias : google
  - Discovery endpoint : Google의 epenid-configuration URL  지정
    (이 값을 지정하면 아래 항목이 자동으로 구성됨)
    : https://google.test.com/login/oauth/authorize
    : https://google.test.com/login/oauth/access_token
    : https://google.test.com/login/userinfo
    : https://google.test.com
  - Client ID : (Google에서 생성된 client_id)
  - Client Secret : (Google에서 생성된 client_secret)
```
