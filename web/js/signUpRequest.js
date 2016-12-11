/**
 * Created by Anastasia_Paramonova on 28.11.2016.
 */
$(document).ready(
    function () {
        $('#signupSubmit').click(
            function () {
                var nickname = $('#nickname').val();
                var email = $('#email').val();
                var password = $('#password').val();
                var passwordAgain = $('#passwordRep').val();
                $.ajax(
                    {
                        type: 'POST',
                        data: {action:"SIGNUP", nickname: nickname, email: email, password: password, passwordRep: passwordAgain},
                        url: '/WriteYourBest.do',
                        success: function (result) {
                            if(result != ""){
                                $('#errorMessage').html(result);
                            }
                            else {
                                window.location.href = '/WriteYourBest.do?action=DETERMINE';
                            }
                        }
                    }
                );
            }
        );
    }
);