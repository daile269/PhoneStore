
$( document ).ready(function() {
    $('table #editBtn').on('click',function (event){
        event.preventDefault();
        var href =$(this).attr('href');

        $.get(href,function (item,status){
            $('#editId').val(item.id);
            $('#editName').val(item.name);
            $('#editAddress').val(item.address);
            $('#editDescription').val(item.description);
            $('#editEmail').val(item.email);
        })
        $('#editModal').modal('show');
    })
    $('table #detailsBtn').on('click',function (event){
        event.preventDefault();
        var href =$(this).attr('href');

        $.get(href,function (item,status){
            $('#detailsId').val(item.id);
            $('#detailsName').val(item.name);
            $('#detailsAddress').val(item.address);
            $('#detailsDescription').val(item.description);
            $('#detailsEmail').val(item.email);
        })
        $('#detailsModal').modal('show');
    })
    $('table #deleteBtn').on('click',function (event){
        event.preventDefault();

        var href = $(this).attr('href');

        $('#confirmDeleteBtn').attr('href',href);
        $('#deleteModal').modal('show');

    })
    $('#search-btn').on('click',function (event){
        var keyword = $('#input_keyword').val();
        window.location.href = "/admin/api/v1/supplier/"+keyword+"?page="+1+"&limit=7&message=";
    })

});
