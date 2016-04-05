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
                <option name="1" value="1">1</option>
                <option name="2" value="2">2</option>
                <option name="3" value="3">3</option>
                <option name="4" value="4">4</option>
                <option name="5" value="5">5</option>
                <option name="6" value="6">6</option>
                <option name="7" value="7">7</option>
                <option name="8" value="8">8</option>
            </select>
            <select name="language">
                <option name="English" value="1">Nederlands</option>
                <option name="Nederlands"value="2">English</option>
            </select>

            <button type="submit" class="btn btn-default" ID="btnlang" >
                add to db
            </button>
    </form>

</div>
</div>