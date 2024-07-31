# 칼로리 계산 시스템

## 개요

최근 자기관리와 건강에 대한 관심이 높아지고 있습니다. 이로 인해 많은 사람들이 운동과 식단을 통해 건강을 관리하려는 노력을 기울이고 있습니다. 이런 추세에 맞춰서 운동 및 식단과 관련된 다양한 정보들이 온라인을 통해 쉽게 공유되고 있습니다. 본인의 목표에 맞는 권장 칼로리를 확인하고, 게시판을 통해 건강한 삶을 살기 위한 동기부여를 제공할 수 있습니다.

## 개발 기간
2024.07.06~

## 사용기술
java17,Spring boot 3.3.1, JPA, H2 database, React JS

## 주요 이슈

### 같은 객체를 2번 반환해버리는 이슈 발생
> org.hibernate.NonUniqueResultException: Query did not return a unique result: 2 results were returned

    칼로리 계산 한 후에 멤버객체를 생성해서 반환하고
    
    멤버서비스의 viewMember 서비스에서 반환된 멤버객체를 받아 응답하는 코드인데 한번 더 저장해버리는 이슈가 발생
   
    멤버서비스의 viewMember 서비스에서 반환된 멤버객체를 받아 응답하는 코드로 수정

### 게시판 순환참조
    댓글을 달고 게시판을 상세보기를 보게되면 순환참조가 발생

    - 해결방안
        - @JsonBackReference와 @JsonManagedReferunce
            @JsonBackReferen
                연관관계의 주인 Entity에 설정
                직렬화가 되지 않도록 수행
            @JsonManagedReferunce
                연관관계의 주인 반대 Entity에 설정
                직렬화 정상 수행
        - dto 사용
            기존에 entity로 reply을 받아서 그대로 응답하여 순환참조 발생을 dto로 받아 응답하는 방식으로 변경
            
        - 최종적으로 Dto 사용
        - 이유
            Entity 클래스를 Dto 클래스로 변환하는 것이 API 응답 형식으로 사용하는 방식으로써 좋다
            Dto 클래스는 필요한 필드만을 가지고 있으므로, 불필요한 데이터 노출이 없다.
            API 응답 형식 변경에 따른 수정도 용이하며, 데이터 변환 오류도 방지할 수 있다.
            근본적으로 사전에 순환참조를 예방할 수 있는 방법이기 때문에
<hr>

## ERD

![personal-kcal_erd](https://github.com/user-attachments/assets/32abc0d9-f94c-4391-8c45-f80c39dda321)


