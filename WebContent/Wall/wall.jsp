<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.myapp.entity.StatusEntity"%>
<%@page import="com.myapp.entity.ProfileEntity"%>
<%@page import="org.apache.commons.io.IOUtils"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page trimDirectiveWhitespaces="true"%>
<jsp:include page="wall_header.jsp" />
<jsp:useBean id="entity" class="com.myapp.entity.ProfileEntity"
	scope="request">
</jsp:useBean>
<jsp:useBean id="friend_entity"
	class="com.myapp.entity.FriendRequestEntity" scope="request">
</jsp:useBean>
<jsp:useBean id="follow_entity" class="com.myapp.entity.FollowEntity"
	scope="request">
</jsp:useBean>

<div class="row mycard">
	<div class="col-md-4"></div>
	<div class="col-md-4 col-sm-6 ">
		<div class="card-container">
			<div class="card">
				<div class="front text-center">
					<div class="cover">
						<!--  <img src="images/rotating_card_thumb2.png" />-->
					</div>
					<div class="user">
						<%
						if(entity!=null) {
						byte[] bytes = IOUtils.toByteArray(entity.getPicture());
						String picture=new String(Base64.encodeBase64(bytes));
						
						
						%>
						<img class="img-circle" src="data:image/jpg;base64,<%=picture %>"/>
						<%} %>
					</div>
					<div class="content">
						<div class="main">
							<h3 class="name"><jsp:getProperty property="full_name"
									name="entity" /></h3>
							<p class="profession"><jsp:getProperty property="occupation"
									name="entity" /></p>
							<h5>
								<i class="fa fa-map-marker fa-fw text-muted"></i><jsp:getProperty
									property="current_city" name="entity" />
							</h5>
							<h5>
								<i class="fa fa-building-o fa-fw text-muted"></i>
								<jsp:getProperty property="dob" name="entity" />
							</h5>
							<h5>
								<i class="fa fa-envelope-o fa-fw text-muted"></i> <span
									id="person"><jsp:getProperty property="user_email"
										name="entity" /></span>
							</h5>
						</div>
						<div class="footer">
							<div class="rating">
								<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
									class="fa fa-star"></i> <i class="fa fa-star"></i>
							</div>
						</div>
					</div>
				</div>
				<!-- end front panel -->
				<div class="back">
					<div class="header">
						<h5 class="motto">"To be or not to be, this is my awesome
							motto!"</h5>
					</div>
					<div class="content">
						<div class="main">
							<h4 class="text-center">Experince</h4>
							<p><jsp:getProperty property="experience"
										name="entity" /></p>
							<h4 class="text-center">Areas of Expertise</h4>
							<p><jsp:getProperty property="interest"
										name="entity" /></p>
						</div>
					</div>
					<div class="footer">
						<!--  <div class="social-links text-center">
							<a href="http://cretive-tim.com" class="facebook"><i
								class="fa fa-facebook fa-fw"></i></a> <a
								href="http://cretive-tim.com" class="google"><i
								class="fa fa-google-plus fa-fw"></i></a> <a
								href="http://cretive-tim.com" class="twitter"><i
								class="fa fa-twitter fa-fw"></i></a>
						</div>-->
					</div>
				</div>
				<!-- end back panel -->
			</div>
			<!-- end card -->
		</div>
		<div id="user" hidden><%=session.getAttribute("user")%></div>
		<div class="row" style="text-align: center">
			<%
				if (!((String) session.getAttribute("user")).equals(entity
						.getUser_email())) {
			%>
			<%
				if ((friend_entity.getConfirm() == null) && request.getAttribute("isFriend")==null) {
			%>
			<button type="button" id="friend" class="btn btn-success"
				onclick='friendRequest();'>Add Friend</button>
			<%
				} else if ((friend_entity.getConfirm()!=null)) {
			%>

			<button type="button" id="UnFriend" class="btn btn-success"
				onclick="friendRequestDelete();">Friend Request Sent</button>
			<%
				} else if((Boolean)request.getAttribute("isFriend")==true){
			%>
			<button type="button" id="" class="btn btn-success" onclick="Unfriend()">Friends</button>
			<%
				}else{
					
			%>
						<button type="button" id="friend" class="btn btn-success"
				onclick='friendRequest();'>Add Friend</button>
			<%
				} 
			%>
			<%
				if ((entity.getUser_email()).equals(follow_entity
							.getFollowing())) {
			%>
			<button type="button" class="btn btn-success" id=""
				onclick="Unfollow();">UnFollow</button>
			<%
				} else {
			%>

			<button type="button" class="btn btn-success" id="follow"
				onclick="follow();">Following</button>
			<%
				}
			%>
			<button type="button" class="btn btn-success" data-target="#Modal"
				data-toggle="modal" onclick="forcePopUp()">Message</button>
			<%
				}
			%>
		</div>
		<!-- end card-container -->
	</div>
	<!-- end col sm 3 -->
	<div class="col-md-4"></div>
</div>
</div>

<div class="row">
	<div id="personal_frame" style="">
		<div class="wall_frame1"  id="frame1">
			<div class="form-group ">
				<label for="comment1" style="margin-left: 40px">Update
					Status:</label>
				<textarea class="form-control" rows="2" id="comment1" required></textarea>
			</div>
			<div class="col-md-7 ">
				<div class="form-group ">
					<div class="btn-group">
						<button type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-map-marker"></i> Location
						</button>
						<button type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-picture"></i> Photo
						</button>
					</div>
				</div>
			</div>
			<button type="button" class="btn btn-default"
				onclick="status_prepend()" Style="float: right;">
				<i class="glyphicon glyphicon-send"></i> Submit
			</button>
		</div>
		<br>
		<div id="status_append">
			<%
				if (request.getAttribute("status_entity") != null) {
					List<HashMap<String, Object>> list_data = (List<HashMap<String, Object>>) request
							.getAttribute("status_entity");
					for (Map map : list_data) {
						byte[] bytes=null;
						String picture=null;
						StatusEntity status = (StatusEntity) map.get("status");
						ProfileEntity sender = (ProfileEntity) map.get("sender");
						bytes = IOUtils.toByteArray(sender.getPicture());
						picture=new String(Base64.encodeBase64(bytes));
			%>
			<div class='status'>
				<%if(((String)(request.getSession().getAttribute("user"))).equals(entity.getUser_email())){ %>
				<a href="DeleteStatus?status=<%=status.getStatus_id()%>"><i class='glyphicon glyphicon-trash' style='float: right;'></i></a> 
				<%} %>
				<a href="profile/<%=sender.getUsername() %>"><span><!--<img src="data:image/jpg;base64,<%=picture %>"
			class="img-circle img-responsive" id="circular-image"
			alt="the-brains">-->
		<h3 id="sender_name"><%=sender.getFull_name() %></h3></a>
			<p id="message_content"><%=status.getContent()%></p></span>
				<!---->
			</div>
			<%
				}
				}
			%>
		</div>
	</div>
	<!-- <div class="wall_frame1" id="frame1">
		<div class="form-group">
			<label for="comment1">Update Status:</label>
			<textarea class="form-control" rows="5" id="comment1"></textarea>
		</div>
		<button type="button" class="btn btn-success"
			onclick="status_prepend()">Success</button>
	</div>
	<div class="wall_frame2" id="frame2">

		<div class="form-group">
			<label for="comment2">Update Status:</label>
			<textarea class="form-control" rows="5" id="comment2"></textarea>
		</div>
		<button type="button" class="btn btn-success"
			onclick="status_prepend()">Success</button>
	</div>-->

</div>

<jsp:include page="wall_footer.jsp" />