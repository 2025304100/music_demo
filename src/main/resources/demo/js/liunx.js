var iq = ["循环", "单曲", "随机"];
var tp_img = {
    'start': '/demo1/css/img/go.png',
    'stop': '/demo1/css/img/20253.png',
    'prepare ': "/demo1/css/img/ds.png",
    'logdin': '/demo1/css/img/ds.png'
}

var music = ['../Benjamin%20Ingrosso%20-%20Do%20You%20Think%20About%20Me.mp3',
    '../蔡健雅 - 红色高跟鞋.mp3',
    "../南征北战NZBZ - 骄傲的少年.mp3",
    "../姚贝娜 - 战争世界.mp3",
    "../刘可 - 寂寞才说爱.mp3",
    "../ATeens - Floorfiller.mp3"

];
new Vue({
    el: ".box",
    data: {
        button0: true,
        vmu: iq,
        index: 0,
        music_index: 0,
        music_is: false,
        audio: null,
        volu: 1

    },
    methods: {
        tomc(index) {
            var mc;
            switch (index) {
                case 1:
                    mc = this.music_index;
                    break;
                case 2:
                    mc = parseInt(Math.random() * music.length);
                    if (mc == index) {
                        tomc(index);
                        return;
                    }
                    break;
            }
            alert(index);
            return mc;
        },
        dom(val, is, to) {

            if (this.audio == null)
                this.audio = new Audio(music[this.music_index]);
            ts = this.music_index + val;
            if (ts >= 0 && ts < music.length && this.index == 0) {
                this.music_index = ts;
            } else if (this.index != 0) {
                this.music_index = this.tomc(this.index);
            } else {
                return;
            }
            if (to) {
                this.audio.pause();
                this.audio = new Audio(music[this.music_index]);
            }
            //预加载声音
            this.audio.volume = this.volu;
            if (is)
                playt(this.audio, this);
            else
                supsent(this.audio);
        },
        ddm(ev) {
            ev = ev || window.event;
            console.log(ev.offsetX, ev.offsetY); //diyi
            var off = ev.offsetX;
            var WI = document.body.clientWidth;
            var ds = (5 * off) / (3 * WI) * 100;
            plan(ds, this.audio);
        },
        ddt(ev) {
            ev = ev || window.event;
            console.log(ev.offsetX, ev.offsetY); //diyi
            var off = ev.offsetX;
            var WI = document.getElementsByClassName("lows")[0].clientWidth;
            var ds = off / WI;
            this.volu = ds;
            Volume_(ds, this.audio);
        },
        //用于切换--可以放到外面--用于实践
        trigger_(audio, this_) {
            audio.onended = function () {
                this_.dom(1, this_.music_is = true, true);
            }
        }
    },
    watch: {}
});

//开始 this_便于调用
function playt(audio, this_) {
    audio.play();
    document.getElementById("play").style = "background-image:url(/ROOT/css/img/go.png)"
    ontimeupdate_(audio);
    ontimeupdate_q(audio);
    //调用vue的数据
    this_.trigger_(audio, this_);
}

//暂停
function supsent(audio) {
    audio.pause();
    document.getElementById("play").style = "background-image: url(/ROOT/css/img/20253.png)"
}


//进度更新
function ontimeupdate_(audio) {

    audio.ontimeupdate = function () {
        progress(audio);
    }
}

//缓存更新
function ontimeupdate_q(audio) {

    audio.onprogress = function () {
        progress_s(audio);
    }
    //载入完成
    audio.oncanplaythrough = function () {
        progress_s(audio);
    }
}

//显示-缓存
function progress_s(audio) {
    // 获取已缓冲部分的 TimeRanges 对象
    var timeRanges = audio.buffered;

    // 获取以缓存的时间
    var timeBuffered = timeRanges.end(timeRanges.length - 1);


    var t = timeBuffered * 100 / audio.duration;
    document.getElementById("box2_c").style = "width:" + t + "%";
}

//显示-运行
function progress(audio) {
    var t = audio.currentTime * 100 / audio.duration;
    document.getElementById("box_gu").style = "width:" + t + "%";
}

//更改声音+更改声音
function Volume_(ts, audio) {
    if (audio != null) {
        audio.volume = ts;
    }
    document.getElementById("box2_pl").style = "width:" + (ts * 100) + "%";
}

//更改进度
function plan(ds, audio) {
    audio.currentTime = (audio.duration * ds / 100);
    ontimeupdate_(audio);
}