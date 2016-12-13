/**
 * Created by Administrator on 2016/12/11 0011.
 */
$(function () {
    $(document).on("change", "#userAvatar", function (e) {
        imgPreview($(".user-avatar img"), e);
    })

    editor = new wangEditor('userEditor');
    editor.config.lang = wangEditor.langs['en'];
    editor.config.pasteText = true;
    wangEditor.config.printLog = false;
    editor.config.uploadImgUrl = '/img/upload/people';
    editor.config.uploadImgFileName = 'file';
    editor.config.menus = [
        'source',
        'bold',
        'underline',
        'italic',
        'strikethrough',
        'eraser',
        'forecolor',
        'bgcolor',
        'quote',
        'fontfamily',
        'fontsize',
        'head',
        'unorderlist',
        'orderlist',
        'alignleft',
        'aligncenter',
        'alignright',
        'link',
        'unlink',
        'table',
        'img',
        'undo',
        'redo',
        'fullscreen'
    ];

    editor.create();
})