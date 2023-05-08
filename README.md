## When is the bus coming

WBC_for_Watch는 내가 즐겨찾기 한 버스의 도착 예정 시간을 갤럭시 워치에서 확인 가능할 수 있는 앱입니다.

스마트폰 앱: https://github.com/spicypunch/WBC
<br>
<br>
<br>

### 순서도
----------
![image](https://user-images.githubusercontent.com/72846127/235494149-8a05c028-fd3f-4ecf-b813-ce062092708b.png)

<br>
<br>
<br>

### 주요 기술
---------
- Retrofit을 통한 버스 도착 정보 API를 사용하였습니다.
- Databindig: xml 파일을 통해 선언적으로 데이터와 뷰를 바인딩합니다.
- Firebase를 통해 스마트폰 앱에서 즐겨찾기 한 버스 정보를 가져옵니다.
- WearableRecyclerView를 사용했고 ListAdapter 상속받아 사용하고 있습니다.

<br>
<br>
<br>

### UI
--------
#### 테스트 계정에 임의의 버스 즐겨찾기 추가
![Screenshot_20230502_003636_ ](https://user-images.githubusercontent.com/72846127/235486260-f93a51e1-3ce1-4bdc-b9dd-fdbc5286ae19.jpg)


#### 로그인 및 즐겨찾기 확인
![20230502_003408_1](https://user-images.githubusercontent.com/72846127/235485446-d82ce21c-f4f2-4c61-8681-1196be55d327.gif) ![20230502_003408_2](https://user-images.githubusercontent.com/72846127/235485454-eb4110f7-1c47-40f3-a7b2-9bc9b43370f0.gif)

- 테스트 계정 아이디로 로그인을 시도합니다.
- 로그인이 완료되면 스마트폰 앱에서 등록한 즐겨찾기가 그대로 출력됩니다.

#### 앱 종료 후 다시 실행
![20230502_003557_1](https://user-images.githubusercontent.com/72846127/235485460-aa705c16-086a-4e97-a340-30b857b3e146.gif)

- 앱을 종료한 후 다시 실행해도 로그아웃 버튼을 누르지 않는 이상 바로 전에 로그인한 계정의 즐겨찾기 목록을 확인할 수 있습니다.

※ 도착 예정 시간 중 0분으로 나오는 이유는 1번째 버스가 막차이기 때문입니다. 0분 대신 운영 종료라고 나오게 수정할 생각입니다.

<br>
<br>
<br>

### 기능 업데이트
--------
#### 새로고침
![20230508_175044_1](https://user-images.githubusercontent.com/72846127/236789573-b6709b0e-a728-486f-aa32-0873bc79b1cd.gif)

- 새로고침 버튼을 추가하고 새로고침을 누를 경우 버스 도착 정보를 갱신합니다.

