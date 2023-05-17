package camping.dto;

public class BoardDTO {

 private int num;   //게시글 고유번호
 private String name;  //작성자
 private String pwd; //비밀번호
 private String email;  //이메일
 private String subject; //제목
 private String content; //내용
 private String ipAddr; //IP주소
 private String created; //작성일
 private int hitCount; //조회수
public int getNum() {
	return num;
}
public void setNum(int num) {
	this.num = num;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPwd() {
	return pwd;
}
public void setPwd(String pwd) {
	this.pwd = pwd;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getSubject() {
	return subject;
}
public void setSubject(String subject) {
	this.subject = subject;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getIpAddr() {
	return ipAddr;
}
public void setIpAddr(String ipAddr) {
	this.ipAddr = ipAddr;
}
public String getCreated() {
	return created;
}
public void setCreated(String created) {
	this.created = created;
}
public int getHitCount() {
	return hitCount;
}
public void setHitCount(int hitCount) {
	this.hitCount = hitCount;
}


}