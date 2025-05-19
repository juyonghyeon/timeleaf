# 타임리프
## 의존성
1. thymeleaf-sping6
2. thymeleaf-extras-java8time / java.time 패키지 추가 기능, 식 객체 - #temporal / 날짜 시간
3. thymeleaf-layout-dialect / 레이아웃 기능 지원


참고)
configureDefaultServletHandling()
- 요청 주소가 오면 기본적으로 컨트롤러 빈을 찾는다
- 못찾으면 정적 경로를 찾는다. 스프리에 의해서 해석되는 것이 아니라 그래도 웹에 출력하는 자원들(이미지, css, js ..) -> addResourceHandler 설정 추가, 정적 경로를 설정할 필요 있음

- 정적 경로 설정
```java
@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }
```

- URL ANT 패턴
- /** : 현재 경로 및 하위 경로 포함 모든 경로, 모든 파일을 의미
  - 예) /upload/** /upload/1.png /upload/sub/1.png
- /* : 현재 경로의 모든 파일 /upload/* -> /upload/1.png
- ? : 문자 1개
  - /sub/m?1 -> /sub/m01 /sub/m11


# 특징
- 내추럴 템플릿

## 타임리프 기본 문법
- 변수식 : ${변수 - 속성}
- 메세지식 : #{코드}
- 링크식 : @{링크}
    - JSTL - <c:url .. /> : 컨텍스트 경로를 자동 추가
    - 경로에 변수형태로 치환 기능

```html
<a th:href="@{/member/info/{email}/{mobile}(email=${requestJoin.email}, mobile=${requestJoin.mobile})}" th:text="#{회원정보}"></a>

<a href="/makeup01/member/info/user01@test.org/010-1000-1000">회원정보</a>
```
- 경로에 (속성명=값, 속성명=값) : 쿼리스트링 값으로 완성

```html
<a th:href="@{/member/info(email=${requestJoin.email}, mobile=${requestJoin.mobile})}" th:text="#{회원정보2}"></a>

http://localhost:3000/makeup01/member/info?email=user01@test.org&mobile=010-1000-1000
```

- 선택변수식 : *{속성명}
    - th:object 와 함께 쓰인다.
    - <th:block ... ></th:block> 노출되지 않는 문법 적용이나 값의 설정, 조건문, 반복문을 사용할때 주로 사용된다.


## 조건문
- th:if
    - test="${...}" / true, false는 상수로 EL식으로 사용하지 않아도 된다.

## 반복문
- th:each
    - th:each="요소 한개 : ${반복 가능한 요소}"
    - th:each="요소 한개, 반복상태객체 : ${반복 가능한 요소}"
        - 반복상태 객체
            - index : 0부터 시작하는 숫자 번호
            - count : 1부터 시작하는 숫자 번호
            - even : 짝수번째 인지
            - odd : 홀수번째 인지
            - first : 첫번째 인지
            - last : 마지막인지


## 기타
- th:classappend
    - 클래스 속성을 추가할때
    - th:classappend="${3항 조건}"
    - th:classappend="${조건식} ? '참일때' : '거짓일때'
    - th:classappend="${조건식} ? '참일때'

- th:text
    - 문자열 출력 / HTML 태그는 해석 X
    - 직접 문자열을 출력해야 하는 경우 \[\[${속성명}\]\]
- th:utext
    - HTML 태그를 해석하고 출력해 주는 역할

- th:속성명
    - 속성명으로 정의된 내용 치환
    - th:src, th:id, th:href, th:action ... th:type ...

## 내장 식객체
- Expression Basic Objects : 내장 객체등등..
- Expression Utility Objects : 편의 객체등등..
    - #temporals - java.time 핵심 클래스로 생성된 날짜 시간 객체의 형식화를 주로 담당
    - #arrays, #numbers, #strings, #urls, #messages ..


## 양식(form)
- 커맨드 객체 값의 자동 완성
- 검증실패시 출력 메세지

## 레이아웃 확장 기능 