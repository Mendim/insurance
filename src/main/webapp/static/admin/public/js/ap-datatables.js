function apDatatablesInit(tableId){
    $("#"+tableId).dataTable({
        bAutoWidth: false,
        language: { aria: {},
            emptyTable: '',
            zeroRecords: ''
        },
        buttons: [],
        responsive: {
            details: {}
        },
        order: [],
        lengthMenu: [],
        dom: ""
    });

    $('.dataTables_empty').attr('colspan', 0);
}