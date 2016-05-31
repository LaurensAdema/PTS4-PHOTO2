<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="java.util.List"%>
<%@ page import="com.domain.photo.Project"%>
<t:Masterpage>

    <jsp:body>
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        Account Info
                    </h3>
                </div>
                <div class="panel-body">
                    <dl>
                        <dt>
                            Account Name:
                        </dt>
                        <dd>
                            Test
                        </dd>
                        <dt>
                            Account Type:
                        </dt>
                        <dd>
                            Test2
                        </dd>
                        <dt>
                            Some more account info:
                        </dt>
                        <dd>
                            Etiam porta sem malesuada magna mollis euismod.
                        </dd>
                        <dt>
                            Even more account info:
                        </dt>
                        <dd>
                            Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.
                        </dd>
                    </dl>
                </div>

            </div>
        </div>       
    </jsp:body>

</t:Masterpage>
