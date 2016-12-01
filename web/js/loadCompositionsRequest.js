/**
 * Created by Anastasia_Paramonova on 30.11.2016.
 */
$(document).ready(
    function () {
        $contentLoadTriggered = false;
        $(document).scroll(
            function () {

                if( ($(window).scrollTop() + screen.height > $('body').height() ) && $contentLoadTriggered == false){
                    $contentLoadTriggered = true;
                    $.ajax(
                        {
                            type: 'POST',
                            data: {action:"LOADCOMPOSITIONS"},
                            url: '/WriteYourBest.do',
                            success: function (result) {
                                $("#compositions-wrapper").append(result);
                                $contentLoadTriggered = false;
                            }
                        }
                    );
                }
            }
        );
    }
);