var cat = [""]


$('.ui.sidebar').sidebar({
    context: $('.bottom.segment')
  })
  // .sidebar('hide')
window.addEventListener('load', async e => {
    await loadJsons();


});

var sel = $('.ui.dropdown.second')

async function loadJsons() {
sel.dropdown({
    apiSettings: {
        url: 'http://perlman.mathcs.carleton.edu:5112/states/',
    }
});

$.getJSON("http://perlman.mathcs.carleton.edu:5112/schools/?adm_rate=%5B.10,.30%5D&sat_avg=%5B1200,1500%5D&md_earn_wne_p10=%5B0,200000%5D&region_id=None&actcmmid=%5B30,36%5D&costt4_a=%5B0,70000%5D", function(data) {
    cat = data
    var table = document.getElementById("results");
    for (item of data) {
      var row = table.insertRow(table.rows.length);
      row.insertCell(0).innerHTML = item["name"]
      row.insertCell(1).innerHTML = item["city"]+ ", " +item["state"]
      row.insertCell(2).innerHTML = Math.round(item["adm_rate"]*100) + "%"
      row.insertCell(3).innerHTML = item["sat_avg"]
      row.insertCell(4).innerHTML = item["actcmmid"]
      row.insertCell(5).innerHTML = item["costt4_a"]
      row.insertCell(6).innerHTML = item["md_earn_wne_p10"]
    }


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
