$("#search").autocomplete({
    serviceUrl:'Auto',
    onSelect: function (suggestion) {
        $('#selection').html('You selected: ' + suggestion.value + ', ' + suggestion.data);
    },
    showNoSuggestionNotice: true,
    noSuggestionNotice: 'Nessun autore trovato',
});