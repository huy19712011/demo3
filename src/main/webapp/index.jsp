<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
    <script>
        $(document).ready(function () {

            $("#submit").click(function() {
                console.log($("#product_id").val());
                $.ajax({
                    url: 'validateProduct',
                    type: 'POST',
                    data: {product_id: $("#product_id").val(), product_name: $("#product_name").val(), submit: "save"},
                    success: function (responseText){

                        console.log("test: ");
                        console.log(responseText);

                        $("#errMsg").text(responseText);

                        if (responseText != null) {
                            $("#product_id").focus();
                        }
                    },
                    error: function () {
                        console.log("Error...");
                    }
                });
            });


            $("#product_id").change(function() {
                console.log($("#product_id").val());
                $.ajax({
                    url: 'validateProduct',
                    type: 'POST',
                    data: {product_id: $("#product_id").val(), product_name: $("#product_name").val()},
                    success: function (responseText){
                        console.log("Oki");
                        $("#errMsg").text(responseText);

                        if (responseText != null) {
                            $("#product_id").focus();
                        }
                    },
                    error: function () {
                        console.log("Error...");
                    }
                });
                console.log("running...");
            });

        });


    </script>
    <script src="js/student.js"></script>
</head>
<body>
<h1><%= "Hello World!" %>
    <form action="filterInput">
        <input type="text" name="name">
        <br>
        <input type="text" name="password">
        <br>
        <button type="submit">Submit</button>
    </form>
    <br>
    <h3>Validate using Ajax</h3>
    <form action="validateProduct">
        Product Id<input type="text" name="product_id" id="product_id"><span id="errMsg"></span>
        <br>
        Product Name<input type="text" name="product_name" id="product_name">
        <br>
        <button type="submit" id="submit">Submit</button>
    </form>
    <hr>
    <h3>Post new Student using Ajax</h3>
    <form id="">
        <div id="responseDiv"></div>
        <div>
            <label>Id</label>
            <input type="text" id="student_id" name="student_id">
        </div>
        <div>
            <label>Name</label>
            <input type="text" id="student_name" name="student_name">
        </div>
        <div>
            <label>Email</label>
            <input type="text" id="student_email" name="student_email">
        </div>
        <div>
            <label>Phone</label>
            <input type="text" id="student_phone" name="student_phone">
        </div>
        <div>
            <button type="button" onclick="saveStudent()">Add</button>
            <button type="button" onclick="showStudents()">List of Students</button>
        </div>
    </form>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
</body>
</html>
