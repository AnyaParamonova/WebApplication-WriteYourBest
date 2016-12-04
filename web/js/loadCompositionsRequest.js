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
                            data: {action:"LOADCOMPOSITIONS", size: "5"},
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

        $(".close").click(
            function () {
                $id = $(this).val();
                $.ajax(
                    {
                        type: 'POST',
                        data: {action:"DELETECOMPOSITION", compositionId: $id},
                        url: '/WriteYourBest.do',
                        success: function (result) {
                            $elem_id = "comp_" + $id;
                            document.getElementById($elem_id).remove();
                        }
                    });
                if($contentLoadTriggered == false){
                    $contentLoadTriggered = true;
                    $.ajax(
                        {
                            type: 'POST',
                            data: {action:"LOADCOMPOSITIONS", size: "1"},
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