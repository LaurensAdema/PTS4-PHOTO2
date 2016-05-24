<%-- 
    Document   : Upload
    Created on : 17-mei-2016, 10:57:36
    Author     : markb
--%>

<%@tag description="The upload tag" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="upload" fragment="true"%>

<%-- any content can be specified here e.g.: --%>
<div class="panel panel-primary col-md-6">
    <div class="panel-heading">
        <h3 class="panel-title">
            File upload
        </h3>
    </div>
    <div class="panel-body">
        <form role="form" class="form form-group" action="UploadDownloadFileServlet" method="post" enctype="multipart/form-data">
            <label for="selectgroup">Select a Group</label>
            <select name="files" id="selectgroup" class="form-control required">
                <optgroup label="Project1">
                    <option value="group1">group1</option>
                    <option value="group2">group2</option>
                </optgroup>
                <optgroup label="Project2">
                    <option value="group3">group3</option>
                    <option value="group4">group4</option>
                </optgroup>
            </select>
            </br>
            <label for="tbfilename">Select File to Upload:</label>
            <input type="file" name="tbfilename" multiple="" class="btn btn-primary" required>
            </br>
            <label for="tbprijs">Add price for all photo's in this group: €  <label>
                    <input type="number" name="tbprijs" step="0.01" min="0.01" max="99" required />
                    <input type="submit" value="Upload" class="btn btn-primary">
                    </form>
                    </div>

                    </div>
