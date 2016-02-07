<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.myapp.entity.MessageEntity"%>
<%@page import="com.myapp.entity.ProfileEntity"%>
<%@page import="org.apache.commons.io.IOUtils"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<jsp:include page="message_header.jsp" />
<div class="box9" id="left_box" style="">
	<h1 class="text-center">Messages</h1>

	<%
		List<HashMap<String, Object>> list = (List<HashMap<String, Object>>) request
				.getAttribute("list");
		if (list != null) {
			for (HashMap<String, Object> data : list) {
				byte[] bytes = null;
				String picture = null;
				MessageEntity message = (MessageEntity) data.get("message");
				ProfileEntity sender = (ProfileEntity) data.get("sender");
				bytes = IOUtils.toByteArray(sender.getPicture());
				picture=new String(Base64.encodeBase64(bytes));
	%>
	<div id="message"
		onclick="show('<%=sender.getFull_name()%>','<%=message.getContent()%>','<%=picture%>')">
		<span><img src="data:image/jpg;base64,<%=picture %>"
			class="img-circle img-responsive" id="circular-image"
			alt="the-brains">
			<h3 id="sender_name"><%=sender.getFull_name()%></h3>
			<p id="message_content"><%=(message.getContent()).substring(0, 10)%>...
			</p></span>
	</div>
	<br />
	<%
		}
		}
	%>

</div>

<div class="box_right text-center" id="right_box" style="">

	<div class="text-center" style="display: block;">
		<img src="" class="text-center img-circle img-responsive"
			id="sender-image" alt="">
		<h1 id="messenger"></h1>
	</div>
	<p id="message_data"></p>

	<br />
</div>
<jsp:include page="message_footer.jsp" />