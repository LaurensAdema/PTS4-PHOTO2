<%@ include file="index.jsp" %>
<div class="input-group">
    <form role="form" class="form-group" ID="language" action="LanguageConfigServlet" method="post">
        <div class="form-group">

            <label for="tbelement">
                Element name
            </label>
            <input type="text" class="form-control" name="tbelname" placeholder="name of element ex. lbl_username"/><br>
            <input type="text" class="form-control" name="tbelvalue" placeholder="value of element so : Username"/>
            <select name="page">
                <option name="master" value="1">master</option>
                <option name="index" value="2">index</option>
            </select>
            <select name="language">
                <option name="English" value="1">English</option>
                <option name="Nederlands"value="2">Nederlands</option>
            </select>

            <button type="submit" class="btn btn-default" ID="btnlang" >
                add to db
            </button>
    </form>

</div>
</div>