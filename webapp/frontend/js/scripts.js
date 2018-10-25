var cat = [""]


$('.ui.sidebar').sidebar({
    context: $('.bottom.segment')
  })
  // .sidebar('hide')
window.addEventListener('load', async e => {
    await loadJsons();


});

function dynamicEvent() {
  // this.innerHTML = 'Succ';
  // this.className += ' positive';
  $('.ui.modal')
  .modal({
    centered: false
  })
  .modal('show')

  var queryURL = 'http://perlman.mathcs.carleton.edu:5112/school/?opeid=' + this.id
  console.log(queryURL);
  $.getJSON(queryURL, function(data) {
      var modal = document.getElementById("content");
      // while (modal.firstChild) {
      //     modal.removeChild(modal.firstChild);
      // }
      var src = "https://logo.clearbit.com/"+ data["insturl"].toString().replace("www.", "")



    });









}

var sel = $('.ui.dropdown.second')

async function loadJsons() {
sel.dropdown({
    apiSettings: {
        url: 'http://perlman.mathcs.carleton.edu:5112/states/',
    }
});

$.getJSON("http://perlman.mathcs.carleton.edu:5112/schools/?adm_rate=%5B.10,.30%5D&sat_avg=%5B1200,1500%5D&md_earn_wne_p10=%5B0,200000%5D&region_id=4&actcmmid=%5B30,36%5D&costt4_a=%5B0,70000%5D", function(data) {
    cat = data
    var table = document.getElementById("results");
    for (item of data) {
      var row = table.insertRow(table.rows.length);
      var x= row.insertCell(0);
      var img = document.createElement('img');
      img.src = "https://logo.clearbit.com/"+ item["insturl"].toString().replace("www.", "") + "?size=20"
      x.appendChild(img);
      row.insertCell(1).innerHTML = item["name"]
      row.insertCell(2).innerHTML = item["city"]+ ", " +item["state"]
      row.insertCell(3).innerHTML = Math.round(item["adm_rate"]*100) + "%"
      row.insertCell(4).innerHTML = item["sat_avg"]
      row.insertCell(5).innerHTML = item["actcmmid"]
      row.insertCell(6).innerHTML = item["costt4_a"]
      row.insertCell(7).innerHTML = item["md_earn_wne_p10"]
      var y= row.insertCell(8);
      var but = document.createElement('button');
      but.className = 'ui button'
      but.innerHTML = 'view'
      but.id = item["opeid"]
      y.appendChild(but);
      but.onclick = dynamicEvent;

    }
    $("#view").on("click", function() {
      console.log("Works")
    });








   });


}


$('#sat').range({
  min: 0,
  max: 1600,
  start: 1300,
  step: 10,

  function() {
    $self = $(this),
    firstVal = $self.range('get thumb value'),
    secVal = $self.range('get thumb value', 'second');
    $('#sat_score').html(secVal + " - " + firstVal);
  },

  onChange: function(value) {
    $self = $(this),
    firstVal = $self.range('get thumb value'),
    secVal = $self.range('get thumb value', 'second');
    $('#sat_score').html(secVal + " - " + firstVal);
  }
});


$('#act').range({
  min: 0,
  max: 36,
  start: 28,
  step: 1,
  function() {
    $self = $(this),
    firstVal = $self.range('get thumb value'),
    secVal = $self.range('get thumb value', 'second');
    $('#act_score').html(secVal + " - " + firstVal);
  },
  onChange: function(value) {
    $self = $(this),
    firstVal = $self.range('get thumb value'),
    secVal = $self.range('get thumb value', 'second');
    $('#act_score').html(secVal + " - " + firstVal);
  }
});

$('#cost').range({
  min: 0,
  max: 36,
  start: 28,
  step: 1,
  function() {
    $self = $(this),
    firstVal = $self.range('get thumb value'),
    secVal = $self.range('get thumb value', 'second');
    $('#act_score').html(secVal + " - " + firstVal);
  },
  onChange: function(value) {
    $self = $(this),
    firstVal = $self.range('get thumb value'),
    secVal = $self.range('get thumb value', 'second');
    $('#act_score').html(secVal + " - " + firstVal);
  }
});

$('#salary').range({
  min: 0,
  max: 36,
  start: 28,
  step: 1,
  function() {
    $self = $(this),
    firstVal = $self.range('get thumb value'),
    secVal = $self.range('get thumb value', 'second');
    $('#act_score').html(secVal + " - " + firstVal);
  },
  onChange: function(value) {
    $self = $(this),
    firstVal = $self.range('get thumb value'),
    secVal = $self.range('get thumb value', 'second');
    $('#act_score').html(secVal + " - " + firstVal);
  }
});

$('#selective').range({
  min: 0,
  max: 36,
  start: 28,
  step: 1,
  function() {
    $self = $(this),
    firstVal = $self.range('get thumb value'),
    secVal = $self.range('get thumb value', 'second');
    $('#act_score').html(secVal + " - " + firstVal);
  },
  onChange: function(value) {
    $self = $(this),
    firstVal = $self.range('get thumb value'),
    secVal = $self.range('get thumb value', 'second');
    $('#act_score').html(secVal + " - " + firstVal);
  }
});
