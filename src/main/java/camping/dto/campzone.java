package camping.dto;

public class campzone {
	int idx;
	String cpname; //야영장명
 	Double lat;
 	Double lng;
 	String addr;
	String cptel;
 	
	String cpLineIntro; // 한줄소개
	String cpIntro; // 소개
	int cpAllar; // 전체면적
	String cpInsrncAt; // 영업배상책임보험 가입여부
	String cpTrsagntNo; // 관광사업자번호
	String cpBizrno; // 사업자번호
	String cpFacltDivNm; // 사업주체, 구분
	String cpMangeDivNm; // 운영주체, 관리주체
	String cpMgcDiv; // 운영기관, 관리기관
	String cpManageSttus; // 운영상태, 관리상태
	String cpHvofBgnde; // 휴장기간, 휴무기간 시작일
	String cpHvofEnddle; // 휴장기간, 휴무기간 종료일
	String cpFeatureNm; // 특징
	String cpInduty; // 업종☆
	String cpLctCl; // 입지구분☆
	String cpDoNm; // 도
	String cpSigunguNm; //시군구
	int cpZipcode; // 우편번호
	String cpAddr2; // 주소상세
	String cpDirection; //오시는길 컨텐츠
	String cpHomepage; //홈페이지
	String cpResveUrl; //예약 페이지
	String cpResveCl; //예약 구분
	int cpManageNmpr; // 상주관리인원
	int cpGnrlSiteCo; // 주요시설 일반야영장
	int cpAutoSiteCo; // 주요시설 자동차야영장
	int cpGlampSiteCo; //주요시설 글램핑
	int cpCaravSiteCo; //주요시설 카라반
	int cpIndvdlCaravSiteCo; //주요시설 개인 카라반
	int cpSitedStnc; //사이트간 거리
	int cpSiteMg1Width; //사이트 크기1 가로
	int cpSiteMg2Width; //사이트 크기2 가로
	int cpSiteMg3Width; //사이트 크기3 가로
	int cpSiteMg1Vrticl; //사이트 크기 1 세로
	int cpSiteMg2Vrticl; //사이트 크기2 세로
	int cpSiteMg3Vrticl; //사이트 크기 3 세로
	int cpSiteMg1Co; //사이트 크기 1 수량
	int cpSiteMg2Co; //사이트 크기 2 수량
	int cpSiteMg3Co; // 사이트 크기 3 수량
	int cpSiteBottomCl1; //잔디
	int cpSiteBottomCl2; //파쇄석
	int cpSiteBottomCl3; // 테크
	int cpSiteBottomCl4; // 자갈
	int cpSiteBottomCl5; // 맨흙
	String cpTooltip; //툴팁
	String cpGlampInnerFclty; // 글램핑 내부시설
	String cpCaravInnerFclty; // 카라반 내부시설
	String cpPrmisnDe; // 인허가일자
	String cpOperPdCl; // 운영기간
	String cpOperDeCl; // 운영일
	String cpTrlerAcmpnyAt; //개인 트레일러 동반 여부(Y:사용, N:미사용)
	String cpCaravAcmpnyAt; //개인 카라반 동반 여부(Y:사용, N:미사용)
	int cpToiletCo; //화장실 개수
	int cpSwrmCo; //샤워실 개수
	int cpWtrplCo; //개수대 개수
	String cpBrazierCl; //화로대
	String cpSbrsCl; //부대시설
	String cpSbrsEtc; //부대시설 기타
	String cpPosblFcltyCl; //주변이용가능시설
	String cpPosblFcltyEtc; //주변이용가능시설 기타
	String cpClturEventAt; //자체문화행사 여부(Y:사용, N:미사용)
	String cpClturEvent; //자체문화행사명
	String cpExprnProgrmAt;//체험프로그램 여부(Y:사용, N:미사용)
	String cpExprnProgrm; // 체험프로그램명
	int cpExtshrCo; //소화기개수
	int cpFrprvtWrppCo; //방화수 개수
	int cpFrprvtSandCo; //방화사 개수
	int cpFireSensorCo; // 화재감지기 개수
	String cpThemaEnvrnCl; // 테마환경
	String cpEqpmnLendCl; // 캠핑장비대여
	String cpAnimalCmgCl; //애완동물출입
	String cpTourEraCl; // 여행시기
	String cpFirstImageUrl; // 대표이미지
	String cpCreatedtime; //등록일
	String cpModifiedtime; //수정일
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	
	public String getCpname() {
		return cpname;
	}
	public void setCpname(String cpname) {
		this.cpname = cpname;
	}
	
