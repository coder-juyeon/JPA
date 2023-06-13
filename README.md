# 코리아 IT 아카데미 국비과정
<a name="readme-top"></a>
## JPA

#### ORM(Object Relational Mapping)
```
객체 진영과 RDB 진영을 자동으로 매핑하여 구조의 불일치를 개발자 대신 해결해주는 기술의 총칭이다.
객체 지향 구조에서 프로그래밍 언어를 사용하여 RDB의 데이터를 조작하는 방법이다.
ORM을 사용하면 개발자가 SQL문을 직접 작성하지 않아도 RDB와 상호 작용할 수 있다.
```

#### JPA(Java Persistence API)
```
ORM을 사용하기 위한 설계도(틀)이다.
Java Application용 RDB 매핑 관리를 위한 인터페이스이며, DBMS 벤더사에 의존하지 않고 독립적으로
ORM을 사용할 수 있는 ORM 표준이다. 인터페이스이기 때문에 구현되어 있지 않은 틀만 제공하며,
자체적인 작업을 수행하지 않는다. JPA에 설계된 구조에 맞춰서 각 메소드를 재정의하여 
직접 ORM을 구현하여 사용한다. JPA는 ORM을 사용할 수 있는 ORM 접근 방식이며, 구현되지 않은
JPA를 ORM이라고 말하기는 어렵다.
```

#### Hibernate Framework
```
모든 Java Application에 대해 객체 관계를 그대로 유지한 채 쿼리 서비스를 제공하는 오픈 소스의 경량 ORM이다.
JPA를 구현한 구현체이며, 여러 구현체 중 가장 대표적인 구현체이다.
객체 간 관계 구성을 지원하며, 상속, 지연성, 페이징 처리, 예외 처리 불필요를 지원한다.
```

#### Spring Data JPA
```
JPA를 추상화한 Repository 인터페이스를 제공하여 JPA를 쓰기 편하게 다양한 기능을 지원한다.
내부적으로는 JPA를 사용하기 때문에 JPA를 모르면 내부 구조를 이해하기 힘들 수 있다.
```

#### 객체와 관계형 데이터베이스의 차이

##### 1. 상속
```
▶ RDB의 상속
[개발자]      [기획자]

 번호      	번호
 -----------   -----------
 이름      	이름
 생년월일      	생년월일
 경력      	경력
 기술등급      	OA등급
 프로젝트 수   	클라이언트 수

또는

 [사원]

 번호
 -----------
 이름
 생년월일
 경력
 기술등급
 OA등급
 프로젝트 수
 클라이언트 수

또는

 [사원] - 슈퍼

 번호(PK)
 -----------   
 이름
 생년월일
 경력

 [개발자] - 서브   [기획자] - 서브

 번호(PK, FK)   번호(PK, FK)
 -----------   -----------
 기술등급      OA등급
 프로젝트 수   클라이언트 수

 1:1 관계에서 INSERT를 하기 위해서는 쿼리를 2번 작성해야하는 불편함이 생긴다.
 게다가 SELECT를 하기 위해서는 JOIN을 사용해야 하는데 쿼리가 굉장히 복잡해진다.
 만약에 이런 RDB의 테이블 관계를 자바 컬렉션으로 바꿀 수 있다면,

▶ 컬렉션 사용

 Developer developer = list.get(developerId);
 위와 같이 간단하게 조회할 수 있다.
```

