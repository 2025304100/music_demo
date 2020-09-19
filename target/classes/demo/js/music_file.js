jQuery(function () {
    var $ = jQuery,
        $list = $('#ts'),
        $btn = $('#ctlBtn'),
        state = 'pending',
        uploader;
    uploader = WebUploader.create({
        // 不压缩image
        resize: false,
        method: "POST",
        fileVal: "music_file",
        // swf文件路径
        swf: 'webuploade/dist/Uploader.swf',
        // 文件接收服务端。
        server: '/resource/updata_music',
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#picker',
        accept: {
            title: "music",
            extensions: "mp3",
            mimeTepes: "audio/mp3"
        },
        fileNumLimit: 1
    });

    // 当有文件添加进来的时候
    uploader.on('fileQueued', function (file) {
        $list.append('<tr id="' + file.id + '" class="success">' +
            '<td class="info">' + file.name + '</td>' +
            '<td class="state">等待上传...</td>' +
            '<td class="tm"><div class="progress progress-striped active" > ' +
            '<div class="progress-bar" role="progressbar" style="width:0%">' +
            '</div></td></tr>');
    });
    // 文件上传过程中创建进度条实时显示。
    uploader.on('uploadProgress', function (file, percentage) {
        var $li = $('#' + file.id),
            $percent = $li.find('.progress .progress-bar');
        // 避免重复创建
        if (!$percent.length) {
            $percent = $('<div class="progress progress-striped active">' +
                '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                '</div>' +
                '</div>').appendTo($li).find('.progress-bar');
        }
        $li.find('td.state').text('上传中');
        $percent.css('width', percentage * 100 + '%');
    });
    uploader.on('uploadSuccess', function (file) {
        $('#' + file.id).find('td.state').text('已上传');
    });
    uploader.on('uploadError', function (file) {
        $('#' + file.id).find('td.state').text('上传出错');
    });
    uploader.on('uploadComplete', function (file) {
        $('#' + file.id).find('.progress').fadeOut();
    });
    uploader.on('all', function (type) {
        if (type === 'startUpload') {
            state = 'uploading';
        } else if (type === 'stopUpload') {
            state = 'paused';
        } else if (type === 'uploadFinished') {
            state = 'done';
        }
        if (state === 'uploading') {
            $btn.text('暂停上传');
        } else {
            $btn.text('开始上传');
        }
    });
    $btn.on('click', function () {
        if (state === 'uploading') {
            uploader.stop();
        } else {
            uploader.upload();
        }
    });
});

