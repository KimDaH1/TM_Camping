package camping.Utils;

public class myUtil{
	
	//전체 페이지 수 구하는것.
	public int getPageCount(int numPerPage, int dataCount) {
		int pageCount = 0;
		pageCount = dataCount/numPerPage;
		
		if(dataCount % numPerPage != 0) {//나머지가 0이 아니면 페이지 하나 더 만듬
			pageCount++;
		}
		return pageCount;
	}
	
	// 페이지 처리 메소드
	// currentPage : 현재 표시할 페이지
	// totalPage : 전체 페이지 수
	// listUrl : 링크를 설정할 URL(ListTest.jsp)
	public String pageIndexList(int currentPage, int totalPage, String listUrl) {
		
		int numPerBlock = 10;  //리스트 밑에 나오는 페이지번호 출력 갯수
		int currentPageSetup; //표시할 첫 페이지의 -1해준 값
		int page;  			  // 하이퍼링크가 될 page index 숫자
		
		StringBuffer sb = new StringBuffer();
		if(currentPage == 0 || totalPage == 0) {//데이터 없을 경우
			return "";
		}
		//ListTest.jsp
		//ListTest.jsp?serchKey = name&serchValue=suzi 검색한 내용의 인수와 변수값이 붙음
		if(listUrl.indexOf("?") != -1) {//물음표가 있으면
			listUrl = listUrl + "&";
			//ListTest.jsp?searchKey=name&searchValue=suzi&
		} else {
			listUrl = listUrl + "?";
		}
		currentPageSetup = (currentPage/numPerBlock)*numPerBlock;
		
		if(currentPage % numPerBlock == 0) {
			currentPageSetup = currentPageSetup - numPerBlock;
		}
		
		// ◀이전 버튼
		if(totalPage > numPerBlock && currentPageSetup>0) {
			sb.append("<a href = \"" + listUrl + "pageNum=" + currentPageSetup + "\">◀이전</a>&nbsp;");
		}
		
		//바로가기 페이지
		page = currentPageSetup+1;
		
		while(page <= totalPage && page <= (currentPageSetup + numPerBlock)) {
			if(page == currentPage) {
				sb.append("<font color = \"Fuchsia\">" + page + "</font>&nbsp;");
			} else {
				sb.append("<a href = \"" + listUrl + "pageNum=" + page + "\">" + page + "</a>&nbsp;");
			}
			page++;
		}
		//다음 ▶, 마지막 페이지
		if(totalPage - currentPageSetup > numPerBlock) {
			sb.append("<a href=\"" + listUrl + "pageNum=" + page + "\">다음▶</a>&nbsp;");
		}
		return sb.toString();
	}
}