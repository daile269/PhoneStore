
$( document ).ready(function() {
    $('table #editBtn').on('click',function (event){
        event.preventDefault();
        var href =$(this).attr('href');

        $.get(href,function (item,status){
            $('#editId').val(item.id);
            $('#editName').val(item.name);
            $('#editBattery').val(item.battery);
            $('#editCategory').val(item.categoryId);
            $('#editColor').val(item.color);
            $('#editCpu').val(item.cpu);
            $('#editDescription').val(item.description);
            $('#editDiscount').val(item.discount);
            $('#editPrice').val(item.price);
            $('#editRam').val(item.ram);
            $('#editImage').val(item.image);
            $('#editScreen').val(item.screen);
            $('#editStockNumber').val(item.stockNumber);
            $('#editSaleNumber').val(item.saleNumber);
            $('#editSupplier').val(item.supplierId);
            $('#editDateOfManufacture').val(item.dateOfManufacture.substr(0,10).replace("T", " "));

        })
        $('#editModal').modal('show');
    })
    $('table #detailsBtn').on('click',function (event){
        event.preventDefault();
        var href =$(this).attr('href');

        $.get(href,function (item,status){
            $('#detailsId').val(item.id);
            $('#detailsName').val(item.name);
            $('#detailsBattery').val(item.battery);
            $('#detailsCategory').val(item.categoryId);
            $('#detailsCpu').val(item.cpu);
            $('#detailsDescription').val(item.description);
            $('#detailsColor').val(item.color);
            $('#detailsDiscount').val(item.discount);
            $('#detailsPrice').val(item.price);
            $('#detailsRam').val(item.ram);
            $('#detailsScreen').val(item.screen);
            $('#detailsStockNumber').val(item.stockNumber);
            $('#detailsSaleNumber').val(item.saleNumber);
            $('#detailsSupplier').val(item.supplierId);
            $('#detailsDateOfManufacture').val(item.dateOfManufacture.substr(0,10).replace("T", " "));
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
        $('#updateImageProduct').modal('show');

    })
    $('.table #photoButton').on('click',function(event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#photoModal #productPhoto').attr('src', href);
        $('#photoModal').modal('show');
    });

});
