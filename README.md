I have build this using:JSP,Servlet,DDD and Maven as dependency manager.
Features:
      Login
      Signup
      Verify email address
      Reset password
      Create Profile
      Add Friends
      Search Friends and access their profile
      Send messages
      Send wall post on anybody wall or yours
      Friend request Notifications
      Read Messages
      Logout
If you want to try this out then please change Username and Password in src/myapp/Mailer/VerifyMail.java
(That would be used as sender email id and password for all mail to users while verifying or reseting password).

Used encryption/decryption to generate token for process of Resetpassword and verify mail.

Added SQL as well so that you can directly use this app.

*I have focused on backend more.Didn't give too much time for frontend and validations.
