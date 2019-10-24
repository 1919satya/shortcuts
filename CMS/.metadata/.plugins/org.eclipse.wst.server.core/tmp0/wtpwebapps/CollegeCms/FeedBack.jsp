<%@page import="com.hcl.cms.HibernateUtil"%>
<%@page import="com.hcl.cms.SubjectCrud"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
SubjectCrud s=new SubjectCrud();
String fid=s.feedBackId();
session.setAttribute("fid", fid);

%>
    
     <form method="get" name="frmFeedback" action="FeedBack.jsp">
     <center>
     Feedback ID :
     <input type="text" name="fid" value=<%=fid %> /> <br/><br/>
     Student Name :
     <input type="text" name="studentName" /> <br/><br/>
     Instructor :
<select name="instructor" onchange="change();">
<option value="please select" >please select</option>
<%
List<String> lstSubjects=new SubjectCrud().showInstructor();
for(String ins :lstSubjects){

%>
<option value="<%=ins %>" > <%=ins %></option>
<% 
}
%>
</select> <br/><br/>
<%

session.setAttribute("studentName", request.getParameter("studentName"));
session.setAttribute("instructor", request.getParameter("instructor"));


%>
</form>

<form method="get" action="ConfirmFeedback.jsp">
  Select Subject :
<select name="subject">
<option value="please select" >please select</option>
<%
String instr=request.getParameter("instructor");

session.setAttribute("instructor", instr);
List<String>lst1= new SubjectCrud().showSubject(instr);
for(String obj1 : lst1){
%>
<option value="<%=obj1 %>" ><%=obj1 %></option>
<%}%>
</select> <br/><br/>
   <p>Please give your Feedback :</p> <br/>
      Feed:
       <input type="radio" name="feed" value="excellent">Excellent</input> &nbsp;&nbsp;
       <input type="radio" name="feed" value="good" >Good</input> &nbsp;&nbsp;
       <input type="radio" name="feed" value="adequate" >Adequate</input> &nbsp;&nbsp;
       <input type="radio" name="feed" value="ok" >InAdequate</input>  &nbsp;&nbsp;
       <input type="submit" value="Confirm" /> <br/><br/>
</form>

<script>
       function change(){
          document.frmFeedback.submit();
       }
   </script>
   
</body>
</html>