##### 2. 연관관계
```
▶ 객체 연관 관계: 단방향으로 흘러간다(Flower에서 Pot접근은 가능, Pot에서 Flower접근 불가능)

Flower   →   Pot

id      	id
name      	shape
Pot pot      color


▶ RDB 연관 관계: 양방향으로 흘러간다(FLOWER에서 POT을, POT에서 FLOWER를 접근할 수 있다)

 FLOWER   ↔   POT

 ID(PK)      ID(PK)
 --------   --------
 NAME      	SHAPE
 POT_ID(FK)   COLOR


▶ RDB 중심 설계

 class Flower{
    Long id;
    String name;
    String potId; //FK는 RDB방식에서의 연관관계이기 때문에 객체방식으로 바꿔야 함.
 }

 RDB 방식으로 설계하면, 조회 시 JOIN을 하여 FLOWER테이블과 POT테이블에서 각각 필요한 정보를 가져와
 각 객체에 담아주거나 DTO에 담아주어야 한다.

▶ 객체 중심 설계

 class Flower{
    Long id;
    String name;
    Pot pot; // 참조로 연관관계를 맺도록 함.
 }

 flower.setPot(pot)형태와 같이 복잡하게 작업해야 한다.

▶ 컬렉션 사용

 하지만 만약 자바 컬렉션으로 관리가 가능하다면,

 list.add(꽃);
 Flower flower = list.get(flowerId);
 Pot pot = flower.getPot();

 훨씬 편하게 작업이 가능하다.
```

##### 3. 그래프 탐색
```
┌─Market─┐
│        │
Client──Order   Flower──Pot
│
Delivery  

객체는 모든 객체 그래프를 탐색할 수 있어야 한다.

하지만 SQL 작성 시 이미 탐색 범위가 결정된다.
만약 Market과 Flower를 JOIN해서 조회를 한다면,
market.getFlower()는 사용 가능하지만
market.getPot()는 null일 수 밖에 없다.

따라서 엔티티에 대한 신뢰가 무너질 수 밖에 없다.

Market market = marketDAO.findById(marketId);
market.getFlower(); // null이 아니라고 확신할 수 없다.
market.getOrder().getClient(); // null이 아니라고 확신할 수 없다.

marketDAO에 있는 findById()를 분석하지 않는 이상 각 엔티티에 대해 신뢰할 수 없다.
따라서 상황에 따라 조회에 대한 메소드를 여러 개 선언해놓아야 한다.

marketDAO.getFlower();
marketDAO.getOrderWithClient();
marketDAO.getOrderWithClientWithDelivery();
...

하지만 위와 같은 방법은 사실상 불가능에 가깝다.
```

##### 4. 값 비교
```
SQL 실행 결과를 담은 뒤 생성자를 호출하여 객체에 담으면 매번 new가 사용되기 때문에
동일한 조회 결과의 객체일지라도 주소가 모두 다르다.

▶ 컬렉션 사용
하지만 만약 자바 컬렉션에서 객체 조회가 가능하다면 
list.get(memberId) == list.get(memberId);
```
##### 5. 결론
```
즉, 객체지향으로 설계할 수록 작업이 오히려 복잡해지고 늘어나기 때문에 RDB 중심으로 설계할 수밖에 없다.
RDB를 자바 컬렉션에 저장하듯 사용하면 굉장히 편해지고 많은 문제가 해결되는데,
바로 이 기술을 JPA라고 한다.
```

#### JPA를 사용해야 하는 이유
```
1. SQL 중심 개발에서 객체 중심으로 개발

2. 생산성
  저장: jpa.persist(market);
  조회: jpa.find(marketId);
  수정: market.setMarketName("이마트");
  삭졔: jpa.remove(market);

3. 유지보수
  클라이언트가 새로운 필드를 요청하여 새로운 필드 추가 시
  클래스 안에 필드만 한 개 추가하면 끝. SQL문을 수정할 필요 없음.

4. 패러다임의 불일치 해결
```

#### JPA와 상속
```
Employee	Developer extends Employee

employeeId	developerId
employeeName	developerLevel
  developerProjectCount


- INSERT

▷ 개발자
  jpa.persist(developer);

▷ JPA
  INSERT 두 번 해줌.

자식 필드에 부모 필드가 포함되어 있기 때문에 필요한 데이터를 자식 객체에 채우기만 하면 됨.

- SELECT

▷ 개발자
  jpa.findById(Developer.class, developerId);

▷ JPA
  부모 테이블과 JOIN해서 데이터를 가져옴
```

