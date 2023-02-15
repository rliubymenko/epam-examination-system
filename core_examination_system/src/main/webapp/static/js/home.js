$(document).ready(function () {

    let $test = document.getElementById('welcome').innerHTML, $html = '', $i;

    for ($i = 0; $i < $test.length; $i++) {
        $html += `<span style="animation: homeLetter ${$i/2.5}s">` + ($test[$i]) + '</span>';
    }
    document.getElementById('welcome').innerHTML = $html;
})
