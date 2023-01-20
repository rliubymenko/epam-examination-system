const date = new Date();
let questionStepper;

$(function () {
    const duration = $('#clock').data('countdown');
    const time = moment(date).add(duration, 'minutes').format("YYYY-MM-DD HH:mm:ss");

    $('#clock').countdown(time, function (event) {
        $(this).html(event.strftime(''
            + '<span class="h1 font-weight-bold">%H :</span>'
            + '<span class="h1 font-weight-bold">%M :</span>'
            + '<span class="h1 font-weight-bold">%S</span>'));
    }).on('finish.countdown', function (event) {

        $('#completeBtn').click(function () {
            addTimeInputsAndSubmit();
        });
    });
    $('#completeBtn').click(function () {
        addTimeInputsAndSubmit();

    });

    questionStepper = new Stepper($('.bs-stepper')[0])

});

function stepNext() {
    questionStepper.next();
}

function stepBack() {
    questionStepper.previous();
}

function addTimeInputsAndSubmit() {
    const startTimeInput = document.createElement("input");
    startTimeInput.setAttribute("type", "hidden");
    startTimeInput.setAttribute("name", "startTime");
    startTimeInput.setAttribute("value", moment(date).format("YYYY-MM-DDTHH:mm:ss"));
    $('#testForm').append(startTimeInput);
    const endTimeInput = document.createElement("input");
    endTimeInput.setAttribute("type", "hidden");
    endTimeInput.setAttribute("name", "endTime");
    endTimeInput.setAttribute("value", moment(new Date()).format("YYYY-MM-DDTHH:mm:ss"));
    $('#testForm').append(endTimeInput);
    $('#testForm').submit();
}
