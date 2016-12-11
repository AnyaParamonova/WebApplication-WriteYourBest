$(document).ready(
        function () {
            $('#add-submit').click(
                    function () {
                        var theme = $('#new-theme').val();
                        $.ajax(
                                {
                                    type: 'POST',
                                    data: {action:"ADDTHEME", theme: theme},
                                    url: '/WriteYourBest.do',
                                    success: function (result) {
                                        if(result != ""){
                                            $('#error-message-add').html(result);
                                        }
                                        else {
                                            $('#success-message-add').html("Theme added.");
                                        }
                                    }
                                }
                        );
                    }
            );
        }
);