/**
 * Created by Anastasia_Paramonova on 28.11.2016.
 */
$(document).ready(
    function () {
        $('#logIn-submit').click(
            function () {
                var nickname = $('#nickname').val();
                var password = $('#password').val();
                $.ajax(
                    {
                        type: 'POST',
                        data: {action:"LOGIN", nickname: nickname, password: password},
                        url: '/WriteYourBest.do',
                        success: function (result) {
                            if(result != ""){
                                $('#errorMessage').html(result);
                            }
                            else {
                                window.location.href = '/WriteYourBest.do?action=CREATEWALL';
                            }
                        }
                    }
                );
            }
        );
    }
);