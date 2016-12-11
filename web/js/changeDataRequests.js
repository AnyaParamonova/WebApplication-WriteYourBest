/**
 * Created by Anastasia_Paramonova on 11.12.2016.
 */

$(document).ready(
    function () {
        $('#save-change-submit').click(
            function () {
                var oldPassword = $('#old-password').val();
                var newPassword = $('#new-password').val();
                var newPasswordRep = $('#new-password-rep').val();
                $.ajax(
                    {
                        type: 'POST',
                        data: {action:"CHANGEPASSWORD", oldPassword: oldPassword, newPassword: newPassword, newPasswordRep: newPasswordRep},
                        url: '/WriteYourBest.do',
                        success: function (result) {
                            if(result != ""){
                                $('#error-message-change').html(result);
                            }
                            else {
                                $('#success-message-change').html("Password has been changed.");
                            }
                        }
                    }
                );
            }
        );

        $('#save-edit-submit').click(
            function () {
                var nickname = $('#new-nickname').val();
                var email = $('#new-email').val();
                $.ajax(
                    {
                        type: 'POST',
                        data: {action:"UPDATEDATA", nickname: nickname, email: email},
                        url: '/WriteYourBest.do',
                        success: function (result) {
                            if(result != ""){
                                $('#error-message-edit').html(result);
                            }
                            else {
                                $('#success-message-edit').html("Changes saved.");
                            }
                        }
                    }
                );
            }
        );
    }
);