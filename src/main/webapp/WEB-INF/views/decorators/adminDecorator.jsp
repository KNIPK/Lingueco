<html>
 <head>
 <title><sitemesh:write property='title'/></title>
 <sitemesh:write property='head'/>
 </head>
 
 <body>
 
 <jsp:include page="../include/header.jsp" />
 This is the admin body in decorator:
 <sitemesh:write property='body'/>
 
  <jsp:include page="../include/footer.jsp" />
 </body>
</html>