#### JPA와 연관관계
```
Flower	→	Pot

id		id
name		shape
Pot pot	color

flower.setPot(pot);
jpa.persist(flower);

jpa.findById(Flower.class, flowerId);
```

#### JPA와 객체 그래프 탐색
```
 ┌─Market─┐
 │        │
Client──Order	Flower──Pot
 │
 Delivery  


Flower flower = jpa.findById(Flower.class, flowerId);
Pot pot = flower.getPot();
market.getOrder().getClient();

※ SELECT 결과가 없으면 문제가 생기기 때문에 NPE 체크는 반드시 해야한다.
```


#### JPA와 값 비교
```
Market market1 = jpa.findById(Market.class, marketId);
Market market2 = jpa.findById(Market.class, marketId);

market1 == market2;

동일한 트랜잭션에서 조회한 엔티티는 무조건 같다.
```

#### JPQL
```
객체 지향 쿼리 언어
엔티티 객체를 대상으로 쿼리를 작성해야 한다.
SQL은 SQL로 변환된다.
키워드는 대소문자 구분이 없다.
별칭(as) 필수
typedQuery: 리턴 타입을 정확히 알때
Query: 리턴 타입이 정확하지 않을때

JPQL을 사용하는 QueryDSL은 무조건 쿼리가 발생한다.
가져온 결과를 1차 캐시에 INSERT 한다.
만약 동일한 객체가 1차 캐시에 존재하면, 쿼리 실행 결과를 버린다.
```

#### Entity의 4가지 상태
```
영속 상태 : 1차 캐시에 등록된 상태
준영속 상태 : detached instance이며. detached()를 사용하여 1차 캐시로부터 분리된 상태
비영속 상태 : 1차 캐시에 등록되지 않은 상태
삭제 상태: remove()를 사용하여 1차 캐시로부터 삭제된 상태
영속 상태인 객체일 경우에만 삭제가 가능하다.
```

#### table 전략

##### 1.SINGLE_TABLE 전략
```
단일 테이블 전략
모든 자식 객체를 모아서 하나의 테이블로 생성하며, 
구분 컬럼을 추가하여 각 정보를 구분할 수 있도록 하는 전략
부모 테이블 extends 해주기
조인을 사용할 필요 없이 조회 가능.
쿼리를 단순하게 작성하여 조회할 수 있다.
자식 엔티티의 필드는 @NotNull을 사용할 수 없다.
테이블의 컬럼이 많아질 수록 조회 성능이 떨어질 수 있다.
```

##### 2.TABLE_PER_CLASS 전략
```
엔티티 당 한개 테이블 전략
부모 엔티티와 자식 엔티티마다 테이블을 생성하는 전략, 실무에서의 사용을 권장하지 않는다.
자식 엔티티의 필드는 @NotNull 사용할 수 있다.
자식 테이블끼리 JOIN시 겹치는 컬럼으로 인해 조회 성능이 떨어지고 쿼리가 복잡해진다.

부모 클래스에 abstract를 붙이면 부모를 단독으로 쓰지 않고 자식 테이블 2개 생성
```

##### 3.joined 전략
```
1.  부모 엔티티 PK를 슈퍼키로 설정하고, 자식 엔티티의 PK를 서브키로 설정하는 전략
2.  정규화 방식
3.  조회 시 JOIN으로 인해 성능 저하가 발생한다.
4.  복잡한 쿼리 작성 필요
5.  INSERT 작성 시 쿼리 2번 실행
```

```
성능 좋은순
일반쿼리 -> 한방쿼리 -> 쿼리 두번
```

