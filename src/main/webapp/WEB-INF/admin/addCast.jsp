<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h2>팝업 페이지</h2>
<!-- 테이블 목록을 표시할 요소 -->
<ul id="tableItemList"></ul>
<button id="confirmButton">등록</button>

<script>
  // 선택한 목록을 저장할 배열
  const xhr = new XMLHttpRequest();
  let selectedItems = [];
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
      const tableItemList = document.getElementById("tableItemList");
      items.forEach(function (crew){
          serverData.push(crew);
          let listItem = document.createElement("li");
          listItem.innerHTML = ' <input type="checkbox" name="tableItem" value=" ' + crew.crewNo + '">'
                + crew.crewName
                + '<img src="' + crew.crewImg + '" width="50px" height="50px">';
          tableItemList.appendChild(listItem);
      });
  }

  // 등록 버튼 클릭 시 선택한 목록을 부모 페이지로 전달
  document.getElementById("confirmButton").addEventListener("click", function() {
    var selectedTableItems = Array.from(document.querySelectorAll('input[name="tableItem"]:checked'));

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
