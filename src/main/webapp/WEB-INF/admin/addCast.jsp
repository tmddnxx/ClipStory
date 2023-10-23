<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <head>
        <meta charset="UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
        <link href="/css/adminCSS/adminAddCast.css" rel="stylesheet">
        <link href="/css/common.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>출연진 목록</title>
</head>
<body>
<div class="input-div"><input name="searchText" type="text" placeholder="검색할 이름을 입력해주세요" oninput="searchCrew(this.value)"/></div>
<ul id="crewList"></ul>
<button id="confirmButton">등록</button>

<script>
  const xhr = new XMLHttpRequest();
  let selectedItems = []; // 선택한 목록
  let serverData = [];
  const getCasts = function () {
      console.log("getCasts");
      xhr.open('GET', '/admin?action=getCrew');
      xhr.send();
      xhr.onreadystatechange = function () {
          if (xhr.readyState !== XMLHttpRequest.DONE) return;

          if (xhr.status === 200) {
              // console.log(xhr.response);
              const json = JSON.parse(xhr.response);
              serverData = json; // serverData에 전체 목록 넣어두기
              displayCrewTag(json);
          }
          else {
              console.error('Error', xhr.status, xhr.statusText);
          }
      }
  };

  const displayCrewTag = function (items){


      const crewList = document.getElementById("crewList");
      crewList.innerHTML = '';
      items.forEach(function (crew){
          let listItem = document.createElement("li");
          listItem.innerHTML = '<input type="checkbox" name="chkCrew" value="' + crew.crewNo + '" ' + (crew.checked ? 'checked' : '') + '>'
                + '<div><span class="crew-name-span">' +crew.crewName + '</span></div>'
                + '<img src="' + crew.crewImg + '" width="50px" height="50px">';

          // 체크상태 저장
          listItem.querySelector('input[name="chkCrew"]').addEventListener('change', function() {
              crew.checked = this.checked;
          });

          crewList.appendChild(listItem);

      });
  }

  // 검색
  function searchCrew(searchText){
      // 검색어 길이 2이상
      if(searchText.length < 2) {
          displayCrewTag(serverData);
          return;
      }
      // 검색한 결과를 results에 담는다
      const results = serverData.filter(crew => crew.crewName.toLowerCase().includes(searchText.toLowerCase()));

      // 검색결과가 없다면 기존목록 출력
      if(results.length === 0)
          displayCrewTag(serverData);
      else
          displayCrewTag(results);
  }

  // 등록 버튼 클릭 시 선택한 목록을 부모 페이지로 전달
  document.getElementById("confirmButton").addEventListener("click", function() {
    let selectedTableItems = Array.from(document.querySelectorAll('input[name="chkCrew"]:checked'));

    selectedTableItems.forEach(function(item) {
      let crewNo = item.value;
      let crew = serverData.find(data => data.crewNo == crewNo);

      if (crew) {
        selectedItems.push(crew);
      }
    });
    // 부모 페이지로 데이터 전송
    window.opener.postMessage(selectedItems, "*");
    window.close();
  });

  document.addEventListener("DOMContentLoaded", function(){
      getCasts();
  });
</script>
</body>
</html>
