
var address = document.getElementById("address");
var lat;
var lng;

function splitString(string) {
    return string.split(" ");
}

function reqListener () {
    console.log(this.responseText);
}

const xhr = new XMLHttpRequest();
xhr.open('GET', 'https://maps.googleapis.com/maps/api/geocode/json?address=' + splitString(address) + '&key=AIzaSyDlXAOpZfmgDvrk4G7MkD6NXxPf9yJeJo8');
xhr.responseType = 'json';
xhr.onload = function(e) {
    if (this.status == 200) {
        console.log('response', this.response.results["0"].geometry.location.lat,
            this.response.results["0"].geometry.location.lng); // JSON response
        lat = this.response.results["0"].geometry.location.lat;
        lng = this.response.results["0"].geometry.location.lng;
        console.log(lat);
        console.log(lng);
    }
};
xhr.send();