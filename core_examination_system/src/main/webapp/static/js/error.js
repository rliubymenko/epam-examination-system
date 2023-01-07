$(document).ready(function () {

    // Initializes Magnific Popup
    $('.popup-youtube').magnificPopup({
        disableOn: 700,
        type: 'iframe',
        mainClass: 'mfp-fade',
        removalDelay: 160,
        preloader: false,

        fixedContentPos: false,

        // Changes iFrame to support Youtube state changes (so we can close the video when it ends)
        iframe: {
            markup: '<div class="mfp-iframe-scaler">' +
                '<div class="mfp-close"></div>' +
                '<iframe id="player" class="mfp-iframe" frameborder="0" allowfullscreen></iframe>' +
                '</div>', // HTML markup of popup, `mfp-close` will be replaced by the close button

            // Converts Youtube links to embeded videos in Magnific popup.
            patterns: {
                youtube: {
                    index: 'youtube.com/',
                    id: 'v=',
                    src: '//www.youtube.com/embed/%id%?autoplay=1&rel=0&showinfo=0&enablejsapi=1'
                }
            }
        },

        // Tracks Youtube video state changes (so we can close the video when it ends)
        callbacks: {
            open: function () {
                new YT.Player('player', {
                    events: {
                        'onStateChange': onPlayerStateChange
                    }
                });
            }
        }
    });

    // Closes Magnific Popup when Video Ends
    function onPlayerStateChange(event) {
        if (event.data === YT.PlayerState.ENDED) {
            $.magnificPopup.close();
        }
    }
});
