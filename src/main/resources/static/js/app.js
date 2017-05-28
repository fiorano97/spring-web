$(document).ready(function() {
	changePageAndSize();
});

function changePageAndSize() {
	$('#pageSizeSelect').change(function(evt) {
        var url = $('#url').val();
        var group = $('#group').val();
        if (group == null || group == "") {

            window.location.replace(url + "/?pageSize=" + this.value + "&page=1");
        } else {
            window.location.replace(url + "/?pageSize=" + this.value + "&page=1&group=" + group);
        }

	});
}
