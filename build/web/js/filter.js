$("#search").autocomplete({
    serviceUrl:'Auto', //tell the script where to send requests
    onSelect: function (suggestion) {
        $('#selection').html('You selected: ' + suggestion.value + ', ' + suggestion.data);
    },
    showNoSuggestionNotice: true,
    noSuggestionNotice: 'Nessun autore trovato',
});