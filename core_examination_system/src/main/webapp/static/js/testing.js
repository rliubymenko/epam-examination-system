let questionStepper;

$(function () {
    const duration = $('#clock').data('countdown');
    const startTime = $('#clock').data('starttime');
    const timeMillisDuration = duration * 60 * 1000;
    const timePassed = moment(new Date()).diff(moment(startTime));
    let timeLeft = timeMillisDuration - timePassed;
    if (timeLeft <= 0) {
        timeLeft = 0;
    }
    const timeDuration = moment(new Date()).add(timeLeft, 'milliseconds').format("YYYY-MM-DD HH:mm:ss");
    $('#clock').countdown(timeDuration, function (event) {
        $(this).html(event.strftime(''
            + '<span class="h1 font-weight-bold">%H :</span>'
            + '<span class="h1 font-weight-bold">%M :</span>'
            + '<span class="h1 font-weight-bold">%S</span>'));
    }).on('finish.countdown', function () {
        addTimeInputsAndSubmit();
    });
    $('#completeBtn').click(function () {
        addTimeInputsAndSubmit();
    });

    questionStepper = new Stepper($('.bs-stepper')[0]);
});

function stepNext() {
    questionStepper.next();
}

function stepBack() {
    questionStepper.previous();
}

function addTimeInputsAndSubmit() {
    const endTimeInput = document.createElement("input");
    endTimeInput.setAttribute("type", "hidden");
    endTimeInput.setAttribute("name", "endTime");
    endTimeInput.setAttribute("value", moment(new Date()).format("YYYY-MM-DDTHH:mm:ss"));
    $('#testForm').append(endTimeInput);
    $('#testForm').submit();
    exitFullscreen();
}

function exitFullscreen() {
    if (document.exitFullscreen) {
        document.exitFullscreen();
    } else if (document.mozCancelFullScreen) {
        document.mozCancelFullScreen();
    } else if (document.webkitExitFullscreen) {
        document.webkitExitFullscreen();
    }
}

let full_screen = document.documentElement;

function goFullscreen() {
    full_screen.webkitRequestFullscreen();
    full_screen.style.display = "";
}

full_screen.onclick = goFullscreen;