	public String getCptel() {
		return cptel;
	}
	public void setcptel(String cptel) {
		this.cptel = cptel;
	}
	
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
		
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public double getLat() {
		return lat;
	}

 	public String getCpLineIntro() {
		return cpLineIntro;
	}
	public void setCpLineIntro(String cpLineIntro) {
		this.cpLineIntro = cpLineIntro;
	}
	public String getCpIntro() {
		return cpIntro;
	}
	public void setCpIntro(String cpIntro) {
		this.cpIntro = cpIntro;
	}
	public int getCpAllar() {
		return cpAllar;
	}
	public void setCpAllar(int cpAllar) {
		this.cpAllar = cpAllar;
	}
	public String getCpInsrncAt() {
		return cpInsrncAt;
	}
	public void setCpInsrncAt(String cpInsrncAt) {
		this.cpInsrncAt = cpInsrncAt;
	}
	public String getCpTrsagntNo() {
		return cpTrsagntNo;
	}
	public void setCpTrsagntNo(String cpTrsagntNo) {
		this.cpTrsagntNo = cpTrsagntNo;
	}
	public String getCpBizrno() {
		return cpBizrno;
	}
	public void setCpBizrno(String cpBizrno) {
		this.cpBizrno = cpBizrno;
	}
	public String getCpFacltDivNm() {
		return cpFacltDivNm;
	}
	public void setCpFacltDivNm(String cpFacltDivNm) {
		this.cpFacltDivNm = cpFacltDivNm;
	}
	public String getCpMangeDivNm() {
		return cpMangeDivNm;
	}
	public void setCpMangeDivNm(String cpMangeDivNm) {
		this.cpMangeDivNm = cpMangeDivNm;
	}
	public String getCpMgcDiv() {
		return cpMgcDiv;
	}
	public void setCpMgcDiv(String cpMgcDiv) {
		this.cpMgcDiv = cpMgcDiv;
	}
	public String getCpManageSttus() {
		return cpManageSttus;
	}
	public void setCpManageSttus(String cpManageSttus) {
		this.cpManageSttus = cpManageSttus;
	}
	public String getCpHvofBgnde() {
		return cpHvofBgnde;
	}
	public void setCpHvofBgnde(String cpHvofBgnde) {
		this.cpHvofBgnde = cpHvofBgnde;
	}
	public String getCpHvofEnddle() {
		return cpHvofEnddle;
	}
	public void setCpHvofEnddle(String cpHvofEnddle) {
		this.cpHvofEnddle = cpHvofEnddle;
	}
	public String getCpFeatureNm() {
		return cpFeatureNm;
	}
	public void setCpFeatureNm(String cpFeatureNm) {
		this.cpFeatureNm = cpFeatureNm;
	}
	public String getCpInduty() {
		return cpInduty;
	}
	public void setCpInduty(String cpInduty) {
		this.cpInduty = cpInduty;
	}
	public String getCpLctCl() {
		return cpLctCl;
	}
	public void setCpLctCl(String cpLctCl) {
		this.cpLctCl = cpLctCl;
	}
	public String getCpDoNm() {
		return cpDoNm;
	}
	public void setCpDoNm(String cpDoNm) {
		this.cpDoNm = cpDoNm;
	}
	public String getCpSigunguNm() {
		return cpSigunguNm;
	}
	public void setCpSigunguNm(String cpSigunguNm) {
		this.cpSigunguNm = cpSigunguNm;
	}
	public int getCpZipcode() {
		return cpZipcode;
	}
	public void setCpZipcode(int cpZipcode) {
		this.cpZipcode = cpZipcode;
	}
	public String getCpAddr2() {
		return cpAddr2;
	}
	public void setCpAddr2(String cpAddr2) {
		this.cpAddr2 = cpAddr2;
	}
	public String getCpDirection() {
		return cpDirection;
	}
	public void setCpDirection(String cpDirection) {
		this.cpDirection = cpDirection;
	}
	public String getCpHomepage() {
		return cpHomepage;
	}
	public void setCpHomepage(String cpHomepage) {
		this.cpHomepage = cpHomepage;
	}
	public String getCpResveUrl() {
		return cpResveUrl;
	}
	public void setCpResveUrl(String cpResveUrl) {
		this.cpResveUrl = cpResveUrl;
	}
	public String getCpResveCl() {
		return cpResveCl;
	}
	public void setCpResveCl(String cpResveCl) {
		this.cpResveCl = cpResveCl;
	}
	public int getCpManageNmpr() {
		return cpManageNmpr;
	}
	public void setCpManageNmpr(int cpManageNmpr) {
		this.cpManageNmpr = cpManageNmpr;
	}
	public int getCpGnrlSiteCo() {
		return cpGnrlSiteCo;
	}
	public void setCpGnrlSiteCo(int cpGnrlSiteCo) {
		this.cpGnrlSiteCo = cpGnrlSiteCo;
	}
	public int getCpAutoSiteCo() {
		return cpAutoSiteCo;
	}
	public void setCpAutoSiteCo(int cpAutoSiteCo) {
		this.cpAutoSiteCo = cpAutoSiteCo;
	}
	public int getCpGlampSiteCo() {
		return cpGlampSiteCo;
	}
	public void setCpGlampSiteCo(int cpGlampSiteCo) {
		this.cpGlampSiteCo = cpGlampSiteCo;
	}
	public int getCpCaravSiteCo() {
		return cpCaravSiteCo;
	}
	public void setCpCaravSiteCo(int cpCaravSiteCo) {
		this.cpCaravSiteCo = cpCaravSiteCo;
	}
	public int getCpIndvdlCaravSiteCo() {
		return cpIndvdlCaravSiteCo;
	}
	public void setCpIndvdlCaravSiteCo(int cpIndvdlCaravSiteCo) {
		this.cpIndvdlCaravSiteCo = cpIndvdlCaravSiteCo;
	}
	public int getCpSitedStnc() {
		return cpSitedStnc;
	}
	public void setCpSitedStnc(int cpSitedStnc) {
		this.cpSitedStnc = cpSitedStnc;
	}
	public int getCpSiteMg1Width() {
		return cpSiteMg1Width;
	}
	public void setCpSiteMg1Width(int cpSiteMg1Width) {
		this.cpSiteMg1Width = cpSiteMg1Width;
	}
	public int getCpSiteMg2Width() {
		return cpSiteMg2Width;
	}
	public void setCpSiteMg2Width(int cpSiteMg2Width) {
		this.cpSiteMg2Width = cpSiteMg2Width;
	}
	public int getCpSiteMg3Width() {
		return cpSiteMg3Width;
	}
	public void setCpSiteMg3Width(int cpSiteMg3Width) {
		this.cpSiteMg3Width = cpSiteMg3Width;
	}
	public int getCpSiteMg1Vrticl() {
		return cpSiteMg1Vrticl;
	}
	public void setCpSiteMg1Vrticl(int cpSiteMg1Vrticl) {
		this.cpSiteMg1Vrticl = cpSiteMg1Vrticl;
	}
	public int getCpSiteMg2Vrticl() {
		return cpSiteMg2Vrticl;
	}
	public void setCpSiteMg2Vrticl(int cpSiteMg2Vrticl) {
		this.cpSiteMg2Vrticl = cpSiteMg2Vrticl;
	}
	public int getCpSiteMg3Vrticl() {
		return cpSiteMg3Vrticl;
	}
	public void setCpSiteMg3Vrticl(int cpSiteMg3Vrticl) {
		this.cpSiteMg3Vrticl = cpSiteMg3Vrticl;
	}
	public int getCpSiteMg1Co() {
		return cpSiteMg1Co;
	}
	public void setCpSiteMg1Co(int cpSiteMg1Co) {
		this.cpSiteMg1Co = cpSiteMg1Co;
	}
	public int getCpSiteMg2Co() {
		return cpSiteMg2Co;
	}
	public void setCpSiteMg2Co(int cpSiteMg2Co) {
		this.cpSiteMg2Co = cpSiteMg2Co;
	}
	public int getCpSiteMg3Co() {
		return cpSiteMg3Co;
	}
	public void setCpSiteMg3Co(int cpSiteMg3Co) {
		this.cpSiteMg3Co = cpSiteMg3Co;
	}
	public int getCpSiteBottomCl1() {
		return cpSiteBottomCl1;
	}
	public void setCpSiteBottomCl1(int cpSiteBottomCl1) {
		this.cpSiteBottomCl1 = cpSiteBottomCl1;
	}
	public int getCpSiteBottomCl2() {
		return cpSiteBottomCl2;
	}
	public void setCpSiteBottomCl2(int cpSiteBottomCl2) {
		this.cpSiteBottomCl2 = cpSiteBottomCl2;
	}
	public int getCpSiteBottomCl3() {
		return cpSiteBottomCl3;
	}
	public void setCpSiteBottomCl3(int cpSiteBottomCl3) {
		this.cpSiteBottomCl3 = cpSiteBottomCl3;
	}
	public int getCpSiteBottomCl4() {
		return cpSiteBottomCl4;
	}
	public void setCpSiteBottomCl4(int cpSiteBottomCl4) {
		this.cpSiteBottomCl4 = cpSiteBottomCl4;
	}
	public int getCpSiteBottomCl5() {
		return cpSiteBottomCl5;
	}
	public void setCpSiteBottomCl5(int cpSiteBottomCl5) {
		this.cpSiteBottomCl5 = cpSiteBottomCl5;
	}
	public String getCpTooltip() {
		return cpTooltip;
	}
	public void setCpTooltip(String cpTooltip) {
		this.cpTooltip = cpTooltip;
	}
	public String getCpGlampInnerFclty() {
		return cpGlampInnerFclty;
	}
	public void setCpGlampInnerFclty(String cpGlampInnerFclty) {
		this.cpGlampInnerFclty = cpGlampInnerFclty;
	}
	public String getCpCaravInnerFclty() {
		return cpCaravInnerFclty;
	}
	public void setCpCaravInnerFclty(String cpCaravInnerFclty) {
		this.cpCaravInnerFclty = cpCaravInnerFclty;
	}
	public String getCpPrmisnDe() {
		return cpPrmisnDe;
	}
	public void setCpPrmisnDe(String cpPrmisnDe) {
		this.cpPrmisnDe = cpPrmisnDe;
	}
	public String getCpOperPdCl() {
		return cpOperPdCl;
	}
	public void setCpOperPdCl(String cpOperPdCl) {
		this.cpOperPdCl = cpOperPdCl;
	}
	public String getCpOperDeCl() {
		return cpOperDeCl;
	}
	public void setCpOperDeCl(String cpOperDeCl) {
		this.cpOperDeCl = cpOperDeCl;
	}
	public String getCpTrlerAcmpnyAt() {
		return cpTrlerAcmpnyAt;
	}
	public void setCpTrlerAcmpnyAt(String cpTrlerAcmpnyAt) {
		this.cpTrlerAcmpnyAt = cpTrlerAcmpnyAt;
	}
	public String getCpCaravAcmpnyAt() {
		return cpCaravAcmpnyAt;
	}
	public void setCpCaravAcmpnyAt(String cpCaravAcmpnyAt) {
		this.cpCaravAcmpnyAt = cpCaravAcmpnyAt;
	}
	public int getCpToiletCo() {
		return cpToiletCo;
	}
	public void setCpToiletCo(int cpToiletCo) {
		this.cpToiletCo = cpToiletCo;
	}
	public int getCpSwrmCo() {
		return cpSwrmCo;
	}
	public void setCpSwrmCo(int cpSwrmCo) {
		this.cpSwrmCo = cpSwrmCo;
	}
	public int getCpWtrplCo() {
		return cpWtrplCo;
	}
	public void setCpWtrplCo(int cpWtrplCo) {
		this.cpWtrplCo = cpWtrplCo;
	}
	public String getCpBrazierCl() {
		return cpBrazierCl;
	}
	public void setCpBrazierCl(String cpBrazierCl) {
		this.cpBrazierCl = cpBrazierCl;
	}
	public String getCpSbrsCl() {
		return cpSbrsCl;
	}
	public void setCpSbrsCl(String cpSbrsCl) {
		this.cpSbrsCl = cpSbrsCl;
	}
	public String getCpSbrsEtc() {
		return cpSbrsEtc;
	}
	public void setCpSbrsEtc(String cpSbrsEtc) {
		this.cpSbrsEtc = cpSbrsEtc;
	}
	public String getCpPosblFcltyCl() {
		return cpPosblFcltyCl;
	}
	public void setCpPosblFcltyCl(String cpPosblFcltyCl) {
		this.cpPosblFcltyCl = cpPosblFcltyCl;
	}
	public String getCpPosblFcltyEtc() {
		return cpPosblFcltyEtc;
	}
	public void setCpPosblFcltyEtc(String cpPosblFcltyEtc) {
		this.cpPosblFcltyEtc = cpPosblFcltyEtc;
	}
	public String getCpClturEventAt() {
		return cpClturEventAt;
	}
	public void setCpClturEventAt(String cpClturEventAt) {
		this.cpClturEventAt = cpClturEventAt;
	}
	public String getCpClturEvent() {
		return cpClturEvent;
	}
	public void setCpClturEvent(String cpClturEvent) {
		this.cpClturEvent = cpClturEvent;
	}
	public String getCpExprnProgrmAt() {
		return cpExprnProgrmAt;
	}
	public void setCpExprnProgrmAt(String cpExprnProgrmAt) {
		this.cpExprnProgrmAt = cpExprnProgrmAt;
	}
	public String getCpExprnProgrm() {
		return cpExprnProgrm;
	}
	public void setCpExprnProgrm(String cpExprnProgrm) {
		this.cpExprnProgrm = cpExprnProgrm;
	}
	public int getCpExtshrCo() {
		return cpExtshrCo;
	}
	public void setCpExtshrCo(int cpExtshrCo) {
		this.cpExtshrCo = cpExtshrCo;
	}
	public int getCpFrprvtWrppCo() {
		return cpFrprvtWrppCo;
	}
	public void setCpFrprvtWrppCo(int cpFrprvtWrppCo) {
		this.cpFrprvtWrppCo = cpFrprvtWrppCo;
	}
	public int getCpFrprvtSandCo() {
		return cpFrprvtSandCo;
	}
	public void setCpFrprvtSandCo(int cpFrprvtSandCo) {
		this.cpFrprvtSandCo = cpFrprvtSandCo;
	}
	public int getCpFireSensorCo() {
		return cpFireSensorCo;
	}
	public void setCpFireSensorCo(int cpFireSensorCo) {
		this.cpFireSensorCo = cpFireSensorCo;
	}
	public String getCpThemaEnvrnCl() {
		return cpThemaEnvrnCl;
	}
	public void setCpThemaEnvrnCl(String cpThemaEnvrnCl) {
		this.cpThemaEnvrnCl = cpThemaEnvrnCl;
	}
	public String getCpEqpmnLendCl() {
		return cpEqpmnLendCl;
	}
	public void setCpEqpmnLendCl(String cpEqpmnLendCl) {
		this.cpEqpmnLendCl = cpEqpmnLendCl;
	}
	public String getCpAnimalCmgCl() {
		return cpAnimalCmgCl;
	}
	public void setCpAnimalCmgCl(String cpAnimalCmgCl) {
		this.cpAnimalCmgCl = cpAnimalCmgCl;
	}
	public String getCpTourEraCl() {
		return cpTourEraCl;
	}
	public void setCpTourEraCl(String cpTourEraCl) {
		this.cpTourEraCl = cpTourEraCl;
	}
	public String getCpFirstImageUrl() {
		return cpFirstImageUrl;
	}
	public void setCpFirstImageUrl(String cpFirstImageUrl) {
		this.cpFirstImageUrl = cpFirstImageUrl;
	}
	public String getCpCreatedtime() {
		return cpCreatedtime;
	}
	public void setCpCreatedtime(String cpCreatedtime) {
		this.cpCreatedtime = cpCreatedtime;
	}
	public String getCpModifiedtime() {
		return cpModifiedtime;
	}
	public void setCpModifiedtime(String cpModifiedtime) {
		this.cpModifiedtime = cpModifiedtime;
	}
	public void setCptel(String cptel) {
		this.cptel = cptel;
	}
	@Override
	public String toString() {
		return "campzone [idx=" + idx + ", cpname=" + cpname + ", lat=" + lat + ", lng=" + lng + ", addr=" + addr
				+ ", cptel=" + cptel + ", cpLineIntro=" + cpLineIntro + ", cpIntro=" + cpIntro + ", cpAllar=" + cpAllar
				+ ", cpInsrncAt=" + cpInsrncAt + ", cpTrsagntNo=" + cpTrsagntNo + ", cpBizrno=" + cpBizrno
				+ ", cpFacltDivNm=" + cpFacltDivNm + ", cpMangeDivNm=" + cpMangeDivNm + ", cpMgcDiv=" + cpMgcDiv
				+ ", cpManageSttus=" + cpManageSttus + ", cpHvofBgnde=" + cpHvofBgnde + ", cpHvofEnddle=" + cpHvofEnddle
				+ ", cpFeatureNm=" + cpFeatureNm + ", cpInduty=" + cpInduty + ", cpLctCl=" + cpLctCl + ", cpDoNm="
				+ cpDoNm + ", cpSigunguNm=" + cpSigunguNm + ", cpZipcode=" + cpZipcode + ", cpAddr2=" + cpAddr2
				+ ", cpDirection=" + cpDirection + ", cpHomepage=" + cpHomepage + ", cpResveUrl=" + cpResveUrl
				+ ", cpResveCl=" + cpResveCl + ", cpManageNmpr=" + cpManageNmpr + ", cpGnrlSiteCo=" + cpGnrlSiteCo
				+ ", cpAutoSiteCo=" + cpAutoSiteCo + ", cpGlampSiteCo=" + cpGlampSiteCo + ", cpCaravSiteCo="
				+ cpCaravSiteCo + ", cpIndvdlCaravSiteCo=" + cpIndvdlCaravSiteCo + ", cpSitedStnc=" + cpSitedStnc
				+ ", cpSiteMg1Width=" + cpSiteMg1Width + ", cpSiteMg2Width=" + cpSiteMg2Width + ", cpSiteMg3Width="
				+ cpSiteMg3Width + ", cpSiteMg1Vrticl=" + cpSiteMg1Vrticl + ", cpSiteMg2Vrticl=" + cpSiteMg2Vrticl
				+ ", cpSiteMg3Vrticl=" + cpSiteMg3Vrticl + ", cpSiteMg1Co=" + cpSiteMg1Co + ", cpSiteMg2Co="
				+ cpSiteMg2Co + ", cpSiteMg3Co=" + cpSiteMg3Co + ", cpSiteBottomCl1=" + cpSiteBottomCl1
				+ ", cpSiteBottomCl2=" + cpSiteBottomCl2 + ", cpSiteBottomCl3=" + cpSiteBottomCl3 + ", cpSiteBottomCl4="
				+ cpSiteBottomCl4 + ", cpSiteBottomCl5=" + cpSiteBottomCl5 + ", cpTooltip=" + cpTooltip
				+ ", cpGlampInnerFclty=" + cpGlampInnerFclty + ", cpCaravInnerFclty=" + cpCaravInnerFclty
				+ ", cpPrmisnDe=" + cpPrmisnDe + ", cpOperPdCl=" + cpOperPdCl + ", cpOperDeCl=" + cpOperDeCl
				+ ", cpTrlerAcmpnyAt=" + cpTrlerAcmpnyAt + ", cpCaravAcmpnyAt=" + cpCaravAcmpnyAt + ", cpToiletCo="
				+ cpToiletCo + ", cpSwrmCo=" + cpSwrmCo + ", cpWtrplCo=" + cpWtrplCo + ", cpBrazierCl=" + cpBrazierCl
				+ ", cpSbrsCl=" + cpSbrsCl + ", cpSbrsEtc=" + cpSbrsEtc + ", cpPosblFcltyCl=" + cpPosblFcltyCl
				+ ", cpPosblFcltyEtc=" + cpPosblFcltyEtc + ", cpClturEventAt=" + cpClturEventAt + ", cpClturEvent="
				+ cpClturEvent + ", cpExprnProgrmAt=" + cpExprnProgrmAt + ", cpExprnProgrm=" + cpExprnProgrm
				+ ", cpExtshrCo=" + cpExtshrCo + ", cpFrprvtWrppCo=" + cpFrprvtWrppCo + ", cpFrprvtSandCo="
				+ cpFrprvtSandCo + ", cpFireSensorCo=" + cpFireSensorCo + ", cpThemaEnvrnCl=" + cpThemaEnvrnCl
				+ ", cpEqpmnLendCl=" + cpEqpmnLendCl + ", cpAnimalCmgCl=" + cpAnimalCmgCl + ", cpTourEraCl="
				+ cpTourEraCl + ", cpFirstImageUrl=" + cpFirstImageUrl + ", cpCreatedtime=" + cpCreatedtime
				+ ", cpModifiedtime=" + cpModifiedtime + "]";
	}

}
