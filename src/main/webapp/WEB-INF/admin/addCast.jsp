<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <head>
        <meta charset="UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
        <link href="/css/adminCSS/adminAddCast.css" rel="stylesheet">
        <link href="./css/common.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>출연진 목록</title>
</head>
<body>
<ul id="crewList"></ul>
<button id="confirmButton">등록</button>

<script>
  const xhr = new XMLHttpRequest();
  let selectedItems = []; // 선택한 목록
  let serverData = [];
  const getCasts = function () {
      console.log("getCastts");
      xhr.open('GET', '/admin?action=getCrew');
      xhr.send();
      xhr.onreadystatechange = function () {
          if (xhr.readyState !== XMLHttpRequest.DONE) return;

          if (xhr.status === 200) {
              console.log(xhr.response);
              const json = JSON.parse(xhr.response);
              displayCrewTag(json);
          }
          else {
              console.error('Error', xhr.status, xhr.statusText);
          }
      }
  };

  const displayCrewTag = function (items){
      const crewList = document.getElementById("crewList");
      items.forEach(function (crew){
          serverData.push(crew);
          let listItem = document.createElement("li");
          listItem.innerHTML = '<input type="checkbox" name="chkCrew" value=" ' + crew.crewNo + '">'
                + '<div><span class="crew-name-span">' +crew.crewName + '</span></div>'
                + '<img src="' + crew.crewImg + '" width="50px" height="50px">';
          crewList.appendChild(listItem);
      });
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
