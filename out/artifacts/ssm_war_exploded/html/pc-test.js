$(function() {
    $(window).load(function() {
        $.ajax({
            url:'ajaxPC',
            type:"get",
            dataType:'json',
            async:true,
            success:function(data){
                    $.each(data, function(key) {
                        switch(key){
                            case uersname:
                                result = data[key];
                                $('#1').after(result);
                            case school:
                                result = data[key];
                                $('#2').after(result);
                            case birth:
                                result = data[key];
                                $('#3').after(result);
                            case college:
                                result = data[key];
                                $('#4').after(result);
                            case tele:
                                result = data[key];
                                $('#5').after(result);
                            case post:
                                result = data[key];
                                $('#6').after(result);
                            case email:
                                result = data[key];
                                $('#7').after(result);

                        }
                    }); 
                },
        });
    });
});


