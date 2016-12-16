/**
 * Created by Administrator on 2016/12/13 0013.
 */
$(function () {
/*    $(document).on("click",".tag-remove",function () {
        if(confirm("Are you sure you want to delete this tag？")){
            var $ele = $(this);
            $ele.parent(".tag").fadeOut(500,function () {
                $ele.parent(".tag").remove();
            })
        }
    })*/

    $(document).on("click",".tag-edit",function () {
        var $ele = $(this).parent(".tag"),
            $name = $ele.find(".tag-name"),
            $input = $ele.find(".tag-input");
        if($input.is(":hidden")){
            $input.find("input").val($name.text())
            $input.show();
            $name.hide();
        }else{
            $input.hide();
            $name.show();
        }
    })

    $(document).on("click",".tag-confirm",function () {
        var $ele = $(this).parent(".tag-input"),
            $input = $ele.find("input"),
            $parent = $ele.parent(".tag"),
            $name = $parent.find(".tag-name"),
            $url = $parent.parents(".tag-area").attr("data"),
            $id = $parent.attr("data");
        if($input.val().length>0){
            postEdit($url, {"id":$id, "name":$input.val()}, $input.val(), $name, $ele);
            // $name.text($input.val()).show();
            // $ele.hide();
        }else{
            alert("The input content can not be null!");
            /*if(confirm("Are you sure to delete this tag?")){
                $parent.fadeOut(500,function () {
                    $parent.remove();
                })
            }*/
        }
    })

    $(document).on("keydown",".tag-input input",function (e) {
        var $ele = $(this).parent(".tag-input"),
            $input = $(this),
            $parent = $ele.parent(".tag"),
            $name = $parent.find(".tag-name"),
            $url = $parent.parents(".tag-area").attr("data"),
            $id = $parent.attr("data");
        switch(e.keyCode){
            case 13:{
                // e.preventDefault();
                /*
                $name.text($(this).val()).show();
                $input.hide();*/
                postEdit($url, {"id":$id, "name":$input.val()}, $input.val(), $name, $ele);
                break;
            }
            case 8:{
                if($(this).val().length==0){
                    /*if(confirm("Are you sure to delete this tag?")){
                        $parent.fadeOut(500,function () {
                            $parent.remove();
                        })
                    }*/
                    alert("The input content can not be null!");
                }
                break;
            }
        }
    })
    
    function postEdit(url,json, $val, $name, $input) {
        $.post(url, json, function () {
            $name.text($val).show();
            $input.hide();
        });
    }
})