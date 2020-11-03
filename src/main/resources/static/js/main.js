/**
 *
 */

$(document).ready(); {
    fillTable();
}

function fillTable() {

    fetch('/admin/users').then(
        response => {
            response.json().then(
                data => {
                    let temp = "";
                    data.forEach((aUser) => {
                        temp += "<tr>";
                        temp += "<td>" + aUser.id + "</td>";
                        temp += "<td>" + aUser.firstName + "</td>";
                        temp += "<td>" + aUser.lastName + "</td>";
                        temp += "<td>" + aUser.age + "</td>";
                        temp += "<td>" + aUser.username + "</td>";
                        temp += "<td>" + aUser.password + "</td>";
                        temp += "<td>";
                        aUser.roles.forEach((item) => {
                            temp += item.role + " ";
                        });
                        temp += "</td>";
                        temp += "<td>" +
                            "<a class='btn btn-info' role='button' onclick='fillEditModal(" + aUser.id + ")'  data-toggle='modal' data-target='#editModal'>Edit</a>" +
                            "</td>";
                        temp += "<td>" +
                            "<a class='btn btn-danger' role='button' onclick='fillDeleteModal(" + aUser.id + ")' data-toggle='modal' data-target='#deleteModal'>Delete</a>" +
                            "</td>"
                        temp += "</tr>"

                    })

                    $('#userTable').empty().append(temp);
                });
        });
}

function fillEditModal(id) {

    $.get("/admin/" + id, function (userJSON) {
        $('#id').val(userJSON.id);
        $('#firstName').val(userJSON.firstName);
        $('#lastName').val(userJSON.lastName);
        $('#age').val(userJSON.age);
        $('#username').val(userJSON.username);
        $('#password').val(userJSON.password);
        $('#selectRole').val(userJSON.roles);
    });

}

function fillDeleteModal(id) {

    $.get("/admin/" + id, function (userJSON) {
        $('#id1').val(userJSON.id);
        $('#firstName1').val(userJSON.firstName);
        $('#lastName1').val(userJSON.lastName);
        $('#age1').val(userJSON.age);
        $('#username1').val(userJSON.username);
        $('#password1').val(userJSON.password);
        $('#selectRole1').val(userJSON.roles);
    });
}

$("#buttonSubmitNew").on('click', (event) => {
    event.preventDefault();

    let user = {
        firstName: $("#firstNameNewUser").val(),
        lastName: $("#lastNameNewUser").val(),
        age: $("#ageNewUser").val(),
        username: $("#emailNewUser").val(),
        password: $("#passwordNewUser").val(),
        roleNames: $("#rolesNewUser").val()
    };

    $.ajax({
        url: "/admin",
        type: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(user)
    }).done(() => {

        fillTable();
    })
});

$("#buttonSubmitEdit").on('click', (event) => {
    event.preventDefault();

    let user = {
        id: $("#id").val(),
        firstName: $("#firstName").val(),
        lastName: $("#lastName").val(),
        age: $("#age").val(),
        username: $("#username").val(),
        password: $("#password").val(),
        roleNames: $("#roles").val()
    };

    $.ajax({
        url: "/admin",
        type: "PUT",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(user)
    }).done(() => {

        $('#closeEditModal').click();

        fillTable();
    })
});

$("#buttonSubmitDelete").on('click', (event) => {
    event.preventDefault();

    let id = $('#id1').val();

    $.ajax({
        url: "/admin/" + id,
        type: "DELETE",
        dataType: "text"
    }).done(() => {

        $('#closeDeleteModal').click();

        fillTable();
    })
});