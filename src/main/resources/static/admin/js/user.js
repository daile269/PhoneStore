
$( document ).ready(function() {
    $('table #editBtn').on('click',function (event){
        event.preventDefault();
        var href =$(this).attr('href');

        $.get(href,function (item,status){
            $('#editId').val(item.id);
            $('#editName').val(item.name);
            $('#editUsername').val(item.username);
            $('#editPassword').val(item.password);
            $('#editAddress').val(item.address);
            $('#editEmail').val(item.email);
            $('#editPhone').val(item.phone);
            $('#editRole').val(item.role);
            $('#editUrlAvatar').val(item.urlAvatar);
            $('#editDateOfBirth').val(item.dateOfBirth.substr(0,10).replace("T", " "));
            if(item.status){
                $('#editStatus').val("true");
            }else $('#editStatus').val("false");

        })
        $('#editModal').modal('show');
    })
    $('table #detailsBtn').on('click',function (event){
        event.preventDefault();
        var href =$(this).attr('href');

        $.get(href,function (item,status){
            $('#detailsId').val(item.id);
            $('#detailsName').val(item.name);
            $('#detailsUsername').val(item.username);
            $('#detailsAddress').val(item.address);
            $('#detailsEmail').val(item.email);
            $('#detailsPhone').val(item.phone);
            $('#detailsRole').val(item.role);
            $('#detailsDateOfBirth').val(item.dateOfBirth.substr(0,10).replace("T", " "));
            if(item.status){
                $('#detailsStatus').val("Active");
            }else $('#detailsStatus').val("Banner");
        })
        $('#detailsModal').modal('show');
    })
    $('table #deleteBtn').on('click',function (event){
        event.preventDefault();

        var href = $(this).attr('href');

        $('#confirmDeleteBtn').attr('href',href);
        $('#deleteModal').modal('show');

    })
    $('table #updateImageBtn').on('click',function (event){
        event.preventDefault();
        var href = $(this).attr('href');

        $('#frmUpdateImg').attr('action',href);
        $('#updateAvatar').modal('show');

    })
    $('.table #photoButton').on('click',function(event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#photoModal #productPhoto').attr('src', href);
        $('#photoModal').modal('show');
    });

});
