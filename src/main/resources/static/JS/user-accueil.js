var dragArea = document.querySelector('.card_groupe');
new Sortable (dragArea, {
    animation: 150,
    handle: '.icon'
});

var dragArea2 = document.querySelector('.card_event');
new Sortable (dragArea2, {
    animation: 150,
    handle: '.icon_event'
});