<%-- 
    Document   : projectinterface
    Created on : 5-apr-2016, 15:27:48
    Author     : markb
--%>

<%@tag description="The project interface" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="project" fragment="true" %>

<%-- any content can be specified here e.g.: --%>
<div class="container-fluid">
   <div class = "panel panel-primary">
   <div class = "panel-heading">
      <h3 class = "panel-title">Project management</h3>
   </div>
       <div class = "panel-body">
    <button id="btnAdd" type="button" class="btn btn-sm">Add Row</button>
    <br><br>
    <table id="grid">
    </table>
    <div id="editprojectdialog" style="display:none">
        <input type="hidden" id="ID">
        <form>
            <div class="form-group">
                <label for="Name">Name</label>
                <input type="text" class="form-control" id="Name">
            </div>
            <div class="form-group">
                <label for="Date">Creation Date</label>
                <input type="text" class="form-control" id="Date">
            </div>
            <button type="button" id="btnSave" class="btn btn-default">Save</button>
            <button type="button" id="btnCancel" class="btn btn-default">Cancel</button>
        </form>
    </div>
    <br>
    <button id="btnsubmitprojectchanges" type="button" class="btn btn-default"> Submit Project Changes</button> 
    </div>
   </div>
    <script>$(document).ready(function () {
    var data, grid, dialog;
    data = [
        { 'ID': 1, 'Name': 'Klassenfoto', 'Date': '31-01-2011' },
        { 'ID': 2, 'Name': 'Trouwfotos henk', 'Date': '31-01-2011' },
        { 'ID': 3, 'Name': 'Dank Memes', 'Date': '31-01-2011' },
    ];
    dialog = $('#editprojectdialog').dialog({
        title: 'Add/Edit Record',
        autoOpen: false,
        resizable: false,
        modal: true
    });
    function Edit(e) {
        $('#ID').val(e.data.id);
        $('#Name').val(e.data.record.Name);
        $('#Date').val(e.data.record.Date);
        $('#editprojectdialog').dialog('open');
    }
    function Delete(e) {
        if (confirm('Are you sure?')) {
            grid.removeRow(e.data.id);
        }
    }
    function Save() {
        if ($('#ID').val()) {
            var id = parseInt($('#ID').val());
            grid.updateRow(id, { 'ID': id, 'Name': $('#Name').val(), 'Date': $('#Date').val() });
        } else {
            grid.addRow({ 'ID': grid.count() + 1, 'Name': $('#Name').val(), 'Date': $('#Date').val() });
        }
        dialog.close();
    }
    grid = $('#grid').grid({
        dataSource: data,
        columns: [
            
            { field: 'ID', width: 32 },
            { field: 'Name' },
            { field: 'Date', title: 'Creation Date' },
            { width: 50, tmpl: '<a href="#">edit</a>', align: 'center', events: { 'click': Edit } },
            { width: 50, tmpl: '<a href="#">delete</a>', align: 'center', events: { 'click': Delete } }
        ]
    });
    $('#btnAdd').on('click', function () {
        $('#ID').val('');
        $('#Name').val('');
        $('#Date').val('');
        $('#editprojectdialog').dialog('open');
    });
    $('#btnSave').on('click', Save);
    $('#btnCancel').on('click', dialog.cancel);
});</script>
    <div class = "panel panel-primary">
       <div class = "panel-body">
    <div class="well well-sm">
        <strong>Foto's within this project</strong>
        <div class="btn-group">
            <a href="#" id="list" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-th-list">
            </span>List</a> <a href="#" id="grid" class="btn btn-default btn-sm"><span
                class="glyphicon glyphicon-th"></span>Grid</a>
        </div>
    </div>
    <div id="products" class="row list-group">
