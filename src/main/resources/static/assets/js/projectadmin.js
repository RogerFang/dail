/**
 * Created by Administrator on 2016/12/12 0012.
 */
$(function () {
    $(document).on("change", "#proAvatar", function (e) {
        imgPreview($(".pro-avatar img"), e);
    })

    editor = new wangEditor('proEditor');
    editor.config.lang = wangEditor.langs['en'];
    editor.config.pasteText = true;
    wangEditor.config.printLog = false;
    editor.config.uploadImgUrl = '/img/upload/projects';
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