#### fetch
```
연관관계를 맺고 있는 경우, 조회시 객체를 가져오는 방법을 기술하는 옵션이다.
- EAGER
	모든 연관관계 객체를 JOIN하여 한방 쿼리로 가져온다.
- LAZY
	첫 객체만 SELECT 하고, 연관관계 객체를 사용할 때 쿼리가 다시 실행된다.
 
복잡한 연관관계 속에서 EAGER로 사용하면 불필요한 JOIN이 발생하기 때문에 성능이슈가 발생할 수 있다.
따라서 실무에서는 LAZY로 설정해야 하며,
한방 쿼리가 필요할 때에는 JPQL을 사용하여 정확히 원하는 테이블끼리만 JOIN하여 사용한다.
```

#### cascade(영속성 전이)
```
엔티티가 영속상태로 전활될 때 참조 엔티티도 영속상태로 같이 전환되고,
삭제상태로 전환될 때도 참조 엔티티까지 삭제상태로 전환된다.
즉, 연관관계 객체에도 영속 상태를 전이하고 싶을 때 사용하는 옵션이다.

참조 엔티티 모두 영속 상태로 전환해야 한다.
하지만 cascade에 CascadeType.PERSIST를 설정하면,
자동으로 참조 엔티티까지 영속 상태로 전환되기 때문에
아래의 코드를 작성할 필요 없다. 
boardDAO.save(reply1);
boardDAO.save(reply2);

엔티티를 영속상태로 변경하고 참조 엔티티까지 영속상태로 변경되었다면,
현재 1차 캐시에는 엔티티 및 참조 엔티티 모두 등록되어 있는 상태이다.

1:1 관계에서는 추후 유지보수시 N이 될 수 있는 개체를 연관관계의 주인으로 설정한다.
CascadeType.Remove는 로직에 따라 설정해야 하며,
잘못 설정 시 참조 엔티티 삭제 후 기존 엔티티까지 삭제하는 이슈가 발생한다.
반려동물 정보를 삭제하면 주인 정보도 삭제된다.
```

#### Hibernate의 쓰기지연저장소 쿼리 실행 순서
```
-find, updqte, insert, remove 순서로 실행된다.
-find를 하기 전에 대상 엔티티 쿼리가 있다면, flush 후 find가 진행된다.
```

<img width="900" src="https://github.com/coder-juyeon/JPA/assets/122768623/1d681348-a215-4a29-926b-6d6f327b334d">

#### FK
```
insert 실행 시, @JoinColum으로 설정된 객체로만 FK를 추가할 수 있다.
@joinColum이 설정되지 않은 연관객체로는 FK를 추가할 수 없다.
```

#### 단방향
```
INSERT 실행 시, @JoinColumn으로 설정된 객체로만 FK를 추가할 수 있다.
//        @JoinColumn이 설정되지 않은 연관객체로는 FK를 추가할 수 없다.
```

#### 양방향 
```
관계에서는 @JoinColumn을 사용하지 않아도 mappedBy로 FK를 설정한다.
mappedBy를 생략하면 모든 테이블에 FK가 생긴다.
RDB에서 설계할 때 N 쪽에 FK를 두기 때문에
FK를 필드로 가지고 있는 엔티티가 연관관계의 주인이 되어야 한다.
```

#### mappedBy 
```
단방향 2개로 양방향을 설계했을 경우 서로 FK를 수정 및 추가할 수 있다.
그런데, 서로 수정을 하게 되면 양쪽 모두의 fk를 동기화해야 하기 때문에(일관성) 번거롭고 무결성에 위반될 수도 있다.
따라서, mappedBy를 사용하여 N 쪽의 FK를 연관관계의 주인으로 설정해야 한다.
mappedBy에 작성한 필드명은 RDB 진영에서 "_id"를 붙여 FK의 이름으로 사용된다. 

문제 발생
mappedBy를 question으로 설정했기 때문에,
question_id는 Question엔티티에서 관리하게 된다.
따라서 Answer 엔티티에 question_id를 추가하고 싶다면,
answer에 question을 넣어주어야 한다.
```

#### 편의메소드 
```
연관관계의 주인 엔티티가 아닌 반대편 엔티티로 FK를 추가하고자 할 때
NULL값을 기존 FK값으로 변경하고자 사용한다.
```