<div class="item  col-xs-4 col-lg-4">
            <div class="thumbnail">
                <img class="group list-group-image" src="http://placehold.it/400x250/000/fff" alt="" />
                <div class="caption">
                    <h4 class="group inner list-group-item-heading">
                        Product title</h4>
                                            <div class="col-xs-6 col-md-6">
                            <a class="btn btn-sm" href="#">Edit info</a>
                        </div>
                                                <div class="col-xs-6 col-md-6">
                            <a class="btn btn-sm" href="#">Delete this picture</a>
                        </div>
                    <br/>
                    <p class="group inner list-group-item-text">
                        Product description... Lorem ipsum dolor sit amet, consectetuer adipiscing elit,
                        sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.</p>
                    <div class="row">
                        <div class="col-xs-12 col-md-6">
                            <p class="lead">
                                $21.000</p>
                        </div>
                        <div class="col-xs-12 col-md-6">
                            <a class="btn btn-success" href="#">Add to cart</a>
                        </div>
                        <br>
                        </br>

                    </div>
                </div>
            </div>
        </div>
        <div class="item  col-xs-4 col-lg-4">
            <div class="thumbnail">
                <img class="group list-group-image" src="http://placehold.it/400x250/000/fff" alt="" />
                <div class="caption">
                    <h4 class="group inner list-group-item-heading">
                        Product title</h4>
                                            <div class="col-xs-6 col-md-6">
                            <a class="btn btn-sm" href="#">Edit info</a>
                        </div>
                                                <div class="col-xs-6 col-md-6">
                            <a class="btn btn-sm" href="#">Delete this picture</a>
                        </div>
                    <p class="group inner list-group-item-text">
                        Product description... Lorem ipsum dolor sit amet, consectetuer adipiscing elit,
                        sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.</p>
                    <div class="row">
                        <div class="col-xs-12 col-md-6">
                            <p class="lead">
                                $21.000</p>
                        </div>
                        <div class="col-xs-12 col-md-6">
                            <a class="btn btn-success" href="#">Add to cart</a>
                        </div>
                        <br>
                        </br>

                    </div>
                </div>
            </div>
        </div>
        <div class="item  col-xs-4 col-lg-4">
            <div class="thumbnail">
                <img class="group list-group-image" src="http://placehold.it/400x250/000/fff" alt="" />
                <div class="caption">
                    <h4 class="group inner list-group-item-heading">
                        Product title</h4>
                                            <div class="col-xs-6 col-md-6">
                            <a class="btn btn-sm" href="#">Edit info</a>
                        </div>
                                                <div class="col-xs-6 col-md-6">
                            <a class="btn btn-sm" href="#">Delete this picture</a>
                        </div>
                    <p class="group inner list-group-item-text">
                        Product description... Lorem ipsum dolor sit amet, consectetuer adipiscing elit,
                        sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.</p>
                    <div class="row">
                        <div class="col-xs-12 col-md-6">
                            <p class="lead">
                                $21.000</p>
                        </div>
                        <div class="col-xs-12 col-md-6">
                            <a class="btn btn-success" href="#">Add to cart</a>
                        </div>
                        <br>
                        </br>

                    </div>
                </div>
            </div>
        </div>
        <div class="item  col-xs-4 col-lg-4">
            <div class="thumbnail">
                <img class="group list-group-image" src="http://placehold.it/400x250/000/fff" alt="" />
                <div class="caption">
                    <h4 class="group inner list-group-item-heading">
                        Product title</h4>
                                            <div class="col-xs-6 col-md-6">
                            <a class="btn btn-sm" href="#">Edit info</a>
                        </div>
                                                <div class="col-xs-6 col-md-6">
                            <a class="btn btn-sm" href="#">Delete this picture</a>
                        </div>
                    <p class="group inner list-group-item-text">
                        Product description... Lorem ipsum dolor sit amet, consectetuer adipiscing elit,
                        sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.</p>
                    <div class="row">
                        <div class="col-xs-12 col-md-6">
                            <p class="lead">
                                $21.000</p>
                        </div>
                        <div class="col-xs-12 col-md-6">
                            <a class="btn btn-success" href="#">Add to cart</a>
                        </div>
                        <br>
                        </br>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
        <script>
        $(document).ready(function() {
    $('#list').click(function(event){event.preventDefault();$('#products .item').addClass('list-group-item');});
    $('#grid').click(function(event){event.preventDefault();$('#products .item').removeClass('list-group-item');$('#products .item').addClass('grid-group-item');});
});</script>
    
    
    </div>
</div>
</div>