#### orphanRemoval = true 
```
컬렉션으로 삭제된 객체들까지 전부 감지하도록 설정
```

#### JpaRepository<Type, Id>
```
Type: 엔티티 이름
Id: PK 자료형
```

#### Spring Data JPA
```
JpaRepository를 상속받은 인터페이스에 직접 구현체를 만든 후 주입해준다.
```

#### @NoArgsConstructor(access = AccessLevel.PROTECTED)
```
외부에서 객체 생성을 막음과 동시에, Spring에서는 사용할 수 있도록 PORTECTED로 설정한다.
```

#### @Builder
```
기본 생성자 만든 후 setter 썼을 때 빼먹은 값이 있을 수 있음
초기화 생성자의 모든 매개변수에 값이 들어와야 메모리에 할당된다.
```

#### 쿼리 메소드
```
메소드 이름으로 쿼리를 생성할 수 있다.
```

#### 정규화
```
정규화란, 한 개의 테이블에서 이상현상 또는 동일한 데이터 중복 발생으로 인해 문제가 생길 경우 여러 테이블로 분리하는 작업을 말한다. 
정규화 후에는 조회 시 JOIN을 사용하여 데이터를 가져오게 된다.
```
#### 반정규화
```
정규화란, 한 개의 테이블에서 이상현상 또는 동일한 데이터 중복 발생으로 인해 문제가 생길 경우 여러 테이블로 분리하는 작업을 말한다. 
정규화 후에는 조회 시 JOIN을 사용하여 데이터를 가져오게 된다.

**
정규화: JOINED
반정규화: SINGLE_COLUMN
```

#### 패치조인
```
패치 조인 시, 해당 테이블의 항목만 다른 절에서 사용가능하다.
pay, pay.order를 패치 조인으로 진행 시, pay만 select 절에서 사용할 수 있다.(pay, order, pay.product X)
from 절에 pay만 작성하고 다른 절에서 원하는 객체를 그래프 탐색으로 접근해야 한다.
```
#### 패치조인을 사용해야 할 때
```
해당 객체를 select절에 작성한 뒤 java쪽에서 엔티티 그래프 탐색을 진행할 때
select(order).from(order).join(order.member).fecthJoin()
```

#### 패치 조인을 사용하면 안될 때
```
select절에 원하는 연관 엔티티를 작성하거나 DTO를 작성해야 할 때
select(order.member).from(order)

기준
  1	    :	  N 	=>  	select(a) 쓴다 ( select(a).~ join(). fetchJoin())
  N 	    :	  1  	=> 	select(b.a)를 쓴다 ( select(b.a) ~ join())
1-> a

패치조인시 추가 적인 조건절은 on절이 아닌 where절에 작성해야 한다.
```

#### DTO(QueryProjection)
```
- 집계함수 사용
- 화면으로 여러 엔티티를 보낼 때
1. 두 개의 엔티티일 경우(fetchJoin, QueryProjection이 필요 없음)
2. 세 개 이상의 엔티티일 경우(join이 가능하면 QueryProjection사용)
```

#### 세션
```
JWT -> 세션저장 안하는 방식 토큰
security

```

#### 스프링 세션 문제점
```
서버 증설시 세션 정보 공유 불가능
```

#### rest로 설계하는 이유
```
	타언어, 타서버와 세션이 연결이 되야됨
```
#### L4 switch
```
동일한 ip로 연결해도 분배기 역할
클라우드 인프라 구축 (클러스터링)
```

#### Redis
```
no sql, no schema
key, value
속도 빠름
```

#### Spring Security
```
스프링 기반의 어플리케이션에서 보안을 위해 인증과 권한 부여를 사용해 접근을 제어하는 프레임워크
```

#### Spring Security 흐름
<img width="900" src="https://github.com/coder-juyeon/JPA/assets/122768623/0a5e2392-1666-4573-b819-b292c745f23d">

<p align="right">(<a href="#readme-top">back to top</a>)</p>
